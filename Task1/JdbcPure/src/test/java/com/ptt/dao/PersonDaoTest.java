package com.ptt.dao;

import com.ptt.dao.impl.PersonDaoImpl;
import com.ptt.pojo.Person;
import org.junit.Test;

import java.util.List;


public class PersonDaoTest {

    PersonDao persondao = new PersonDaoImpl();
    Person person;

    @Test
    public void getPersonByName() {
        List list = persondao.getPersonByName("小华");
        System.out.println(list+"");
    }

    @Test
    public void getPersonByOnlingId() {
        List list = persondao.getPersonByOnlingId("hjwl-467687");
        System.out.println(list+"");
    }

    @Test
    public void addPerson() {
        person = new Person();
        person.setP_Name("一龙");
        person.setMajor("修真");
        person.setOnline_id("XZ-987");
        person.setP_qq("10000012");
        person.setP_createTime(System.currentTimeMillis());
        person.setP_updateTime(System.currentTimeMillis());
        persondao.addPerson(person);

    }

    @Test
    public void updatePerson() {
        person = persondao.getPersonById(10);
        person.setP_Name("小红");
        person.setMajor("国际关系");
        person.setOnline_id("gjgx-32457");
        persondao.updatePerson(person);
    }

    @Test
    public void deletePerson() {
        persondao.deletePerson(7);
    }

    @Test
    public void getAll() {
        List list = persondao.getAll();
        System.out.println(list+"");
    }

    @Test
    public void getPersonById() {
        person = persondao.getPersonById(10);
        System.out.println(person+"");
    }

    @Test
    public void countPerson() {
        System.out.println(""+persondao.countPerson());

    }


}