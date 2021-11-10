package com.hawk.example.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hawk.example.spring_security.mapper.UserMapper;
import com.hawk.example.spring_security.model.UserDO;
import com.hawk.example.spring_security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.service.impl
 * @desc
 * @date 2021/10/27
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

   // 正经的业务代码
    @Override
    public UserDO getUserByUserName(String userName){
        if(userList == null){
            return null;
        }
        return userList.get(userName);
    }

    // 用来模拟数据库的一个Map
    private static Map<String,UserDO> userList;


    public UserServiceImpl(){
        initUserList();
    }


    /**
     * 模拟数据库用户
     * */
    private void initUserList(){
        if (userList == null){
            userList = new HashMap<>(3);
            userList.put("zhangsan",new UserDO(1L,"zhangsan",password_123,"张三"
                    , Arrays.asList("admin","role1"),Arrays.asList("p1","p2","p3")));
            userList.put("lisi",new UserDO(2L,"lisi",password_123,"李四"
                    , Collections.singletonList("role1"),Arrays.asList("p1","p2")));
            userList.put("wangwu",new UserDO(3L,"wangwu",password_123,"王五"
                    , Collections.singletonList("role2"),Arrays.asList("p3","p2")));
            userList.put("gree", new UserDO(4L, "gree", password_123, "格力",
                    Arrays.asList("role3", "role2"), Arrays.asList("p1","p5")));
        }
    }


    private static final String password_123 = "$2a$10$MOJOzkwqh/iWiXLLgJb9l.VgCi1S3hvrRiQ7plHVY3NxOzVGZZWx2";
}
