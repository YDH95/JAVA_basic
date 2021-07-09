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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.Criteria;
import com.awesome.domain.HotdealVO;
import com.awesome.domain.PageMaker;
import com.awesome.domain.ReviewVO;
import com.awesome.domain.ShopVO;
import com.awesome.service.AdminService;
import com.awesome.util.CheckSession;
import com.awesome.util.WebHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping(value = "/admin", name = "관리자")
@Log4j
@Controller
@RequiredArgsConstructor
public class AdminController {

	private final WebHelper web; // 헬퍼
	private final AdminService service;

	@GetMapping("/home.do")
	public String admin(Model model, String name, HttpSession session) {
		log.info("시스템 관리자페이지 이동");
		//////  ADMIN, PRO 권한 인증 체크 /////////////////////////
		int check = CheckSession.checkAdminSession(session);
		log.info("checkSession Result >>>>>>>" + check);

		if(check == 0) 	return "redirect:/login";
		
		model.addAttribute("usersinfo", service.mainGetUsersList());
		model.addAttribute("shoplist", service.mainGetShopList());
		model.addAttribute("reviewlist", service.mainGetReviewList());

		return "admin";
	}

	@GetMapping("/user_list.do")
	public String userList(Model model, Criteria cri, HttpSession session) {
		log.info("회원관리페이지");

		// 세션체크후 예외처리
		int check = CheckSession.checkAdminSession(session);
		if (check == 0)	return "redirect:/login";

		web.init();
		String search = web.getString("search");
		model.addAttribute("search", search);

		// ======== 페이징 처리부분 ========================================================
		cri.setSearch(search); // criteria객체에 검색어 값 저장
		cri.setPerPageNum(10); // 페이지당 출력할 갯수 저장
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 검색어
		int countUsersList = service.countUsersList(search); // 회원목록 전체게시글수 가져옴
		if (countUsersList == 0)
			countUsersList = 100; // 가져온 갯수가 없을경우 에러를 방지하기 위해 임의로 100으로 할당
		pageMaker.setTotalCount(countUsersList); // pageMaker객체에 totalcount 멤버변수에 가져온 전체수를 넣어줌

		model.addAttribute("pageMaker", pageMaker);
		// log.info("criteria >>" + cri.toString() + "pageMaker >>" +
		// pageMaker.toString());
		// ======== 페이징 처리부분 ================================================

		model.addAttribute("usersinfo", service.getUsersList(cri));

		return "user/user_list";
	}

	@GetMapping("/shop_list.do")
	public String shopList(Model model, Criteria cri, HttpSession session) {
		log.info("식당관리페이지");

		// 세션체크후 예외처리
		int check = CheckSession.checkAdminSession(session);
		if (check == 0)	return "redirect:/login";

		web.init();
		String search = web.getString("search");
		
		model.addAttribute("search", search);
		
		// ======== 페이징 처리부분   ==================================================================
		cri.setSearch(search); // criteria객체에 검색어 값 저장
		cri.setPerPageNum(10); // 페이지당 출력할 갯수 저장
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 검색어
		int countShopList = service.countShopList(search); // 회원목록 전체게시글수 가져옴
		if (countShopList == 0)
			countShopList = 100; // 가져온 갯수가 없을경우 에러를 방지하기 위해 임의로 100으로 할당
		pageMaker.setTotalCount(countShopList); // pageMaker객체에 totalcount 멤버변수에 가져온 전체수를 넣어줌

		model.addAttribute("pageMaker", pageMaker);
		// log.info("criteria >>" + cri.toString() + "pageMaker >>" +
		// pageMaker.toString());
		// ======== 페이징 처리부분
		// ==================================================================

		model.addAttribute("shoplist", service.getShopList(cri));
		return "shop/shop_list";
	}

	@GetMapping("/proReview_list.do")
	public String proReviewList(Model model, Criteria cri, HttpSession session) {
		log.info("리뷰관리페이지");
		
		web.init();
		String search = web.getString("search");
		model.addAttribute("search", search);

		// ======== 페이징 처리부분
		// ==================================================================
		cri.setSearch(search); // criteria객체에 검색어 값 저장
		cri.setPerPageNum(6); // 페이지당 출력할 갯수 저장
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 검색어
		int countUsersList = service.countUsersList(search); // 회원목록 전체게시글수 가져옴
		if (countUsersList == 0)
			countUsersList = 100; // 가져온 갯수가 없을경우 에러를 방지하기 위해 임의로 100으로 할당
		pageMaker.setTotalCount(countUsersList); // pageMaker객체에 totalcount 멤버변수에 가져온 전체수를 넣어줌

		model.addAttribute("pageMaker", pageMaker);
		// log.info("criteria >>" + cri.toString() + "pageMaker >>" +
		// pageMaker.toString());
		// ======== 페이징 처리부분
		// ==================================================================

		model.addAttribute("reviewlist", service.getReviewList(cri));
		return "review/proReview_list";

	}

	// 리뷰등록페이지이동처리
	@GetMapping("/proreview.do")
	public String proReview() {
		log.info("리뷰등록페이지 이동");
		return "/review/reg_proreview";
	}

	// 리뷰등록시 식당검색
	@GetMapping("/getShop.do")
	public String searchShop(Model model, @RequestParam("search") String search) {
		if (search != null || search != "") {
			model.addAttribute("search", "식당명을 입력하세요!");
		}

		ShopVO shopVO = service.getShop(search); // 식당코드 조회
		if (shopVO == null) {
			model.addAttribute("search", "식당명이 없습니다!");
			return "/review/reg_proreview";
		}
		int rscode = shopVO.getRscode();
		String rsname = shopVO.getRsname();
		model.addAttribute("rscode", rscode);
		model.addAttribute("rsname", rsname);

		return "/review/reg_proreview";
	}

	@PostMapping("/reg_proreview.do")
	public String reg_proreview(HttpServletRequest request, ReviewVO reviewVO, MultipartHttpServletRequest multirequest,
			@RequestParam("file") MultipartFile[] file) {
		log.info("Pro리뷰등록처리");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		reviewVO.setId(id);

		// 1) 리뷰내용 review 테이블에 저장
		service.insertProReview(reviewVO); // review 먼저 저장하고 다음으로

		int rvcode = reviewVO.getRvcode(); // reviewpic 테이블에 들어갈 rvcode를 저장하기 위해 최근등록된 RVCODE가져옴
		log.info("prorvcode>>>>>>>>>" + rvcode);

		// 2) 리뷰사진 reviewpic테이블에 저장
		service.uploadReviewFile(multirequest, file, rvcode);
		// review 테이블에 저장후 result rvcode 받아옴
		// id, rscode, bkcode 넘겨받음
		// 1) service review 등록 mapper 에서 등록하면서 select key max(rvcode) 최신 rvcode 받아옴
		// reviewpic 에 rvcode를 넣어야 함
		// 2) restpic 서비스 등록 파일 업로드 처리
		// rvcode를 reviewpic 에 전달

		return "redirect:/admin/proReview_list.do";
	}
	
	/*---------------   핫딜 관리 시작 -----------------------------------------------------*/
	// 등록페이지 이동
//	@GetMapping(value= "/reg_hotdeal.do", name = "핫딜 등록 페이지 이동")
//	public String reg_hotdeal() {
//		log.info("핫딜등록페이지이동");
//		return "hotdeal/reg_hotdeal";
//	}

	// 핫딜 등록에 검색처리
	@GetMapping(value = "/reg_hotdeal.do", name = "핫딜 등록 페이지")
	public String reg_hotdeal(Model model) {

		log.info("0");

		web.init(); // WebHelper 초기화 root-context.xml에서 bean 객체로 미리 설정해놓음
		String search = web.getString("search"); // getString 메서드를 통해 브라우저 입력 파라미터 가져오기
	
		/* model.addAttribute("search", search); */

		if (search != null && search != "") {
			log.info("왔다시와"+search);
			ShopVO shopVO = service.getShopInfo(search);
			if (shopVO != null) {
				model.addAttribute("rscode", shopVO.getRscode()); // 서비스클래스에서 데이타 받아옴
			}
		}

		return "hotdeal/reg_hotdeal";
	}
	
	
	
	
	// 핫딜파일업로드
	@PostMapping(value = "/reg_hotdealOk.do", name = "핫딜 등록 처리") // http://localhost:8080/hotdeal 요청이 들어오면, 관리자 admin
	public ModelAndView regHotdealAPI(MultipartHttpServletRequest multipartRequest, MultipartFile file,
			HotdealVO hotdealVO) {
		web.init(); // WebHelper 초기화

		int hdcode = service.hotdealRegist(multipartRequest, file, hotdealVO); // 핫딜 등록
		if (hdcode == 0) {
			return web.redirect(web.getRootPath() + "/admin/reg_hotdeal.do", "핫딜 등록에 실패했습니다.");
		}

		return web.redirect(web.getRootPath() + "/admin/hotdeal_list.do", null);
	} 
	
	// 핫딜목록 페이징및 검색
	@GetMapping(value = "/hotdeal_list.do", name = "핫딜 목록") // http://localhost:8080/hotdeal 요청이 들어오면, 관리자 admin
	public String hotdealList(Model model, Criteria cri) {
		log.info("핫딜목록처리");
		
		web.init();
		String search = web.getString("search");
		
		log.debug("search = " + search);
		
		model.addAttribute("search", search);

		// ======== 페이징 처리부분 ==================================================================    
		cri.setSearch(search);	// 브라우저 검색어 저장
		cri.setPerPageNum(5); 	// 페이지당 출력 갯수 저장
		PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체
		pageMaker.setCri(cri);
		// 전체 게시글 수 조회 param : 검색어
		int countHodealList = service.getCount(search); 
		if (countHodealList == 0) countHodealList = 100;  // 출력할 갯수가 없는 경우 임의로 100을 지정함
		pageMaker.setTotalCount(countHodealList);
		model.addAttribute("pageMaker", pageMaker);
		log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString());
		// ======== 페이징 처리부분 ==================================================================
		model.addAttribute("hotdeals", service.getList(cri));	
		return "hotdeal/hotdeal_list"; // VIEW URI 전송 /WEB-INF/views/test.jsp (ViewResolver설정 servlet-context.xml)
	}

	// 핫딜 상세보기 처리
	@GetMapping("/detail_hotdeal.do")
	public String detail_hotdeal(Model model, @RequestParam("hdcode") int hdcode) {
		log.info("param hdcode >>" + hdcode);
		model.addAttribute("detailhotdeal", service.detail_hotdeal(hdcode));
		return "/hotdeal/detail_hotdeal";
	}

	// 핫딜 수정페이지 이동
	@GetMapping("/update_hotdeal.do")
	public String update_hotdeal(Model model, @RequestParam("hdcode") int hdcode) {
		model.addAttribute("hotdeal", service.detail_hotdeal(hdcode));
		return "/hotdeal/update_hotdeal";
	}

	// 핫딜 수정 처리
	@PostMapping(value = "/update_hotdealOk.do", name = "핫딜 수정 처리")
	public String update_hotdealOk(HotdealVO hotdealVO, RedirectAttributes rttr) {
		service.hotdealUpdate(hotdealVO);
		rttr.addFlashAttribute("result", "수정처리가 완료되었습니다");
		
		return "redirect:/admin/hotdeal_list.do";	
	}

	
	
	/*
	 * @GetMapping(value = "/hotdeal_list.api", name = "[API] 핫딜 목록", produces =
	 * "application/json") // http://localhost:8080/hotdeal public String
	 * hotdealListAPI(HttpServletResponse response) { log.info("[API] 핫딜목록처리");
	 * 
	 * web.init(); // WebHelper 초기화 root-context.xml에서 bean 객체로 미리 설정해놓음 String
	 * search = web.getString("search"); // getString 메서드를 통해 브라우저 입력 파라미터 가져오기
	 * log.debug("search = " + search);
	 * 
	 * List<HotdealVO> hotdeals = service.getList(search);// 서비스클래스에서 데이타 받아옴
	 * 
	 * Map<String, Object> data = new HashMap<String, Object>(); data.put("rtCode",
	 * "200"); data.put("hotdeals", hotdeals);
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); try {
	 * mapper.writeValue(response.getWriter(), data); } catch
	 * (JsonGenerationException e) { log.debug(e.getLocalizedMessage());
	 * web.printJsonRt("500", "알 수 없는 에러가 발생했습니다. 관리자에게 문의바랍니다."); return null; }
	 * catch (JsonMappingException e) { log.debug(e.getLocalizedMessage());
	 * web.printJsonRt("500", "알 수 없는 에러가 발생했습니다. 관리자에게 문의바랍니다."); return null; }
	 * catch (IOException e) { log.debug(e.getLocalizedMessage());
	 * web.printJsonRt("500", "알 수 없는 에러가 발생했습니다. 관리자에게 문의바랍니다."); return null; }
	 * 
	 * return null; }
	 */
	
	/*---------------   핫딜 관리 끝 -----------------------------------------------------*/
	
	
	/*---------------   식당 관리 시작 -----------------------------------------------------*/
	
	//식당페이지 이동
	@GetMapping("/reg_shop.do")
	public String reg_shop() {
		log.info("식당등록페이지");
		return "shop/reg_shop";
	}

	//식당등록
	@PostMapping("/insert_shop.do")
	public String insert_shop(HttpServletRequest request, ShopVO shopVO, MultipartHttpServletRequest multirequest,
			@RequestParam("file") MultipartFile[] file) throws Exception {

		log.info("식당등록처리");

		// 식당정보등록처리 -> rscode 값을 가져옴
		service.insertShop(shopVO);
		int rscode = shopVO.getRscode(); // Mybatis에 설정한 SelectKey의 property값이 shopVO에 저장된것을 가져옴

		log.info("rscode >> " + rscode);

		// 식당사진 업로드 처리
		service.uploadFile(multirequest, file, rscode);

		// 메뉴등록 처리
		service.insertMenu(request, rscode);

		return "redirect:/admin/shop_list.do";
	}
	
	//핫딜 검색기능
	@GetMapping("search_hotdealShop.do")
	public String search_hotdealShop() {
		return "";
	}

	@GetMapping("/markUpdateShop")
	public String markUpdateShop(@RequestParam("rscode") int rscode) {
		log.info("식당마크처리");
		
		service.markUpdateShop(rscode);
		
		return "redirect:/admin/shop_list.do";
	}
			
	//식당 인증마크 삭제
	@GetMapping("/markdeleteShop")
	public String markdeleteShop(@RequestParam("rscode") int rscode) {
		log.info("식당마크삭제처리");

		service.markdeleteShop(rscode);

		return "redirect:/admin/shop_list.do";
	}
	
	@GetMapping("/updateShop")
	public String updateShop(@RequestParam("rscode") int rscode) {
		log.info("식당등록처리");
		
		service.updateShop(rscode);
		
		return "redirect:/admin/shop_list.do";
	}
	
	@GetMapping("/deleteShop")
	public String deleteShop(@RequestParam("rscode") int rscode) {
		log.info("식당삭제처리");
		
		service.deleteShop(rscode);
		
		return "redirect:/admin/shop_list.do";
	}

}
