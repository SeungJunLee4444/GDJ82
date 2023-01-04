package com.gdu.app12.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	
	// [[[ 보안 관련 클래스
	
	// [ 크로스 사이트 스크립팅 방지 : text 입력에 <script>가 입력되는것을 방지하기

	public String preventXSS(String str) {
		
		// 들어온값 : <script>
		
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("\"", "&quot;");
		str = str.replace("\'", "&#x27");
		
		// 나가는값 : lt;scriptlt;	-> db로 들어올때 스크립트로 인식되지 않는다
		
		return str;
	}
	
	
	// [ SHA-256 암호화
	// - 의미 : SECURE HASH ALGOLISM
	// - 기능 : 기본적으로 입력값을 256비트(32바이트) 암호화처리	=> db에저장하기 위해서 64로 바꿔줘야함
	// - 특징 : 암호화는 가능하지만 복호화는 불가능
	// * 비밀번호에 적용할것
	
	// - 설명
	/* 
		- 1바이트를 2글자로 표현하면 총 64글자(DB에 저장될 때 크기) 공간이 필요
		- 모든 입력이 64
		- JAVA에서 지원하는 기능
	 */
	public String sha256(String str) {
		
		MessageDigest md = null;
		try {
		md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes()); 					// * 문자열을 byte단위로 변환
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] b = md.digest();						// * 배열 b는 문자열 str이 암호화된 32바이트 크기 배열
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < b.length; i++) {		// 32번 조회
			sb.append(String.format("%2X", b[i]));	// %2X : 2자리 16진수 (0-F), %2x : 2자리 16진수(0-f)
		}
		return sb.toString();
		
		
	}
	
	// [ 인증코드 만들기
	// - 방법 : SecureRandom 클래스 활용 
	// * 보안측면에서 Math.random(), Random() 클래스는 사용하지 않는다
	
	private SecureRandom secureRandom = new SecureRandom();
	
	public String getAuthCode(int length) {
		
		/* 
		  		문자		아스키 코드 	생성
		  		'0'				48			48부터 10개 중 하나의 난수('0'~'9')
		  		'A'				65			65부터 25개 중 하나의 난수('A'~'Z')
		  		'a'				97			97부터 25개 중 하나의 난수('a'~'z')
		 */
		StringBuilder sb = new StringBuilder();
		for(int cnt = 0; cnt < length; cnt++) {			// length 만큼 반복
			if(secureRandom.nextDouble() < 0.5) {		// 50퍼센트 확률
				sb.append((char)(secureRandom.nextInt(10) + '0'));	// 0~9 사이의 숫자 중 하나
			} else {
				sb.append((char)(secureRandom.nextInt(26) + 'A'));  // A~Z 사이의 알파벳 중 하나
			}
		}
		return sb.toString();
	}
	
	
}
