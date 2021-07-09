package com.awesome.domain;

import lombok.Data;

@Data
public class ReviewVO {
	private int rvcode; // 리뷰코드
	private String id; // 사용자아이디
	private int rscode; // 레스토랑 코드
	private int bkcode; // 예약 코드
	private String rvtitle; // 리뷰 제목
	private String rvcontent; // 댓글내용
	private double rvscore; // 리뷰 평점
	private double prorvscore;
	private int rvcnt; // 리뷰 조회수
	private String rvdate; // 댓글등록일
	private String rvimg;
	private int rsmark;
	

	// 조인 목적 - 최성은
	private int replycount; // 댓글수

	// 조인 목적 - 건휘
	private double avgrvscore; 	// 평균평점
	private double avgprorvscore; //프로평균평점
	private String photo; // User 테이블의 자기 사진
	private int rpcode; // 댓글코드
	private String rpcontent; // 댓글내용
	private String rpdate; // 댓글등록일
	private String rpid;

	// 상우
	// UsersVO
	private String role;
	// ShopVO
	private String rsname;
	// ShopPicVO
	private String rsimg;
	
	//동환
	private String nickname;    // 유저 닉네임

	
	
}
