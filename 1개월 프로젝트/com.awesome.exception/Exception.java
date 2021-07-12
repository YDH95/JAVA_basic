package com.awesome.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class Exception {
	
	@RequestMapping("/throwable")
	public String throwable(HttpServletRequest request, Model model) {
		log.info("throwable");
		pageErrorLog(request);
		model.addAttribute("msg", "예외가 발생하였습니다");
		return "error/error";	// 에러페이지
	}
	
	@RequestMapping("/exception")
	public String exception(HttpServletRequest request, Model model) {
		log.info("Exception!!");
		pageErrorLog(request);
		model.addAttribute("msg", "예외가 발생하였습니다");
		return "error/error";	
	}
	
	@RequestMapping("/400")
	public String pageError400(HttpServletRequest request, Model model) {
		log.info("page error code 400");
		pageErrorLog(request);
		model.addAttribute("msg", "잘못된 요청입니다");
		return "error/error";	
	}
	
	@RequestMapping("/403")
	public String pageError403(HttpServletRequest request, Model model) {
		log.info("page error code 403");
		pageErrorLog(request);
		model.addAttribute("msg", "접근이 금지되었습니다");
		return "error/error";	
	}
	
	@RequestMapping("/404")
	public String pageError404(HttpServletRequest request, Model model) {
		log.info("page error code 404");
		pageErrorLog(request);
		model.addAttribute("msg", "요청된 페이지가 존재하지 않습니다");
		return "error/error";	
	}
	
	@RequestMapping("/405")
	public String pageError405(HttpServletRequest request, Model model) {
		log.info("page error code 405");
		pageErrorLog(request);
		model.addAttribute("msg", "요청된 메소드가 허용되지 않습니다");
		return "error/error";	
	}
	
	@RequestMapping("/500")
	public String pageError500(HttpServletRequest request, Model model) {
		log.info("page error code 500");
		pageErrorLog(request);
		model.addAttribute("msg", "서버에 오류가 발생하였습니다");
		return "error/error";	
	}
	
	@RequestMapping("/503")
	public String pageError503(HttpServletRequest request, Model model) {
		log.info("page error code 503");
		pageErrorLog(request);
		model.addAttribute("msg", "서비스를 사용할 수 없습니다");
		return "error/error";	
	}
	
	private void pageErrorLog(HttpServletRequest request) {
		log.info("status_code : " + request.getAttribute("javax.servlet.error.status_code"));
		log.info("exception_type : " + request.getAttribute("javax.servlet.error.exception_type"));
		log.info("message : " + request.getAttribute("javax.servlet.error.message"));
		log.info("request_uri : " + request.getAttribute("javax.servlet.error.request_uri"));
		log.info("exception : " + request.getAttribute("javax.servlet.error.exception"));
		log.info("servlet_name : " + request.getAttribute("javax.servlet.error.servlet_name"));
	}
	
}
