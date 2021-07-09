package com.awesome.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awesome.domain.Criteria;
import com.awesome.domain.PageMaker;
import com.awesome.domain.UsersVO;
import com.awesome.service.HotdealService;
import com.awesome.service.ShopService;
import com.awesome.util.CheckSession;
import com.awesome.util.WebHelper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class HomeController {

	/**
	 * Main & Sub Menubar URL
	 */

	ShopService service;	
	HotdealService hdservice;

	private final WebHelper web; // Web 헬퍼
	
	@GetMapping("/")
	public String home(Model model, UsersVO usersVO) {
		log.info("홈페이지이동");
		model.addAttribute("proReview_List", service.getProReviewList());
		model.addAttribute("userReview_List", service.getUserReviewList());
		model.addAttribute("hotdeal_List", service.getHotdealList());
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		log.info("로그인창이동");
		return "/user/login";
	}

	// 검색기능 추가@@@@@@@@@@@@@@@@@@@@@@@@@@김건휘
	@RequestMapping(value = "/find_shop")
	public String find_shop(Model model, Criteria cri) {
		
		web.init();
		String keyword = web.getString("keyword");
		String sort = web.getString("sort");
		
		log.info("keyword>>>>>>>>>" + keyword);
		model.addAttribute("keyword", keyword);
		model.addAttribute("sort", sort);

		if (sort == null || sort == "") {
			sort = "rscode";
		}

		// ======== 페이징 처리부분
		// ==================================================================
		cri.setSearch(keyword);
		cri.setSort(sort);
		cri.setPerPageNum(4); // 페이지당 출력갯수
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 식당코드, 검색어
		int countShopList = service.countShopList(keyword);
		log.info("countShopList" + countShopList);
		if (countShopList == 0) { // 검색결과가 없으면
			model.addAttribute("result", "검색 결과가 없습니다");
			countShopList = 10;
		}
		pageMaker.setTotalCount(countShopList);
		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분
		// ==================================================================

		model.addAttribute("shopSearchList", service.searchinfo(cri));

		log.info("검색결과페이지이동");
		return "/shop/find_shop";

	}

	@RequestMapping(value = "/shop_list")
	public String shop_list() {
		log.info("식당목록(서브)페이지이동");
		return "/shop/shop_list";
	}

	@RequestMapping(value = "/detail_shop")
	public String detail_shop() {
		log.info("식당 상세보기 이동");
		return "/shop/detail_shop";
	}

	@RequestMapping(value = "/mypage")
	public String mypage(HttpSession session) {
		log.info("마이페이지 이동");
		int check = CheckSession.checkUserSession(session);
		log.info("check>>>>>>>"+check);
		String result = (check == 1) ? "/mypage/mypage" : "/user/login";
		return result;
	}

	@GetMapping(value = "/hotdeal_list")
	public String hotdeal_sort(Model model, Criteria cri) {
		log.info("핫딜쿠폰 정렬");
		web.init();

		String hdorderby = web.getString("hdorderby");
		cri.setSort(hdorderby);
		model.addAttribute("hdorderby", hdorderby);
		log.info("hdorderby" + hdorderby);

		PageMaker pageMaker = new PageMaker();
		cri.setPerPageNum(6);
		pageMaker.setCri(cri);

		// 전체 게시글 수 조회 param : 식당코드, 검색어
		int countHotdealList = hdservice.countHotdealList();
		if (countHotdealList == 0)
			countHotdealList = 100;
		pageMaker.setTotalCount(countHotdealList);

		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분
		// ==================================================================
		model.addAttribute("hotdeal_List", hdservice.getHotdealPagingList(cri));
		return "/hotdeal/hotdeal_list";
	}


}
