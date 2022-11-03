package prac3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParseMain {
	
	public static void main(String[] args) {
		
		// ex) 	영화 한편 : movie 객체에 저장
		// 		영화 전체 : list<movie> 리스트
		
		File file = new File("c:\\storage", "boxoffice.xml");
		List<Movie> movies = new ArrayList<Movie>();	// * 자바 1.7이후 뒤의 <>는 생략이 가능해짐
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			NodeList boxOfficeList = doc.getElementsByTagName("dailyBoxOffice");
			// * 바로 nodelist 쓸 수도 있음
			
			for(int i = 0; i < boxOfficeList.getLength(); i++) {
				
				Element boxOffice = (Element)boxOfficeList.item(i);
				// * node를 쓰지않는건 node는 getelement 메서드를 쓰지 못하기 때문이다
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();
				// * elementbytagname까지는 nodelist item까지는 node, textcontent까지는 텍스트(메서드체인)
				
				String movieNm = boxOffice.getElementsByTagName("movieNm").item(0).getTextContent();
				String openDt = boxOffice.getElementsByTagName("openDt").item(0).getTextContent();
				String salesAcc = boxOffice.getElementsByTagName("salesAcc").item(0).getTextContent();
				String audiAcc = boxOffice.getElementsByTagName("audiAcc").item(0).getTextContent();
				// * 위 값들이 숫자여도 다 텍스트로 처리
				
				// * body에 데이터를 넣어서 보내는것을 post 방식이라 한다
				
				Movie movie = Movie.builder()
						.movieCd(movieCd)
						.movieNm(movieNm)
						.openDt(openDt)
						.salesAcc(salesAcc)
						.audiAcc(audiAcc)
						.build();
				// * 빌터패턴 : movie 객체 하나에 여러 객체 담기 ( 메서드 체인의 일종)
				// * setter 실무에서는 필요하나 느림 => bean 에서 복습
				
				// 위와같은 자바객체를 pojo라 한다, 데이터만 담고있는 순수한 자바객체
				
				// * 
				movies.add(movie);	// * list배열에 데이터만 담은 순수한 자바의 객체를 담는다
				
				// * collection 복습하기(금)
				
				
			} // for끝
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		for(Movie movie : movies) {
			System.out.println(movie);  	// tostring 
			}
		
		
	}

}
