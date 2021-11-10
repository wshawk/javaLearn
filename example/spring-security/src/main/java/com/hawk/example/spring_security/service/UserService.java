package com.hawk.example.spring_security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawk.example.spring_security.model.UserDO;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.service
 * @desc
 * @date 2021/10/27
 */
public interface UserService extends IService<UserDO> {
    UserDO getUserByUserName(String userName);
}
