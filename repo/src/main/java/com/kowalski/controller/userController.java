package com.kowalski.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kowalski.common.QueryPageParam;
import com.kowalski.common.Result;
import com.kowalski.entity.User;
import com.kowalski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 20:38
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class userController {


    @GetMapping
    public String hello(){
        return "hello springboot";
    }

    //让spring自动注入对象
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
//        return userService.list();
        return userService.listAll();
    }

    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return userService.save(user);
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }
    //查询（模糊匹配、完全匹配）
    @PostMapping("/listE")
    public List<User> listE(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName,user.getName());
        return userService.list(lambdaQueryWrapper);
    }
    @PostMapping("/listL")
    public Result listL(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }
        return Result.success(userService.list(lambdaQueryWrapper));
    }
@PostMapping("/listPage")
//    public List<User> listPage(@RequestBody HashMap hashMap){
    public List<User> listPage(@RequestBody QueryPageParam queryPageParam){
//    System.out.println("num=" + queryPageParam.getPageNum());
//    System.out.println("size=" + queryPageParam.getPageSize());

//    HashMap map = queryPageParam.getParam();
//    System.out.println("no=" + (String) map.get("no"));
//    System.out.println("name" + (String) map.get("name"));
//    System.out.println(hashMap);

    HashMap map = queryPageParam.getParam();
    String name = (String)map.get("name");
    Page<User> page = new Page<>();
    page.setCurrent(queryPageParam.getPageNum());
    page.setSize(queryPageParam.getPageSize());

    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.like(User::getName,name);

    IPage res =  userService.page(page,lambdaQueryWrapper);
    System.out.println("total=" + res.getTotal());

    return res.getRecords();

    }
    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam queryPageParam){


    HashMap map = queryPageParam.getParam();
    //在这里获取需要查找的字段
    String name = (String)map.get("name");
//    Integer id = (Integer) map.get("id");
    Page<User> page = new Page<>();
    page.setCurrent(queryPageParam.getPageNum());
    page.setSize(queryPageParam.getPageSize());

    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //这句话是用来定义根据什么查找数据的
    lambdaQueryWrapper.like(User::getName,name);
    //例如还可以这么写。
//    lambdaQueryWrapper.eq(User::getId,id);

    IPage res =  userService.pageC(page,lambdaQueryWrapper);
    System.out.println("total=" + res.getTotal());

    return res.getRecords();

    }
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam){


        HashMap map = queryPageParam.getParam();
        //在这里获取需要查找的字段
        String name = (String)map.get("name");
//    Integer id = (Integer) map.get("id");
        Page<User> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //这句话是用来定义根据什么查找数据的
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(User::getName,name);
        }
        //例如还可以这么写。
//    lambdaQueryWrapper.eq(User::getId,id);

        IPage res =  userService.pageC(page,lambdaQueryWrapper);
        System.out.println("total=" + res.getTotal());

        return Result.success(res.getRecords(), res.getTotal());

    }
}
