package com.yuyue.thelife.jobsearch.service.fallback;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.jobsearch.service.JobSearchService;
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
