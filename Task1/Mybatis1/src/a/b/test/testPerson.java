package a.b.test;

import java.util.List;

import org.junit.Test;

import a.b.dao.PersonDao;
import a.b.entity.Person;

public class testPerson {
	
	PersonDao persondao = new PersonDao();
	
	@Test //增
    public void TestInsert() throws Exception {
       Person person = new Person();
       person.setNAME("ross");
       person.setGender("男");
       person.setAge("28");
       persondao.add(person);
    }

	@Test //通过ID查找
	public void TestFindById() throws Exception {
		Person person = persondao.findById(19);
		System.out.println(person.getId()+"，"+person.getNAME()+"，"+person.getGender()+
				"，"+person.getAge());
	}
	
	@Test //删除
	public void TestDelete() throws Exception {
		Person person = persondao.findById(17);
		persondao.delete(person);
	}
	
	@Test //查找所有
	public void TestFindAll() throws Exception {
		List<Person> persons = persondao.findAll();
		for(Person person : persons){
			System.out.println(person.getId()+"，"+person.getNAME()+"，"+person.getGender()+"，"+person.getAge());
		}
	}
	
	@Test //删除
	public void TestUpdate() throws Exception {
		Person person = persondao.findById(219);
        person.setNAME("小莉");
        person.setGender("人妖");
        
		persondao.update(person);;
	}
	
}


