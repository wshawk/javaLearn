package com.hawk.example.transactional.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hawk.example.transactional.entity.User;

/**
 * @author hawk
 * @package com.hawk.example.transactional
 * @desc
 * @date 2022/1/18
 */
public interface UserService extends IService<User> {
    void add();

    void test();
}
