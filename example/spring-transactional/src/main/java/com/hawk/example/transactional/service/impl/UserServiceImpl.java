package com.hawk.example.transactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.transactional.MyAnnotation;
import com.hawk.example.transactional.entity.User;
import com.hawk.example.transactional.mapper.UserMapper;
import com.hawk.example.transactional.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author hawk
 * @package com.hawk.example.transactional.service.impl
 * @desc
 * @date 2022/1/18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private TransactionTemplate transactionTemplate;

    @MyAnnotation
    public void add(){
        User u = new User();
        u.setName("sss");
        u.setEnableFlag(1L);
        int i = 1 / 0;
        save(u);
    }

    @Override
    @Transactional
    public void test() {
    }
}
