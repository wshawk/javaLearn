package com.hawk.example.shardingsphere_jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.shardingsphere_jdbc.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hawk
 * @package com.hawk.example.shardingsphere_jdbc
 * @desc
 * @date 2021/11/10
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
