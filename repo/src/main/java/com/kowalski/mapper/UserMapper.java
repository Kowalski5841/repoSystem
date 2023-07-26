package com.kowalski.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kowalski.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 21:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> listAll();

    IPage pageC(IPage<User> page,@Param(Constants.WRAPPER) Wrapper wrapper);
}
