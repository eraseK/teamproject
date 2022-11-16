package com.spring.biz.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

//@SessionAttributes : board라는 이름의 Model 있으면 session에 저장
//  단, 현재위치(클래스)에서만 유효
@Controller
@SessionAttributes("board") 
@RequestMapping("/board") //@RequestMapping 클래스 선언부 사용시 모든 메소드 요청 경로의 부모(root) 경로로 추가됨
public class BoardController { 
	@Autowired
	private BoardService boardService;
	
	// 메소드의 정의부에 선언된 @ModelAttribute 는 리턴된 데이터를 View에 전달
	// @ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행
	// 뷰(View)에 전달될 때 설정된 명칭 사용(예: conditionMap)
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("=======> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}

	//리턴타입 : ModelAndView ---> String
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println(">> 게시글 상세 보여주기");
		System.out.println("boardService : " + boardService);
		BoardVO board = boardService.getBoard(vo);
		
		model.addAttribute("board", board);
		System.out.println("board : " + board);
		
		return "board/getBoard";
	}
	
	//GET, POST 방식 요청을 모두 처리
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println(">> 게시글 목록 보여주기");
		System.out.println("vo : " + vo);

		//List<BoardVO> list = boardDAO.getBoardList();
		List<BoardVO> list = boardService.getBoardList(vo);
		
		//mav.addObject("boardList", list); // Model에 데이터 저장
		//mav.setViewName("getBoardList.jsp"); // View 명칭 저장
		
		model.addAttribute("boardList", list);
		
		return "board/getBoardList";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(">>>>> 게시글 입력");
		System.out.println("vo : " + vo);
		
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println(">> uploadFile : " + uploadFile);
		
		if (uploadFile == null) {
			System.out.println("::: uploadFile 파라미터가 전달되지 않았을때~");
		} else if (!uploadFile.isEmpty()) {
			System.out.println("uploadFile.isEmpty() : " + uploadFile.isEmpty());
			String fileName = uploadFile.getOriginalFilename(); //원본파일명
			System.out.println("::: 원본파일명 : " + fileName);
			System.out.println("::: 저장파일명 : " + UUID.randomUUID().toString());
			uploadFile.transferTo(new File("c:/MyStudy/temp/" + UUID.randomUUID().toString()));
		}
		
		boardService.insertBoard(vo);
		
		return "redirect:getBoardList.do";
	}
	
	@GetMapping("/insertBoard.do")
	public String inserboardgo() {
		System.out.println("이동");
		return "board/insertBoard";
		
	}
	
	
	@RequestMapping("/updateBoard.do")
	//@ModelAttribute("board") BoardVO : board라는 이름의 Model 객체가 있으면 사용
	//	없으면 새로 생성
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(">> 게시글 수정");
		System.out.println("vo : " + vo);
		
		boardService.updateBoard(vo);
		
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println(">> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardService.deleteBoard(vo);
		
		return "redirect:getBoardList.do";
	}
}
