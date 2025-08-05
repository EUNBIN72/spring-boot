package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// "Http GET 방식의 요청"을 처리
	@GetMapping("list")
	// void이지만 model and view가 리턴됨
	public void list(Model model) throws Exception{
		
		// 조회한 데이터를 Model에 담아서 JSP로 전달
		model.addAttribute("list", productService.list());
	}
	
	@GetMapping("detail")
	public void detail(ProductVO productVO, Model model) throws Exception{
		 model.addAttribute("vo", productService.detail(productVO));
	}
	
	// form 태그로 이동하는 메소드
	@GetMapping("add")
	public String add()throws Exception{
		return "products/product_form";
	}
	
	
	// ModelAndView 객체를 직접 만드는 예시
	@PostMapping("add")
	// 매개변수 : ProductVO - 상품정보를 담고 있는 VO , Model - View로 데이터를 전달할 때 사용하는 Spring의 모델 객체
	public ModelAndView add(ProductVO productVO, Model model) throws Exception{
		int result=productService.insert(productVO);
		
		String msg="상품 등록 실패";
		if(result>0) {
			msg= "상품등록 성공";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", msg);
		mv.addObject("url", "./list");
		
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", "./list");
		
		mv.setViewName("commons/result") ;
		return mv;
	}
	
	// ModelAndView를 직접 매개변수로 받는 방법 예시
	@GetMapping("update")
	public ModelAndView update(ProductVO productVO, ModelAndView mv) throws Exception{
		productVO = productService.detail(productVO);
//		model.addAttribute("vo", productVO);
		
		mv.addObject("vo", productVO);
		mv.setViewName("products/product_form");
		return mv;
	}

	@PostMapping("update")
	public String updateProcess(ProductVO productVO, Model model) throws Exception{
		int result = productService.update(productVO);
		String msg="상품 수정 실패";
		if(result>0) {
			msg= "상품 수정 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", "./detail?productNum="+productVO.getProductNum());
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(ProductVO productVO, Model model) throws Exception{
		int result = productService.delete(productVO);
		String msg="상품 삭제 실패";
		if(result>0) {
			msg= "상품 삭제 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", "./list");
		return "commons/result";
	}	
	
}