package ex02_writter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// * ctrl + shift + o : import 정리, 필요한 내용 만들어주고, 필요없는 내용 가져와줌

public class Main_o {
	
	public static void m1() {
		
		// 1. 폴더 생성
		
		File dir = new File("c:\\storage");
		if(dir.exists() == false) {
			dir.mkdir();
		} 
		File file = new File(dir, "m1.txt");
		// * 폴더의 경로는 (1) 직접입력, (2) 폴더 객체명 둘다 가능
		
	// -----------------------------------------------------------
		
		// 2. 스트림으로 파일만들기
		// => filewriter은 문자열 타입 데이터를 출력하는데 사용된다
	
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);	
			
		} catch (IOException e) {	// * 입출력에서는 언제나 ioe 예외발생
			e.printStackTrace();	
		} finally {			
			try {
				if(fw != null) {	
					fw.close();	 // * close 없으면, resource leak 경고(생략가능)
				}
				// * fw가 null일 수 있는 예외사항이 발생할 수 있음
				// => trycatch에서 null값이 메서드로 던져지면 null 오류가 발생한다
				// => 입출력에서 왜 finally에 다시 예외처리를 하는지
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			}	
	}
	
	// -----------------------------------------------------------
		
	public static void m2() {
		
		// 3. 문자타입 데이터 출력
		// * 데이터 저장시, 문자 하나씩 읽을때는 int, 
		// 				여러 문자를 읽을때는 char[], string을 쓴다
		// * 데이터작성은 filewriter과 write() 메서드
		
		File file = new File("c:\\storage", "m2.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			
			// # 입력할 데이터들(임의)
			int c = 'i';
			char[] cbuf = {' ', 'a', 'm'};
			String str = " IronMan";
			// * 데이터는 빈공간도 1byte 취급한다
			
			// # write 메서드로 데이터 출력
			fw.write(c);
			fw.write(cbuf);
			fw.write(str);

			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null)  {	// * filewriter은 파일에 내용을 저장하는 메서드, 내용이 null이 아닐때 멈춤
					fw.close();
				} 
				}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		}
	
	// -----------------------------------------------------------
	
	public static void m3() {
		
		// 4. try-catch-resources 문
		// => resource는 자원으로, 여기서는 스트림을 의미한다
		// * 스트림의 종료(close)를 자동으로 처리하는 try-catch문을 의미한다
		
		// 1) 형태
		//try (스트림 생성자) {} 
		// => close를 자동으로 진행해주는 try문이 t-c-r문이다
		
		File file = new File("c:\\storage", "m3.txt");
		try(FileWriter fw = new FileWriter(file)) {
			
			fw.write("나는 아이언맨이다.");
			fw.write("\n");
			fw.write("너는 타도스냐? \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// -----------------------------------------------------------
		
		// 5. 일부내용만 파일에 저장하기
	
		public static void m4() {
			File file = new File("c:\\storage", "m4.txt");
		
		try(FileWriter fw = new FileWriter(file)) {
			char[] cbuf = {'a', 'b', 'c', 'd'}; 
			String str = "abcde";
			
			fw.write(cbuf, 0, 2);	// 인덱스0부터 2글자		// * 일부만 내용 저장하기
			fw.write(str,2, 3); 	// 인덱스 2부터 3글자
		} catch (IOException e) {e.printStackTrace();
		}
	}
		
		public static void m5() {
		
			// 6. filewritter은 속도가 느리기 때문에, bufferedwriter을 추가해서 함께 사용한다
			
			File file = new File("c:\\storage", "m5.txt");
			
			FileWriter fw = null;
			BufferedWriter bw = null;
			
			try {
				
				fw = new FileWriter(file); 
				bw = new BufferedWriter(fw); // *2 속도 향상을 위한 보조스트림(메인스트림이없으면 사용x)
				
			  //fw.write(null);	
				bw.write("오늘은 수요일인데 수업이 안끝나요 ㅎㅎㅎ");	
				
			} catch (IOException e) {
				e.printStackTrace();	// * 정밀한 오류내역 출력
			} finally {
				try {
				
					if(bw != null) {	// * 시작값이 null이라서?
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
	
		
			
		}
		
		public static void m6() {
			
		// 7. printwriter 클래스										// * writer은 텍스트파일 출력에 사용된다
		// => print(), println(), 메소드를 지원한다 
		// => ** 특히 println(자동줄바꿈)를 쓰기 위해서 printwriter를 쓴다
			
			File file = new File("c://storage", "m6.txt");
			PrintWriter out = null;
			
			try {
				out = new PrintWriter(file);
				
				// *1 write 메서드는 줄바꿈을 "\n"으로 처리한다
				out.write("안녕하세요\n");
				
				// *2 println메서드는 자동으로 줄바꿈을 삽입하여 출력된다
				out.println("반갑습니다");
				out.println("처음뵙겠습니다");
					
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
				if(out != null) {
					out.close(); 
				}
			} catch (Exception e) {	// => *3 printwriter이 최근에 나온 클래스라, ioe가 아닌 exception으로 처리
				e.printStackTrace();
			}
			}
			
			
			
			
		}
		

	public static void main(String[] args) {
		
		//m1();
		//m2();
		m3();
		//m4();
		//m5();
		//m6();
		
		

	}

}
