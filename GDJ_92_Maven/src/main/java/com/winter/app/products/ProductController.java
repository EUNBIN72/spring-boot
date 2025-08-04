package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/products/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// "HTTP GET 방식의 요청"을 처리
	// String 타입인 이유 : JSP와 같은 View 페이지의 경로(논리적 뷰 이름)를 반환
	@GetMapping("list")
	// Model 객체 :  String 컨트롤러에서 JSP(뷰)로 데이터를 전달할 때 사용(스프링에서 제공)
	public String list(Model model) throws Exception{
		
		// 1. 서비스에서 리스트를 조회(DB)
		List<ProductVO> list = productService.list();
		
		// 2. 조회한 데이터를 Model에 담아서 JSP로 전달
		model.addAttribute("list", list);
		
		// 3. /WEB-INF/views/products/list.jsp 파일로 이동
		return "products/list";
		
	}
	
	@GetMapping("detail")
	public void detail(ProductVO productVO, Model model) throws Exception{
		
		// 가지고 온 데이터를 보냄(detail 이라는 이름으로 productVO를 보냄)
		model.addAttribute("vo", productService.detail(productVO));
	}
	
	// form 태그로 이동하는 메소드
	@GetMapping("add")
	public String add() throws Exception{
		return "products/product_form";
	}
	
	@PostMapping("add")
	public String insert(ProductVO productVO, Model model) throws Exception {
		int result = productService.insert(productVO);
		
		String msg = "상품 등록 실패";
		if(result>0) {
			msg="상품 등록 성공";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", "./list");
	
		// 데이터를 가지고 리스트로 넘어감 (redirect 써줌)
		return "commons/result"; // /products.list로 redirect
	}
	
	/*
	 * @GetMapping("update") public String update(ProductVO productVO, Model model)
	 * throws Exception{ ProductVO vo = productService.detail(productVO);
	 * model.addAttribute("vo", vo);
	 * 
	 * return "products/product_form"; }
	 * 
	 * @PostMapping("update") public String update(ProductVO productVO, Model
	 * moddel) throws Exception { int result = productService.update(productVO);
	 * 
	 * String msg = "수정 실패";
	 * 
	 * if(result > 0) { msg = "수정 성공"; }
	 * 
	 * String url = "./detail?productNum=" + productVO.getProductNum(); model.add
	 * 
	 * return "commons/result";
	 * 
	 * 
	 * }
	 */
	
	
	@PostMapping("delete")
	public String delete(ProductVO productVO, Model model) throws Exception {
		int result = productService.delete(productVO);
		
		String msg = "삭제 실패";
		
		if(result > 0) {
			msg = "삭제 성공";
		}
		
		String url = "./list";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
		
		
	}
	
	
	
}
