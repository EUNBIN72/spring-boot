package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;


@Service
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.notice}")
	private String board;

	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		// DAO 계층한테 "게시글 목록을 가져와 달라"고 요청함
		Long totalCount = noticeDAO.totalCount(pager);
		pager.makeNum(totalCount);
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception{
		return noticeDAO.detail(boardVO);  // 받을 애를 보내줌
	}
	
	
	@Override
	public int insert(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		// 공지 게시글 등록
		int result = noticeDAO.insert(boardVO);
		
		for(MultipartFile m:attaches) {
			if (attaches == null || m.isEmpty()) {
				continue;
			}
			// 1. File을 하드디스크에 저장
			// fileManager.fileSave(저장경로, 업로드파일)
			// 실제 서버에 파일을 저장하고, 저장된 파일명을 리턴함
			String fileName = fileManager.fileSave(upload+board, m);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO vo = new BoardFileVO();
			vo.setOriName(m.getOriginalFilename());  // 클라이언트가 올린 원본 파일
			vo.setSaveName(fileName);  // 서버에 실제 저장된 파일명
			vo.setBoardNum(boardVO.getBoardNum());  // 게시글 번호
			result = noticeDAO.insertFile(vo);  // 파일 정보를 DB에 저장
		}
	
		
		return result; //noticeDAO.insert(boardVO);
	}
	
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.detail(boardVO);
		
		for(BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload+board, vo.getSaveName());
		}
		int result = noticeDAO.fileDelete(boardVO);
		
		return noticeDAO.delete(boardVO);
	}
	
}
