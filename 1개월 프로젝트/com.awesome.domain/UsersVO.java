package com.awesome.domain;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
 Lombok Annotation @Data => (@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정)
*/
@Data				
public class UsersVO {
	private String id;
	private String password;
	private String name;
	private String nickname;
	private String juminnum;
	private String email;
	private String phonenum;
	private String role;
	private int leaves;
	private String photo;
	private MultipartFile file;		

}
