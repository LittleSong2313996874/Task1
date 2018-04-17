package com.ss;

import com.ss.Dao.PersonDao;
import com.ss.pojo.Person;
import com.ss.util.productData;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.List;


public class Main {
    /**
     * main方法
     */
    private static Logger logger = Logger.getLogger(Main.class);
    static Person person;
    static int i;
    public static void main(String[] args) {
        //加载spring配置
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
       SqlSession sqlsession = (SqlSession)ctx.getBean("sqlsession");
       PersonDao personDao = sqlsession.getMapper(PersonDao.class);

       logger.info("------------首先我们增加100条数据------------");
       List list = productData.getPersonLIST(100);
       i = personDao.addbatch(list);
       logger.info("增加了"+i+"行");
       list = personDao.getAll();
       logger.info("----------然后把它们打印出来-----------");
       logger.info(list+"");

       logger.info("--------下面打印的是根据UUID获取记录-------");
        logger.info("---------------");
       person = personDao.getPersonById("91f0baf2-3fc5-11e8-972d-00163e301dcb");
       logger.info(person+"");

       logger.info("-----------这是根据姓名<艼厦圩>获取记录-----------");
       logger.info(personDao.getPersonByName("艼厦圩")+"");
        logger.info("----------下面是根据人员ID获取记录-------------------");
        person = personDao.getPersonByNumber(6295987752L);
        logger.info(person+"");
        person.setP_senior("斗战胜佛");
        person.setP_traintype("撕逼经理");
        logger.info("------我把上一条数据的改了，更新后在打印出来-------");
        personDao.updatePerson(person);
        logger.info(personDao.getPersonByNumber(6295987752L)+"");
        i = personDao.deletePerson("91f0baf2-3fc5-11e8-972d-00163e301dcb");
        logger.info("删除了"+i+"行");
        person = personDao.getPersonByNumber(6295987752L);
        logger.info("--------好了看看下面是不是NULL--------");
        logger.info(person+"");
    }
}

