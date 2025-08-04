package com.winter.app.products;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	
	// 여러개라 List<> 타입으로
	// list
	public List<ProductVO> list() throws Exception;
	
	//detail
	public ProductVO detail(ProductVO productVO) throws Exception;
	
	// insert
	public int insert(ProductVO productVO) throws Exception;
	
	// update
	public int update(ProductVO productVO) throws Exception;
	 
	// delete
	public int delete(ProductVO productVO) throws Exception;
	
}
