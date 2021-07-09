package com.awesome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.ReviewVO;
import com.awesome.domain.UsersVO;
import com.awesome.service.MypageService;
import com.awesome.util.CheckSession;
import com.awesome.util.WebHelper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mypage")
@AllArgsConstructor // 생성자를 만들고 자동으로 주입하도록 하기 위해
public class MypageController {

	MypageService service;
	private WebHelper web;

	@GetMapping("/mypage")
	public String myinfo(Model model, HttpServletRequest request, HttpSession session) {
		log.info("마이정보페이지");
		int check = CheckSession.checkUserSession(session);
		String result = (check == 1) ? "/mypage/mypage" : "/user/login";
		String id = CheckSession.getSession(request);
		model.addAttribute("my_info", service.getMyinfo(id));
		return result;

//		@GetMapping("/list")	// -->  user/list
//		public String list(Model model) {
//			log.info("list");
//			model.addAttribute("users", service.getList());
//			return "loginList";
//		}

	}

	@RequestMapping(value = "/booking_list")
	public String booking_list(Model model, HttpServletRequest request) {
		log.info("예약및방문관리 페이지");
		String id = CheckSession.getSession(request);
		log.info("id>>>>>>>" + id);
		model.addAttribute("my_info", service.getMyinfo(id));
		model.addAttribute("booking_list", service.getBookingList(id));
		return "mypage/booking_list";
	}

	@RequestMapping(value = "/favorite_list")
	public String favorite_list(Model model, HttpServletRequest request) {
		log.info("즐겨찾기 페이지");
		String id = CheckSession.getSession(request);
		log.info("id>>>>>>>" + id);
		model.addAttribute("my_info", service.getMyinfo(id));
		model.addAttribute("favorite_list", service.getFavoriteList(id));
		return "mypage/favorite_list";
	}

	@RequestMapping(value = "/myhotdeal_list")
	public String getHotdealList(Model model, HttpServletRequest request) {
		log.info("핫딜쿠폰 페이지");
		String id = CheckSession.getSession(request);
		log.info("id>>>>>>>" + id);
		model.addAttribute("my_info", service.getMyinfo(id));
		model.addAttribute("hotdeal_list", service.getHotdealList(id));
		return "mypage/hotdeal_list";
	}

	@GetMapping(value = "/myhotdeal_sort")
	public String myhotdeal_sort(Model model, HttpServletRequest request) {
		log.info("핫딜쿠폰 다시 페이지");
		String id = CheckSession.getSession(request);
		log.info("id>>>>>>>" + id);
		web.init();
		String orderby = web.getString("orderby");
		log.info(orderby);
		model.addAttribute("hotdeal_list", service.myhotdeal_sort(orderby, id));
		return "mypage/hotdeal_list";
	}

	@GetMapping(value = "/myhotdeal_cancel")
	public String myhotdeal_cancel(@RequestParam("hdsseq") int hdsseq) {
		log.info("핫딜 삭제");
		service.myhotdeal_cancel(hdsseq);
		return "redirect:myhotdeal_list";
	}

	@GetMapping(value = "/myhotdeal_use")
	public String myhotdeal_use(@RequestParam("hdsseq") int hdsseq, Model model) {
		log.info("핫딜 사용");
		service.myhotdeal_use(hdsseq);
		return "redirect:myhotdeal_list";
	}

	@GetMapping(value = "/favorite_cancel")
	public String favorite_cancel(@RequestParam("rscode") int rscode) {
		log.info("즐겨찾기 취소");
		service.favorite_cancel(rscode);
		return "redirect:favorite_list";
	}

	@GetMapping(value = "/booking_cancel")
	public String booking_cancel(@RequestParam("bkcode") int bkcode) {
		log.info("즐겨찾기 취소");
		service.booking_cancel(bkcode);
		return "redirect:booking_list";
	}

	@RequestMapping(value = "/review_list")
	public String getReviewList(Model model, HttpServletRequest request) {
		log.info("리뷰 관리 페이지");
		String id = CheckSession.getSession(request);
		log.info("id>>>>>>>" + id);
		model.addAttribute("my_info", service.getMyinfo(id));
		model.addAttribute("review_list", service.getReviewList(id));
		return "mypage/review_list";
	}

	@GetMapping(value = "/reg_review")
	public String reg_review(Model model, HttpServletRequest request) {
		log.info("리뷰등록 페이지");
		// id, rscode, bkcode 넘겨받음
		String id = CheckSession.getSession(request);
		web.init();
		model.addAttribute("id", id);
		model.addAttribute("rscode", web.getString("rscode"));
		model.addAttribute("bkcode", web.getString("bkcode"));
		return "mypage/reg_review";
	}

	@PostMapping("/submit_review")
	public String submit_review(HttpServletRequest request, ReviewVO reviewVO, MultipartHttpServletRequest multirequest,
			@RequestParam("file") MultipartFile[] file) {
		log.info("리뷰등록 처리");
		
		service.insertReview(reviewVO);
		double rvscore = reviewVO.getRvscore();
		log.info(rvscore);
		int rvcode = reviewVO.getRvcode(); // Mybatis에 설정한 SelectKey의 property값이 shopVO에 저장된것을 가져옴

		log.info("rvcode >> " + rvcode);

		service.uploadFile(multirequest, file, rvcode);
		// review 테이블에 저장후 result rvcode 받아옴
		// id, rscode, bkcode 넘겨받음
		// 1) service review 등록 mapper 에서 등록하면서 select key max(rvcode) 최신 rvcode 받아옴
		// reviewpic 에 rvcode를 넣어야 함

		// 2) restpic 서비스 등록 파일 업로드 처리

		// rvcode를 reviewpic 에 전달

		return "redirect:review_list";
	}

	@RequestMapping("/update")
	public String update(Model model, HttpServletRequest request) {

		String id = CheckSession.getSession(request);
		model.addAttribute("my_info", service.getMyinfo(id));
		return "/mypage/update_user";
	}

	@PostMapping("/updateuser")
	public String update_user(HttpServletRequest request, UsersVO usersVO, MultipartHttpServletRequest multirequest,
			@RequestParam("file") MultipartFile file, RedirectAttributes rttr) throws Exception {
		log.info("회원정보 수정 처리");

		service.update_user(usersVO);

		String id = usersVO.getId(); // Mybatis에 설정한 SelectKey의 property값이 shopVO에 저장된것을 가져옴

		log.info("id  >> " + id);
		
		log.info("getPhoto:" + file.isEmpty());
		//if(usersVO.getPhoto() != null) {
		
		if(!file.isEmpty()) {
        	log.info("파일업로드 실행");
        	service.uploadUserPic(multirequest, file, id);
		}
		rttr.addFlashAttribute("result", "회원 정보 수정 완료!");
		// review 테이블에 저장후 result rvcode 받아옴
		// id, rscode, bkcode 넘겨받음
		// 1) service review 등록 mapper 에서 등록하면서 select key max(rvcode) 최신 rvcode 받아옴
		// reviewpic 에 rvcode를 넣어야 함

		// 2) restpic 서비스 등록 파일 업로드 처리

		// rvcode를 reviewpic 에 전달

		return "redirect:/mypage/mypage";
	}

	@GetMapping(value = "/show_review")
	public String show_review(Model model, HttpServletRequest request) {
		log.info("리뷰보기 모달");
		web.init();
		String id = CheckSession.getSession(request);
		model.addAttribute("id", id);
		int rvcode = web.getInt("rvcode");
		model.addAttribute("review_list", service.getReviewList(id));
		model.addAttribute("showReview", service.showReview(rvcode));
		return "/mypage/review_list";
	}
}
