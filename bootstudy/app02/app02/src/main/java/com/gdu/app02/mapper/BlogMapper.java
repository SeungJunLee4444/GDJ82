package com.gdu.app02.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app02.domain.BlogDTO;
import com.gdu.app02.domain.SummernoteImageDTO;

@Mapper
public interface BlogMapper {
	public int selectBlogListCount();
	public List<BlogDTO> selectBlogListByMap(Map<String, Object> map);
	public int insertSummernoteImage(SummernoteImageDTO summernote);
	public int insertBlog(BlogDTO blog);
	public int updateHit(int blogNo);
	public BlogDTO selectBlogByNo(int blogNo);
	public int updateBlog(BlogDTO blog);
	public int deleteBlog(int blogNo);
	public List<SummernoteImageDTO> selectSummernoteImageListInBlog(int blogNo);
	public List<SummernoteImageDTO> selectAllSummernoteImageList();
	public int deleteSummernoteImage(String filesystem);
}
