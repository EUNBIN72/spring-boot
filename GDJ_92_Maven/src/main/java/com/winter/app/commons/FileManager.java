package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public boolean fileDelete(String dir, String fileName) throws Exception {
		File file = new File(dir, fileName);
		
		return file.delete();
		
	}
	
	
	public String fileSave(String dir, MultipartFile attaches) throws Exception {
		// 1. 디렉토리 생성
		File file = new File(dir);
		if (!file.exists()) {
			// mkdir() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우 생성 불가
			// mkdirs() : 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우 상위 디렉토리까지 생성
			
			file.mkdirs();
		}
		
		// 2. 저장할 파일명을 생성(파일명 충돌 방지)
		// UUID를 파일명에 붙이는 이유 : 
		// 여러 사용자가 동일한 파일명으로 파일을 올릴 수 있는데 그냥 원본 파일명만 저장하면 나중에 덮어쓰기 되거나 이전 파일이 사라지는 데이터 유실이 발생할 수 있음
		// 파일 하나하나마다 UUID를 붙여서 고유성 부여해줌(DB나 서버 어디에서든 "이 파일은 딱 하나임")
		String fileName = UUID.randomUUID().toString();
		// 저장할 파일명에 원본파일명 갖다 붙임 
		fileName = fileName + "_" + attaches.getOriginalFilename();
		
		// 3. 파일을 하드디스크에 저장
		file = new File(file, fileName);
		
		// 3-1, 3-2 둘 중에 아무거나 쓰면 됨
		// 3-1) MultipartFile transferTo 메서드 사용
//		attaches.transferTo(file);
		
		// 3-2) FileCopyUtils의 copy 메서드 사용
		FileCopyUtils.copy(attaches.getBytes(), file);
		
		// 저장된 파일명을 리턴(DB에 저장할 떄 활용함)
		return fileName;
		
	}

}
