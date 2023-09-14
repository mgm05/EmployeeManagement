package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.validation.constraints.NotNull;

/** 
 * 共通部品.
 */
@ControllerAdvice
public class CommonUtils {
    
    /**
     * Date型日付をString型yyyy/MM/ddに変換.
     * @param date Date
     * @return strDate yyyy/MM/dd
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdf.format(date);
        
        return strDate;
    }
    
    /**
     * String型日付yyyy/MM/ddをDate型に変換.
     * @param strDate String
     * @return date
     */
    public static Date parseSlashDate(@NotNull String strDate) {
        SimpleDateFormat strSdf = new SimpleDateFormat("yyyy/MM/dd");
        
        Date date = null;
        
        try {
            date = strSdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * String型日付yyyy-MM-ddをDate型に変換.
     * @param strDate String
     * @return date
     */
    public static Date parseHyphenDate(@NotNull String strDate) {
        SimpleDateFormat strSdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = null;
        
        try {
            date = strSdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 誕生日から年齢を計算.
     * @param birthDay Date
     * @return age
     */
    public static int calcAge(Date birthDay) {
        Calendar calendarNow = Calendar.getInstance();
        Calendar calendarBirthDay = Calendar.getInstance();
        
        //現在日時取得、calendarにセット
        Date date = new Date();
        calendarNow.setTime(date);
        calendarBirthDay.setTime(birthDay);
        
        //現在年-誕生年で年齢の計算
        int age = calendarNow.get(Calendar.YEAR) - calendarBirthDay.get(Calendar.YEAR);
        //誕生月を迎えていなければ-1
        if (calendarNow.get(Calendar.MONTH) < calendarBirthDay.get(Calendar.MONTH)) {
            age -= 1;
        //誕生日を迎えていなければ-1
        } else if (calendarNow.get(Calendar.MONTH) == calendarBirthDay.get(Calendar.MONTH)) {
            if (calendarNow.get(Calendar.DATE) < calendarBirthDay.get(Calendar.DATE)) {
                age -= 1;
            }
        }
        return age;
    }
}
