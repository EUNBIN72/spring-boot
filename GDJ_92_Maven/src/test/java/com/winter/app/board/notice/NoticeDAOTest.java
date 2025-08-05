package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j // log를 찍을 수 있는 메서드를 제공
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;

	void listTest() throws Exception {
		List<BoardVO> list = noticeDAO.list();

		assertNotEquals(0, list);
	}

//	@Test // test를 할 수 있는 어노테이션
//	void detailTest() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardNum(1L); // long 타입으로 형 변환 해야 됨
//		BoardVO boardVO = noticeDAO.detail(noticeVO);
//		log.info("result : {}", boardVO);
//
//		// 단정문
//		assertNotNull(boardVO);
//	}

	
	  @Test 
	  void insertTest() throws Exception { 
		  
		  for(int i=0; i<105; i++) {
		
		  NoticeVO noticeVO = new  NoticeVO(); 
		  noticeVO.setBoardTitle("title3" + i);
		  noticeVO.setBoardContents("contents3" + i); 
		  noticeVO.setBoardWriter("writer3" + i);
		  int result = noticeDAO.insert(noticeVO);
		  if(i%10 == 0) {
			  // 너무 많이 한번에 넣으면 멈춰버리니까 잠깐 쉬었다가 진행시키기 위한 코드
			  Thread.sleep(500);
		  }
		   } 
		  // 단정문 assertEquals(1, result);
	  
	  }
	  
//	  @Test void updateTest() throws Exception { NoticeVO noticeVO = new
//	  NoticeVO(); noticeVO.setBoardTitle("update_title");
//	  noticeVO.setBoardContents("update_contents");
//	  noticeVO.setBoardWriter("update_writer"); int result =
//	  noticeDAO.update(noticeVO);
//	  
//	  assertEquals(1, result); }
//	  
//	  @Test void deleteTest() throws Exception { NoticeVO noticeVO = new
//	  NoticeVO(); noticeVO.setBoardTitle("update_title");
//	  noticeVO.setBoardContents("update_contents");
//	  noticeVO.setBoardWriter("update_writer"); int result = noticeDAO.delete(noticeVO);
//	  
//	  assertEquals(1, result); }
//	  

}
