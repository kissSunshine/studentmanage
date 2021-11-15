package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.service.EnumService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class EnumServiceImpl implements EnumService {

    @Override
    public Object getValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseVo<List<Map<String, String>>> enumToListMap(Enum[] enums) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Enum e : enums) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", getValueByFieldName("code", e).toString());
            map.put("label", getValueByFieldName("name", e).toString());
            mapList.add(map);
        }
        return ResponseVo.success("查询成功", mapList);
    }

}
