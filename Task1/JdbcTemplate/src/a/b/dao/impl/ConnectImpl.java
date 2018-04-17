package a.b.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import a.b.dao.ConnectDao;
import a.b.entity.Person;

public class ConnectImpl implements ConnectDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Person person) {
		// ？？？可以在后面传参直接替换
		String sql = "insert into table1(NAME,gender,age) values(?,?,?)";
		int i = jdbcTemplate.update(sql, person.getNAME(), person.getGender(),
				person.getAge());
		System.out.println("影响了"+i+"行");
		
	}

	@Override
	public void update(Person person) {
		String sql = "update table1 set NAME=?,gender=?,age=? where id = ?";
		jdbcTemplate.update(sql, person.getNAME(), person.getGender(),
				person.getAge(), person.getId());

	}

	public Person getPerson(Integer personid) {
		String sql = "SELECT * FROM table1 WHERE id = ?";
		// new Object[] {参数1,参数2, . . . } 大括号里放sql里的待填充参数 '?'。 
		return jdbcTemplate.queryForObject(sql, new Object[] { personid },
				new RowMapper<Person>() {
					public Person mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						if (rs != null) {
							Person p = new Person();
							p.setNAME(rs.getString("NAME"));
							p.setAge(rs.getString("age"));
							p.setGender(rs.getString("gender"));
							return p;
						}
						return null;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersons2() {
		String sql = "SELECT * FROM table1";
		// new Object[] {参数1,参数2, . . . } 大括号里放sql里的待填充参数 '?'。 
		return (List<Person>) jdbcTemplate.query(sql,
				new RowMapper<Person>() {
					public Person mapRow(ResultSet rs, int rowNum)
							throws SQLException {
							Person p = new Person();
							p.setNAME(rs.getString("NAME"));
							p.setAge(rs.getString("age"));
							p.setGender(rs.getString("gender"));
							return p;
					}
				});
	}

	
	@Override
	public List<Person> getPersons() {
		String sql = "select * from table1";
		// 添加为final是为了在RowCallbackHandler里可以调用！不添加不能在里面add(user)!
		// 不可以写在 processRow(ResultSet rs) {里面}，不然外面无法调用！
		 final List<Person> list = new ArrayList<Person>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				Person user = new Person();
				user.setNAME(rs.getString("NAME"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getString("age"));
				;
				list.add(user);
			}
		});

		return list;
	}

	@Override
	public void delete(Integer personid) throws Exception {
		String sql = "delete from table1 where id = ?";
		jdbcTemplate.update(sql, personid);

	}

}
