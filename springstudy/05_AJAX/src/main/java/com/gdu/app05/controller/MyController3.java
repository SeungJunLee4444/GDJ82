package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.MovieService;

@Controller
public class MyController3 {

	
	// # jsp 이동 : movie.jsp로
	@GetMapping("movie") 
	public String welcome() {
		return "movie";
	}
	
	// # 서비스
	@Autowired
	MovieService movieService;
	
	// # 객체사용 파라미터 처리
	@ResponseBody
	@GetMapping("movie/boxOfficeList")
	public String boxOfficeList(@RequestParam String targetDt) {	// @RequestParam의 value는 생략가능
		
		return movieService.getBoxOffice(targetDt);	// String response를 view로 반환한다 ================================>
	}
}
