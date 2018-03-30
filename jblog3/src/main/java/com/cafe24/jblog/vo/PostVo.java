package com.cafe24.jblog.vo;

public class PostVo {

	
	private Long post_no;
	private String title;
	private String content;
	private String postdate;
	private Long cate_no;
	public Long getPost_no() {
		return post_no;
	}
	public void setPost_no(Long post_no) {
		this.post_no = post_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public Long getCate_no() {
		return cate_no;
	}
	public void setCate_no(Long cate_no) {
		this.cate_no = cate_no;
	}
	
	
}
