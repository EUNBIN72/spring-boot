package com.winter.app.products;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO {
	
	// 컬럼명을 카멜케이스로
	private Long productNum;
	private String productName;
	private String productContents;
	private LocalDate productDate;
	private Double productRate;
	private Long kindNum;
	
	// 1:1
	// 단방향
	private ProductKindVO productKindVO;

}
