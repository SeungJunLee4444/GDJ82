package com.gdu.app02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// # 자버컨테이너
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 1. servlet 컨테이너의 <resource> 옮기기
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/load/image/**")
			.addResourceLocations("file:///c:/summernoteImage/");
	}
	
}
