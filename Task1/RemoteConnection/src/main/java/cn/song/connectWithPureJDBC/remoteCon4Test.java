
package main.java.cn.song.connectWithPureJDBC;


import org.junit.jupiter.api.Test;

import java.sql.*;

public class remoteCon4Test {

    //private ResultSet rs;

    @Test
    public void testForRemote() throws Exception {
        try {
            String sql = "INSERT INTO table1(NAME,gender,age) VALUES(?,?,?);";
            Connection con = null;
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://39.107.247.104:3306/Jnshu1?useUnicode=true&"+
                            "characterEncoding=utf-8&useSSL=false",
                    "root","123456xyz");
            PreparedStatement pstat = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstat.setString(1, "小华");
            pstat.setString(2, "男");
            pstat.setString(3, "108");
            pstat.executeUpdate();
            ResultSet rs = pstat.getGeneratedKeys();
            // 得到返回的自增长字段
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            pstat.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}