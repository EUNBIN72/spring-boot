package com.winter.app.products;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.ToString;

@Getter
@Service
@ToString
public class ProductKindVO {
	// ProductVO에서 kindNum은 숫자 형태로 받음
	// 근데 우리가 쓰고 출력하고 싶은 건 kindNum(1, 2, 3)의 kindName(예금, 적금, 대출)이니까 VO 추가해줌
	// PK kindNum이랑 매핑해줄 kindName만 있으면 됨
	

	private Long kindNum;
	private String kindName;
	
	// 1:N
//	private List<ProductVO> list;
}
