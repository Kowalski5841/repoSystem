package com.kowalski.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Kowalski
 * @version 1.0
 * @Date 2023/7/25 19:13
 */
@Data
public class QueryPageParam {
    //默认分页大小和页数
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUMBER = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUMBER;
    private HashMap param = new HashMap();
}
