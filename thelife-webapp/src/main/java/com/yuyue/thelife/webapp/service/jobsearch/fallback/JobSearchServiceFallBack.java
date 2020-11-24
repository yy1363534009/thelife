package com.yuyue.thelife.webapp.service.jobsearch.fallback;

import com.yuyue.thelife.base.result.TheLifeResponse;
import com.yuyue.thelife.webapp.service.jobsearch.JobSearchService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 00:05:09
 */
@Component
public class JobSearchServiceFallBack implements JobSearchService {

    @Override
    public TheLifeResponse get() {
        return null;
    }

}
