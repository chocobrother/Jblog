package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;
import com.cafe24.mysite.exception.UserDaoException;



@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insert(UserVo vo) {
		
		int count = sqlSession.insert("user.insert",vo);
		
		sqlSession.insert("user.bloginsert", vo);
		
		

		return count == 1;
	}
	
	
	
	public UserVo get(UserVo vo) throws UserDaoException{
		
		UserVo result =
				sqlSession.selectOne("user.getByEmailAndPassword", vo);
		
		return result;
		}
	
	
	
//	
}