package com.example.tkmybatisdemo.service;

import com.example.tkmybatisdemo.dao.UserMapper;
import com.example.tkmybatisdemo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public boolean addUser(User user) {
        // 实际插入的记录数是否大于0
        return userMapper.insert(user) > 0;
    }

    public boolean updateUser(User user) {
        // 实际更新的记录数是否大于0
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    public List<User> queryUserByUserName(String userName) {
        User user = new User();
        user.setName(userName);
        return userMapper.select(user);
    }

    public boolean deleteUserById(int id) {
        User user = new User();
        user.setId(id);
        // 删除的记录数是否大于0，大于0则表示删除成功
        return userMapper.deleteByPrimaryKey(user) > 0;
    }
}
