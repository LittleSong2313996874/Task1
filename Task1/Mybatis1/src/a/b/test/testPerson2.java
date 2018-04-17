package a.b.test;

import java.util.List;

import org.junit.Test;

import a.b.dao.PersonDao2;
import a.b.entity.Person;
/**
 * 动态SQL测试类
 * @author Administrator
 *
 */
public class testPerson2 {
	
	PersonDao2 persondao = new PersonDao2();
	
	@Test //增
    public void TestInsert() throws Exception {
       Person person = new Person();
       person.setNAME("小龙人");
       //person.setGender("");
       person.setAge("26");
       persondao.add(person);
    }

	@Test //删除
	public void TestDELETE() throws Exception {
		persondao.delete(134,135,137);
	}

	
	@Test //条件查找所有
	public void TestFindAll() throws Exception {
		List<Person> persons = persondao.findAll(null,"傻逼",null,null);
		for(Person person : persons){
			System.out.println(person.getId()+"，"+person.getNAME()+
					"，"+person.getGender()+"，"+person.getAge());
		}
	}
	
	@Test //更新
	public void TestUpdate() throws Exception {
		persondao.update("傻逼", "太监", "10000", 142);
	}
	
	
/*	@Test //删除
	public void TestUpdate() throws Exception {
		Person person = persondao.findById(219);
        person.setNAME("小莉");
        person.setGender("人妖");
        
		persondao.update(person);;
	}
	*/
}


