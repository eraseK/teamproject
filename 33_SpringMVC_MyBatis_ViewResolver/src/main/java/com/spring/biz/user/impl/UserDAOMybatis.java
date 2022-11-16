package com.spring.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.user.UserVO;

@Repository
public class UserDAOMybatis {
	//JDBC 관련 변수(필드)
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserDAOMybatis() {
		System.out.println(">> UserDAOMybatis() 객체 생성");
	}
	
	// id, password 가 일치하는 데이터 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> mybatis로 getUser() 실행");
		
		return mybatis.selectOne("userDAO.getUser", vo);
	}
}
