package a.b.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import a.b.dao.PersonDao3;
import a.b.entity.Person;
import a.b.util.MybatisUtil;

public class testPerson3 {
	
	static PersonDao3 persondao;
	static SqlSession sqlSession;

	 // 使用了该元数据的方法在每个测试方法执行之前都要执行一次。（方法开始前执行）
	@Before
    public void init() throws Exception {
    	persondao = null;
    	sqlSession = null;
    	sqlSession = MybatisUtil.getsqlSession();
		persondao = sqlSession.getMapper(PersonDao3.class);
		System.out.println("++++++++ before +++++++++");
    }
    
    //方法是在执行每个测试方法之后执行的，方法名最好和tearDown()一样，但不强求，在每一个测试方法之后被执行
    @After
    public void aft() {
    	sqlSession.commit();
		MybatisUtil.closesqlSession();
    	System.out.println("========== after ==========");
    }
	
	@Test  //增
	public void testAdd() throws Exception{
			
			Person person = new Person();
			person.setNAME("Chelender");
		    person.setGender("男");
		    person.setAge("29");
			persondao.add(person);
			System.out.println("执行了ADD方法....");
			
	}
	
	@Test   // 通过ID查找
	public void testFindById() throws Exception{

			Person person = persondao.findById(234);
			//System.out.println(person);
			System.out.println("执行了FindById方法....");
	}
	
	@Test // 更新
	public void testUpdate() throws Exception{
			
			Person person = persondao.findById(234);
		    person.setGender("men");
			persondao.update(person);
			System.out.println("执行了Update方法....");
	}
	
	@Test  //查找所有
	public void testFindAll() throws Exception{
			
			List<Person> persons = persondao.findAll();
			//System.out.println(persons);
			System.out.println("执行了FindAll方法....");
			
	}
	@Test  // 删除
	public void testDELETE() throws Exception{
			Person person = persondao.findById(45);
			persondao.delete(person);
			System.out.println("执行了DELETE方法....");
			
	}
	

}
