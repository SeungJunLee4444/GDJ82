package ex09_upcasting_o;

public class Main {

	public static void main(String[] args) {
		
		// ex09 : 업캐스팅
		// # 업캐스팅 : 슈퍼클래스 객체로 서브클래스 호출해서 만들기
	
		
		// # new student 와 new alba는 모두 person 타입으로 처리할 수 있다
		// => 객체가 다른데 동일한 타입으로 처리 가능하다
		
		// 한 교실에 student와 alba가 섞여있다
		// => person 타입의 배열을 이용해 모두 처리할 수 있다
		
		Person[] people = new Person[10];
		
		people[0] = new Alba();	// 먹는다 공부한다 일한다
		people[1] = new Alba();	// 먹는다 공부한다 일한다
		people[2] = new Student();	// 먹는다 공부한다
		// # 
	
		
//		for(int i = 0; i < people.length; i++) {
//			if(people[i] != null) {
//				people[i].eat();
//				people[i].study();
//				people[i].work();
//			}
			// null 오류, 배열에 빈칸이 있기 때문
			// => if문을 통한 해결
		
		for(Person person : people) {
			// * 0,1,2까지의 값은 채워지지않았기 때문에 null
				person.eat();
				person.study();
				person.work();
			
		}
		
	}
}
