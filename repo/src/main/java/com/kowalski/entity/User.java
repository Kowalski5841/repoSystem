package com.kowalski.entity;

import lombok.Data;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/24 21:24
 */
@Data
public class User {
    private Integer id;
    private String no;
    private String name;
    private String password;
    private int sex;
    private int roleId;
    private String phone;
    private String isvalid;
    //此处roleId在数据库中对应role_id，在这里要写成roleId才能对应识别
    //此处isvalid对应数据库中isValid，这里写成小写才能对应识别
    //或者还有一种写法:
/*
    @TableField("isvalid")
    private String isValid;
*/
}