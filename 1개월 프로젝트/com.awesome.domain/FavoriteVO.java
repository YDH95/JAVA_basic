package com.awesome.domain;

import lombok.Data;

@Data
public class FavoriteVO {
	private String id;
	private int rscode;
	
//	ShopVO
	private String rsname;
	private String rsaddr;
	private String rstell;
	
//	ShopPicVO
	private String rsimg;
}
