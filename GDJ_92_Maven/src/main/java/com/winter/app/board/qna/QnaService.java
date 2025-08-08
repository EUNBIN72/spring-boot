package com.winter.app.board.qna;

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
public class QnaService implements BoardService{
// BoardService 상속받음
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String board;
	
	// list
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.totalCount(pager);
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
	public int insert(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		// qna 게시글 등록
		int result = qnaDAO.insert(boardVO);
		result = qnaDAO.refUpdate(boardVO);

		for(MultipartFile m:attaches) {
			if (attaches == null || m.isEmpty()) {
				continue;
			}
		// 1. File을 하드디스크에 저장
		// fileManager.fileSave(저장경로, 업로드파일)
		// 실제 서버에 파일을 저장하고, 저장된 파일명을 리턴함
		String filename = fileManager.fileSave(upload+board, m);
		
		// 저장된 파일의 정보를 DB에 저장
		BoardFileVO vo = new BoardFileVO();
		vo.setOriName(m.getOriginalFilename());  // 클라이언트가 올린 원본 파일
		vo.setSaveName(filename);  // 서버에 실제 저장된 파일명
		vo.setBoardNum(boardVO.getBoardNum());  // 게시글 번호
		
		result = qnaDAO.insertFile(vo);  // 파일 정보를 DB에 저장
		}
		return result;
	}
	

	@Override
	public int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		int result = qnaDAO.update(boardVO);
		result = qnaDAO.refUpdate(boardVO);
		
		// 글만 있으면 (첨부파일이 없으면)
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
		vo.setOriName(m.getOriginalFilename());
		vo.setSaveName(fileName);
		vo.setBoardNum(boardVO.getBoardNum());
		result = qnaDAO.insertFile(vo);
		}
		return result;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		boardVO =  qnaDAO.detail(boardVO);
		
		for (BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload+board, vo.getSaveName());
		}
		int result = qnaDAO.fileDelete(boardVO);
		
		return qnaDAO.delete(boardVO);
		
	}
	
	@Override
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		// 순서를 지켜야 됨
		// 파일을 먼저 지우고 DB를 지워야 경로를 알 수 있음
		// 1. File 조회
		boardFileVO = qnaDAO.fileDetail(boardFileVO);
		
		// 2. File 삭제
		boolean result = fileManager.fileDelete(upload+board, boardFileVO.getSaveName());
		
		
		return qnaDAO.fileDeleteOne(boardFileVO);
		}
	
	
	
	

}
