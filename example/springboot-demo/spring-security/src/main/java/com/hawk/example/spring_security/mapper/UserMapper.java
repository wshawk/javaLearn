package com.hawk.example.spring_security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.spring_security.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.mapper
 * @desc
 * @date 2021/10/25
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
