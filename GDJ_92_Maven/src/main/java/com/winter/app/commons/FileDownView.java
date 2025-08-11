package com.winter.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.BoardFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownView extends AbstractView {
	
	@Value("${app.upload}")
	private String path;
	
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		log.info("========= custom view ===========");
		log.info("{}", model);
		
		BoardFileVO boardFileVO = (BoardFileVO)model.get("vo");
		
		String filePath = path + model.get("board").toString();
		
		File file = new File(filePath, boardFileVO.getSaveName());
		
		// 1. 총 파일의 크기를 세팅
		 response.setContentLengthLong(file.length());
		 
		// 2. 다운로드시 파일의 이름을 인코딩
		String fileName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
		 
		// 3. header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// 4. HDD에서 파일을 읽고
		 FileInputStream fi = new FileInputStream(file);
		 
		 // 5. Client로 전송 준비
		 OutputStream os = response.getOutputStream();
		 
		 // 6. 전송
		 FileCopyUtils.copy(fi, os);
		 
		 // 7. 자원 해제
		 os.close();
		 fi.close();
		
		
	}

}
