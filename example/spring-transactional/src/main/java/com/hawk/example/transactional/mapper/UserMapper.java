package com.hawk.example.transactional.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.transactional.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hawk
 * @package com.hawk.example.transactional.mapper
 * @desc
 * @date 2022/1/18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
