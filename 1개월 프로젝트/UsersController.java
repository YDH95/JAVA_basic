package com.awesome.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.UsersVO;
import com.awesome.service.UsersService;
import com.awesome.util.WebHelper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
@AllArgsConstructor // 생성자를 만들고 자동으로 주입하도록 하기 위해
@SessionAttributes({ "id", "role", "rscode", "nick", "photo" })
public class UsersController {

	private UsersService service;
	private final WebHelper web;

	@RequestMapping(value = "/reg_user")
	public String reg_user() {
		log.info("회원가입 페이지 이동");
		return "/user/reg_user";
	}

	// 회원가입 요청
	@PostMapping(value = "/register") // --> user/register
	public String register(UsersVO usersVO, RedirectAttributes rttr) {
		log.info("register:" + usersVO);
		usersVO.setRole("USER");
		String msg = usersVO.getName() + "님 회원이 되신것을 환영합니다!";
		rttr.addFlashAttribute("result", msg);
		service.register(usersVO);
		return "redirect:/"; // = response.sendRedirect() 처리
	}

	@GetMapping(value = "/idCheck", name = "[API] 로그인 중복 확인", produces = "application/json")
	public String idCheckAPI(HttpServletResponse response) {
		log.info("[API] 아이디 중복 체크");
		web.init();
		String id = web.getString("id");
		log.info("id = " + id);

		// 서비스 호출에서 id 중복체크 결과값 가져오기
		int idResult = service.idCheck(id);

		if (idResult == 1) { // 아이디가 있으면
			// web.printJsonRt(String rtCode, String msg)
			web.getInt("true", idResult);
			web.printJsonRt("아이디가 이미 존재합니다");

		} else { // 중복이 없으면
			web.printJsonRt("사용가능한 아이디입니다");
		}
		return null;
	}

	// 로그인요청
	@PostMapping(value = "/login")
	public String login(Model model) {
		web.init();
		String id = web.getString("id"); // 입력한 id값 삽입
		String password = web.getString("password"); // 입력한 password값 삽입

		UsersVO usersVO = service.login(id, password); // 로그인 조회
		log.info("id :" + id + "password :" + password + "login 결과값 :" + usersVO);

		if (usersVO == null) {
			model.addAttribute("resultMessage", "로그인실패 : 다시 로그인 해주세요.");
			return "/user/login";
		} else { // 로그인 성공시
			// 로그인 성공시 세션 저장 @SessionAttributes 이용 model에 담아서 클라이언트에 전달
			model.addAttribute("id", usersVO.getId());
			model.addAttribute("nick", usersVO.getNickname());
			model.addAttribute("photo", usersVO.getPhoto());

			String role = usersVO.getRole();
			if (role.contains("-")) { // 식당 매니저 권한 별도 처리
				model.addAttribute("role", "MGR"); //
				String[] arr_role = role.split("-"); // MGR-1 "-" 기준으로 나눔
				model.addAttribute("rscode", arr_role[1]); // 식당코드 세션 저장
			} else {
				model.addAttribute("role", role);
			}
			return "redirect:/";
		}
	}

	// 사용자 계정 조회 요청
	@GetMapping("/find_account")
	public String find_account() {
		return "/user/find_account";
	}

	// 사용자 계정 찾기 결과
	@PostMapping("/account_result")
	public String account_result(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("email") String email, Model model) {
		log.info("/account_result.........");

		UsersVO usersVO = service.find_account(id, name, email);

		if (usersVO == null) {
			model.addAttribute("resultMessage", "실패 : 다시 입력 해주세요.");
			return "/user/find_account";
		}

		model.addAttribute("resultMessage", "비밀번호를 변경해주세요");
		model.addAttribute("result", usersVO);
		return "/user/account_result";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(RedirectAttributes rttr, @RequestParam("password") String password,
			@RequestParam("id") String id) {
		log.info("/updatePassword............");

		int pwresult = service.updatePassword(id, password);

		log.info("result>>>>>>>..............................." + pwresult); // 성공시 1

		if (pwresult == 1) {
			rttr.addFlashAttribute("result", "비밀번호가 변경되었습니다!");
		} else {
			rttr.addFlashAttribute("result", "비밀번호번경을 하지못하였습니다!");
			return "redirect:/find_account";
		}
		return "redirect:/login";

	}

	// 로그아웃요청
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

	// 목록요청
	@GetMapping("/list") // --> user/list
	public String list(Model model) {
		log.info("list");
		model.addAttribute("users", service.getUsersList());
		return "loginList";
	}

	// 수정요청
	@PostMapping("/modify") // --> user/modify
	public String modify(UsersVO userVO, RedirectAttributes rttr) {
		log.info("modify :" + userVO);

		if (service.modify(userVO)) {
			rttr.addFlashAttribute("result", "수정완료메시지");
		}
		return "redirect:/user/list";
	}

	// 삭제요청
	@PostMapping("/remove") // --> user/remove
	public String remove(String id, RedirectAttributes rttr) {
		log.info("remove :" + id);

		if (service.remove(id)) {
			rttr.addFlashAttribute("result", "삭제완료메시지");
		}
		return "redirect:/user/list";
	}

}