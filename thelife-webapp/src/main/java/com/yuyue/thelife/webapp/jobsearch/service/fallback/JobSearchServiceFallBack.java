package com.yuyue.thelife.webapp.jobsearch.service.fallback;

import com.yuyue.thelife.base.result.JsonRestResponseVo;
import com.yuyue.thelife.webapp.jobsearch.service.JobSearchService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 00:05:09
 */
@Component
public class JobSearchServiceFallBack implements JobSearchService {

    @Override
    public JsonRestResponseVo get() {
        return null;
    }

}
