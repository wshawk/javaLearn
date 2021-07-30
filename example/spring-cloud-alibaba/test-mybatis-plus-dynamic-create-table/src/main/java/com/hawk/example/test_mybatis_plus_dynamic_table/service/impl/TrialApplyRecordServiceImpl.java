package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivityMappingApplyRecord;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;
import com.hawk.example.test_mybatis_plus_dynamic_table.mapper.TrialActivityMappingApplyRecordMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.mapper.TrialApplyRecordMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialActivityService;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialApplyRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
public class TrialApplyRecordServiceImpl extends ServiceImpl<TrialApplyRecordMapper, TrialApplyRecord> implements TrialApplyRecordService {
    @Resource
    TrialApplyRecordMapper trialApplyRecordMapper;

    @Resource
    TrialActivityMappingApplyRecordMapper trialActivityMappingApplyRecordMapper;

    @Resource
    TrialActivityService trialActivityService;

    @Override
    public int createTrialApplyRecord(String tableName) {
        return trialApplyRecordMapper.createTable(tableName);
    }

    @Override
    public int getCount() {
        String tableName = "trial_apply_record_" + trialActivityMappingApplyRecordMapper.getLastRecord().getApplyTableSuffixId();
        return trialApplyRecordMapper.getCount(tableName);
    }

    @Override
    public boolean judgeNeedCreateNewTable() {
        int maxAllowRowNum = 10;
        return getCount() > maxAllowRowNum;
    }

    @Override
    public void createTableWhenBeyondMax() {
        if (judgeNeedCreateNewTable()){
            // 先获取到最后一个活动的id
            Long activityId = trialActivityService.getMaxTrialActivity().getId() + 1L;
            String tableName = "trial_apply_record_" + activityId;
            createTrialApplyRecord(tableName);

            TrialActivityMappingApplyRecord mapping = new TrialActivityMappingApplyRecord();
            mapping.setApplyTableSuffixId(activityId);
            mapping.setTrialActivityIdBegin(activityId);
            trialActivityMappingApplyRecordMapper.insert(mapping);
        }
    }


}
