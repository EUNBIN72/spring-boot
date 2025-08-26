package com.winter.app.members;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.members.update.UpdateGroup;
import com.winter.app.members.validation.AddGroup;
import com.winter.app.products.ProductVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("join")
	public void join(MemberVO memberVO) throws Exception {
		
	}
	
	// AddGroup으로 지정된 클래스만 사용하겠다
	// @Validated를 쓰고 검증 그룹을 지정하지 않으면 그룹이 없는 것들만 검증함
	// BindingResult : 매개변수 위치 중요 / MVC에서 폼 입력값 검증(Validation) 결과를 담는 객체
	// 반드시 @Validated 또는 @Valid가 붙은 객체 바로 뒤에 선언해야 됨 -> 해당 객체의 결과가 bindingResult에 바인딩 됨
	
	// config사용해서 여기서 사용 안함
//	@PostMapping("join")
//	public String join(@Validated({AddGroup.class, UpdateGroup.class}) MemberVO memberVO, BindingResult bindingResult, MultipartFile profile) throws Exception {
//		
//		boolean check = memberService.hasMemberError(memberVO, bindingResult);
//		
//		// 에러가 있다면
//		if(check) {
//			return "member/join";
//		}
//		
//		int result = memberService.join(memberVO, profile);
//		
//		return "redirect:/";
//	}
	
	@GetMapping("update")
	public String update(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		model.addAttribute("memberVO", memberVO);
		return "member/memberUpdate";
	}
	
	@PostMapping("update")
	// @Valid와 @Validated 두 어노테이션 비교
	// UpdateGroup으로 지정된 클래스만 사용하겠다
	public String update(@Validated(UpdateGroup.class) MemberVO memberVO,BindingResult bindingResult, MultipartFile profile, HttpSession session) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "member/memberUpdate";
		}
		
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		memberVO.setUsername(member.getUsername());
		
		int result = memberService.update(memberVO);
		
		if(result > 0) {
			memberVO.setPassword(member.getPassword());
			memberVO = memberService.login(memberVO);
			session.setAttribute("member", memberVO);
		}
		
		return "redirect:./detail";
	}

	@GetMapping("login")
	public String login(Principal principal) throws Exception{
		if(principal != null) {
			return "redirect:/";
		}
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.login(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		
		return "redirect:/";
	}

	// security가 대신함
	
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
	// profile
	@GetMapping("detail")
	public void detail() throws Exception{
		
	}
	
	@PostMapping("cartAdd")
	@ResponseBody
	public int cartAdd(Long productNum, HttpSession session) throws Exception {
		// 세션에 저장된 MemberVO 객체를 가져옴
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("productNum", productNum);
		
		return memberService.cartAdd(map);
				
	}
	
	@GetMapping("cartList")
	public void cartList(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		List<ProductVO> list = memberService.cartList(memberVO);
		
		model.addAttribute("list", list);
		
	}
	
	@PostMapping("cartDelete")
	public String cartDelete(Long [] productNum, HttpSession session)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		int result = memberService.cartDelete(productNum, memberVO);
		
		return "redirect:./cartList";
	}
	

	 
	
}
