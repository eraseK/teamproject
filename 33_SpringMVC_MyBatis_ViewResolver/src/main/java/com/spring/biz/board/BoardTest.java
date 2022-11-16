package com.spring.biz.board;

import java.util.List;

import com.spring.biz.board.impl.BoardDAO;

public class BoardTest {

	public static void main(String[] args) {
		//JDBCUtil.getConnection();
		BoardDAO dao = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트2");
		vo.setWriter("홍길동2");
		vo.setContent("테스트2-내용");
		
		//dao.insertBoard(vo);
		//수정 ----------------
		vo.setTitle("테스트2-수정");
		vo.setContent("테스트2-내용수정");
		vo.setSeq(3);
		
		dao.updateBoard(vo);
		
		//삭제 -------------------
		dao.deleteBoard(vo);
		
		//게시글 1개 조회 --------------------
		vo = new BoardVO();
		vo.setSeq(2);
		BoardVO getVO =  dao.getBoard(vo);
		System.out.println("getVO : " + getVO);
		
		
		//게시글 전체 목록 조회 ------------------
		List<BoardVO> list = dao.getBoardList();
		System.out.println("list : " + list);
		for (BoardVO board : list) {
			System.out.println(board);
		}
	}

}
