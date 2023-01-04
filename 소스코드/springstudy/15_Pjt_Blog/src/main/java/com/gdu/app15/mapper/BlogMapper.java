package com.gdu.app15.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app15.domain.BlogDTO;

@Mapper
public interface BlogMapper {
	
	// # 목록 (블로그 = 게시글)
	// (1) : 전체 블로그 수
	public int selectBlogListCount();
	// (2) : 전체 목록
	public List<BlogDTO> selectBlogListByMap(Map<String, Object> map);
	
	// # 블로그 삽입
	public int insertBlog(BlogDTO blog);
	
	// # 조회수 증가
	public int updateHit(int blogNo);
	
	// # 상세조회 : 상세조회를 detail이라 지으면 안되는 이유 => 수정에도 detail이 필요하기 때문
	public BlogDTO selectBlogByNo(int blogNo);
	
	// # 블로그 수정
	public int updateBlog(BlogDTO blog);
	
	// # 블로그 삭제
	public int deleteBlog(int blogNo);

}
