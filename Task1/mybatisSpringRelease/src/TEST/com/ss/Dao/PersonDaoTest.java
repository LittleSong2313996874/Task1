package com.ss.Dao;

import com.ss.pojo.Person;
import com.ss.util.productData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class PersonDaoTest {

    @Autowired
    PersonDao persondao;
    @Resource
    Person person;

    Logger logger = LoggerFactory.getLogger(PersonDaoTest.class);
    @Test
    public void addbanth() {
        List list = productData.getPersonLIST(10);
        int i= persondao.addbatch(list);
        logger.info("添加了"+i+"行");
    }

    @Test
    public void getPersonByNumber() {
        person = persondao.getPersonByNumber(9338622615L);
        logger.info(person+"");
    }

    @Test
    public void addPerson() {
        person = productData.getPersonLIST(1).get(0);
        logger.info(person+"");
        int rs = persondao.addPerson(person);
        logger.info("增加了"+rs+"行");
        person = persondao.getPersonByNumber(person.getP_personid());
        logger.info(person+"");
    }

    @Test
    public void updatePerson() {
        person = persondao.getPersonByNumber(11611580L);
        logger.info(person+"");
        person.setP_Name("王五");
        persondao.updatePerson(person);
        person = persondao.getPersonByName("王五");
        logger.info(person+"");
    }

    @Test
    public void deletePerson() {
        person = persondao.getPersonById("f7667df5-3f08-11e8-972d-00163e301dcb");
        logger.info(person+"");
        int  i = persondao.deletePerson(person.getUuid());
        logger.info("删除了"+i+"行");
    }

    @Test
    public void getAll() {
        List<Person> list = persondao.getAll();
        logger.info(list+"");
    }

    @Test
    public void getPersonById() {
        person = persondao.getPersonById("b93e621f-3fdc-11e8-972d-00163e301dcb");
        logger.info(person+"");

    }

    @Test
    public void getPersonByName() {
        person = persondao.getPersonByName("洇碪抉");
        logger.info(person+"");
    }

}