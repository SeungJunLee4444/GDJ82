package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;
import com.gdu.app14.mapper.UploadMapper;
import com.gdu.app14.util.MyFileUtil;


@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private MyFileUtil fileUtil;
	
	
	
	// [[ 목록 : 원래는 페이징 처리를 해줘야함(11장 참고)
	@Override
	public List<UploadDTO> getUploadList() {
		List<UploadDTO> list = uploadMapper.selectUploadList();
				//System.out.println(list);
		return list;
		
	}
	
	// [[ 삽입 
	@Transactional // * insert를 두번 사용해야 함
	@Override
	public void save(MultipartHttpServletRequest multipartHttpRequest, HttpServletResponse response) {
		
		// 1. 업로드 테이블에 저장
		
		// # 파라미터
		String title = multipartHttpRequest.getParameter("title");
		String content = multipartHttpRequest.getParameter("content");
		
		// # DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
				.title(title)
				.content(content)
				.build();
		
	
		
		// # 게시글 삽입 : db로 보내기
		int uploadResult = uploadMapper.insertUpload(upload);	
		// - 결과 : <SelectKey>로 인해 uploadNo가 uploadDTO에 저장되있다
		
		// ** uploadDTO와 동일한 uploadNo PK를 가져와서 attachDTO의 FK에 넣어줘야한다 **
		
		
		
		
		
		List<MultipartFile> files = multipartHttpRequest.getFiles("files");
		
		// 2. 첨부테이블에 삽입
		
		// # 첨부결과
		int attachResult = 0;
		if(files.get(0).getSize() == 0) {	// * 첨부가 없는 경우 files 리스트에 size=0은 파일이 1개다 [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]]
			attachResult = 1;
		} else {
			attachResult = 0;
		};
			
			
	
		
		// # 첨부된 파일 죄다 가져오기
		
		
		// & 첨부된 파일 목록 순회(하나씩 저장)
		for(MultipartFile multipartFile : files) {
			try {
				
				// & 첨부가 되었는지 점검
				if(multipartFile != null && multipartFile.isEmpty() == false) {	 // * 둘다 필요
					
					// & 원래이름 : origin
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\.") + 1); // * 인터넷 익스플로러는 origin에 전체 경로가 붙음
			
					// & 저장할 이름 : filename
					String filesystem = fileUtil.getFilename(origin);
					
					System.out.println(filesystem);
					
					// & 저장할 경로 : path
					String path = fileUtil.getTodayPath();
					
					// & 저장할 경로 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					// & 첨부할 파일 객체
					File file = new File(dir, filesystem);
						
					// & 첨부파일 서버에 저장(업로드 진행)
					multipartFile.transferTo(file);
					
					// # AttachDTO 생성
					AttachDTO attach = AttachDTO.builder()
							.path(path)
							.origin(origin)
							.filesystem(filesystem)
							.uploadNo(upload.getUploadNo())
							.build();
					
					// # db에 attachDTO 저장
					attachResult += uploadMapper.insertAttach(attach);
					
					// * 반복문에 의해, 첨부파일이 3개면 attachResult는 3이 나온다 ----*
						
					// * 생성경로 : sts
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 3. 응답 
			// # 첨부된 파일 개수와 attachResult와 개수가 같은지 확인
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if(uploadResult > 0 && attachResult == files.size()) {
					out.println("<script>");
					out.println("alert('업로드 되었습니다.');");
					out.println("location.href='" + multipartHttpRequest.getContextPath() + "/upload/list'");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('업로드 실패했습니다.');");
					out.println("history.back();");
					out.println("</script>");
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
