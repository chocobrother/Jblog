package com.cafe24.jblog.vo;

public class UserVo {

	
	private Long no;
	private String id;
	private String name;
	private String passwd;
	private String userdate;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserdate() {
		return userdate;
	}
	public void setUserdate(String userdate) {
		this.userdate = userdate;
	}
	@Override
	public String toString() {
		return "userVo [no=" + no + ", id=" + id + ", name=" + name + ", passwd=" + passwd + ", userdate=" + userdate
				+ "]";
	}
	
	
	
	
}
