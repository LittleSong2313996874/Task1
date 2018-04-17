package a.b.dao;

import java.util.List;

import a.b.entity.Person;

public interface ConnectDao {
	
	 public void save(Person person);
	  
	  public void update(Person person);
	  
	  public Person getPerson(Integer personid);
	  
	  public List<Person> getPersons();
	  
	  public void delete(Integer personid) throws Exception;


	  public List<Person> getPersons2();
}

