package a.b.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.b.dao.ConnectDao;
import a.b.entity.Person;
/**
 * CRUD : 增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)
 * @author Administrator
 *
 */
public class test1 {

	private ApplicationContext ctx = null;
	private ConnectDao connectDao;
	{
		ctx = new ClassPathXmlApplicationContext("a/b/dao/beans.xml");
		connectDao = (ConnectDao) ctx.getBean("connectImpl");
	}
	@Test
	public void testSAVE() throws Exception {
		Person person = new Person();
		person.setNAME("洛神2");
		person.setGender("男");
		person.setAge("77"); 
		connectDao.save(person);
	}
	
	@Test //删除
	public void testDELETE() throws Exception {
		connectDao.delete(2);
	}
	
	@Test //更改
	public void testUPDATA() throws Exception {
		Person person = new Person();
		person.setNAME("小H");
		person.setGender("男");
		person.setAge("35"); 
		person.setId(8);
		connectDao.update(person);;
	}
	
	@Test //查询所有
	public void testQUERY() throws Exception {
		List<Person> list = connectDao.getPersons();
		System.out.println(list.toString());
	}
	
	@Test //查询所有
	public void testQUERY2() throws Exception {
		List<Person> persons = connectDao.getPersons2();
		for(Person person:persons){

			System.out.println(person.getNAME());

			}
	}
	
	@Test //通过ID查询
	public void testgetPersonById() throws Exception {
	  Person p = connectDao.getPerson(18);
		System.out.println(p.getNAME());
	}
	

	
}
