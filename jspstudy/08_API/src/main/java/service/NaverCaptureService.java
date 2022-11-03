package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NaverCaptureService {
	
	// # 실제 서비스 인터페이스
	// - 하나의 인터페이스에 복수의 메서드로 구성된 경우가 많다
	public String getCaptchaKey();
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key); 
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response);
	public boolean validateUserInput(HttpServletRequest request); 

}
