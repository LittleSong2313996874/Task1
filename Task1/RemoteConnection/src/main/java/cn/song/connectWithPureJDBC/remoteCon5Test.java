
package main.java.cn.song.connectWithPureJDBC;


import main.java.cn.song.entity.Person;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class remoteCon5Test {

    static List<Person> list = new ArrayList<Person>();
    static List<Person> list2 = new ArrayList<Person>();
    @Test
    public void testForRemote() throws Exception {
        try {
            String sql = "INSERT INTO table1(NAME,gender,age,qq) VALUES(?,?,?,?)";
            Connection con = null;
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://39.107.247.104:3306/Jnshu1?useUnicode=true&"+
                            "characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true",
                    "root","123456xyz");
            PreparedStatement pstat = con.prepareStatement(sql);

            con.setAutoCommit(false);
            list2 = getPersonWithArg();
            System.out.println(list2.size());

            long start = System.currentTimeMillis();
            // 模拟数据
            for (int i=0; i<list2.size(); i++) {

                Person person = list2.get(i);
                pstat.setString(1, person.getNAME());
                pstat.setString(2, person.getGender());
                pstat.setString(3, person.getAge());
                pstat.setString(4, person.getQq());

                pstat.addBatch();
                if(i % 50000 == 0){
                    pstat.executeBatch();
                    pstat.clearBatch();
                }
            }
            pstat.executeBatch();
            pstat.clearBatch();
            con.commit();

            long end = System.currentTimeMillis();
            pstat.close();
            con.close();
            System.out.print((end-start)/1000+"秒。");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 模拟数据
    //1000万数据执行后会内存溢出
    public List<Person> getPersonWithArg(){


        for (int r = 0; r < 1000000 ; r++) {
            String name = "";
            String sex;
            String age = "";
            String qq = "";

            Person person = new Person();
            //姓名
            for (int i = 0; i < 3; i++) {
                name += ((char) (0x4e00 + (Math.random() * (0x9fa5 - 0x4e00 + 1))));
            }
            person.setNAME(name);

            //性别
            int sexf = (int)(Math.random() * 2);
            if(sexf==0){
                sex = "男";
            }else if( sexf == 1){
                sex = "女";

            }else {
                sex = "人妖";

            }
            person.setGender(sex);
            //年龄
            int d;
            for (int i = 0; i < 2; i++) {
                d = (int)(Math.random()*10);
                age += d;
            }
            person.setAge(age);

            // qq
            int j;
            for (int i = 0; i < 10; i++) {
                d = (int)(Math.random()*10);
                qq += d;
            }
            person.setQq(qq);

            list.add(person);
        }
        return list;
    }


}

