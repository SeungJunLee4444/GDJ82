package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader_o {

	public static void main(String[] args) {

		
		File file = new File("c:\\storage", "product.csv") ;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			br.readLine();	// *1 첫행 읽어서 저장 안해버리기
			
			// # 한줄 읽어서 product 객체 생성하고 arraylist에 저장하기
			List<Product> products = new ArrayList<Product>();
			String line = null;
			while((line = br.readLine()) != null) {
				String[] arr = line.split(",");		// 데이터를 string배열에 저장
				Product product = new Product();	// 
				product.setNumber(line.split();
				
				product.setName(arr[1]);
				product.setPrice(Integer.parseInt(arr[2]));	// produce의 필드값에 저장
				products.add(product);	// 필드값이 저장된 product 인스턴스를 products에 저장
				System.out.println(product);
			}
			// * 인스턴스에 저장된 필드값을 list배열에 저장할 수 있는가?
			
			// # arraylist 확인(반복문)
			for(Product product : products) {
				System.out.println(product);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
