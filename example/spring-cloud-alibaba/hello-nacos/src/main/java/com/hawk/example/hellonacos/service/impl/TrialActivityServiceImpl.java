package com.hawk.example.hellonacos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.hellonacos.common.entity.TrialActivity;
import com.hawk.example.hellonacos.mapper.TrialActivityMapper;
import com.hawk.example.hellonacos.service.TrialActivityService;
import org.springframework.stereotype.Service;

/**
 * @author hawk
 * @package com.hawk.example.hellonacos.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
public class TrialActivityServiceImpl extends ServiceImpl<TrialActivityMapper, TrialActivity> implements TrialActivityService{
}
