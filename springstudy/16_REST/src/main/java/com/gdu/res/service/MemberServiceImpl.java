package com.gdu.res.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.res.domain.MemberDTO;
import com.gdu.res.mapper.MemberMapper;
import com.gdu.res.util.PageUtil;

@Service
public class MemberServiceImpl implements MemberService { 
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PageUtil pageUtil;
	
	
	// == 추가
	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response) {
		
		try {
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));
			return result;
			
			
			
			// == 예외처리 : 예외사항을 하나씩 붙여서 처리
			// - 오류가 어떤것인지 확인하는법
			// * 이용 : 프론트에서도 오류사항을 막아놓고, 백에서도 막아놓는게 좋다
		} catch (DuplicateKeyException e) {	// 아이디중복 오류
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501);  // 응답 코드 501
				out.println("이미 사용 중인 아이디입니다.");  // 응답 메시지
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
				
		} catch(DataIntegrityViolationException e) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(502);  // 응답 코드 502
				out.println("필수 정보가 누락되었습니다.");  // 응답 메시지
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		} catch(Exception e) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(503);  // 응답 코드 503
				out.println("입력 정보를 확인하세요.");  // 응답 메시지
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return null;
		
	}
	
	// # 목록
	
	@Override
	public Map<String, Object> getMemberList(int page) {
		
		// 전체 게시글 수 
		int totalRecord = memberMapper.selectMemberCount();
		
		// 페이징 처리
		pageUtil.setPageUtil(page, totalRecord);
		
		// 전체 조회를 위한 begin, end를 담은 map
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 반환할 결과
		Map<String, Object> result = new HashMap<>();
		result.put("memberList", memberMapper.selectMemberListByMap(map));
		result.put("pageUtil", pageUtil);

		return result;
	}
	
	// # 상세
	
	@Override
	public Map<String, Object> getMember(int memberNo) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("member", memberMapper.selectMemberByNo(memberNo));
		
		return result;
	}
	
	// # 수정
	
	@Override
	public Map<String, Object> modifyMember(Map<String, Object> map , HttpServletResponse response)  {
		
		
		try {
			
			// 1. 성공작업
			Map<String, Object> result = new HashMap<>();
			result.put("updateResult", memberMapper.updateMember(map));
			return result;
			
		} catch (DataIntegrityViolationException e) {	// 잘못된 데이터가 전달될경우(정보누락)
			try {
				response.setContentType("/text/plain; charset=utf-8");
				PrintWriter out = response.getWriter();	
				response.setStatus(501);
				out.println("필수정보가 누락되었습니다");		// println, println은 여기서 상관없다 : 어자피 한줄만쓰기때문
				out.close();
				
				// responseEntity는 해당 응답코드를 처리해줄 수 있다, 위의 try, catch문을 대신할 수 있음
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
		
		
		return null;
	}
	
	// # 삭제
	// * string인 이유
	// 일단 string으로 통쨰로 가져와서 서비스에서 list로 변환
	@Override
	public Map<String, Object> deleteMember(String memberNoList) {
		
		// split 하나의 문자열을 쪼개셔 배열로 만드는 함수
		List<String> list = Arrays.asList(memberNoList);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("deleteResult", memberMapper.deleteMemberList(list));
		
		return result;
	}
	
	

}
