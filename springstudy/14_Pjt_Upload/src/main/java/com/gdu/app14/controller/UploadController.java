
package com.gdu.app14.controller;

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
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 목록창 이동 : list.jsp로 uploadList 전달 => ${uploadList}
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList());	
		
		return "/upload/list";
	}
	
	// # 게시글 추가 창 이동
	@GetMapping("/upload/write")
	public String write() {
		return "/upload/write";
	}
	
	// # 게시글 추가 로직 : 
	// * 첨부파일을 삽입할 때는 MultipartHttpServletRequest 사용
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartHttpRequest, HttpServletResponse response) {
		uploadService.save(multipartHttpRequest, response);
	}
	
	// # 상세화면 이동
	@GetMapping("/upload/detail")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	} 
	
	// # 파일 다운로드
	// * @responsebody와 responseentitiy를 통해서 ajax없이도 페이지 변화없이 로직 처리
	@ResponseBody	
	@GetMapping("upload/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String Agent,	// @requestHeader : 요청 헤더를 뒤지는 에너테이션
											@RequestParam("attachNo") int attachNo) {	
		return uploadService.download(Agent, attachNo) ;
	}
	
	// # 삭제
	// 1. 첨부파일 삭제 : 첨부파일 삭제후 detail로 돌아가기위해 uploadNo도 필요
	@GetMapping("/upload/attach/remove") 
	public String attachRemove(@RequestParam("attachNo") int attachNo,
								@RequestParam("uploadNo") int uploadNo) {
		uploadService.removeAttachByAttachNo(attachNo);
		return "redirect:/upload/detail?uploadNo=" + uploadNo;
	}
	
	
}
