package com.awesome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.Criteria;
import com.awesome.domain.PageMaker;
import com.awesome.domain.ShopVO;
import com.awesome.service.ShopAdminService;
import com.awesome.util.WebHelper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/shopadmin")
public class ShopAdminController {

	ShopAdminService service;
	private final WebHelper web; // Web 헬퍼

	// 식당관리자 메인 화면 처리
	@GetMapping("/home.shop")
	public String shop(Model model, HttpSession session, SessionStatus sessionstatus) {
		log.info("업체 관리자페이지 이동");
		//sessionstatus.setComplete(); // 세션삭제처리
		
		log.info("session id ........................" + session.getAttribute("id"));
		log.info("session rscode ........................" + session.getAttribute("rscode"));
		int rscode;
		if (session.getAttribute("rscode") != null) {
			rscode = Integer.parseInt(session.getAttribute("rscode").toString());
			ShopVO shopVO = service.getShop(rscode);
			session.setAttribute("shopname", shopVO.getRsname());
			model.addAttribute("rsname", shopVO.getRsname());
		} else {
			model.addAttribute("resultMessage", "세션이 만료되었습니다 다시 로그인해주세요");
			return "redirect:/login";
		}

		model.addAttribute("bookingList", service.getBookingList(rscode, 3)); // 실시간 예약상황 가져오기  (식당코드, 출력갯수)
		model.addAttribute("hotdealList", service.getHotdealList(rscode, 8)); // 실시간 핫딜상황 가져오기
		model.addAttribute("reviewList", service.getReveiwList(rscode, null)); // 리뷰 관리 가져오기
		return "shopadmin";
	}

	// 핫딜목록요청 (검색 기능 포함 + paging 기능 추가)
	@GetMapping("/hotdeal_list.shop")
	public String hotdeal_list(Model model, Criteria cri) {
		log.info("핫딜관리 페이지로 이동");
		web.init(); 
		String search = web.getString("search"); 
		int rscode = web.getInt("rscode"); 
		model.addAttribute("search", search); 
		
		// ======== 페이징 처리부분 ==================================================================    
		cri.setRscode(rscode);
		cri.setSearch(search);

		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 식당코드, 검색어
		int countHotdealList = service.countHotdealList(rscode, search); 
		if (countHotdealList == 0) countHotdealList = 100;
		pageMaker.setTotalCount(countHotdealList);
		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분 ==================================================================
		
		model.addAttribute("hotdealList", service.getHotdealPagingList(cri));
		
		return "/hotdeal/hotdeal_list";
	}

	// 예약목록요청 (검색 기능  + paging 기능 추가)
	@GetMapping("/booking_list.shop")
	public String booking_list(Model model, Criteria cri, HttpSession session) {
		log.info("예약관리 페이지로 이동");

		web.init();
		String search = web.getString("search");
		
		//int rscode = web.getInt("rscode");
		int rscode = Integer.parseInt(session.getAttribute("rscode").toString());
		
		model.addAttribute("search", search);
		
		// ======== 페이징 처리부분 ==================================================================    
		cri.setRscode(rscode);
		cri.setSearch(search);

		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 식당코드, 검색어
		int countBookingList = service.countBookingList(rscode, search); 
		if (countBookingList == 0) countBookingList = 100;
		pageMaker.setTotalCount(countBookingList);
		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분 ==================================================================
		
		model.addAttribute("bookingList", service.getBookingPagingList(cri));

		return "/book/booking_list";
	}

	// 예약승인요청
	@GetMapping("/bookingApprove.shop")
	public String bookingApprove(RedirectAttributes rttr) {
		log.info("예약 승인 처리");

		web.init();
		int bkcode = web.getInt("bkcode");
		service.approveBooking(bkcode);
		rttr.addFlashAttribute("result", "예약승인이 처리되었습니다");
		return "redirect:/shopadmin/booking_list.shop";
	}

	// 예약노쇼요청
	@GetMapping("/bookingNoShow.shop")
	public String bookingNoShow(RedirectAttributes rttr) {
		log.info("예약 노쇼 처리");

		web.init();
		int bkcode = web.getInt("bkcode");
		service.noShowBooking(bkcode);
		rttr.addFlashAttribute("result", "예약 노쇼가 처리되었습니다");
		return "redirect:/shopadmin/booking_list.shop";
	}

	// 예약방문요청
	@GetMapping("/bookingComplete.shop")
	public String bookingComplete(RedirectAttributes rttr) {
		log.info("예약방문 완료처리");

		web.init();
		int bkcode = web.getInt("bkcode");
		service.completeBooking(bkcode);
		rttr.addFlashAttribute("result", "방문 확인 처리되었습니다");
		return "redirect:/shopadmin/booking_list.shop";
	}

	// 리뷰관리 목록 + 정렬기능(param:sort)
	@GetMapping("/review_list.shop")
	public String review_list(Model model, Criteria cri) {
		log.info("리뷰관리 페이지로 이동");

		web.init();
		int rscode = web.getInt("rscode");
		String sort = web.getString("sort");
		model.addAttribute("sort", sort);
		if (sort == null)	sort = "rvdate";

		// ======== 페이징 처리부분 ==================================================================    
		cri.setRscode(rscode);
		cri.setSort(sort);
		cri.setPerPageNum(3);
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 식당코드
		int countReivewList = service.countReviewList(rscode); 
		if (countReivewList == 0) countReivewList = 6;
		pageMaker.setTotalCount(countReivewList);
		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분 ==================================================================
		
		model.addAttribute("reviewList", service.getReviewPagingList(cri));
		return "/review/review_list";
	}

	@GetMapping("/shop_list.shop")
	public String shop_list(Model model, HttpSession session) {
		log.info("식당관리 페이지로 이동");
//		web.init();
//		int rscode = web.getInt("rscode");
		int rscode = Integer.parseInt(session.getAttribute("rscode").toString());
		model.addAttribute("shopInfo", service.getShop(rscode)); // 식당정보
		model.addAttribute("Menus", service.getMenu(rscode)); // 식당메뉴정보
		return "/shop/shop_list";
	}

	@GetMapping("/update_shop.shop")
	public String update_shop(Model model) {
		log.info("식당관리 수정 페이지로 이동");
		web.init();
		int rscode = web.getInt("rscode");
		model.addAttribute("shopInfo", service.getShop(rscode)); // 식당정보
		model.addAttribute("menus", service.getMenu(rscode)); // 식당메뉴정보
		model.addAttribute("shoppics", service.getShoppic(rscode)); // 식당이미지정보

		return "/shop/update_shop";
	}

	@PostMapping("/update_shop.shop")
	public String insert_shop(HttpServletRequest request, ShopVO shopVO, MultipartHttpServletRequest multirequest,
			@RequestParam("file") MultipartFile[] file) throws Exception {

		log.info("식당등록 수정 처리");

		int rscode = shopVO.getRscode();

		// 식당정보등록처리
		service.updateShop(shopVO);

		// 식당사진 업로드 처리
		MultipartFile files = shopVO.getFile();
		log.info("선택 파일>>" + files);
        if(!files.isEmpty()) {
        	log.info("파일업로드 실행");
			service.uploadFile(multirequest, file, rscode);
		}
		// 메뉴수정 처리
		service.updateMenu(request, rscode);

		return "redirect:/shopadmin/shop_list.shop";
	}

}
