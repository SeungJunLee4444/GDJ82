package com.gdu.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.service.MovieService;
import com.gdu.movie.util.SecurityUtil;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private SecurityUtil securityUtil;

	// # move : 웰컴페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// # ajax : 전체조회
	@ResponseBody
	@GetMapping(value="/searchAllMovies", produces="application/json; charset=UTF-8")
	public List<MovieDTO> listMovie(Model model) {
		return movieService.getMovieList(model);
		
	}
	
	
	// # ajax : 검색조회
	
	 @ResponseBody
	 @GetMapping(value="/searchMovie", produces="application/json; charset=UTF-8")
	 public MovieDTO oneMovie(HttpServletRequest request, HttpServletResponse response) {
		 return movieService.findMovie(request, response);
	 } 
	
	 
	
}