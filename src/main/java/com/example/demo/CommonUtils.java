package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
     * Date型日付をString型yyyy/MM/ddに変換.
     * @param date Date
     * @return strDate yyyy/MM/dd
     */
    public static Optional<String> formatOptDate(Date date) {
    	Optional<String> opStrDate = Optional.empty();
    	if(date == null) {
    		return opStrDate;
    	}
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        opStrDate = Optional.of(sdf.format(date));
        
        return opStrDate;
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
    public static Optional<Date> parseHyphenDate(String strDate) {
        Optional<Date> optDate = Optional.empty();
        
        if(StringUtils.isEmpty(strDate)) {
        	return optDate;
        }
        try {
            SimpleDateFormat strSdf = new SimpleDateFormat("yyyy-MM-dd");
            optDate = Optional.of(strSdf.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
            tmp(e);
        }
        return optDate;
    }
    
    private static void tmp(Exception e) {
    	new Exception();
    }
    
    /**
     * 文字列数字をIntegerに変換
     * @param strNum
     * @return Optional<Integer>
     */
    public static Optional<Integer> parseStrNum(String strNum){
    	Optional<Integer> optNum = Optional.empty();
    	
    	if(StringUtils.isEmpty(strNum)) {
    		return optNum;
    	}
    	optNum = Optional.of(Integer.parseInt(strNum));
    	return optNum;
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
