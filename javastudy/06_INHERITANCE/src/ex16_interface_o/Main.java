package ex16_interface_o;

public class Main {

	public static void main(String[] args) {
		
		Dog dog = new Dog("백구");
		
		Cat cat = new Cat("냥냥이");
		
		Snake snake = new Snake("낼름이");
		
		Person person = new Person();
		person.foodFeed(dog, "개껌");
		person.foodFeed(cat, "츄르");
		person.foodFeed(snake, "쥐");
		
		// [상속 - 인터페이스 활용)
		
		person.walk(dog); 
		person.walk(cat); 
//		person.walk(snake); 	// 실행을 못하게 막고싶다
		
		// # public void walk(Pet pet)는 불가능
		// => walkable 인터페이스를 형성하고, 
		

	}

}
