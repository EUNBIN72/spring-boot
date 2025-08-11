package com.winter.app.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeService;
import com.winter.app.commons.FileDownView;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {

    private final FileDownView fileDownView;

    private final NoticeService noticeService;
	
	@Autowired
	private QnaService qnaService;
	
	// application.properties에 정의된 board.qna 값을 읽어서 name 변수에 주입함
	@Value("${board.qna}")
	private String name;

    QnaController(NoticeService noticeService, FileDownView fileDownView) {
        this.noticeService = noticeService;
        this.fileDownView = fileDownView;
    }
	
	// 
	@ModelAttribute("board")  // model은 key와 value를 가짐
	public String getBoard() {
		return name;
	}
	
	@GetMapping("list")
	public String list (Pager pager, Model model) throws Exception {
		model.addAttribute("pager", pager);
		model.addAttribute("list", qnaService.list(pager));
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("vo", qnaService.detail(qnaVO));
		
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String relpy(QnaVO qnaVO, Model model) throws Exception{
		model.addAttribute("vo", qnaVO);
		return "board/add";
	}
	
	@PostMapping("reply")
	public String relpy(QnaVO qnaVO, MultipartFile[] attaches) throws Exception{
		int result = qnaService.reply(qnaVO, attaches);
		return "redirect:./list";
	}
	
	@GetMapping("add")
	public String insert() throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String insesrt(QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		int result = qnaService.insert(qnaVO, attaches);
		return "redirect:./list"; 
			
	}
	
	@GetMapping("update")
	public String update (BoardVO boardVO, Model model) throws Exception {
		BoardVO vo = qnaService.detail(boardVO);
		model.addAttribute("vo", vo);
		
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(QnaVO qnaVO, MultipartFile[] attaches, Model model) throws Exception {
		int result = qnaService.update(qnaVO, attaches);
		
		String msg = "수정 실패";
		
		if(result > 0) {
			msg = "수정 성공";
		}
		
		String url = "./detail?boardNum=" + qnaVO.getBoardNum(); 
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		// commons의 result로 리턴
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(QnaVO qnaVO, MultipartFile[] attaches, Model model) throws Exception {
		int result = qnaService.delete(qnaVO);
		
		String msg = "삭제 실패";
		
		if(result > 0) {
			msg = "삭제 성공";
		}
		
		String url = "./list";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@PostMapping("fileDelete")
	@ResponseBody //컨트롤러 메서드의 반환값을 HTTP 응답의 Body에 직접 담아 보냄
	public int fileDelete(BoardFileVO boardFileVO, Model model) throws Exception {
		int result = qnaService.fileDelete(boardFileVO);
		
		return result;
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileVO boardFileVO, Model model) throws Exception {
		boardFileVO = qnaService.fileDetail(boardFileVO);
		model.addAttribute("vo", boardFileVO);
		
		return "fileDownView";
	}
	
	
	
	
  
}
