package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/*
	 * @RequestMapping : 요청과 처리(Controller) 연결(HandlerMapping 역할 대체)
	 * Command 객체 사용 : 파라미터로 전달되는 값을 Command 객체에 설정
	 * 1. UserVO 타입 객체 생성
	 * 2. UserVO 타입 객체에 전달받은 파라미터 값을 설정(이름 일치하는 경우)
	 * 3. UserVO 타입 객체를 메소드의 파라미터 값으로 전달
	 */
	//@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@PostMapping("login.do") // 4.3버전 부터 사용가능
	public String login(UserVO vo) {
		System.out.println(">>> 로그인 처리");
		System.out.println("vo : " + vo);
		
		//일부러 예외발생
		if(vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디 반드시 입력되어야 합니다");
		}
		
		UserVO user = userService.getUser(vo);
		
		//3. 화면 네비게이션(화면전환, 화면이동)
		// 로그인 성공 : 게시글 목록 보여주기
		// 로그인 실패 : 로그인 화면으로 이동
		if (user != null) {
			System.out.println(">> 로그인 성공!!!");
			return "redirect:/board/getBoardList.do";
		} else {
			System.out.println(">> 로그인 실패~~~");
			return "redirect:login.do";
		}
	}
	
	/* @ModelAttribute : 모델(Model)의 속성값으로 저장(속성명 별도 지정)
	 * 별도로 명칭부여 안하면 <데이터타입>의 첫글자 소문자로 작성된 명칭 사용
	 * @ModelAttribute UserVO ----> 속성명 userVO 명칭 사용
	 * @ModelAttribute("user") UserVO ----> 속성명 user 사용
	 */
	//@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	@GetMapping("/login.do") // 4.3버전 부터 사용가능
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println(">>> 로그인 화면 이동 - loginView()");
		vo.setId("test");
		vo.setPassword("test");
		
		return "user/login";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println(">> 로그아웃 처리");
		session.invalidate();
		return "user/login";
	}
	
}
