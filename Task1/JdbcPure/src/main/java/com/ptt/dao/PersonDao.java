package com.ptt.dao;


import com.ptt.pojo.Person;

import java.util.List;

public interface PersonDao {

    List<Person> getPersonByName(String name);

    List<Person> getPersonByOnlingId(String id);

    void addPerson(Person person);

    void updatePerson(Person user);

    void deletePerson(int id);

    List<Person> getAll();

    Person getPersonById(int id);

    int countPerson();

}






