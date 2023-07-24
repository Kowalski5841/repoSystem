package com.kowalski.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kowalski.entity.User;
import com.kowalski.mapper.UserMapper;

import com.kowalski.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 21:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
