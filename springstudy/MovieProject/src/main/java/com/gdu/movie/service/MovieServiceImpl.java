package com.gdu.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;


@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	// # 목록조회
	@Override
	public List<MovieDTO> getMovieList(Model model) {
		
		// 1. 전체목록 조회 
		List<MovieDTO> list = movieMapper.selectAllMovies();
		
		// 2. 전체목록 개수 
		int movielistCnt = movieMapper.selectAllMoviesCount();
		// * 컨트롤러 영역으로 끌고올라갈 수 없으니 서비스에서 처리
		// System.out.println(movielistCnt);	// 10
		
		model.addAttribute("movieListCnt", movielistCnt);
		// System.out.println(model.getAttribute("movieListCnt"));
		
		return list;
	}
	
	// # 검색조회 : select를 다른 파라미터와 같이 db로 전송해야할때 map을 사용
	@Override
	public MovieDTO findMovie(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터
		String column = request.getParameter("column");
		String searchText = request.getParameter("searchText");
		
		// 2. 조회개수
		int movielistCnt = movieMapper.selectAllMoviesCount();
		
		// 3. dto
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("searchText", searchText);
		map.put("movielistCnt", movielistCnt);
		
		// 4. db접근
		MovieDTO movie = movieMapper.selectMoviesByQuery(map);
		map.put("movie", movie);
		
		return movie;
		
		
		

		
	}

}
