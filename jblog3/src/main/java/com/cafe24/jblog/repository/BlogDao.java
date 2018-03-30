package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	public int update(BlogVo vo) {
		System.out.println("blogdao");

//		System.out.println("blogdao !!" + vo.getTitle() + vo.getImage() + vo.getUser_no());

		int count = sqlSession.update("blog.update", vo);

		return count;
	}

	public BlogVo getlist() {

		BlogVo vo = sqlSession.selectOne("blog.getlist");

		return vo;
	}

	public List<CategoryVo> getCategory() {

		List<CategoryVo> list = sqlSession.selectList("blog.getcategory");

		return list;
	}

	public int categoryadd(Map map) {

		int count = sqlSession.insert("blog.categoryadd", map);

		return count;
	}

	public int categorydelete(Long no) {

		int count = sqlSession.delete("blog.categorydelete", no);

		return count;
	}
	

	public List<CategoryVo> categorylist() {

		List<CategoryVo> list = sqlSession.selectList("blog.categorylist");

		return list;
	}
	
	public int postadd(PostVo vo) {

		int count = sqlSession.insert("blog.postadd",vo);

		return count;
	}
}
