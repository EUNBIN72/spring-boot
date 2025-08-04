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
	
	@GetMapping("list")
	public String list(Model model) throws Exception{
		List<ProductVO> list = productService.list();
		
		model.addAttribute("list", list);
		return "products/list";
		
	}
	
	@GetMapping("detail")
	public void detail(ProductVO productVO, Model model) throws Exception{
		model.addAttribute("vo", productService.detail(productVO));
	}
	
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
	
	@GetMapping("update")
	public String update() throws Exception{
		return "products/product_form";
	}
}
