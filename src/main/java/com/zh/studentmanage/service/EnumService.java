package com.zh.studentmanage.service;


import com.zh.studentmanage.vo.ResponseVo;

import java.util.Map;

public interface EnumService {

    /**
     * 利用反射，通过属性名获取属性值
     * @param fieldName 属性名
     * @param object 类
     * @return 值
     */
    Object getValueByFieldName(String fieldName, Object object);

    /**
     * 将枚举类转为List<Map<String, String>>
     * @param enums 枚举
     * @return 值
     */
    ResponseVo<Map<String, String>> enumToListMap(Enum[] enums);

}
