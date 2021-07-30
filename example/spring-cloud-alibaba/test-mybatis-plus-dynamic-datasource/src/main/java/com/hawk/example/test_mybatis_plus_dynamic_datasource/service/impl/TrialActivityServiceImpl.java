package com.hawk.example.test_mybatis_plus_dynamic_datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.common.TrialActivity;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.mapper.TrialActivityMapper;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.service.TrialActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
public class TrialActivityServiceImpl extends ServiceImpl<TrialActivityMapper, TrialActivity> implements TrialActivityService {

}
