package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {
	
	// BoardDAO를 상속 받으므로 BoardDAO에 정의된 CRUD 메서드는 자동으로 포함됨
	// QnaVO(답글)에서만 필요한 것들은 별도로 선언해야 함
	
	// ref(그룹번호)
	public int refUpdate(BoardVO boardVO) throws Exception;
	
	// 답글 등록시 QnaVO로 등록
	public int replyUpdate(QnaVO qnaVO) throws Exception;
	
	// public int replyInsert(QnaVO qnaVO) throws Exception;

}
