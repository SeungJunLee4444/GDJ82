package com.gdu.app13.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

@PropertySource(value = {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {


	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserById(id) != null);
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByEmail(email) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
			
		// 인증코드 만들기
		String authCode = securityUtil.getAuthCode(6);  // String authCode = securityUtil.generateRandomString(6);
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");             // 구글 메일로 보내는 포트 번호
		properties.put("mail.smtp.auth", "true");            // 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true"); // TLS 허용
		
		/*
			이메일 보내기 API 사용을 위한 사전 작업
			
			1. 구글 로그인
			2. [Google 계정] - [보안]
			    1) [2단계 인증]  - [사용]
			    2) [앱 비밀번호]
			        (1) 앱 선택   : 기타
			        (2) 기기 선택 : Windows 컴퓨터
			        (3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		*/
		
		// 사용자 정보를 javax.mail.Session에 저장
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username, "인증코드관리자"));            // 보내는사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는사람
			message.setSubject("[Application] 인증 요청 메일입니다.");                   // 제목
			message.setContent("인증번호는 <strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8");  // 내용
			
			Transport.send(message);  // 이메일 전송
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// join.jsp로 반환할 데이터
		// 생성한 인증코드를 보내줘야 함
		// 그래야 사용자가 입력한 인증코드와 비교를 할 수 있음
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;
		
	}
	
	
	
	// [[[ 회원가입
	@Transactional
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
		 
		// [[ 요청
		
		// 파라미터
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		// # 일부 파라미터는 db에 넣을수 있도록 가공
		// 1) pw 암호화
		pw = securityUtil.sha256(pw);	
		// 2) name 크로스 사이트 스크립팅 방지
		// * 크로스 사이트 스크립팅 방지 : 텍스트에 입력된 <가 태그로 인식되는 것을 방지
		name = securityUtil.preventXSS(name);
		
		// 3) db birthday 만들기 : birthday = birthmonth + birthdate(sql참고)
		String birthday = birthmonth + birthdate;	
		
		// 4) detailAddress 크로스 사이트 스크립팅 방지
		detailAddress = securityUtil.preventXSS(detailAddress);
		
		// 5) db agreeCode 동의코드 만들기 : location과 promotion 사용 
		// * name으로 전달된 파라미터변수는 null처리가 불가능하기 때문에 isemply()를 쓴다
		int agreeCode = 0; // 0은 필수동의
		if(!location.isEmpty() && promotion.isEmpty()) {
			agreeCode = 1; // 필수 + 위치
		} else if(location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 2; // 필수 + 프로모션
		} else if(!location.isEmpty() && !promotion.isEmpty()) {
			agreeCode = 3; // 필수 + 위치 + 프로모션
		}
		
		// # db로 보낼 userdto만들기
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthday(birthday)
				.birthyear(birthyear)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build();
	
		// # db로 전송(가입하기 insert) ------------------> db
		int result = userMapper.insertUser(user);
		
		// [[ 응답
		try {
			
			// # 회원가입 후 로그인
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				
				// # 로그인 처리를 위해서 session에 로그인된 사용자 정보를 올려둠
				request.getSession().setAttribute("loginUser", userMapper.selectUserById(id));	// -> 테이블의 name값 사용가능
				
				// # 로그인 기록 남기기
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);
				}
				
				
				// # 성공응답
				out.println("<script>");
				out.println("alert('회원가입 성공');");
				out.println("location.href = '" + request.getContextPath() + "';" );
				out.println("</script>");
			} else {
				// # 
				out.println("<script>");
				out.println("alert('회원가입 실패');");
				// * 두칸 돌려보내기
				out.println("history.go(-2);" );
				out.println("</script>");
			}
			out.close();
	
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// [[[ 회원 삭제, 은퇴자 테이블에 추가
	@Transactional	// 복수의 서비스 처리
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		
		// # 탈퇴에 필요한 파라미터 : userno, id, joindate 		=> session에 세가지 정보가 저장되어있다
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// # 탈퇴할 회원 retireUserDTO 생성
		RetireUserDTO retireUserDTO = RetireUserDTO.builder()
							.userNo(loginUser.getUserNo())
							.id(loginUser.getId())
							.joinDate(loginUser.getJoinDate())
							.build();
		
		// # 탈퇴처리
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		int insertResult = userMapper.insertRetireUser(retireUserDTO);
		
		// [[ 응답
				try {
					
					// # 회원가입 후 로그인
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					if(deleteResult > 0 && insertResult >0) {
						
						// # 로그인된 session 초기화 
						session.invalidate();
					
						// # 성공응답
						out.println("<script>");
						out.println("alert('탈퇴 성공');");
						out.println("location.href = '" + request.getContextPath() + "';" );
						out.println("</script>");
					} else {
						// # 삭제실패
						out.println("<script>");
						out.println("alert('탈퇴 실패');");

						out.println("history.go(-1);" );
						out.println("</script>");
					}
					out.close();
			
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			
	}
	
	// [[[ 로그인
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		
		// [[ 요청
		// # 파라미터 3개
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// # pw를 암호화 처리
		pw = securityUtil.sha256(pw);
		
		// # db에 전송하기위해 dto에 저장
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.build();
		
		// # id, pw가 일치하는 회원 db에서 조회
		UserDTO loginUser = userMapper.selectUserByIdPw(user);
		
		// [[ 로그인처리 : 로그 기록 남기기 + session 저장
		// # id, pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장
		if(loginUser != null) {
			
			// # 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id);
			}
			// # 로그인 처리를 위해서 session에 로그인된 사용자 정보를 올려둠
			request.getSession().setAttribute("loginUser", userMapper.selectUserById(id));	// -> 테이블의 name값 사용가능
			
			
			try {
			// # id, pw가 일치하는 회원이 없다 : 로그인 페이지로 돌아가기
				response.sendRedirect(url);	// * url 관련에는 입출력 예외가 발생한다
			} catch(IOException e) {
				e.printStackTrace();
			}	
			
		} else {
			// [[ 응답 
			try {
				// # 회원가입 후 로그인
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						
						// # 실패응답
						out.println("<script>");
						out.println("alert('일치하는 회원 정보가 없습니다.');");
						out.println("location.href='" + request.getContextPath() + "';");
						out.println("</script>");
						out.close();
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
}
			