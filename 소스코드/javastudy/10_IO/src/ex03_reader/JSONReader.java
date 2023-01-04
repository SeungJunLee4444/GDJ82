package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReader {
	
	// * 제이슨 공부 + 메모장 내용 다시 추가
	
	public static void main(String[] args) {
		
		// & filereader로 데이터를 문자열타입으로 담고, 이를 jsonarray
		
		File file = new File("c:\\storage", "product.json");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line); // * "\n" 은 있어도 되고 없어도 된다
			}
			String str = sb.toString();
			
			
			JSONArray arr = new JSONArray(str);  // * json 데이터 타입 배열
			
			
			List<Product> products = new ArrayList<Product>();		// * 리스트를 이용한 간편한 배열생성
			
			for(int i = 0, length = arr.length(); i < length; i++) {
				JSONObject obj = arr.getJSONObject(i);
				Product product = new Product();
				product.setNumber(obj.getString("number"));
				product.setName(obj.getString("name"));
				products.add(product);
				}
			
			for(Product product : products) {
				System.out.println(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
