package com.kowalski.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kowalski.entity.User;

import java.util.List;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 21:38
 */
public interface UserService extends IService<User> {
    List<User> listAll();
}