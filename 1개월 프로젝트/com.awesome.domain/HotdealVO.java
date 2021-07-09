package com.awesome.domain;

import lombok.Data;

@Data
public class HotdealVO {
	private int hdcode;
	private int rscode;
	private String hdname;
	private int hdcnt;
	private String hdstart;
	private String hdend;
	private String hddiscount;
	private String hdcomment;
	private String hdimg;

	//Join 결과물 저장을 위한 변수
	private String rsname;	// 식당이름
	private int offercount;	// 핫딜쿠폰 발행수
	private int hdcount;	// 핫딜쿠폰 발행수(상우)
	
	private String nowDate; // 현재날짜
	
	private String search;	//검색 조건
	
	
}
