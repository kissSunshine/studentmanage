package com.zh.studentmanage.vo;

import lombok.Data;

@Data
public class PageVo<T> {
    // 各类型数据
    private T data;
    // 数据总量
    private Integer total;

    public PageVo(T data, Integer total) {
        this.data = data;
        this.total = total;
    }

}
