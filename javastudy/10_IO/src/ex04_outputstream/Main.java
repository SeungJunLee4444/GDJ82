package ex04_outputstream;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	// & 19일차
	// [1] outputstream
	// => 출력스트림
	// => 자식클래스 : 
	// ByteArrayOutputStream, FileOutputStream, FilterOutputStream, ObjectOutputStream, PipedOutputStream
	
	
	// &보충 read 메서드
	// (1) read()	:  1바이트씩 읽고, 읽은 수를 값으로 반환
	// (2) read(byte[] b) : read할 내용을 byte 배열에 저장
	// => 바이트의 양이 많을 때는 (2)가 효과적
	
	// & bufferedreader의 readline 메서드
	// => 텍스트파일을 한줄씩 읽는 메서드
	
	
	// *보충 입출력 데이터
	// => 출력할 데이터가 문자 '하나' 면 int에 반환값을 저장
	// => 출력할 데이터가 여러 글자일때는 byte[] 에 반환값을 저장
	// => 둘의 반환값은 데이터를 읽은 총 반복횟수
	
	// 입출력 데이터의 byte[]와 read의 read(byte[])는 다른것이다 ^*^
	
	// & 기타
	// 1) string 타입은 메서드로 getbyte를 가지고 있다
	// => string 타입을 매개변수로 byte[]값을 만들어주는 메서드 *
	
	
	
	// & fileoutputstream 정리 ^*^
	// 1. byte 기반 출력 클래스를 말한다	* 문자 기반인 writter과 다르다
	// 2. 입출력데이터: 문자 하나면 int, 여러문자를 출력할 때는 byte[]를 사용한다
	// 3. 3개의 보조스트림을 가지고 있다
	// 1) 속도향상
	// bufferedoutputStream(보조스트림)
	// * string - getbyte 메서드 : string에 입력된 문자열을 매개변수로 byte[]을 만들어준다
	// 2) 데이터 타입별로 나눠 출력
	// DataOutputStream - writeutf(문자열), writeint, writedouble
	// => 바이트 타입 스트림에서 double타입 실수를 출력하는 특수한 경우에 유용하다
	// => 데이터를 타입별로 나눠서 저장하고 출력하는 보조 스트림 
	// 3) 
	// outputstream - writeeobject 
	// => 객체를 그대로 메서드에 담아 출력
	
	
	
	public static void m1() {
		
		// 1. c:\\storage\\b1.bin 파일 연결되는 바이트 출력 스트림
		// & 
		File file = new File("c:\\storage", "b1.bin");
		FileOutputStream fos = null;
		
		try {

			fos = new FileOutputStream(file);
			// * FileOutputStream: byte 기반 출력 클래스(writter은 문자 기반)
												
			// # fileoutput의 출력할 데이터 타입 ^*^
			// 1byte
			// 2byte이상	: byte[]	(byte기반인데 여러개니까) 
			// => 영문, 숫자는 1byte, 한글은 2~3byte기 떄문에 그대로 출력하면 오류가발생
			// => outputstreamwriter의 사용 이유
			
			// # 출력할 데이터들
			int c = 'A';
			String str = "pple Mango 맛있다.";		
					
			// # 출력
			// => String값을 매개변수로 받아와 byte[]로 만들어주는 메서드	
			byte[] b = str.getBytes(StandardCharsets.UTF_8);	
			// * string의 getbyte 메서드 : 문자열을 바이트 타입으로 변환해준다 *
			// => outputstream은 데이터를 byte타입으로 출력하기때문
				
			// => 결과 Apple Mango 맛있다.(22바이트)

			
			// # 출력
			fos.write(c);	// * 문자 하나는 바로 write를 사용해 출력
			fos.write(b);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if(fos != null) {
				fos.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void m2() {
		
		// 2. 출력속도 향상
		// => bufferedoutputStream(보조스트림)
		// * string (클래스) - getbyte(메서드)
		File file = new File("c:\\storage", "b2.bin");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			
			String str = "안녕하세요 반갑습니다.";	// * 데이터 
			byte[] b = str.getBytes("UTF-8");		// * ----------------------?
			
			bos.write(b);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if(bos != null) {
				bos.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m3() {
		
		// 3. 변수를 그대로 출력하는 DataOutPutSteam
		// => 보조 스트림이다
		// * dataoutputstream (클래스) - writeutf, writeint, writedouble( 메서드)
		File file = new File("c:\\storage", "b3.dat");
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			dos = new DataOutputStream(new FileOutputStream(file));
			
			// # 출력할 변수
			String name = "에밀리";
			int age = 30;
			double height = 165.5;
			
			// # 출력
			dos.writeUTF(name); // * 문자 보내는 메서드
			dos.writeInt(age);
			dos.writeDouble(height);
			// * 타입별로 메서드를 호출할 수 있는것이 특징이다 ^*^
			// => output의 기본단위는 byte, int라 double 데이터를 훼손시켜서 보내게 되는데, 
			// dataoutputstream은 이렇게 실수값을 출력할 때 사용된다
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos != null) {
					dos.close();
				}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	
	
	public static void m4() {
		
		// 4. 객체를 그대로 출력하는 objectoutputstream
		// => 보조스트림
		// * outputstream (클래스) - wirteobject( 메서드)----------------------*
		
		File file = new File("c:\\storage", "b4.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
		
			// # list 데이터
			List<User> users = Arrays.asList(
					new User(1, "kim", 30),
					new User(2, "lee", 40),
					new User(3, "choi", 50));
			
			// # user 데이터 
			User user = new User(4, "min", 60);
			
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			oos.writeObject(users);
			oos.writeObject(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null) {
					oos.close();
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	public static void main(String[] args) {
		
		//m1();
		//m2();
		//m3();
		m4();
		
	}

}
