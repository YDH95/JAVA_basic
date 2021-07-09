package com.awesome.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awesome.domain.HotdealStorageVO;
import com.awesome.service.HotdealService;
import com.awesome.util.CheckSession;
import com.awesome.util.WebHelper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/hotdeal")
@AllArgsConstructor
public class HotdealController {

	HotdealService service;
	private WebHelper web;

	// 핫딜목록

	@GetMapping(value = "/hotdeal_get")
	public String hotdeal_get(@RequestParam("hdcode") int hdcode,
			HotdealStorageVO hotdealstorageVO, RedirectAttributes rttr, HttpServletRequest request) {
		log.info("핫딜 다운로드");
		String id = CheckSession.getSession(request);
		log.info(id + hdcode);
		hotdealstorageVO.setId(id);
		hotdealstorageVO.setHdcode(hdcode);
		int check = service.checkhds(id, hdcode);
		log.info(check);
		if (id == null) {
			rttr.addFlashAttribute("result", "로그인을 하세요!");
			return "redirect:/hotdeal_list";
		}
		if (check == 0) {
			service.hotdeal_get(hotdealstorageVO);
			rttr.addFlashAttribute("result", "핫딜 쿠폰 다운로드 완료되었습니다!");
			return "redirect:/hotdeal_list";
		} else {
			rttr.addFlashAttribute("result", "이미 보유 중입니다!");
			return "redirect:/hotdeal_list";
		}
	}
	// 핫딜검색

}
