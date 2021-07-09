package com.awesome.domain;

import lombok.Data;

@Data
public class ReplyVO {
	private String rpcode;			//댓글코드
	private int rvcode;			//리뷰코드
	private String id;			//사용자아이디
	private String rpcontent; 	//댓글내용
	private String rpdate;		//댓글등록일
	private String photo;
	/* private String rpid; */
}
