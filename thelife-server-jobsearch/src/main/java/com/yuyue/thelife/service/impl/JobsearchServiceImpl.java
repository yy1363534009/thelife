package com.yuyue.thelife.service.impl;

import com.yuyue.thelife.service.JobsearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuyue
 * @create 2020/12/3 15:13
 */
@Service("jobsearchService")
public class JobsearchServiceImpl implements JobsearchService {

    @Override
    public List<String> getJobsearchList() {
        List<String> list = new ArrayList<>();
        list.add("插秧");
        list.add("补苗");
        list.add("捞稻草");
        return list;
    }

}
