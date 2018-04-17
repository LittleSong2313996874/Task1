package com.ss.util;

import com.ss.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class productData {

   private static List list = new ArrayList();
    // 模拟数据
    //1000万数据执行后会内存溢出
   static public List<Person> getPersonLIST(int size){

        //由一个StringBuffer来代替之前4个String的反复字符串操作。
        StringBuffer sb = new StringBuffer("");// String = "";
        int tem;
        for (int r = 0; r < size ; r++) {
            Person person = new Person();

            // person-id
            for (int i = 0; i < 10; i++) {
                tem = (int)(Math.random()*10);
                sb.append(tem);
            }
            person.setP_personid(Long.parseLong(sb.toString()));
            sb.delete(0,sb.length());

            //create_at/update_at
            person.setP_createTime(System.currentTimeMillis());
            person.setP_updateTime(System.currentTimeMillis());

            //NAME
            for (int i = 0; i < 3; i++) {
                sb.append((char) (0x4e00 + (Math.random() * (0x9fa5 - 0x4e00 + 1))));
            }
            person.setP_Name(sb.toString());
            sb.delete(0,sb.length());

            // QQ
            int j;
            for (int i = 0; i < 10; i++) {
                tem = (int)(Math.random()*10);
                sb.append(tem);
            }
            person.setP_qq(sb.toString());
            sb.delete(0,sb.length());

            // TRAINTYPE
            tem = (int)(Math.random() * 5);//[0,2)
            if(tem==0){
                sb.append("Java后端工程师");
            }else if( tem == 1){
                sb.append("运维");
            }else if( tem == 2){
                sb.append("Python");
            }else if( tem == 3){
                sb.append("Web前端工程师");
            }else if( tem == 4){
                sb.append("产品经理");
            }else{
                sb.append("CEO_Exception");
            }
            person.setP_traintype(sb.toString());
            sb.delete(0,sb.length());

            //JOINTIME
            person.setP_jointime("2018-04-06");

            // GRADUATION
            tem = (int)(Math.random() * 5);//[0,2)
            if(tem==0){
                sb.append("北京航空航天大学");
            }else if( tem == 1){
                sb.append("景德镇斯凯诺野鸡大学");
            }else if( tem == 2){
                sb.append("王尔雅家里蹲联邦州立大学");
            }else if( tem == 3){
                sb.append("西安航空学院");
            }else if( tem == 4){
                sb.append("北极圈科考十一附中");
            }else{
                sb.append("Null_SCHOOL");
            }
            person.setP_graduation(sb.toString());
            sb.delete(0,sb.length());

            //ONLINID
            for (int i = 0; i < 5; i++) {
                tem = (int)(Math.random()*10);
                sb.append(tem);
            }
            person.setP_onlineid(sb.toString());
            sb.delete(0,sb.length());

            //DAILYLINK
            tem = (int)(Math.random() * 3);//[0,2)
            if(tem==0){
                sb.append("www.zhihu.com");
            }else if( tem == 1){
                sb.append("www.baidu.com");
            }else if( tem == 2){
                sb.append("www.tenxun.cn");
            }else{
                sb.append("Null_LINK");
            }
            person.setP_dailylink(sb.toString());
            sb.delete(0,sb.length());

            //OATH
            tem = (int)(Math.random() * 3);//[0,2)
            if(tem==0){
                sb.append("穷且益艰，不坠青云之志");
            }else if( tem == 1){
                sb.append("大漠孤烟直，长河落日圆");
            }else if( tem == 2){
                sb.append("将军百战死，壮士十年归");
            }else{
                sb.append("Null_OATH");
            }
            person.setP_oath(sb.toString());
            sb.delete(0,sb.length());

            //SENIOR
            tem = (int)(Math.random() * 3);//[0,2)
            if(tem==0){
                sb.append("宇智波.佐助");
            }else if( tem == 1){
                sb.append("鸣人");
            }else if( tem == 2){
                sb.append("卡卡西");
            }else{
                sb.append("呃呃");
            }
            person.setP_senior(sb.toString());
            sb.delete(0,sb.length());

            //Source
            tem = (int)(Math.random() * 3);//[0,2)
            if(tem==0){
                sb.append("知乎");
            }else if( tem == 1){
                sb.append("贴吧");
            }else if( tem == 2){
                sb.append("朋友推荐");
            }else{
                sb.append("呃呃");
            }
            person.setP_source(sb.toString());
            sb.delete(0,sb.length());

            //status
            tem = (int)(Math.random() * 2);//[0,2)
            if(tem==0){
                sb.append("通过");
            }else if( tem == 1){
                sb.append("未通过");
            }else{
                sb.append("呃呃");
            }
            person.setP_status(sb.toString());
            sb.delete(0,sb.length());

            list.add(person);
        }
        return list;
    }

}
