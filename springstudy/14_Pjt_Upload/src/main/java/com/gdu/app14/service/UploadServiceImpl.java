package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
	private MyFileUtil myFileUtil;
	
	// [[ 목록 : 원래는 페이징 처리를 해줘야함(11장 참고)
	@Override
	public List<UploadDTO> getUploadList() {
		return uploadMapper.selectUploadList();
	}
	
	
	

	
	// [[ 삽입 
	@Transactional // * insert를 두번 사용해야 함
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 1. 업로드 테이블에 저장
		
		// # 파라미터
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// # DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
				.title(title)
				.content(content)
				.build();
		
	
		
		// # 게시글 삽입 : db로 보내기
		int uploadResult = uploadMapper.insertUpload(upload); 
		// - 결과 : <SelectKey>로 인해 uploadNo가 uploadDTO에 저장되있다
		
		// ** uploadDTO와 동일한 uploadNo PK를 가져와서 attachDTO의 FK에 넣어줘야한다 **
		
	
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files");  // <input type="file" name="files">
		
		
		
		// 2. 첨부테이블에 삽입
		
		// # 첨부결과
		int attachResult;
		if(files.get(0).getSize() == 0) {	// * 첨부가 없는 경우 files 리스트에 size=0은 파일이 1개를 의미[MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]]
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
					origin = origin.substring(origin.lastIndexOf("\\") + 1); // * 인터넷 익스플로러는 origin에 전체 경로가 붙음
			
					// & 저장할 이름 : filename
					String filesystem = myFileUtil.getFilename(origin);
					
					System.out.println(filesystem);
					
					// & 저장할 경로 : path
					String path = myFileUtil.getTodayPath();
					
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
			
		}
			// 3. 응답 
			// # 첨부된 파일 개수와 attachResult와 개수가 같은지 확인
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if(uploadResult > 0 && attachResult == files.size()) {
					out.println("<script>");
					out.println("alert('업로드 되었습니다.');");
					out.println("location.href='" + multipartRequest.getContextPath() + "/upload/list'");
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
	
	// [[ 상세보기
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));		// * 게시글 정보 가져오기
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));	 // * 게시글에 다중 첨부된 파일 가져오기
	}
	
	// [[ 상세보기 + 다운로드 
	@Override
	public ResponseEntity<Resource> download(String userAgent, int attachNo) {
		
		// * ResponseEntity : ajax 전용 객체, 페이지 안바꾸고, 값만 반환
		
		// # 다운로드할 첨부파일의 정보(경로, 이름) : 상세보기 쿼리와 비슷
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		File file = new File(attach.getPath(), attach.getFilesystem());	// * 파일의 원래이름인 origin uuk가 아닌, 랜덤으로 바꾼 filesystem 이름으로 만들어야한다
		
		
		
		
		
		// # 반환할 resource
		Resource resource = new FileSystemResource(file);
		
		// # 파일이 존재하지 않을 경우
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		
		
		
		
		// # 다운로드 수 증가
		uploadMapper.updateDownloadCnt(attachNo);
		
		
		
		
		
		// # 다운로드 되는 파일명 (브라우저마다 다르게 시팅)
		String origin = attach.getOrigin();
		try {
			
			// IE (userAgent에 "Trident"가 포함되 있음) 
			if(userAgent.contains("Trident")) {
				origin = URLEncoder.encode(origin, "utf-8").replaceAll("[+]", " "); // * 브라우저는 공백을 +로 표현, 다시 바꾸기
			}
			
			// Edge (userAgent에 "Edg"가 포함되있음)
			else if (userAgent.contains("Edg")) {
				origin = URLEncoder.encode(origin, "utf-8");
				
			// 나머지
			} else {
				origin = new String(origin.getBytes("utf-8"), "ISO-8859-1");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		// # 다운로드 헤더 만들기
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "attachment; filename=" + origin);
		header.add("Content-Length", file.length() + "");
		
		// 반환
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	
	// [[ 삭제
	// 1. 첨부파일 삭제
	@Override
	public void removeAttachByAttachNo(int attachNo) {
		
		// # 삭제할 attach정보를 받아와야함(삭제전에)
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		
		
		// # db에서 삭제
		int result = uploadMapper.deleteAttachByAttachNo(attachNo);
		
		
		// # 첨부파일 삭제
		if(result > 0) {
			
			// & 첨부파일을 file 객체로 만듬
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			// 삭제
			if(file.exists()) {
				file.delete();
			}
			
		} else {
			
		}
			
		
		
	}
	
	
	
}
