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
		
		// 글만 있으면(첨부파일이 없으면)
		if(attaches ==null) {
			return result;
		}
		
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
	public int update(BoardVO boardVO,MultipartFile [] attaches) throws Exception {
		int result = noticeDAO.update(boardVO);
		
		// 글만 있으면(첨부파일이 없으면)
		if(attaches == null) {
			return result;
		}
		
		// 1. 파일을 하드디스크에 저장
		for (MultipartFile m : attaches) {
			if (m == null || m.isEmpty()) {
				continue;
			}
		
		
		String fileName = fileManager.fileSave(upload+board, m);
				
		
		// 2. 파일 정보를 FileDB에 저장(insert)
		BoardFileVO vo = new BoardFileVO();
		vo.setOriName(m.getOriginalFilename());  // 클라이언트가 올린 원본 파일
		vo.setSaveName(fileName);  // 서버에 실제 저장된 파일명
		vo.setBoardNum(boardVO.getBoardNum());  // 게시글 번호
		result = noticeDAO.insertFile(vo);
		}
		
		return result;
		
		
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
	
	@Override
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		// 순서를 지켜야 됨
		// DB 기록을 먼저 지우면 실제 파일의 경로를 알 수 없음 -> 물리적인 파일을 먼저 삭제해야 됨
		// 1. File 조회
		// noticeDAO 객체의 fileDetail 메소드를 호출해 삭제할 파일의 상세 정보를 데이터베이스에서 가져옴
		// 서버에 저장된  실제 파일의 이름(saveName)을 알아내기 위해 필수 -> boardFileVO 객체에 담겨 반환됨
		boardFileVO = noticeDAO.fileDetail(boardFileVO);
		
		// 2. File 삭제
		// fileManager의 fileDelete 메소드를 호출하여 서버에 저자왼 실제 파일을 삭제
		// 메소드 인자로는 파일이 저장된 폴더 경로(upload+board)와 고유한 파일명(boardFileVO.getSaveName())이 전달됨
		// 파일 삭젝 성공하면 result 변수에 true가 저장됨
		boolean result = fileManager.fileDelete(upload+board, boardFileVO.getSaveName());
				
		
		// 3. DB 삭제
		// 물리적 파일이 성공적으로 삭제되면, DB에 저장된 해당 파일의 정보를 삭제함
		// noticeDAO의 fileDeleteOne 메소드를 호출하여 관련 데이터를 DB에서 삭제
		return noticeDAO.fileDeleteOne(boardFileVO);
	}
	
	@Override
	public BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception {
		
		return noticeDAO.fileDetail(boardFileVO);
	}
	
	@Override
	public String boardFile(MultipartFile multipartFile) throws Exception {
		if(multipartFile == null || multipartFile.getSize() == 0) {
			return null;
		}
		
		String filename = fileManager.fileSave(upload+board, multipartFile);
		return "/files/" + board + "/" + filename;
	}
	
	
	@Override
	public boolean boardFileDelete(String fileName) throws Exception {
		fileName = fileName.substring(fileName.lastIndexOf("/"));
		
		return fileManager.fileDelete(upload+board, fileName);
	}
	
	
}
