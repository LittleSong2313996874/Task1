
package main.java.cn.song.connectWithPureJDBC;


import main.java.cn.song.entity.Person;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class remoteCon2Test {

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

            logger.info("将插入数据量："+ amount/10000+"w ");

            long start = System.currentTimeMillis();

            for (int i=0; i<amount; i++) {

    		    pstat.setString(1, "蒲巴甲");
	            pstat.setString(2, "男");
	            pstat.setString(3, "29");
                pstat.setString(4, "435432546545");

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
            ;
            if(rs.next()){
                person.setId(rs.getInt(1));
                person.setNAME(rs.getString(2));
                person.setGender(rs.getString(3));
                ;
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



}

