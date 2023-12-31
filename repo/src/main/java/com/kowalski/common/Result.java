package com.kowalski.common;

import lombok.Data;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/26 8:42
 */

@Data
public class Result {
    private int code;   //编码200、/40
    private String msg; // 成功、失败
    private Long total; //总记录数
    private Object data; //数据，类型不确定。可能是数组也可能是对象

    public static Result fail() {
        return result(400,"失败", 0L, null);
    }

    public static Result success() {
        return result(200,"成功", 0L, null);
    }

    public static Result success(Object data) {
        return result(200,"成功", 0L, data);
    }

    public static Result success(Object data, Long total) {
            return result(200,"成功", total, data);
    }

    private static Result result(int code, String msg, Long total, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }
}
