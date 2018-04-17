package com.ss.Dao;

import com.ss.pojo.Person;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface PersonDao {

    Person getPersonByName(String name);

    Person getPersonByNumber(long number);

    int addPerson(Person person);

    int addbatch(List<Person> userList);

    int updatePerson(Person user);

    int deletePerson(String id);

    List<Person> getAll();

    Person getPersonById(String id);
}






