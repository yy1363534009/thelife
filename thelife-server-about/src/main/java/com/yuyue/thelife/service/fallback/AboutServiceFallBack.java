package com.yuyue.thelife.service.fallback;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.AboutService;
import org.springframework.stereotype.Component;

/**
 * @author: yuyue
 * @create 2020/12/2 17:21
 */
@Component
public class AboutServiceFallBack implements AboutService {

    @Override
    public JsonRestResponseVo getJobsearch() {
        return null;
    }

}
