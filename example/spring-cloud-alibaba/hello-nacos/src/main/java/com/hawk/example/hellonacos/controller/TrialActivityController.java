package com.hawk.example.hellonacos.controller;

import com.hawk.example.hellonacos.service.TrialActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.hellonacos.controller
 * @desc
 * @date 2021/7/24
 */
@RestController
@RequestMapping("/hawk/hello/trial")
public class TrialActivityController {
    @Resource
    private TrialActivityService trialActivityService;

    @RequestMapping("/test")
    public Integer test(){
        return trialActivityService.count();
    }
}
