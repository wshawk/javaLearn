package com.hawk.example.test_mybatis_plus_dynamic_table.controller;

import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialActivityService;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialApplyRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.controller
 * @desc
 * @date 2021/7/24
 */
@RestController
@RequestMapping("/hawk/test_mybatis_plus_dynamic_table/trial")
public class TrialActivityController {
    @Resource
    private TrialActivityService trialActivityService;

    @Resource
    private TrialApplyRecordService trialApplyRecordService;

    @RequestMapping("/test")
    public Integer test(){
        return trialActivityService.count();
    }

    @RequestMapping("/get")
    public TrialApplyRecord get(){
        return trialActivityService.getTrialApplyRecord();
    }

    @RequestMapping("/create")
    public int createTable(){
        return trialApplyRecordService.createTrialApplyRecord("trial_apply_record_33");
    }

    @RequestMapping("/judge")
    public boolean judge(){
        return trialApplyRecordService.judgeNeedCreateNewTable();
    }

    @RequestMapping("/add_new")
    public String addNew(){
        trialApplyRecordService.createTableWhenBeyondMax();

        return "success";
    }

    @RequestMapping("/get_count")
    public int getCount(){
        return trialApplyRecordService.getCount();
    }
}
