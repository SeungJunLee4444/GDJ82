package prac2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	
	public static void q2() {
		
		// q2 : eclipse-jee-2021-03-R-win32-x86_64 복사하기
		// 		복사할 파일명은 eclipse.zip
		
		File src = new File("c:\\GDJ\\installer", "eclipse-jee-2021-03-R-win32-x86_64.zip");
		File copy = new File("c:\\GDJ\\installer", "eclipse.zip");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(copy));
			
			int readByte = 0;
			byte[] b = new byte[1024]; // 1kb
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bos != null) bos.close();
				if(bis != null) bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void q3() {
		
		// q3 : 복사한 파일을 c: storage로 이동
		// => 복사코드 + 기존 파일에 삭제메서드
		
		File src = new File("c:\\GDJ\\installer", "eclipse.zip");
		File dst = new File("c:\\storage", src.getName());
											// * file클래스의 get메서드
		File dir = new File("c:\\storage");
		if(dir.exists() == false) {
			dir.mkdir();
		}
		// * 폴더는 경로생성만 한다
		
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dst));
			src.delete();
			int readByte = 0;
			byte[] b = new byte[1024]; // 1kb
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte); 
			}
			bis.close();
			bos.close();	// * 이동을 위해서 close하기
			// =>  스트림이 열려있는 상태에서 삭제하면 삭제되지 않는다
			
			// # 원본과 복사본의 크기가 동일하면 원본제거
			if(src.length() == dst.length()) {
				src.deleteOnExit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// * close를 위에 썻으니 finally는 쓸 필요가없다
	
		
	}
			
		
		
		
		
		
		
		
	

	public static void main(String[] args) {
		
		//q2();
		q3();
		
		

	}

}
