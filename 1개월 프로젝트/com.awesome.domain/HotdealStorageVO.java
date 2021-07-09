package com.awesome.domain;

import lombok.Data;

@Data
public class HotdealStorageVO {
	private int hdsseq;
	private String id;
	private int hdcode;
	private String hdsregdate;
	private String hdsusedate;
	
//	HotdealVO
	private String hdname;
	private String hdend;
	private int hddiscount;
	private String hdcomment;
	private String hdimg;
	
//	ShopVO
	private String rsname;
	private String rsaddr;
	private String rstell;
	
//	ShopPicVO
	private String rsimg;
}
