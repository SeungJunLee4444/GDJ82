package com.gdu.app14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {
	
	// [[ 파일첨부에 필요한 config 
	
	// # CommonsMultipartResolver: 인코딩, 파일 한개당, 파일 전체 용량 설정가능
	@Bean
	public CommonsMultipartResolver multipartResolver () {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		// # 인코딩
		multipartResolver.setDefaultEncoding("utf-8");
		// # 파일 하나당 최대용량 : 10mb
		multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);
		// # 총 파일 용량 : 100mb
		multipartResolver.setMaxUploadSize(1024 * 1024 * 100);
		
		return multipartResolver;
	}

}
