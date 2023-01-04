package quiz_04;

public class Product {
	
	// # 1-1
	
	private String name;
	private String price;
	
	// #1) meet, milk, snack의 모든 공통 요인을 지닌 부모클래스는 product
	public Product(String name, String price) {
		this.name = name;
		this.price = price;
	}
	// => 업캐스팅

	// # 1-2) 외부에서 물건과 가격을 확인할 수 있도록 getter, setter만들기
	// => 영수증 만들때 필요함(info)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

	
	
	
	
	


}
