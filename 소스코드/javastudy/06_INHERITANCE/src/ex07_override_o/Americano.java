package ex07_override_o;

public class Americano extends Espresso {
	
	// # ex07 메서드 오버라이징
	
	// # 부모가 에스프레소
	
	private int extraWater;
	// # 에스프레소에 물을 더 넣으면 아메리카노
	
//	@Override
//	public void taste() {
//		System.out.println("고소하다");
//	}
	
	@Override
	public void taste() {
		System.out.println("덜 쓰다");
	}	// TO DO 해야할 일을 적어 둠
	// => 오버라이딩도 자동호출이 가능하다 (ctrl + 스페이스바) , source

	
	

}
