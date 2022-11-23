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
	
	// # 상세
	public UploadDTO selectUploadByNo(int uploadNo);
	public List<AttachDTO> selectAttachList(int uploadNo);
	
	// # 상세 + 다운로드 : 첨부파일 번호를 전달하면 다운로드수를 증가시키는 로직
	public int updateDownloadCnt(int attachNo);
	public AttachDTO selectAttachByNo(int attachNo);
	
	// # 삭제
	public int deleteAttachByAttachNo(int attachNo);

}
