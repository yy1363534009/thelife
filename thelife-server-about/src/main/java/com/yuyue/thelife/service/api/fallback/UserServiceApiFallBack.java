package com.yuyue.thelife.service.api.fallback;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.service.api.UserServiceApi;
import org.springframework.stereotype.Component;

/**
 * @author: yuyue
 * @create 2020/12/2 17:21
 */
@Component
public class UserServiceApiFallBack implements UserServiceApi {

    @Override
    public JsonRestResponseVo getJobsearch() {
        return null;
    }

}
