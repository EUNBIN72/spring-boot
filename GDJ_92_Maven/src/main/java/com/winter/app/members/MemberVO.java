package com.winter.app.members;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {	
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private LocalDate birth;
	private boolean accountNonExpired;
	private boolean accountNonLoked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	// 1대1
	private ProfileVO profileVO;
	
	private List<RoleVO> roleVOs; 

}
