
package main.java.cn.song.connectWithPureJDBC;


import main.java.cn.song.entity.Person;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class remoteCon3Test {

    static List<Person> list = new ArrayList<Person>();
    static List<Person> list2 = new ArrayList<Person>();
    static Logger logger = Logger.getLogger(main.java.cn.song.Main.class);
    Person person = new Person();

    static Connection con;

    static public Connection getConnection() {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取到远程服务器的连接
            con = DriverManager.getConnection("jdbc:mysql://39.107.247.104:3306/Jnshu1?useUnicode=true&" +
                            "characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true",
                    "root", "123456xyz");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            return con;
        }
    }




    public void testForRemote(int amount) throws Exception {
        try {
            //准备sql语句   ,create_at,update_at
            String sql = "INSERT INTO table1(NAME,gender,age,qq) VALUES(?,?,?,?)";

            Connection con = getConnection();

            //设置非自动提交事务
            con.setAutoCommit(false);

            PreparedStatement pstat = con.prepareStatement(sql);

            //获得模拟数据
            list2 = getPersonWithArg(amount);
            logger.info("将插入数据量："+list2.size()/10000+"w ");

            long start = System.currentTimeMillis();

            for (int i=0; i<list2.size(); i++) {

                Person person = list2.get(i);
    		    pstat.setString(1, person.getNAME());
	            pstat.setString(2, person.getGender());
	            pstat.setString(3, person.getAge());
                pstat.setString(4, person.getQq());

                //10w提交一次
                pstat.addBatch();
                if(i % 100000 == 0){
                    pstat.executeBatch();
                    pstat.clearBatch();
                }
    		}
    		    pstat.executeBatch(); //执行批处理
    		    pstat.clearBatch();  //清空批处理
    		    con.commit();
                long end = System.currentTimeMillis();
            logger.info("完成时间："+(end-start)/1000+"秒。");

            ResultSet rs = pstat.executeQuery("select count(id) from table1;");
            rs.next();
            logger.info("table中数据量："+rs.getInt(1)+" 。");

            pstat.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getPersonRow(long index){

        try {
            Connection con = getConnection();

            PreparedStatement pstat = null;
            pstat = con.prepareStatement("select * from table1 where id = ?");
            pstat.setLong(1,index);
            long start = System.currentTimeMillis();
            ResultSet rs = pstat.executeQuery();
            long end = System.currentTimeMillis();
            logger.info("查找用时 ："+(end-start)/1000+" 秒。");
            if(rs.next()){
                person.setId(rs.getInt(1));
                person.setNAME(rs.getString(2));
                person.setGender(rs.getString(3));
                person.setAge(rs.getString(4));
                person.setQq(rs.getString(5));
        }
            logger.info(person+"");
            pstat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 模拟数据
    //1000万数据执行后会内存溢出
    public List<Person> getPersonWithArg(int amount){

        //由一个StringBuffer来代替之前4个String的反复字符串操作。
        StringBuffer sb = new StringBuffer("");// String = "";
        for (int r = 0; r < amount ; r++) {
            Person person = new Person();
            //随机产生姓名
            for (int i = 0; i < 3; i++) {
                sb.append((char) (0x4e00 + (Math.random() * (0x9fa5 - 0x4e00 + 1))));
            }
            person.setNAME(sb.toString());
            //用完后清楚里面的内容，以供下一次使用
            sb.delete(0,sb.length());

            //随机产生性别
            int sexf = (int)(Math.random() * 2);//[0,2)
            if(sexf==0){
                sb.append("男");
            }else if( sexf == 1){
                sb.append("女");
            }else {
                sb.append("Unknow");
            }
            person.setGender(sb.toString());
            sb.delete(0,sb.length());
            //随机产生年龄
            int d;
            for (int i = 0; i < 2; i++) {
                d = (int)(Math.random()*10);
                sb.append(d);
            }
            person.setAge(sb.toString());
            sb.delete(0,sb.length());

            // 随机产生qq
            int j;
            for (int i = 0; i < 10; i++) {
                d = (int)(Math.random()*10);
                sb.append(d);
            }
            person.setQq(sb.toString());
            sb.delete(0,sb.length());

            list.add(person);
        }
        return list;
    }

    @Test
    public void testGenerateRandom() throws Exception {
        //(int)(Math.Random()*n) 可以得到一个0到n范围的随机数。
        //产生一个大于等于0到小于1的double型随机数
        double d = Math.random();
        //乘以10然后再强转为int，转为int后会将小数部分弃掉，不是四舍五入
        System.out.println((int)(d*10)+" and "+ d);
        //以下代码可以生成随机的字符
        /**
         * 'z'-'a'=25，Math.random()产生的是0到1的小数[0,1),乘以26得到的是[0,26)的double
         * 然后'a'的ASCII码表对应的是97，加上[0,26)的double得到[97,123)的double,但是自动舍弃小数部分。
         * 从而的到了a到z的码表值。
         */
        char ca = (char)('a'+Math.random()*('z'-'a'+1));
        System.out.println(ca);
        /**
         * 汉字的unicode范围是：0x4E00~0x9FA5。故可通过以下代码生成随机汉字
         *(char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)))
         * 其实不用int强转，后面的char强转会弃掉后面的小数位
         */
        System.out.println((char) (0x4e00 + (Math.random() * (0x9fa5 - 0x4e00 + 1))));

    }

}

/*
进入正式测试，100万数据插入

100万整个一批   27s

50万                 26s

20万                28

10万                19s

5万一批处理    18s

1万                  28s

5000               48s

由上面可知，批处理的速度跟每批处理数据量有关，每次处理太少会慢，但太高也不好，经过上面的测试对于100万的插入每批10万或5万最佳。

 */