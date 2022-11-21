package com.gdu.app14.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;

@Mapper
public interface UploadMapper {
	
	// # 목록
	public List<UploadDTO> selectUploadList();
	// # 삽입 : 다중파일
	public int insertUpload(UploadDTO upload);
	public int insertAttach(AttachDTO attach);

}
