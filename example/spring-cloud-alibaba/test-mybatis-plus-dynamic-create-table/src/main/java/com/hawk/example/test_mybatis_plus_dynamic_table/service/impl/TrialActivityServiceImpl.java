package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivity;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;
import com.hawk.example.test_mybatis_plus_dynamic_table.mapper.TrialActivityMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.service.TrialActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
public class TrialActivityServiceImpl extends ServiceImpl<TrialActivityMapper, TrialActivity> implements TrialActivityService {
    @Resource
    TrialActivityMapper trialActivityMapper;

    @Override
    public TrialApplyRecord getTrialApplyRecord() {
        return trialActivityMapper.getTrialApplyRecord("trial_apply_record_22");
    }

    @Override
    public TrialActivity getMaxTrialActivity() {
        LambdaQueryWrapper<TrialActivity> wrapper = Wrappers.lambdaQuery(TrialActivity.class)
                .orderByDesc(TrialActivity::getId)
                .last("limit 1");

        return this.getOne(wrapper);
    }
}
