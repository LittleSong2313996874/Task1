package a.b.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import a.b.entity.Person;

// 遵循四个原则
// 接口 方法名 == Student.xml 中 id 名
// 返回值类型 与 Mapper.xml文件中返回值类型要一致
// 方法的入参类型 与Mapper.xml中入参的类型要一致
// 命名空间 绑定此接口

public interface PersonDao4 {

	@Insert("insert into table1(NAME,gender,age) values(#{NAME},#{gender},#{age})")
	public void add(Person person);
	
	//注意这里只有一个参数，则#{}中的标识符可以任意取
	@Select("select id,NAME,gender,age from table1 where id = #{id}")
	public Person findById(int id) ;

	@Select("select id,NAME,gender,age from table1")
	public List<Person> findAll();

	@Update("update table1 set name=#{NAME},gender=#{gender},age=#{age} where id=#{id}")
	public void update(Person person) ;
	 
	@Delete("delete from table1 where id = #{id}")
	public void delete(Person person);
	
	@Select("select id,NAME,gender,age from table1 where NAME = #{NAME}")
	List<Person> findByName(String name) ;
	
	
	
}
