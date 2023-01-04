package ex01_one_dim;

public class Ex04_advanced_for {

	public static void main(String[] args) {
		
		// 5. 향상된 for문
		// => 배열 구조에서 사용되는 for문
		
		// 1) 일반 for문

		String[] friends = {"라이언", "프로도", "어파치"};
	
		for(int i = 0; i < friends.length; i++) {
			System.out.println(friends[i]);
			System.out.println((i+1) + "번째 친구" + friends[i]);
			
			
			
		// 2) 향상 for문
			
		for(String friend : friends) { 
		// => friends 배열의 모든 요소를 하나씩 String friend로 옮긴다
		System.out.print(friend + "\t");	
			
		} ;
		
		// => 어디까지나 보조적 수단, for 모든걸 대체는 못함
			
			
			
			
			
			
			
			
			
		}
	}

}
