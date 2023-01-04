package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class NaverCaptchaServiceimpl implements NaverCaptureService {
	
	// # field : 네이버 개발자의 내 애플리케이션 정보
	private final String CLIENT_ID = "8DMw73fQdkYHHygxtkVX";
	private final String CLIENT_SECRET = "JMXw6j0N4f";
	// & final 선언시 상수
	

	@Override
	public String getCaptchaKey() {
		
		// 참고 : https://developers.naver.com/docs/utils/captcha/reference/
		
		// [키값 획득]
		
		// # 요청 url과 키발급 : 네이버 이미지캡챠 api 래퍼런스
		// 	(키발급 = code = 0 : "키발급", 1 : "사용자입력값 검증")
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=0";
		
		// # 반환할 key값(네이버 api가 제공하는 캡차키)
		String key = null;
		
	
		try {
			// # apiURL 접속 : 
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 요청메서드(http메서드)
			con.setRequestMethod("GET");
			
			// # 요청 헤더 : 헤더에 클라리언트 아이디, 비밀번호를 추가
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// # 입력스트림 생성(네이버 api의 정보를 읽기 위함)
			BufferedReader reader = null;
			if(con.getResponseCode() == 200 ) {			// =  HttpUrlConnection.HTTP_OK
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// # 네이버 api 서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		
		// 네이버에서 api서버가 보낸 데이터 확인 및 반환
		JSONObject obj = new JSONObject(sb.toString());
			key = obj.getString("key");
			
			
			
		// 자원반납
			reader.close();
			con.disconnect();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
		
		// & 결과 : key값 획득, key값은 호출마다 다르게 출력
		// => 획득한 key값으로 이미지를 획득

	}

	@Override
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key) {
		// [이미지획득]
		
		Map<String, String> map = new HashMap<String, String>();
		
		// URL + KEY(위에서 구한 key값 : JVG3b71sHUbj8mPq을 파라미터에 전송)
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		
		try {
			// # apiURL 접속 : 
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 요청메서드(http메서드)
			con.setRequestMethod("GET");
			
			// # 요청 헤더 : 헤더에 클라리언트 아이디, 비밀번호를 추가
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// # 입력스트림 생성(jpeg = jpg 형식의 이미지 데이터 반환)
			// => reader은 문자만 읽어낼 수 있으니, 바이트 타입을 읽어내기 위한 inputstream을 사용한다
			
			if(con.getResponseCode() == 200 ) {			
				
				// 1) 응답이 성공한경우 이미지(jpg)가 응답(입력 + 출력)
				// & 파일이니 텍스트가 아닌 바이트 타입 스트림이 필요하다
				// (1) 입력
				BufferedInputStream in = new BufferedInputStream(con.getInputStream());
				
				// #  캡차 이미지 다운로드 경로
				String dirname = "ncaptcha";
				String realPath = request.getServletContext().getRealPath(dirname);
				File dir = new File(realPath);	// 절대경로 위치 C:\GDJ\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\08_API
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				// # 캡차 이미지 이름(타임스탬프)
				String filename = System.currentTimeMillis() + ".jpg";
						
				//  캡차 이미지 객체 생성
				File file = new File(dir, filename);
						
				// 네이버 api로부터 정보를 읽어서 서버경로에 저장(out)
				
				// (2) 출력
			
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				
				// 저장(다운로드)
				byte[] b = new byte[1024];
				int readByte = 0;
				while((readByte = in.read(b)) != -1) {
					out.write(b, 0, readByte);
				}
				
				// # jsp에 만든 이미지의 이름을 화면상에 알려주기
				// => 로그인 jsp로 전달할 데이터(캡차이미지 경로 + 파일명 + 캡차키) 
				map.put("dirname", dirname);
				map.put("filename", filename);
				map.put("key", key);
				
				
				
				// 성공시 자원 반납
				out.close();
				in.close();
				
				
				
			} else {
				// 2) 응답이 실패한경우 reader을 통한 실패 텍스트 형식으로 응답
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				// # 네이버 api 서버가 보낸 데이터 저장
				StringBuilder sb = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				System.out.println("<<응답실패 사유>>");
				System.out.println(sb.toString());
				reader.close();
			}
			
			// 자원 반납
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response) {

		// [ajax 응답데이터 형식 : json]
		// => map에 저장된 데이터를 제이슨 으로 응답하는 메서드
		response.setContentType("application/json");
		
		// 응답데이터
		/* 
		 {
		 	"dirname" : "ncaptcha",
		 	"filename" : "111111111.jpg"	// => login.jsp의 ${}변수로 전달될 값
		 	"key"	: "이미지별 키값"
		 } 
		  
		 */
		
		// 캡차키, 캡차이미지 새로 요청
		String key = getCaptchaKey();
		Map<String, String> map = getCaptchaImage(request, key);
		// => map<string, string>을 반환하는 getcaptchaimage
		
		JSONObject obj = new JSONObject(map);	// json오브젝트는 map타입도 받을 수 있다
		
		// 응답
		try {
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean validateUserInput(HttpServletRequest request) {
		
		// # 요청 파라미터
		String key = request.getParameter("key");		// 이미지의 키
		String value = request.getParameter("value"); 	// 사용자가 입력한 방지문자
		
		// # 사용자 검증 요청 url
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=1&key=" + key + "&value=" + value;
		// - code는 1, key, value 파라미터와 값을 전달해줘야함
		// - 입력값에 따라 다른값을 전달하도록 변수처리해서 전달
		
		
		// # getCapturekey 메서드를 그대로 이용
		
		// 반환할값
		boolean result = false;
		
		try {
			// # apiURL 접속 : 
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 요청메서드(http메서드)
			con.setRequestMethod("GET");
			
			// # 요청 헤더 : 헤더에 클라리언트 아이디, 비밀번호를 추가
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// # 입력스트림 생성(네이버 api의 정보를 읽기 위함)
			BufferedReader reader = null;
			if(con.getResponseCode() == 200 ) {			// =  HttpUrlConnection.HTTP_OK
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// # 네이버 api 서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		
		// 네이버에서 api서버가 보낸 데이터 확인 및 반환
		JSONObject obj = new JSONObject(sb.toString());
		result = obj.getBoolean("result");				// 응답시 속성 "result"(true, false) 반환
														// 네이버개발자 참고
			
			
			
		// 자원반납
			reader.close();
			con.disconnect();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		// & 결과 : key값 획득, key값은 호출마다 다르게 출력
		// => 획득한 key값으로 이미지를 획득

	

		
		
		
		
		
	}

}
