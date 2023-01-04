package ex05_inputstream;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;

import ex04_outputstream.User;

public class Main {
	
	public static void m1() {
		
		// & 정리
		// * inputstreareader 	: byte 타입의 데이터를 문자열로 변환해주는 보조스트림
		// * datainputstream 	: byte 타입의 데이터를 타입별로 입력받을 수 있으며,
		// 특히 문자열 저장이 가능해진다
		

		// 1. 
		File file = new File("c:\\storage", "b1.bin");
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			
			// 1) 입력데이터
			// 1개는 int, 여러개면 byte[]출력과 동일)
			
			// 2) 모든 정보를 stringbuiler에 저장한 후 확인
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[5];				// * 복수의 문자들을 저장하며, 이를 5바이트씩 읽어온다는 뜻
			int readByte = 0;
			
			// 3) int read(byte[] b)
			// => 읽은 내용은 byte 배열 b에 저장하고, 읽은 바이트수는 반환처리한다 *
			// ex) 13바이트를 읽는다면 5바이트를 읽고, 나머지 3바이트는 3바이트만 읽고, 없으면 -1을 반환
			while((readByte = fis.read(b)) != -1) {
				// ex) 5바이트 읽고 5를 반환
				sb.append(new String(b, 0, readByte));
				// * append(객체, 시작인덱스, 길이) : 일부분만 가져올 수 있는 메서드(단, 배열이 char[]이여야가능)
				// * string도 append처럼 위와 같은 기능을 byte[] 배열로 생성할 수 있다
				System.out.println(sb.toString());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				} 
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 결과
//		Apple
//		Apple Mang
//		Apple Mango 맛
//		Apple Mango 맛있�
//		Apple Mango 맛있��.
		// => 한글은 영어와 달리 하나당 2~3바이트 단위기 때문에, 제대로 입력되지못함
		// => 문자를 바이트 스트림으로 읽었기 때문에 문제가 발생한것

		
		
	}
	
	public static void m2() {
	
	// 2. inputstreamReader ** 
	// => 바이트 입력스트림을 문자 입력 스트림으로 변환해준다
	
	File file = new File("c:\\storage", "b2.bin");
	FileInputStream fis = null;
	InputStreamReader isr = null;
	
	
	try {
		isr = new InputStreamReader(new FileInputStream(file));
		
		 StringBuilder sb = new StringBuilder();
		 
		 char[] cbuf = new char[5]; 	// & 5글자씩 읽기 위해서
		 int readCnt = 0;
		 while((readCnt = isr.read(cbuf)) != -1) {
			 sb.append(cbuf, 0, readCnt);			 
		 }
		 System.out.println(sb.toString());
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if(fis != null) {
				fis.close();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 결과: 안녕하세요 반갑습니다.
		// => 한글이 깨지지 않게 출력시켜준다
	}
	}
	
	public static void sissis() {
		
		// 3. 스캐너 없이도
		
		try {
			
			// 1) system.in 키보드와 연결된 바이트 스트림
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("입력 >>>");
			String str = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m3() {

		// 4. 변수를 그대로 입력받는 datainputstream
		
		File file = new File("c:\\storage", "b3.dat");
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new FileInputStream(file));
			String name = dis.readUTF();  // * writeutf와 쌍이되는 메서드
			int age = dis.readInt();
			double height = dis.readDouble();
			
			System.out.println(name + " , " + age + " , " + height );

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// & 
		// fileinputstream - datainputstream - readutf		: 
		// fileoutputstream - dateoutputstream - writeutf	: 이런 식으로도 byte타입의 값을 문자열로 변환할 수 있다 *
		// * data의 writeutf, readutf는 꼭 기억해둘것
		
		
		
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void m4() {
		
		// 5. 객체를 그대로 입력받는 objectinputstream
		
		File file = new File("c:\\storage", "b4.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			
			List<User> users = (List<User>)ois.readObject();	// # user3명 배열
			User user = (User)ois.readObject();					// # user 한명
			
			for(User u : users) {
				System.out.println(u);
			}
			System.out.println(user);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private static List<User> readObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void m5() {}
	
	public static void main(String[] args) {
		
		//m1();
		//m2();
		//sissis();
		m3();
		//m4();
		//m5();
	}

}
