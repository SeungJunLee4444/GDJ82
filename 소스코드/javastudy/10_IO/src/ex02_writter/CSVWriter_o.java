package ex02_writter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CSVWriter_o {
	
	public static void main(String[] args) {
		
	
	
	// 1. csv
	// => 콤마로 분리된 값을 말한다
	// ex) a, b, c, ...
	// 확장자가 .csv인 파일(엑셀, 메모장으로 오픈가능)
	// (일반 txt파일과 별로 다를게 없는 파일)
	
	// c: \storage\product.csv
	// 제품번호, 제품명, 가격
	// 100, 새우깡, 1500\n
	// 101, 양파링, 2000\n
	// 103, 홈런볼, 3000\n
	
	List<String> header = Arrays.asList("제품번호", "제품명", "가격");
	List<String> product1 = Arrays.asList("100", "새우깡", "1500");
	List<String> product2 = Arrays.asList("101", "양파링", "2000");
	List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
	
	List<List<String>> list = Arrays.asList(header, product1, product2, product3);
	// * list 인터페이스를 이용한 2차배열
	
	// => 위에있는 데이터를 바탕으로 아래처럼 데이터를 읽어들이기
	// 
	
	File file = new File("c:\\storage", "product.csv");
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	// 해석 : list 이중 배열문을 출력하기 위해 2중 for문을 사용
	// * list배열 line을 만든 이유: list 배열들이 이름이 각기 다리기 때문 *
	
	try {
		bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
		for(int i = 0, length = list.size(); i < length; i++) {
			List<String> line = (list.get(i));	// list 배열내 list 배열의 변수들을 line 하나의 이름으로 통합
			for(int j = 0, size = line.size(); j < size; j++) {
				if(j == size - 1) {
					bw.write(line.get(j) + "\n");	// * 마지막이 아니면 줄바꿈
				} else {
					bw.write(line.get(j) + ",");	// * 마지막 요소는 ,
				}
			}
			// * 결과
//				제품번호, 제품명, 가격
//				100, 새우깡, 1500
//				101, 양파링, 2000
//				102, 홈런볼", 3000
			
			
			
			
		}
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

		
		// => 이걸 파일로 만드는 것이 핵심
		
	}
	
}
