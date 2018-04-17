package com.ptt.dao.impl;

import com.ptt.util.JDBCUtil;
import com.ptt.dao.PersonDao;
import com.ptt.pojo.Person;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    static Logger logger = Logger.getLogger(PersonDaoImpl.class);
    Connection con;
    PreparedStatement prestmt;
    ResultSet rs;


    @Override
    public void addPerson(Person person) {

       con =  JDBCUtil.getConnection();
       String sql =  "INSERT INTO Person(NAME,major,onlineID,qq,createTime,updateTime) VALUES (?,?,?,?,?,?)";
       try {
            prestmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prestmt.setString(1,person.getP_Name()); //该方法下标从一开始
            prestmt.setString(2,person.getMajor());
            prestmt.setString(3,person.getOnline_id());
            prestmt.setString(4,person.getP_qq());
            prestmt.setLong(5, person.getP_createTime());
            prestmt.setLong(6,person.getP_updateTime());

            boolean b = prestmt.execute();//如果是查询的话有结果集返回就是true，如果是更新或插入的话就返回false
            rs = prestmt.getGeneratedKeys();
            rs.next();
            String r = rs.getString(1);//此处索引从一开始
            logger.info("主键值为："+r);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
           JDBCUtil.closeConnection(rs,prestmt,con);
       }
    }

    @Override
    public List<Person> getPersonByName(String name) {
        con =  JDBCUtil.getConnection();
        String sql =  "SELECT * FROM Person WHERE NAME = ?";
        List<Person> list = new ArrayList<Person>();
        try {
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1,name); //该方法下标从一开始

            rs = prestmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setP_personid(rs.getLong(1));//此处索引从一开始
                person.setP_Name(rs.getString(2));
                person.setMajor(rs.getString(3));
                person.setOnline_id(rs.getString(4));
                person.setP_qq(rs.getString(5));
                person.setP_createTime(rs.getLong(6));
                person.setP_updateTime(rs.getLong(7));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
        return list;
    }

    @Override
    public List<Person> getPersonByOnlingId(String id) {
        con =  JDBCUtil.getConnection();
        String sql =  "SELECT * FROM Person WHERE onlineID = ?";
        List<Person> list = new ArrayList<Person>();
        try {
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1,id); //该方法下标从一开始

            rs = prestmt.executeQuery();
            while(rs.next()) {
                Person person = new Person();
                person.setP_personid(rs.getLong(1));//此处索引从一开始
                person.setP_Name(rs.getString(2));
                person.setMajor(rs.getString(3));
                person.setOnline_id(rs.getString(4));
                person.setP_qq(rs.getString(5));
                person.setP_createTime(rs.getLong(6));
                person.setP_updateTime(rs.getLong(7));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
        return list;
    }

    @Override
    public void updatePerson(Person person) {
        con =  JDBCUtil.getConnection();
        String sql =  "UPDATE Person SET NAME=?,major=?,onlineID=?,qq=? WHERE id = ?";
        try {
            prestmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prestmt.setString(1,person.getP_Name()); //该方法下标从 1 开始
            prestmt.setString(2,person.getMajor());
            prestmt.setString(3,person.getOnline_id());
            prestmt.setString(4,person.getP_qq());
            prestmt.setLong(5,person.getP_personid());

            boolean b = prestmt.execute();//如果是查询的话有结果集返回就是true，如果是更新或插入的话就返回false
            logger.info("有没有rs："+b);
            rs = prestmt.getGeneratedKeys();
            if(rs.next()) {
                String r = rs.getString(1);//此处索引从一开始
                logger.info("主键值为：" + r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
    }

    @Override
    public void deletePerson(int id) {
        con =  JDBCUtil.getConnection();
        String sql =  "DELETE FROM Person WHERE id = ?";
        try {
            prestmt = con.prepareStatement(sql);
            prestmt.setInt(1,id);
            int n = prestmt.executeUpdate();
           logger.info("删除了 "+n+" 行。");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
    }

    //查找所有
    @Override
    public List<Person> getAll() {
        con =  JDBCUtil.getConnection();
        String sql =  "SELECT * FROM Person";
        List<Person> list = new ArrayList<Person>();
        try {
            prestmt = con.prepareStatement(sql);

            rs = prestmt.executeQuery();
            while (rs.next()){
                Person person = new Person();
                person.setP_personid(rs.getLong(1));//此处索引从一开始
                person.setP_Name(rs.getString(2));
                person.setMajor(rs.getString(3));
                person.setOnline_id(rs.getString(4));
                person.setP_qq(rs.getString(5));
                person.setP_createTime(rs.getLong(6));
                person.setP_updateTime(rs.getLong(7));
                list.add(person);
            };
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
        return list;
    }

    //根据id查找
    @Override
    public Person getPersonById(int id) {
        con =  JDBCUtil.getConnection();
        String sql =  "SELECT * FROM Person WHERE id = ?";
        Person person = new Person();
        try {
            prestmt = con.prepareStatement(sql);
            prestmt.setInt(1,id); //该方法下标从一开始

            rs = prestmt.executeQuery();
            while(rs.next()) {
                person.setP_personid(rs.getLong(1));//此处索引从一开始
                person.setP_Name(rs.getString(2));
                person.setMajor(rs.getString(3));
                person.setOnline_id(rs.getString(4));
                person.setP_qq(rs.getString(5));
                person.setP_createTime(rs.getLong(6));
                person.setP_updateTime(rs.getLong(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
        return person;

    }

    @Override
    public int countPerson() {
        con =  JDBCUtil.getConnection();
        String sql =  "SELECT COUNT(*) FROM Person";
        int count = 0;
        try {
            prestmt = con.prepareStatement(sql);

            rs = prestmt.executeQuery();
            while(rs.next()) {
               count = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("我们似乎遇到了些状况。。。");
        }finally {
            JDBCUtil.closeConnection(rs,prestmt,con);
        }
        return count;
    }
}
