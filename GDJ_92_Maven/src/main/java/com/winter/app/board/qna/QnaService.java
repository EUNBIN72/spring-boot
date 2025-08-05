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

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDAO.detail(boardVO);
	}

	@Override
	public int insert(BoardVO boardVO) throws Exception {
		int result = qnaDAO.insert(boardVO);
		// ref 값을 update 하는 쿼리를 만들어줘야 됨
		result = qnaDAO.refUpdate(boardVO);
		return result;
	}
	

	@Override
	public int update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
