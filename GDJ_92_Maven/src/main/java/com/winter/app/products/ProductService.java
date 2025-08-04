package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	// list
	public List<ProductVO> list() throws Exception {
		return productDAO.list();
	}
	
	// detail
	public ProductVO detail(ProductVO productVO) throws Exception {
		return productDAO.detail(productVO);
	}
	
	// insert
	public int insert(ProductVO productVO) throws Exception {
		return productDAO.insert(productVO);
	}
	
	// update
	public int update(ProductVO productVO) throws Exception {
		return productDAO.update(productVO);
	}
	
	// delete
	public int delete(ProductVO productVO) throws Exception {
		return productDAO.delete(productVO);
	}
}
