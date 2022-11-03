package prac1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	// c:\\storage\\prac1.txt
	// 1바이트씩 읽어서,
	// 같은 위치의 copy.txt 파일에
	
	// filewriter, filereader 사용
	// int read() 메서드
	
	// 걸린시간 출력
	
	public static void m1() {
		
		File src = new File("c:\\storage", "prac1.txt");
		File cpy = new File("c:\\storage", "prac1_copy.txt");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			
			fr = new FileReader(src);
			fw = new FileWriter(cpy);
			
			int c;
			
			long begin = System.currentTimeMillis();
			while((c = fr.read()) != -1) { // * 전부 읽을떄까지
				fw.write(c);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) fw.close(); 
				if(fw != null) fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void m2() {
		
		// # 100글자씩 읽어서 보내기
		// => bufferedreader, bufferedwriter
		
		File src = new File("c:\\storage", "prac1.txt");
		File cpy = new File("c:\\storage", "prac1_copy.txt");
		
		BufferedReader br = null;
		BufferedWriter bw = null; 
		
		try {
			br = new BufferedReader(new FileReader(src));
			bw = new BufferedWriter(new FileWriter(cpy));
			
			char[] cbuf = new char[100];
			int readCnt = 0;
			
			long begin = System.currentTimeMillis();
			while((readCnt = br.read(cbuf)) != -1) {
				bw.write(cbuf, 0, readCnt);			
		}
		long end = System.currentTimeMillis();
			
		System.out.println("복사시간:" + (end - begin) * 0.001 + "초");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			
			if(bw != null) {
				bw.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	

	public static void main(String[] args) {
		
		m1();
		m2();
		


	}

}
