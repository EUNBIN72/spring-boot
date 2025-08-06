package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String dir, MultipartFile attaches) throws Exception {
		// 1. 디렉토리 생성
		File file = new File(dir);
		if (!file.exists()) {
			// mkdir() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우 생성 불가
			// mkdirs() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우 상위 디렉토리까지 생성
			
			file.mkdirs();
		}
		
		// 2. 저장할 파일명을 생성
		String fileName = UUID.randomUUID().toString();
		// 확장자 갖다 붙이기
		fileName = fileName + "_" + attaches.getOriginalFilename();
		
		// 3. 파일을 하드디스크에 저장
		file = new File(file, fileName);
		
		// 3-1, 3-2 둘 중에 아무거나 쓰면 됨
		// 3-1) MultipartFile transferTo 메서드 사용
//		attaches.transferTo(file);
		
		// 3-2) FileCopyUtils의 copy 메서드 사용
		FileCopyUtils.copy(attaches.getBytes(), file);
		
		return fileName;
		
	}

}
