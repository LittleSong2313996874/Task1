package main.java.cn.song.entity;

public class Person {
	
	private Integer id;
	private String NAME;
	private String gender;
	private String age;

	private  String qq;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
	@Override
    public String toString() {
        return "(" + id + "," + NAME +","+gender+ "," + age +","+qq+")\n";
        
    }
	
	
}
