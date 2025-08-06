package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@Service
public class QnaService implements BoardService{
// BoardService 상속받음
	
	
	@Autowired
	private QnaDAO qnaDAO;
	
	// list
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.totalCount();
		pager.makeNum(totalCount);
		return qnaDAO.list(pager);
	}

	// detail
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDAO.detail(boardVO);
	}
	
	
	// reply
	public int reply(QnaVO qnaVO) throws Exception {
		// 부모글 정보(ref / step / depth) 조회
		QnaVO parent = (QnaVO)qnaDAO.detail(qnaVO);
		
		// REF : 부모의 REF를 자기의 REF로 지정
		// STEP : 부모의 STEP에 +1한 값을 자기의 STEP으로 지정 (바로 아래로)
		// DEPTH : 부모의 DEPTH에 +1한 값을 자기의 DEPTH로 지정 (한 단계 들여쓰기)
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		// 같은 ref 그룹 내에, 부모보다 step이 큰 글들의 step을 1씩 증가
		int result = qnaDAO.replyUpdate(parent);
		result = qnaDAO.insert(qnaVO);
		return result;
	}
	

	// ref insert
	@Override
	public int insert(BoardVO boardVO) throws Exception {
		int result = qnaDAO.insert(boardVO);
		// ref 값을 update
		result = qnaDAO.refUpdate(boardVO);
		return result;
	}
	

	@Override
	public int update(BoardVO boardVO) throws Exception {
		return qnaDAO.update(boardVO);
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return qnaDAO.delete(boardVO);
	}
	
	

}
