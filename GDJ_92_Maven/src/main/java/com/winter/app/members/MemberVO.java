package com.winter.app.members;

import java.time.LocalDate;
import java.util.List;

import com.winter.app.members.update.UpdateGroup;
import com.winter.app.members.validation.AddGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {	
	
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
	private boolean accountNonLoked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	// 1대1
	private ProfileVO profileVO;
	
	private List<RoleVO> roleVOs; 

}
