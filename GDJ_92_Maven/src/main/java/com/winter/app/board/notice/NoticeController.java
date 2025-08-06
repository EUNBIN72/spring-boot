package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.qna.QnaVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/board/*")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
	// 
	@ModelAttribute("board")  // model은 key와 value를 가짐
	public String getBoard() {
		return name;
	}
	
	@GetMapping("list")  // “HTTP GET 방식의 요청”을 처리
	// String 타입인 이유 : JSP와 같은 View 페이지의 경로(논리적 뷰 이름)를 반환
	public String list(Pager pager, Model model) throws Exception {
		// Model 객체 : spring 컨트롤러에서 JSP(뷰)로 데이터를 전달할 때 사용(스프링에서 제공)
		
		
		// 1. 서비스에서 게시글 리스트를 조회(DB)
		// "목록"이니까 List<> 사용해서 list에 담아
 		List<BoardVO> list = noticeService.list(pager);
		
		// 2. 조회한 데이터를 Model에 담아서 JSP로 전달
		model.addAttribute("list", list);
		
		// 3. /WEB-INF/views/notice/list.jsp 파일로 이동
		return "board/list";
	}
	
	@GetMapping("detail")
	// String 타입인 이유 : JSP와 같은 View 페이지의 경로(논리적 뷰 이름)를 반환
	public String detail(NoticeVO noticeVO, Model model) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		
		// 2. 가지고 온 데이터를 보냄(detail 이라는 이름으로 boardVO를 보냄)
		model.addAttribute("vo", boardVO);
		
		// 3. /WEB-INF/views/notice/detail.jsp 파일로 이동
		return "board/detail";
	}
	
	// form 태그로 이동하는 메소드
	@GetMapping("add")
	public String insert() throws Exception{
		return "board/add";
	}
	
	
	// 오버로딩 : 같은 이름의 메소드를 여러개 만드는 것
	@PostMapping("add")
	public String insert(NoticeVO noticeVO, MultipartFile attaches) throws Exception {
		int result = noticeService.insert(noticeVO, attaches);
		
		// 데이터를 가지고 리스트로 넘어감 (redirect 써줌)
		return "redirect:./list"; // /notice/list 로 redirect
	}
	
	// 이동
	@GetMapping("update")
	public String update(BoardVO noticeVO, Model model)throws Exception{
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("vo", boardVO);
		
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(NoticeVO noticeVO, Model model) throws Exception {
		int result = noticeService.update(noticeVO);
		
		String msg = "수정 실패";
		
		if (result > 0) {
			msg="수정 성공";
		}
		
		String url = "./detail?boardNum="+noticeVO.getBoardNum();
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result"; //"redirect:./detail?boardNum="+noticeVO.getBoardNum();
	}
	
	
	@PostMapping("delete")
	public String delete(NoticeVO noticeVO, Model model) throws Exception {
		int result = noticeService.delete(noticeVO);
		
		String msg = "삭제 실패";
		
		if(result > 0) {
			msg="삭제 성공";
		}
		
		// 삭제 성공하면 list로 이동
		String url = "./list";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
		
	}
	
//	@GetMapping("add")
//	public void insert() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDAO.insert(noticeVO);
//	}
//	
//	@GetMapping("update")
//	public void update() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDAO.update(noticeVO);
//	}
//	
//	@GetMapping("delete")
//	public void delete() throws Exception {
//
//		int result = noticeDAO.delete();
//	}
	
	

}
