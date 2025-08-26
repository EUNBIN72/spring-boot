package com.winter.app.members;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.members.update.UpdateGroup;
import com.winter.app.members.validation.AddGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO implements UserDetails {	
	
	// (message  = "") : error 메세지를 직접 정해줄 수 있음
	// (groups = {} ) : 사용할 그룹에서만 사용(배열이면 {}안에, 하나면 그냥 작성)
	@NotBlank(message = "ID는 필수", groups = AddGroup.class)
	private String username;
	@NotBlank
	@Size(min = 6, max = 8, groups = AddGroup.class)
	private String password;
	
	// 두번째 입력한 비밀번호가 첫번째 입력한 비밀번호와 같은지 검사
	private String passwordCheck;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	@Email(groups = {AddGroup.class, UpdateGroup.class})
	private String email;
//	@Pattern(regexp="")
	private String phone;
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	@Past(groups = {AddGroup.class, UpdateGroup.class})  // 과거의 날짜만 가능(반대로 미래의 날짜는 @Future - 예: 예약날짜 등등)
	private LocalDate birth;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	// 1대1
	private ProfileVO profileVO;
	
	private List<RoleVO> roleVOs;


	// UserDetails 인터페이스를 구현할 때 쓰이는 권한(Authorities)반환기능
	// 로그인한 사용자가 가지고 있는 권한 (ROLE_XXX)을 GrantedAuthority 형태로 변환해서 Spring Security에게 넘겨줌
	// Spring Security는 이 정보를 기반으로 인가(Autorization), "어떤 URL이나 기능에 접근할 수 있는가"를 판단
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		
		for (RoleVO roleVO : roleVOs) {
			list.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return list;
	} 

	
}
