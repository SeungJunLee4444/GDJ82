package com.gdu.app01.xml04;

public class Dao {
	
	// [4장 : 스프링에서는 싱글턴을 사용할 필요가없다]
	// 1) dao 클래스에서 싱글턴 코드를 사용하지않는다
	// 2) 스프링 컨테이너에 빈으로 등록할 때 scope 속성으로 싱글턴을 적용할 수 있다
	// * scope는 기본적으로 생략하면 싱글턴이 적용된다
	
	// method
	public void list() {
		System.out.println("목록 가져오기");
	}
	
	public void detail() {
		System.out.println("상세보기");
	}

}
