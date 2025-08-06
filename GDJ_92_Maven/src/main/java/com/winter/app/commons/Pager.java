package com.winter.app.commons;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	
	// limit의 시작 인덱스 번호
	private Long startIndex;
	
	// limit의 끝 인덱스 번호 
	private Long endIndex;
	
	// 페이지당 보여줄 row의 갯수
	private Long perPage;
	
	// page 번호
	private Long pageNum;
	
	// 전체 페이지 수
	private Long totalPage;
	
	// 페이징에서 시작과 끝 번호(1, 11, 21... / 10, 20, 30...)
	private Long startNum;
	private Long endNum;
	
	private void makePage() {
		// SQL의 Limit값을 계산
		
		this.startIndex = (this.getPageNum() - 1) * this.getPerPage();
		this.endIndex = perPage;
		
		
	}
	
	// totalCount :  전체 글의 갯수
	public void makeNum(Long totalCount) {
		
		// 1. totalPage : 전체 페이지 갯수 
		// 몫을 계산함
		this.totalPage = totalCount/this.getPerPage();
		// 근데 101개 항목을 perPage로 나눈 몫은 10인데 페이지는 11이 되어야 하니까
		// 나머지가 있다면 페이지를 더해줌
		if(totalCount % perPage != 0) {
			this.totalPage++;
		}
		
		// 2. totalBlock : 전체 블럭의 갯수
		// 예시) 10개를 5개씩 보여주려면 블럭 2개, 11개를 5개씩 보여주려면 블럭 3개 ...
		Long perBlock = 5L;  // 블럭당 출력할 번호의 갯수
		Long totalBlock = totalPage/perBlock;
		
		// totalPage랑 마찬가지로 나머지가 있다면 페이지를 증가시켜줌
		if (totalPage % perBlock != 0) {
			totalBlock++;
		}
		
		// 3. 현재 페이지 번호로 현재 블럭 번호를 계산
		// 1페이지이면 블럭1, 2페이지이면 블럭1, 5페이지이면 블럭1, 7페이지이면 블럭2, 11페이지이면 블럭3 ...
		Long curBlock = this.getPageNum()/perBlock;
		if(this.pageNum % 5 != 0) {
			curBlock++;
		}
		
		// 4. 현재 블럭 번호로 시작번호와 끝번호 계산
		this.startNum =  (curBlock-1) * perBlock+1;
		this.endNum = curBlock * perBlock;
		
		// 5. 마지막 블럭일 경우
		// 예) 11번페이지이면 블럭3 -> 블럭3은 15페이지까지 있는데 실제 페이지는 11까지만 있어서 그걸 맞춰줌
		if(curBlock ==  totalBlock) {
			this.endNum = totalPage;
		}
		
		//----------
		this.makePage();
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public Long getEndIndex() {
		return endIndex;
	}

	public Long getPerPage() {
		if(this.perPage == null) {
			this.perPage = 10L;
		}
		return perPage;
	}

	public Long getPageNum() {
		// 현재 페이지가 null이거나 0보다 작으면 1페이지로
		if (this.pageNum == null || this.pageNum < 1) {
			this.pageNum = 1L;
		} 
		// 페이지가 계속 증가해도 마지막 페이지로
		if (this.pageNum > totalPage) {
			this.pageNum = totalPage;
		}
		return pageNum;
	}
	
	
	
}
