package com.hawk.example.shardingsphere_jdbc;

import com.hawk.example.shardingsphere_jdbc.entity.Order;
import com.hawk.example.shardingsphere_jdbc.mapper.OrderMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.shardingsphere_jdbc
 * @desc
 * @date 2021/11/10
 */
@RestController
public class OrderController {
    @Resource
    OrderMapper orderMapper;

    public Order test(){
       return orderMapper.selectById(1L);
    }
}
