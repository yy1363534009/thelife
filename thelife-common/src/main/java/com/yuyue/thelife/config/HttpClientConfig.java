package com.yuyue.thelife.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuyue
 * @Date: 2021/1/3 12:13
 * @Description: Httpclient配置类
 */
@Configuration
public class HttpClientConfig {

    /**
     * 最大连接数
     */
    @Value("${http.maxTotal}")
    private Integer maxTotal = 100;

    /**
     * 并发数
     */
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute = 20;

    /**
     * 创建连接的最长时间
     */
    @Value("${http.connectTimeout}")
    private Integer connectTimeout = 1000;

    /**
     * 从连接池中获取到连接的最长时间
     */
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout = 500;

    /**
     * 数据传输的最长时间
     */
    @Value("${http.socketTimeout}")
    private Integer socketTimeout = 10000;

    /**
     * 提交请求前测试连接是否可用
     */
    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled = true;

    /**
     * 关闭池中的空闲连接-等待时间
     */
    @Value("${http.waitTime}")
    private int waitTime = 30000;

    /**
     * 关闭池中的空闲连接-空闲时间
     */
    @Value("${http.idleConTime}")
    private int idleConTime = 3;


    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param httpClientConnectionManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {

        //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(httpClientConnectionManager);

        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     *
     * @param httpClientBuilder
     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     *
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }

    /**
     * 使用builder构建一个RequestConfig对象
     *
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }

    @Bean
    public IdleConnectionEvictor createIdleConnectionEvictor(PoolingHttpClientConnectionManager poolManager) {
        IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(poolManager, waitTime, idleConTime);
        return idleConnectionEvictor;
    }

    /**
     * 定期清理无效的http连接
     *
     * @author viruser
     */
    public static class IdleConnectionEvictor extends Thread {

        private final HttpClientConnectionManager manager;

        private Integer waitTime;

        private Integer idleConTime;

        private volatile boolean shutdown = true;

        public IdleConnectionEvictor(HttpClientConnectionManager manager, Integer waitTime, Integer idleConTime) {
            this.manager = manager;
            this.waitTime = waitTime;
            this.idleConTime = idleConTime;
            this.start();
        }

        @Override
        public void run() {
            try {
                if (shutdown) {
                    synchronized (this) {
                        wait(waitTime);
                        manager.closeIdleConnections(idleConTime, TimeUnit.SECONDS);
                        // 关闭失效的连接
                        manager.closeExpiredConnections();
                    }
                }
            } catch (Exception e) {

            }
        }

        @PreDestroy
        public void shutdown() {
            shutdown = false;
            synchronized (this) {
                notifyAll();
            }
        }

    }

}
