package com.zh.studentmanage.utils;

import com.zh.studentmanage.enums.EnumInterface;

public class EnumUtil {
    public static <T extends EnumInterface> T getEnumName(Integer code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public static <T extends EnumInterface> T getEnumName(String code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
