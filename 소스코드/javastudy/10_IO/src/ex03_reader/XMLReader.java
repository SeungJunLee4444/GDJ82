package ex03_reader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	
	public static void main(String[] args) {
		
		// * xml은 외우지 말고 최대한 이해하면서하기
		
//		try {
//		
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // * newinstance 쓰는 이유는?
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		File file = new File("c:\\storage", "product.xml"); 
//		Document document = builder.parse(file);				// * product.xml을 분석
//		
//		
//		// * 최상위 요소(루트)			* products를 말하는것
//		Element root = document.getDocumentElement();
//		System.out.println(root.getNodeName());
//	
//		
//		// * 최상위 요소의 자식 노드들
//		NodeList nodeList = root.getChildNodes(); // * 자식노드들 가져오기(배열같은거라 for문 사용가능)
//		for(int i = 0; i < nodeList.getLength(); i++) {
////			nodeList[i]
//			Node node = nodeList.item(i);	// * 배열[i] 역할 , // * # text 줄바꿈과 <product> 태그로 구성
//			System.out.println(node.getNodeName());
//			if(node.getNodeType() == node.ELEMENT_NODE) { // * emement node = 태그를 의미
//															// * 노드가 element인가? // * 줄바꿈 제외, 태그만남음
//				System.out.println(node.getNodeName());  
//				NodeList nodeList2 = node.getChildNodes(); // <product> 태그의 자식노드(3개) <number, name, price>
//				
//				for(int j = 0; j < nodeList2.getLength(); j++) {
//					Node node2 = nodeList2.item(j);
//					System.out.println(node2.getNodeName());
//					if(node2.getNodeType() == node.ELEMENT_NODE)
//						Product product = new Product();
//						switch(node2.getNodeName()) {
//						case "number" : product.setNumber(node2.getTextContent()); break;
//						case "name" : product.setName(node2.getTextContent()); break;
//						case "price" : product.setPrice(Integer.parseInt(node2.getTextContent()); break;
//						}
//				}
//			}
//			// * arraylist에 product 추가
//			products.add(product)
//		}
//		} for(Product product : products) {
//			System.out.println(product);
//		}
//	}
//		
//		
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		
//		}
//	}
	}
}
