package com.spring.biz.view.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;

//@Controller
@RestController //클래스 내의 요청처리 결과가 모두 @ResponseBody 처리됨
public class BoardAjaxController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getJsonBoardList.do")
	//@ResponseBody //response 응답객체의 몸체(body)에 데이터 전달
	public List<BoardVO> getJsonBoardList(BoardVO vo) {
		System.out.println("========= BoardAjaxController getJsonBoardList() 실행");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("boardList : " + boardList);
		
		return boardList;
	}
	
	//JSON 데이터를 받아 게시글 찾아서 JSON 데이터로 리턴(응답)
	@PostMapping("/getJsonBoard.do")
	public BoardVO getJsonBoard(@RequestBody BoardVO vo) { //@RequestBody post방식 전달데이터 처리
		System.out.println("========== BoardAjaxController getJsonBoard(vo) 실행");
		System.out.println("getJsonBoard() vo : " + vo);
		
		BoardVO board = boardService.getBoard(vo);
		System.out.println("getJsonBoard() board : " + board);
		
		return board;
	}
	
}
