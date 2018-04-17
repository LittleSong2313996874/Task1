package com.ptt.pojo;


public class Person {

	private Long p_personid;


	private String p_Name;
	private String major;
	private String online_id;
	private String p_qq;

	private Long p_createTime;

	private Long p_updateTime;



	@Override // 在字符串中，'要以转义字符\'的形式存在。
	public String toString() {
		return "Person{ id='" + p_personid +'\'' +
				", 在线ID='" + online_id + '\'' +
				", 姓名='" + p_Name + '\'' +
				", QQ='" + p_qq + '\'' +
				", 修真类型='" + major + '\'' +
				", 创建时间='" + p_createTime + '\'' +
				", 更新时间='" + p_updateTime + '\'' +
				'}'+"\n";

	}



	public Long getP_personid() {
		return p_personid;
	}

	public void setP_personid(Long p_personid) {
		this.p_personid = p_personid;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getOnline_id() {
		return online_id;
	}

	public void setOnline_id(String online_id) {
		this.online_id = online_id;
	}

	public String getP_qq() {
		return p_qq;
	}

	public void setP_qq(String p_qq) {
		this.p_qq = p_qq;
	}

	public Long getP_createTime() {
		return p_createTime;
	}

	public void setP_createTime(Long p_createTime) {
		this.p_createTime = p_createTime;
	}

	public Long getP_updateTime() {
		return p_updateTime;
	}

	public void setP_updateTime(Long p_updateTime) {
		this.p_updateTime = p_updateTime;
	}


}
