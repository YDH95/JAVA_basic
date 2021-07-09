package com.awesome.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data	
public class ShopVO {
	private int rscode;
	private String rsname;
	private String rstype;
	private String rsrode;
	private String rsaddr;
	private String rstell;
	private String rsprice;
	private int rsparking;
	private String rsopen;
	private String rsclose;
	private String rsoffday;
	private int rsmark;
	private int rscnt;
	private String rsupdatedate;
	private int rsleave;	// 식당탈퇴변수
	
	private MultipartFile file;		// 파일업로드 예외처리를 위한 변수 - 성은
	//조인목적 - 건휘
	private double rvscore;
	private double prorvscore;
}
