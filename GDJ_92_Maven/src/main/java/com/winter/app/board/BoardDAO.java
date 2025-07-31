package com.winter.app.board;

// 객체 못 만들고 타입으로 사용
public interface BoardDAO {
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	// detail(select)
	public BoardVO detail(BoardVO boardVO) throws Exception;
	
	// update
	public int update(BoardVO boardVO) throws Exception;
	
	//delete
	public int delete() throws Exception;
	
	
}
