package a.b.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import a.b.dao.PersonDao4;
import a.b.entity.Person;
import a.b.util.MybatisUtil;

public class testPerson4 {
	
	private static PersonDao4 persondao;
	/**
	 * 插入1000条数据90s
	 * 
	 * 
	 */
	@Test  //增
	public void testAdd() throws Exception{
		SqlSession sqlSession = null;
		
		Person person = new Person();
		person.setNAME("张无忌");
	    person.setGender("男");
	    person.setAge("21");
    	
	    try {
			int i = 0;

	    	sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			while(i<1000){
				persondao.add(person);
				i++;
			}
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	@Test   // 通过ID查找
	public void testFindById() throws Exception{
		SqlSession sqlSession = null;
	    try {
			sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			
			Person person = persondao.findById(10);
			System.out.println(person);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
	}

	@Test   // 通过姓名查找
	public void testFindByName() throws Exception{
		SqlSession sqlSession = null;
	    try {
			sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			
			List<Person> persons = persondao.findByName("小华");;
			System.out.println(persons);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	@Test // 更新
	public void testUpdate() throws Exception{
			
		SqlSession sqlSession = null;
	    try {
			sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			
			Person person = persondao.findById(5);
		    person.setGender("Unknow");
			persondao.update(person);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
			
	}
	
	@Test  //查找所有
	public void testFindAll() throws Exception{
			
		SqlSession sqlSession = null;
	    try {
			sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			
			List<Person> persons = persondao.findAll();
			System.out.println(persons);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
			
	}
	@Test  // 删除
	public void testDELETE() throws Exception{

		SqlSession sqlSession = null;
	    try {
			sqlSession = MybatisUtil.getsqlSession();
			persondao = sqlSession.getMapper(PersonDao4.class);
			
			Person person = persondao.findById(9);
			persondao.delete(person);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		} finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	

}
