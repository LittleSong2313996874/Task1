package a.b.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import a.b.entity.Person;
import a.b.util.MybatisUtil;

// 遵循四个原则
// 接口 方法名 == Student.xml 中 id 名
// 返回值类型 与 Mapper.xml文件中返回值类型要一致
// 方法的入参类型 与Mapper.xml中入参的类型要一致
// 命名空间 绑定此接口

public interface PersonDao3 {

	/**
	 * 增加学生
	 */
	public void add(Person person);
	/**
	 * 根据ID查询学生 
	 */
	public Person findById(int id) ;
	/**
	 * 查询所有学生 
	 */
	public List<Person> findAll();
	/**
	 * 更新学生 
	 */
	public void update(Person person) ;
	/**
	 * 删除学生 
	 */
	public void delete(Person person);
	
	
}
