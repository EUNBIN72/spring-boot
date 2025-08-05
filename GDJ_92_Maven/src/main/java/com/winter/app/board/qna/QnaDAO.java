package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO{
	
	// BoardVO를 상속 받는데 BoardVO에는 없고 QnaVO에만 있는 것들은 만들어줘야 됨
	
	public int refUpdate(BoardVO boardVO) throws Exception;
	
	public int replyUpdate(QnaVO qnaVO) throws Exception;

}
