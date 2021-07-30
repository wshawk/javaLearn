package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivityMappingApplyRecord;
import com.hawk.example.test_mybatis_plus_dynamic_table.mapper.TrialActivityMappingApplyRecordMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialActivityMappingApplyRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
public class TrialActivityMappingApplyRecordServiceImpl extends ServiceImpl<TrialActivityMappingApplyRecordMapper, TrialActivityMappingApplyRecord> implements TrialActivityMappingApplyRecordService {

    @Resource
    TrialActivityMappingApplyRecordMapper trialActivityMappingApplyRecordMapper;

    @Override
    public TrialActivityMappingApplyRecord getRecordByActivityId(Long activityId) {
        LambdaQueryWrapper<TrialActivityMappingApplyRecord> wrapper = Wrappers.lambdaQuery(TrialActivityMappingApplyRecord.class)
                .le(TrialActivityMappingApplyRecord::getTrialActivityIdBegin, activityId)
                .lt(TrialActivityMappingApplyRecord::getTrialActivityIdEnd, activityId);
        return getOne(wrapper);
    }

    @Override
    public TrialActivityMappingApplyRecord getMaxId() {
        return trialActivityMappingApplyRecordMapper.getLastRecord();
    }

}
