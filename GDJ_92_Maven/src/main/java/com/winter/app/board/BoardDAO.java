package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

// 객체 못 만들고 타입으로 사용
public interface BoardDAO {
	
	// list
	// <BoardVO>: 그 리스트 안에 BoardVO 타입 객체만 들어간다
	public List<BoardVO> list(Pager pager) throws Exception;
	
	public Long totalCount() throws Exception;
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	// detail(select)
	// 접근 지정자 + 리턴타입(필요한 주체에다가 보내줌) + (매개변수)  
	public BoardVO detail(BoardVO boardVO) throws Exception;
	
	// update
	public int update(BoardVO boardVO) throws Exception;
	
	//delete
	public int delete(BoardVO boardVO) throws Exception;
	
	
	
}
