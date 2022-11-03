package ex02_writter;

import java.io.File;

import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

	public static void main(String[] args) {
		
		// 2. xml
		// => csv, xml, 등은 데이터 관련 확장자 이름
		// => 확장 마크업 언어
		// => 표준 마크업 언어인 html의 확장버전
		// => 정해진 태그(<>)를 쓰는 html과 달리 사용자가 직접 제작
		
		/*
		 <products>
			 <product>
			 	<number>100</number>	// * 하나하나를 element(요소)라 하며,
			 	<name>새우깡</name>		// 크게 보면 product도 element라 본다
			 	<price>1500</price>		// * 계층구조이기 때문에, 
			 </product>					// product.appendchild(product의 자식추가)
			  <product>					// => 자바스크립트에서 자주나옴
			 	<number>101</number>
			 	<name>양파링</name>
			 	<price>2000</price>
			 </product>
			  <product>
			 	<number>102</number>
			 	<name>홈런볼</name>
			 	<price>3000</price>
			 </product>
		</products>
		 
		 // => 이런 형태로 제공되는 데이터를 xml데이터라 한다
		 */
		
		try {
			
			// 1) Document 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();	// (서류 만드는클래스)
			Document document = builder.newDocument();
			// => factory패턴이라 하며, 새 문서를 세팅하는 코드다 **
			document.setXmlStandalone(true);
			// => standalone = "no" 제거하는 코드 **
			
			// Document에 products 태그 추가
			Element products = document.createElement("products");
			document.appendChild(products);
			// => products 태그를 만들고 문서에 추가하는 코드 **
			// => appendchild는 자식태그메서드를 형성할 때 사용 **
			
			
			
			// 2) 태그 생성
		  //List<String> header = Arrays.asList("제품번호", "제품명", "가격");
			List<String> product1 = Arrays.asList("100", "새우깡", "1500");
			List<String> product2 = Arrays.asList("101", "양파링", "2000");
			List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
			
			List<List<String>> list = Arrays.asList(product1, product2, product3);
			
			for(List<String> line : list) {
				
				Element product = document.createElement("product");
				Element number = document.createElement("number");
				number.setTextContent(line.get(0));
				Element name = document.createElement("name");
				name.setTextContent(line.get(1));
				Element price = document.createElement("price");
				price.setTextContent(line.get(2));
				// * 태그 생성 후 이름을 붙여주고, 데이터를 저장(settextcontent)
			
			// 3) 태그배치(상하관계) **
				
				products.appendChild(product);	// * products 밑에 product
				product.appendChild(number);
				product.appendChild(name);
				product.appendChild(price);
				
			}
			
			// 4) xml 생성
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.setOutputProperty("indent", "true"); // * 태그간 들여쓰기 **
			transformer.setOutputProperty("doctype-public", "yes"); // * 개행 
			
			
			Source source = new DOMSource(document);
			File file = new File("c:\\storage", "product.xml"); 
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// json 데이터
		// ex) "number" : "100"
		// => 대부분의 데이터는 json, xml, csv로 제공된다
		
		

	}

}
