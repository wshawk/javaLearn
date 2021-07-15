package com.hawk.example.use_log4j2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hawk
 * @package com.hawk.example.use_log4j2.controller
 * @desc
 * @date 2021/7/15
 */
@RestController
@RequestMapping("/hawk/log4j2")
@Slf4j
public class Log4j2Controller {
    @RequestMapping("/test")
    public String test(){
        log.info("Log4j2Controller test[] start");
        return "log4j2";
    }
}
