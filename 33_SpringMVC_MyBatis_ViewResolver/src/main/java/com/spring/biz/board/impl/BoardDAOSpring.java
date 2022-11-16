package com.spring.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

@Repository
public class BoardDAOSpring {
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 문
	private final String BOARD_INSERT
	 	="INSERT INTO BOARD (SEQ, TITLE, WRITER, CONTENT)"
	 	+ "VALUES ((SELECT NVL(MAX(SEQ),0) + 1 FROM BOARD),?,?,?)";
	private final String BOARD_UPDATE
		="UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
	private final String BOARD_DELETE
		="DELETE FROM BOARD WHERE SEQ = ?";
	private final String BOARD_GET
		="SELECT * FROM BOARD WHERE SEQ = ?";
	private final String BOARD_LIST
		="SELECT * FROM BOARD ORDER BY SEQ DESC";
	
	// TITLE(제목) 에서 검색
	private final String BOARD_LIST_T
		= "SELECT * FROM BOARD WHERE TITLE LIKE '%'|| ? ||'%' ORDER BY SEQ DESC";
	// CONTENT(내용) 에서 검색
	private final String BOARD_LIST_C
		= "SELECT * FROM BOARD WHERE CONTENT LIKE '%'|| ? ||'%' ORDER BY SEQ DESC";
	
	@Autowired
	public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
		System.out.println(">> BoardDAOSpring(jdbcTemplate) 객체 생성");
		this.jdbcTemplate = jdbcTemplate;
	}

	//글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 실행");
		
		Object[] args = {vo.getTitle(), vo.getContent(), vo.getWriter()};
		jdbcTemplate.update(BOARD_INSERT, args);
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 실행");
		
		Object[] args = {vo.getTitle(), vo.getContent(), vo.getSeq()};
		jdbcTemplate.update(BOARD_UPDATE, args);
		
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 실행");
		
		Object args = vo.getSeq();
		jdbcTemplate.update(BOARD_DELETE, args);
		
	}
	
	//글 1개 조회(실습)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 실행");
		
		Object[] args = { vo.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	//글 목록 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> Spring JDBC로 getBoardList() 실행");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
	
	
	//검색조건 적용해서 검색 ----수정~~
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로  getBoardList(vo) 실행");
		String sql;
		if("TITLE".equals(vo.getSearchCondition())) {
			Object[] args = {vo.getSearchKeyword()};
			//return jdbcTemplate.query(BOARD_LIST_T, args, new BoardRowMapper());
			//return jdbcTemplate.query(BOARD_LIST_T, new BoardRowMapper(), vo.getSearchKeyword());
			sql = BOARD_LIST_T;
		} else {
			Object[] args = {vo.getSearchKeyword()};
			//return jdbcTemplate.query(BOARD_LIST_C, args, new BoardRowMapper());
			sql = BOARD_LIST_C;
		}
		return jdbcTemplate.query(sql, new BoardRowMapper(), vo.getSearchKeyword());
	}
		
		
		
	
}
