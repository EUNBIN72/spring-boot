package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class QnaService implements BoardService{
	
	@Autowired
	private QnaDAO qnaDAO;
	
	// list
	@Override
	public List<BoardVO> list() throws Exception {
		return qnaDAO.list();
	}

	// detail
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDAO.detail(boardVO);
	}
	
	
	// reply
	public int reply(QnaVO qnaVO) throws Exception {
		// 답글:
		QnaVO parent = (QnaVO)qnaDAO.detail(qnaVO);
		// REF : 부모의 REF를 자기의 REF로 지정
		// STEP : 부모의 STEP에 +1한 값을 자기의 STEP으로 지정
		// DEPTH : 부모의 DEPTH에 +1한 값을 자기의 DEPTH로 지정
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep()+1);
		qnaVO.setBoardDepth(parent.getBoardDepth()+1);
		
		int result = qnaDAO.replyUpdate(parent);
		result = qnaDAO.insert(qnaVO);
		return result;
	}
	


	// ref insert
	@Override
	public int insert(BoardVO boardVO) throws Exception {
		int result = qnaDAO.insert(boardVO);
		// ref 값을 update 하는 쿼리를 만들어줘야 됨
		result = qnaDAO.refUpdate(boardVO);
		return result;
	}
	

	@Override
	public int update(BoardVO boardVO) throws Exception {
		
		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
