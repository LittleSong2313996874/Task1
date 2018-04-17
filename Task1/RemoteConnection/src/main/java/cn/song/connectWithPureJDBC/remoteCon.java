package main.java.cn.song.connectWithPureJDBC;

import com.mysql.jdbc.Statement;

import java.sql.*;

public class remoteCon {

    public static void main(String[] args) throws SQLException {

        try {
            String sql = "INSERT INTO table1 (name,gender,age) VALUES('小白','男','19');";
            Connection con = null;
            Statement stat = null;
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://39.107.247.104:3306/Jnshu1?"+
                            "useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                    "root","123456xyz");
            stat = (Statement) con.createStatement();

			ResultSet rs = stat.executeQuery("select * from table1 where id = 11402026");
			/*Person person = new Person();
            if(rs.next()){
				person.setId(rs.getInt(1));
				person.setNAME(rs.getNString(1));
				person.setGender(rs.getNString(1));
				person.setAge(rs.getNString(1));
				person.setQq(rs.getNString(1));
			}*/

            stat.close();
            con.close();

        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }

	public void testForRemote() throws Exception {
		try {
			String sql = "INSERT INTO person(name_,gender,age) VALUES('小绿','男','19');";
			Connection con = null;
			Statement stat = null;
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://39.107.247.104:3306/Jnshu1?characterEncoding=utf-8&useSSL=false",
					"root","123456xyz");
			stat = (Statement) con.createStatement();
			stat.execute(sql);
			
			stat.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
