package com.hawk.example.test_mybatis_plus_dynamic_table.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivityMappingApplyRecord;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.service
 * @desc
 * @date 2021/7/24
 */
public interface TrialActivityMappingApplyRecordService extends IService<TrialActivityMappingApplyRecord> {
    TrialActivityMappingApplyRecord getRecordByActivityId(Long activityId);

    TrialActivityMappingApplyRecord getMaxId();
}
