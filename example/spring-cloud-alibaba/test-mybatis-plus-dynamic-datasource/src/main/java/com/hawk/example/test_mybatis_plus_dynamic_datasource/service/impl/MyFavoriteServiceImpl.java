package com.hawk.example.test_mybatis_plus_dynamic_datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.common.MyFavorite;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.common.TrialActivity;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.mapper.MyFavoriteMapper;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.mapper.TrialActivityMapper;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.service.MyFavoriteService;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.service.TrialActivityService;
import org.springframework.stereotype.Service;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.service.impl
 * @desc
 * @date 2021/7/24
 */
@Service
@DS("slave_1")
public class MyFavoriteServiceImpl extends ServiceImpl<MyFavoriteMapper, MyFavorite> implements MyFavoriteService {

}
