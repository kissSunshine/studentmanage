package com.zh.studentmanage.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component("TimeUtil")
public class TimeUtil {

    /**
     * 系统当前日期 - 传入日期
     * @return 差 ;如果为空，则是传入日期有误
     */
    public Integer dateCompareNow(String date){
        // 校验传入的格式是不是yyyy-MM-dd
        String regex = "((19|20)[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
        if (!Pattern.matches(regex, date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return LocalDate.now().compareTo(localDate);
    }

}
