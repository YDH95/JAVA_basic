package com.awesome.domain;

import lombok.Data;

@Data
public class BookingVO {
	private int bkcode;
	private String id;
	private int rscode;
	private String bkdate;
	private int bknum;
	private String bkrequest;
	private int bknoshow;
	private int bkapprove;
	private int bkcomplete;

	// Join 결과값 저장을 위한 멤버변수추가
	private String name; 		// 예약자이름
	private String phonenum; // 예약자연락처

	// mypage 예약 보여주기 페이지에서 사용 ShopVO
	private String rsname;
	private int rsmark;

	// reviewVO
	private int rvcode;
	private String rvtitle;
	private String rvcontent;
	private double rvscore;
	private int rvcnt;
	private String rvdate;
}
