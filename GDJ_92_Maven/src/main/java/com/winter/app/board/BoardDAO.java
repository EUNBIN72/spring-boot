package com.winter.app.board;

import java.util.List;

// 객체 못 만들고 타입으로 사용
public interface BoardDAO {
	
	// list
	// <BoardVO>: 그 리스트 안에 BoardVO 타입 객체만 들어간다
	public List<BoardVO> list() throws Exception;
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	// detail(select)
	public BoardVO detail(BoardVO boardVO) throws Exception;
	
	// update
	public int update(BoardVO boardVO) throws Exception;
	
	//delete
	public int delete() throws Exception;
	
	
	
}
