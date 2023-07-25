package com.kowalski.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kowalski.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 21:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> listAll();
}
