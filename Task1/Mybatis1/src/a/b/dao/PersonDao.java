package a.b.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import a.b.entity.Person;
import a.b.util.MybatisUtil;


public class PersonDao {

	/**
	 * 增加学生
	 */
	public void add(Person person) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			//事务开始（默认）
			//读取StudentMapper.xml映射文件中的SQL语句
			sqlSession.insert(Person.class.getName()+".add",person);
			/*while(i<100){
				 sqlSession.insert(Person.class.getName()+".add",person);
		    	   i++;
		       }*/
			//事务提交
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
	/**
	 * 查询所有学生 
	 */
	public List<Person> findAll() throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			System.out.println("haole");
			return sqlSession.selectList(Person.class.getName()+".findAll");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	/**
	 * 更新学生 
	 */
	public void update(Person student) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			sqlSession.update(Person.class.getName()+".update",student);
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
	 * 删除学生 
	 */
	public void delete(Person student) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getsqlSession();
			sqlSession.delete(Person.class.getName()+".delete",student);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			MybatisUtil.closesqlSession();
		}
	}
	
}
