package com.yuyue.thelife.exception.hanlder;

import com.yuyue.thelife.exception.exception.ServiceException;
import com.yuyue.thelife.result.JsonRestResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 11:29
 * @Description: 全局异常统一JOSN返回
 */
@RestControllerAdvice
public class GlobalExceptionHanlder {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHanlder.class);

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public JsonRestResponseVo hanlderThrowable(Throwable e) {
        return buildJsonRestResponseVo(e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public JsonRestResponseVo handleSecurityException(ServiceException e) {
        return buildJsonRestResponseVo(e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonRestResponseVo hanlderMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        logger.info("MethodArgumentNotValidException:" + allErrors.get(0).getDefaultMessage());
        return buildJsonRestResponseVo(allErrors.get(0).getDefaultMessage());
    }

    /**
     * springsecurity异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public JsonRestResponseVo badCredentialsException(BadCredentialsException e) {
        return buildJsonRestResponseVo(e.getMessage());
    }

    /**
     * 统一返回json
     *
     * @param msg
     * @return
     */
    public JsonRestResponseVo buildJsonRestResponseVo(String msg) {
        return JsonRestResponseVo.error(400, msg);
    }

}
