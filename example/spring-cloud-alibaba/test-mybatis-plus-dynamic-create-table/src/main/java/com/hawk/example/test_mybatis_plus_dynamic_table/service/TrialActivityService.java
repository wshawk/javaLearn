package com.hawk.example.test_mybatis_plus_dynamic_table.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivity;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.service
 * @desc
 * @date 2021/7/24
 */
public interface TrialActivityService extends IService<TrialActivity> {

    TrialApplyRecord getTrialApplyRecord();

    TrialActivity getMaxTrialActivity();
}
