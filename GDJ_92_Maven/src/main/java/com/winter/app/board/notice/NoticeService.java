package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public List<BoardVO> list() throws Exception {
		// DAO 계층한테 "게시글 목록을 가져와 달라"고 요청함
		return noticeDAO.list();
	}
	
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception{
		return noticeDAO.detail(boardVO);  // 받을 애를 보내줌
	}
	
	@Override
	public int insert(BoardVO boardVO) throws Exception {
		return noticeDAO.insert(boardVO);
	}
	
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return noticeDAO.delete(boardVO);
	}
	
}
