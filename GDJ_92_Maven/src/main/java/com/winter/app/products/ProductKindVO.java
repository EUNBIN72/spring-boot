package com.winter.app.products;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.ToString;

@Getter
@Service
@ToString
public class ProductKindVO {

	private Long kindNum;
	private String kindName;
	
	// 1:N
//	private List<ProductVO> list;
}
