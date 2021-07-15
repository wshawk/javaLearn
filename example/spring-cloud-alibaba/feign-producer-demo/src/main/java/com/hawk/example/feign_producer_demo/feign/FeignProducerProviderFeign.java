package com.hawk.example.feign_producer_demo.feign;

import com.hawk.example.feign_producer_demo.feign.impl.FeignProducerProviderFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hawk
 * @package com.hawk.example.feign_demo.feign
 * @desc
 * @date 2021/7/15
 */
@FeignClient(value = "hawk-feign-producer", fallback = FeignProducerProviderFeignImpl.class)
@Component
public interface FeignProducerProviderFeign {
    @RequestMapping("/hawk/feign_producer/hello")
    String hello();
}
