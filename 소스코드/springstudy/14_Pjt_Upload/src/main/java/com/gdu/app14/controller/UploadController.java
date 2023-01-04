
package com.gdu.app14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	/*
	   * 반환값
	   (1) 단순이동 				: string
	   (2) 서비스   				: void
	   (3) ajax(또는 화면변동없이)  : responseEntity
	 */
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # service + move : 전체목록창 이동
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList());	
		
		return "/upload/list";
	}
	
	// # move : 게시글 추가창 이동
	@GetMapping("/upload/write")
	public String write() {
		return "/upload/write";
	}
	
	// # service : 게시글 추가
	// * MultipartHttpServletRequest : 이미지 첨부가 가능한 request
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartHttpRequest, HttpServletResponse response) {
		uploadService.save(multipartHttpRequest, response);
	}
	
	// # move : 상세화면 이동
	@GetMapping("/upload/detail")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	} 
	
	// # service : 개별 다운로드
	// * @responsebody와 responseentitiy를 통해서 ajax없이도 페이지 변화없이 로직 처리
	@ResponseBody	
	@GetMapping("upload/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String Agent,	// @requestHeader : 요청 헤더를 뒤지는 에너테이션
											@RequestParam("attachNo") int attachNo) {	
		return uploadService.download(Agent, attachNo) ;
	}
	
	// # service : 전체 다운로드
	@ResponseBody
	@GetMapping("/upload/downloadAll")
	public ResponseEntity<Resource> downloadAll(@RequestHeader("User-Agent") String userAgent, @RequestParam("uploadNo") int uploadNo) {
		return uploadService.downloadAll(userAgent, uploadNo);
	}
	
	// # move : 편집창 이동
	@PostMapping("/upload/edit")
	public String edit(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	// # service : 게시글 수정
	@PostMapping("/upload/modify")
	public void modify(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.modifyUpload(multipartRequest, response);
	}
	

	// # service : 첨부파일 삭제
	// 1. 첨부파일 삭제 : 첨부파일 삭제후 detail로 돌아가기위해 uploadNo도 필요
	@GetMapping("/upload/attach/remove") 
	public String attachRemove(@RequestParam("attachNo") int attachNo,
								@RequestParam("uploadNo") int uploadNo) {
		uploadService.removeAttachByAttachNo(attachNo);
		return "redirect:/upload/detail?uploadNo=" + uploadNo;
	}
	
	// # service : 게시글 삭제
	// * 게시글 삭제시 첨부파일도 삭제?
	@PostMapping("/upload/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		uploadService.removeUpload(request, response);
	}
	
	
	
	
	
}
