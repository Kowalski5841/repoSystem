package com.kowalski.controller;

import com.kowalski.entity.User;
import com.kowalski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 20:38
 */
@RestController
public class HelloController {
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String hello(){
        return "hello springboot";
    }

    //让spring自动注入对象
    private final UserService userService;

    @GetMapping("/list")
    public List<User> list(){
//        return userService.list();
        return userService.listAll();
    }

}
