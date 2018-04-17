package a.b.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import a.b.entity.Person;
import a.b.util.MybatisUtil;
/**
 * 动态SQL
 * @author Administrator
 *
 */
public class PersonDao2 {

	/**
	 * 有条件的查询所有学生 
	 */
	public List<Person> findAll(Integer id, String name,String gender,String age) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			map.put("pid", id);
			map.put("pname", name);
			map.put("pgender", gender);
			map.put("page", age);
			
			return sqlSession.selectList(Person.class.getName()+".findALL_",map);
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	//更新学生 
	public void update(String name,String gender,String age,Integer id) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			map.put("pname", name);
			map.put("pgender", gender);
			map.put("page", age);
			map.put("pid", id);
			
			sqlSession.update(Person.class.getName()+".upDATA_",map);
			
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	

	// 删除学生 
	public void delete(int... ids) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			                       //名称空间ID.子标签ID
			sqlSession.delete(Person.class.getName()+".DELETE_",ids);
			
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	
	/**
	 * 增加学生
	 */
	public void add(Person person) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();

			sqlSession.insert(Person.class.getName()+".ADD_",person);
			
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//事务回滚
			sqlSession.rollback();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	/**
	 * 根据ID查询学生 
	 */
	public Person findById(int id) throws Exception{
		SqlSession sqlSession = null;
		try{
			
			sqlSession = MybatisUtil.getsqlSession();
			Person student = sqlSession.selectOne(Person.class.getName()+".findById",id);
			sqlSession.commit();
			return student;
		
		}catch(Exception e){
		
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	
	
}
