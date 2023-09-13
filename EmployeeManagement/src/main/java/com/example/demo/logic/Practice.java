package com.example.demo.logic;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.T1Entity;
import com.example.demo.entity.T2Entity;

/**
 * 銀行取引を想定.
 */
public class Practice {

    /**
     * mainクラス.
     * @param args String[]
     */
    public static void main(String[] args) {
        //T1にKashitsukeNoが入ってない場合、SyousyoNoとRonNoが一致する
        //KashitsukeNoを探して あった場合はKashitsukeNo+1をした値をT2に挿入する。
        List<T1Entity> t1List =  setT1Val();
        for (T1Entity t1: t1List) {
            System.out.println(t1.getSyousyoNo()); 
            System.out.println(t1.getRonNo()); 
            System.out.println(t1.getKashitsukeNo()); 
            System.out.println(t1.getOtrOid()); 
            
            if (t1.getKashitsukeNo() == 0) {
                
            }
        }
    }
    
    /**
     * T1テーブルバリューをセット.
     * @return t1List
     */
    private static List<T1Entity> setT1Val() {
        List<T1Entity> t1List = new ArrayList<>();
        T1Entity t1 = new T1Entity();
        t1.setSyousyoNo(1);
        t1.setRonNo(1);
        t1.setKashitsukeNo(1);
        t1.setOtrOid(1);
        t1List.add(t1);
        T1Entity tt1 = new T1Entity();
        tt1.setSyousyoNo(1);
        tt1.setRonNo(1);
        tt1.setOtrOid(2);
        t1List.add(tt1);
        T1Entity ttt1 = new T1Entity();
        ttt1.setSyousyoNo(2);
        ttt1.setRonNo(2);
        ttt1.setKashitsukeNo(1);
        ttt1.setOtrOid(3);
        t1List.add(ttt1);
        return t1List;
    }
    
    /**
     * T2テーブルバリューをセット.
     * @return t2List
     */
    private static List<T2Entity> setT2val(){
        List<T2Entity> t2List = new ArrayList<>();
        T2Entity t2 = new T2Entity();
        t2.setSyousyoNo(1);
        t2.setRonNo(1);
        t2.setKashitsukeNo(1);
        t2.setOtrOid(1);
        t2List.add(t2);
        return t2List;
    }
}
