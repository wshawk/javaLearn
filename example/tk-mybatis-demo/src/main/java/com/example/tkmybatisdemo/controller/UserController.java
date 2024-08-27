package com.example.tkmybatisdemo.controller;

import com.example.tkmybatisdemo.entity.User;
import com.example.tkmybatisdemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user){
       return userService.addUser(user);
    }

    @GetMapping("/queryByName")
    public List<User> queryByName(@RequestParam String name){
        return userService.queryUserByUserName(name);
    }

    @PostMapping("/update")
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestParam int id){
        return userService.deleteUserById(id);
    }
}
