package com.gdu.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.gdu.movie.domain.MovieDTO;

public interface MovieService {
	
	// # 목록
	public List<MovieDTO> getMovieList(Model model);
	
	// # 검색어
	public MovieDTO findMovie(HttpServletRequest request, HttpServletResponse response);
	
}
