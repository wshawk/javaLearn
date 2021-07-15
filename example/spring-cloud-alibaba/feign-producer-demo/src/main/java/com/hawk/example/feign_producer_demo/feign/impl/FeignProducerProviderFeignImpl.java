package com.hawk.example.feign_producer_demo.feign.impl;

import com.hawk.example.feign_producer_demo.feign.FeignProducerProviderFeign;

/**
 * @author hawk
 * @package com.hawk.example.feign_demo.feign.impl
 * @desc
 * @date 2021/7/15
 */
public class FeignProducerProviderFeignImpl implements FeignProducerProviderFeign {
    @Override
    public String hello() {
        return "null";
    }
}
