package com.gdu.app05.service;

import java.io.File;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class GalleryServiceImpl implements GalleryService {

	@Override
	public ResponseEntity<byte[]> imageDisplay(String path, String filename) {
		
		// # 파일객체 만들기
		File file = new File(path, filename);		// * 경로, 파일이름을 매개변수로 파일 생성가능
		
		// # 응답정보
		ResponseEntity<byte[]> entity = null;
		try {
			String contentType = Files.probeContentType(file.toPath());
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", contentType );			// * image의 mine-type :  (파일의 컨텐츠타입을 알아서 가져오는 메서드)
			
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			// * FileCopyUtils.copyToByteArray(file) : 파일을 byte[]로 변환하였다
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// * ResponseEntity (응답본문(여기선 byte[]), header, httpstatus)
		
		return entity;
	}

}
