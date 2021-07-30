package com.hawk.example.test_mybatis_plus_dynamic_datasource.controller;

import com.hawk.example.test_mybatis_plus_dynamic_datasource.service.MyFavoriteService;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.service.TrialActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.controller
 * @desc
 * @date 2021/7/24
 */
@RestController
@RequestMapping("/hawk/test_mybatis_plus_dynamic_datasource")
public class TrialActivityController {
    @Resource
    private TrialActivityService trialActivityService;

    @Resource
    private MyFavoriteService myFavoriteService;

    @RequestMapping("/master")
    public Integer master() {
        return trialActivityService.count();
    }

    @RequestMapping("/slave")
    public Integer slave() {
        return myFavoriteService.count();
    }
}