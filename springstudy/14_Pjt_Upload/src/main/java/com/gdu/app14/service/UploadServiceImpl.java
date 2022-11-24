package com.gdu.app14.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
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
	
	// * myfileutil : 파일생성에 필요한 유틸(전용)
	@Autowired
	private MyFileUtil myFileUtil;
	
	// # 목록조회
	@Override
	public List<UploadDTO> getUploadList() {
		return uploadMapper.selectUploadList();
	}
	
	
	

	
	// # 게시글 추가
	// (1) 게시글추가 + (2) 첨부파일 추가
	// insertUpload 	insertAttach
	// * 트랜잭션 처리 필요
	@Transactional 
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
					// 설명 : http://파일명.확장자 => 파일명.확장자
			
					// & 저장할 이름 : filename
					String filesystem = myFileUtil.getFilename(origin);
					// => 파일명
					
					System.out.println(filesystem);
					
					// & 저장할 경로 : path
					String path = myFileUtil.getTodayPath();
					
					// & 저장할 경로 만들기
					File dir = new File(path);						// * 파일이 저장되는 경로 : 별도의 지정이 없으면 sts 폴더를 루트로 폴더, 파일이 생성된다
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
			
			// ******   결과 : 첨부한 파일을 db에도 기록 + 파일로도 생성
			
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
	
	// # 상세화면 이동
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));		// * 게시글 정보 가져오기
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));	 // * 게시글에 다중 첨부된 파일 가져오기
	}
	
	
	// # 개별 다운로드
	// (1) 해당 다운로드 파일 조회  (2) 다운로드 수 증가
	//	selectAttachByNo			updateDownloadCnt
	// * updateDownloadCnt는 개별과 전체 동일한 쿼리문 사용
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
	
	// # 전체 다운로드
	// (1) 첨부파일 전체 조회  (2) 다운로드 수 증가
	// selectAttachList			updateDownloadCnt
	// * updateDownloadCnt는 개별과 전체 동일한 쿼리문 사용
	@Override
	public ResponseEntity<Resource> downloadAll(String userAgent, int uploadNo) {
		
		// storage/temp 디렉터리에 임시 zip 파일을 만든 뒤 이를 다운로드 받을 수 있음
		// com.gdu.app14.batch.DeleteTmpFiles에 의해서 storage/temp 디렉터리의 임시 zip 파일은 주기적으로 삭제됨
		
		// 다운로드 할 첨부 파일들의 정보(경로, 이름)
		List<AttachDTO> attachList = uploadMapper.selectAttachList(uploadNo);
		
		// zip 파일을 만들기 위한 스트림
		FileOutputStream fout = null;
		ZipOutputStream zout = null;   // zip 파일 생성 스트림
		FileInputStream fin = null;
		
		// storage/temp 디렉터리에 zip 파일 생성
		String tmpPath = "storage" + File.separator + "temp";
		
		File tmpDir = new File(tmpPath);
		if(tmpDir.exists() == false) {
			tmpDir.mkdirs();
		}
		
		// zip 파일명은 타임스탬프 값으로 생성
		String tmpName =  System.currentTimeMillis() + ".zip";
		
		try {
			
			fout = new FileOutputStream(new File(tmpPath, tmpName));
			zout = new ZipOutputStream(fout);
			
			// 첨부가 있는지 확인
			if(attachList != null && attachList.isEmpty() == false) {

				// 첨부 파일 하나씩 순회
				for(AttachDTO attach : attachList) {
					
					// zip 파일에 첨부 파일 추가
					ZipEntry zipEntry = new ZipEntry(attach.getOrigin());
					zout.putNextEntry(zipEntry);
					
					fin = new FileInputStream(new File(attach.getPath(), attach.getFilesystem()));
					byte[] buffer = new byte[1024];
					int length;
					while((length = fin.read(buffer)) != -1){
						zout.write(buffer, 0, length);
					}
					zout.closeEntry();
					fin.close();

					// 각 첨부 파일 모두 다운로드 횟수 증가
					uploadMapper.updateDownloadCnt(attach.getAttachNo());
					
				}
				
				zout.close();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// 반환할 Resource
		File file = new File(tmpPath, tmpName);
		Resource resource = new FileSystemResource(file);
		
		// Resource가 없으면 종료 (다운로드할 파일이 없음)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		// 다운로드 헤더 만들기
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "attachment; filename=" + tmpName);  // 다운로드할 zip파일명은 타임스탬프로 만든 이름을 그대로 사용
		header.add("Content-Length", file.length() + "");
		
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	
	// # 게시글 수정
	// (1) 게시글 수정		(2) 첨부파일 추가
	// updateUpload			insertAttach
	// * insertAttach : 게시글 추가 + 수정에서 사용됨
	@Transactional
	@Override
	public void modifyUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		/*  UPLOAD 테이블 수정하기 */
		
		// 파라미터
		int uploadNo = Integer.parseInt(multipartRequest.getParameter("uploadNo"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
				.uploadNo(uploadNo)
				.title(title)
				.content(content)
				.build();
		
		// DB 수정
		int uploadResult = uploadMapper.updateUpload(upload);
		
		/* ATTACH 테이블에 저장하기 */
		
		// 추가하려는 첨부 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files");  // <input type="file" name="files">

		// 첨부 결과
		int attachResult;
		if(files.get(0).getSize() == 0) {  // 첨부가 없는 경우 (files 리스트에 [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 이렇게 저장되어 있어서 files.size()가 1이다.
			attachResult = 1;
		} else {
			attachResult = 0;
		}
		
		// 첨부된 파일 목록 순회(하나씩 저장)
		for(MultipartFile multipartFile : files) {
			
			try {
				
				// 첨부가 있는지 점검
				if(multipartFile != null && multipartFile.isEmpty() == false) {  // 둘 다 필요함
					
					// 원래 이름
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);  // IE는 origin에 전체 경로가 붙어서 파일명만 사용해야 함
					
					// 저장할 이름
					String filesystem = myFileUtil.getFilename(origin);
					
					// 저장할 경로
					String path = myFileUtil.getTodayPath();
					
					// 저장할 경로 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부할 File 객체
					File file = new File(dir, filesystem);
					
					// 첨부파일 서버에 저장(업로드 진행)
					multipartFile.transferTo(file);
					
					// AttachDTO 생성
					AttachDTO attach = AttachDTO.builder()
							.path(path)
							.origin(origin)
							.filesystem(filesystem)
							.uploadNo(uploadNo)
							.build();
					
					// DB에 AttachDTO 저장
					attachResult += uploadMapper.insertAttach(attach);
					
				}
				
			} catch(Exception e) {
				
			}
			
		}  // for
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(uploadResult > 0 && attachResult == files.size()) {
				out.println("<script>");
				out.println("alert('수정 되었습니다.');");
				out.println("location.href='" + multipartRequest.getContextPath() + "/upload/detail?uploadNo=" + uploadNo + "'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('수정 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// # 첨부파일 삭제 
	// (1) 첨부파일 조회 		(2) 첨부파일 삭제
	// selectAttachByNo			deleteAttach
	// * 
	@Override
	public void removeAttachByAttachNo(int attachNo) {
		
		
		// * 왜 조회랑 삭제가 같이 있냐?
		// - 답 : 조회는 파일 삭제에 사용, 삭제문은 db에 저장된 정보 삭제에 사용
		// (1) 삭제 : db에 저장된 첨부파일 정보 삭제
		// (2) 조회 : 조회받은 attach를 이용해 file의 경로, 파일명을 찾아 파일을 삭제
		
		// DB에서 Attach 정보 삭제
		int result = uploadMapper.deleteAttach(attachNo);
		
		// 삭제할 Attach 정보 가져오기
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		
		// 첨부 파일 삭제
		if(result > 0) {
			
			// 첨부 파일을 File 객체로 만듬
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			// 삭제
			if(file.exists()) {
				file.delete();
			}
			
		}
		
	}
	
	
	// # 게시글 삭제 
	// (1) 첨부된 전체파일 가져오기		(2) 게시글 삭제
	// selectAttachList					deleteUpload
	// ? 왜 첨부파일은 삭제안하지?
	@Override
	public void removeUpload(HttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 파라미터
		int uploadNo = Integer.parseInt(multipartRequest.getParameter("uploadNo"));
		
		// 삭제할 Upload에 첨부된 첨부파일 목록 가져오기
		List<AttachDTO> attachList = uploadMapper.selectAttachList(uploadNo);
		
		// DB에서 Upload 정보 삭제
		int result = uploadMapper.deleteUpload(uploadNo);
		
		// 첨부 파일 삭제 : 첨부파일 삭제는 별도의 쿼리문 없이 파일을 직접 생성 후 
		if(result > 0) {
			if(attachList != null && attachList.isEmpty() == false) {
				// 순회하면서 하나씩 삭제
				for(AttachDTO attach : attachList) {
					// 삭제할 첨부 파일의 File 객체 생성
					File file = new File(attach.getPath(), attach.getFilesystem());
					// 삭제
					if(file.exists()) {
						file.delete();
					}
				}
			}
		}
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				out.println("<script>");
				out.println("alert('삭제 되었습니다.');");
				out.println("location.href='" + multipartRequest.getContextPath() + "/upload/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('삭제 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
		
	
	
	
}
