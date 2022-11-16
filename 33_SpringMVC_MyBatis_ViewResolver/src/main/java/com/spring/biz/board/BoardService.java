package com.spring.biz.board;

import java.util.List;

public interface BoardService {
	//CRUD 기능 구현 메서드 정의
	void insertBoard(BoardVO vo);
	void updateBoard(BoardVO vo);
	void deleteBoard(BoardVO vo);
	BoardVO getBoard(BoardVO vo); //1개의 데이터 조회
	//List<BoardVO> getBoardList(); //전체글 조회
	List<BoardVO> getBoardList(BoardVO vo);
}
