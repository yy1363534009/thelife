package com.yuyue.thelife.webapp.wechat.service.fallback;

import com.yuyue.thelife.webapp.wechat.base.result.TheLifeResult;
import com.yuyue.thelife.webapp.wechat.service.JobSearchService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 00:05:09
 */
@Component
public class JobSearchServiceFallBack implements JobSearchService {

    @Override
    public TheLifeResult get() {
        return null;
    }

}
