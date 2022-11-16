package com.spring.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public BoardDAOMybatis() {
		System.out.println(">> BoardDAOMybatis() 객체 생성");
	}
	
	//글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 insertBoard() 실행");
		mybatis.insert("boardDAO.insertBoard", vo);

	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 updateBoard() 실행");
		mybatis.update("boardDAO.updateBoard", vo);
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 deleteBoard() 실행");
		mybatis.delete("boardDAO.deleteBoard", vo);
		
	}
	
	//글 1개 조회(실습)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis 사용 getBoard() 실행");
		
		return mybatis.selectOne("boardDAO.getBoard", vo);
	}
	
	//검색조건 적용해서 검색 ----수정~~
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis 사용  getBoardList(vo) 실행");
		
//		if ("TITLE".equals(vo.getSearchCondition())) {
//			list = mybatis.selectList("boardDAO.getBoardList_T", vo.getSearchKeyword());
//		} else {
//			list = mybatis.selectList("boardDAO.getBoardList_C", vo.getSearchKeyword());
//		}
		
		return mybatis.selectList("boardDAO.getBoardList", vo);
	}
	

	
			
	
}
