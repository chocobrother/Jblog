package com.cafe24.jblog.vo;

public class CategoryVo {

	private Long no;
	private String posttype;
	private String content;
	private Long user_no;
	private Long count;
	
	
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPosttype() {
		return posttype;
	}
	public void setPosttype(String posttype) {
		this.posttype = posttype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", posttype=" + posttype + ", content=" + content + ", user_no=" + user_no
				+ "]";
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	
	
	
}
