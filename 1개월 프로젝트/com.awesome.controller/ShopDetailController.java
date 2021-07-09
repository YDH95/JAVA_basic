package com.awesome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.BookingVO;
import com.awesome.domain.Criteria;
import com.awesome.domain.PageMaker;
import com.awesome.domain.ReplyVO;
import com.awesome.service.ShopDetailService;
import com.awesome.util.CheckSession;
import com.awesome.util.WebHelper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class ShopDetailController {

	ShopDetailService service;
	WebHelper web;

	@GetMapping("/detail_shop")
	public String shopdetail(Model model, HttpSession session, @RequestParam("rscode") int rscode, 
							  @RequestParam(value="rvcode", defaultValue="0", required=false) int rvcode, Criteria cri) {

		web.init();
		String sort = web.getString("sort");
		//int rvcode = web.getInt("rvcode");	
		String id = (String) session.getAttribute("id");

		model.addAttribute("rscode", rscode);
		model.addAttribute("id", id);
		if (sort != null) {
			model.addAttribute("sort", sort);
		}
		
		 // ======== 페이징 처리부분  ==================================================================
		  cri.setRvcode(rvcode);
		  cri.setPerPageNum(2); //페이지당 출력갯수
		  log.info("출력수: " + cri.getPerPageNum());
		  		
		  PageMaker pageMaker = new PageMaker(); // 화면 페이지 출력 객체 
		  pageMaker.setCri(cri); // 전체 게시글 수 조회
		  //param : 식당코드, 검색어 
		  int countReplyList = service.countReplyList(rvcode);
		  log.info("countReplyList" + countReplyList); 
		  if (countReplyList == 0) {			  //검색결과가 없으면 
 			  model.addAttribute("result", "검색 결과가 없습니다"); 
		  }
		  pageMaker.setTotalCount(countReplyList); 
		  model.addAttribute("pageMaker",pageMaker); 
		  log.info("criteria >>" + cri.toString() + "pageMaker >>" + pageMaker.toString()); 
		// ======== 페이징 처리부분  ==================================================================
		  
		  
		model.addAttribute("rvcode", rvcode);
		// 리뷰
		model.addAttribute("reviewinfo", service.getList(rscode, sort));
		// 평점
		model.addAttribute("avgscore", service.getScore(rscode));
		// 조회수
		model.addAttribute("cntreview", service.getReviewCount(rscode));
		// 즐겨찾기 수
		model.addAttribute("countingFavorite", service.countingFavorite(rscode));
		// 식당 정보
		model.addAttribute("shopinfo", service.get(rscode));
		// 메뉴 정보
		model.addAttribute("menuinfo", service.getmenu(rscode));

		model.addAttribute("getFavorite", service.getFavorite(rscode, id));
		// 댓글 가져오기
		model.addAttribute("ReplyDetail", service.ReplyDetail(cri));
		// 리뷰 한개만 가져오기
		model.addAttribute("ReviewOne", service.ReviewOne(rvcode));
		// 리뷰 사진 가져오기
		model.addAttribute("ReviewPic", service.Reviewpic(rvcode));
		return "/shop/detail_shop";
	}

	// 리뷰모달창 출력
	/*
	 * @RequestMapping(value = "/reviewdetail", method = { RequestMethod.GET,
	 * RequestMethod.POST}) public String review(Model model, int rvcode,
	 * HttpSession session) { String id= (String) session.getAttribute("id");
	 * model.addAttribute("id", id); model.addAttribute("rvcode", rvcode);
	 * model.addAttribute("ReviewDetail", service.ReviewDetail(rvcode));
	 * log.info("리뷰를 데리고옴!"); model.addAttribute("ReplyDetail",
	 * service.ReplyDetail(rvcode)); log.info("댓글들을 데리고옴!"); return
	 * "/shop/detail_review"; }
	 */
	// 댓글 삽입
	@PostMapping(value = "/insertreply")
	public String reply(ReplyVO replyVO, RedirectAttributes rttr) {
		web.init();
		String code = web.getString("rvcode");
		String rscode = web.getString("rscode");
		log.info("댓글들을 삽입하는중!");
		service.ReplyInsert(replyVO);
		rttr.addFlashAttribute("result", replyVO.getRpcode());
		return "redirect:/detail_shop?rvcode=" + code + "&rscode=" + rscode + "&sort=''";
	}

	// 댓글 삭제
	@GetMapping(value = "/deletereply")
	public String remove(int rpcode, int rvcode, RedirectAttributes rttr) {
		web.init();
		String code = web.getString("rvcode");
		String rscode = web.getString("rscode");
		log.info("댓글 삭제하는 중");
		if (service.DeleteReply(rpcode)) {
			rttr.addFlashAttribute("result", "삭제완료메시지");
		}
		return "redirect:/detail_shop?rvcode=" + code + "&rscode=" + rscode + "&sort=''";
	}

	// 댓글 수정
	@GetMapping(value = "/updatereply")
	public String edit(@RequestParam("rpcode") int rpcode, @RequestParam("rpcontent") String rpcontent,
			RedirectAttributes rttr) {
		web.init();
		String code = web.getString("rvcode");
		String rscode = web.getString("rscode");
		log.info("댓글 수정......");
		service.UpdateReply(rpcode, rpcontent);
		// rttr.addFlashAttribute("result", "수정완료메시지");

		return "redirect:/detail_shop?rvcode=" + code + "&rscode=" + rscode + "&sort=''";
	}

	// 즐겨찾기 삭제
	@GetMapping(value = "/deletefav")
	public String DeleteFav() {
		web.init();
		String id = web.getString("id");
		int rscode = web.getInt("rscode");
		log.info("id>>" + id + "rscode:" + rscode);
		service.DeleteFav(id, rscode);
		return "redirect:/detail_shop?rvcode=0&sort=&rscode=" + rscode;
	}

	@GetMapping(value = "/insertfav")
	public String InsertFav() {
		web.init();
		String id = web.getString("id");
		int rscode = web.getInt("rscode");
		log.info("id>>" + id + "rscode:" + rscode);
		service.InsertFav(id, rscode);
		return "redirect:/detail_shop?rvcode=0&sort=&rscode=" + rscode;
	}

	//예약처리
	@PostMapping(value = "/booking")
	public String Booking(BookingVO bookingVO, RedirectAttributes rttr, HttpServletRequest request) {
		web.init();
		int code = web.getInt("rscode");
		log.info("rscode@@@@@@@@@@@@@@" + code);
		if(CheckSession.getSession(request) == null) {
			rttr.addFlashAttribute("msg", "로그인이 필요한 서비스입니다");
			return "redirect:/detail_shop?rscode="+code;
		}
		
		service.Booking(bookingVO);
		return "redirect:/detail_shop?rvcode=0&sort=&rscode=" + code;
	}

	@GetMapping(value = "/upcnt")
	public String Uprscnt() {
		web.init();
		int rscode = web.getInt("rscode");
		service.Uprscnt(rscode);
		return "redirect:/detail_shop?rscode=" + rscode;
	}
}
