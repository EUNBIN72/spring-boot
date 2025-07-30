package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;

//	@Test
//	void insertTest() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title3");
//		noticeVO.setBoardContents("contents3");
//		noticeVO.setBoardWriter("writer3");
//		int result = noticeDAO.insert(noticeVO);
//		
//		// 단정문
//		assertEquals(0, result);
//		
//	}
	
	@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("update_title");
		noticeVO.setBoardContents("update_contents");
		noticeVO.setBoardWriter("update_writer");
		int result = noticeDAO.update(noticeVO);
		
		assertEquals(1, result);
	}
	
	@Test
	void deleteTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("update_title");
		noticeVO.setBoardContents("update_contents");
		noticeVO.setBoardWriter("update_writer");
		int result = noticeDAO.delete();
		
		assertEquals(1, result);
	}

}
