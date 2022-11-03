package quiz02_rsp;

import java.util.HashSet;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		
	
	
	String[] rsp = {"가위", "바위", "보"};
	
	// rsp 배열에서 임의의 값을 선택하여 hashset에 저장한다
	// rsp[0] == "가위"
	// rsp[1] == "바위"
	// rsp[2] == "보"
	
	// 몇번만에 hashset에 모두 넣을 수 있는지 확인한다
	
	Set<String> set = new HashSet<String>();
	int cnt = 0;
	
	// set에 저장된게 3개가 아니면 계속해라
	while(set.size() < 3) {
		int i = (int)(Math.random() * 3);
		set.add(rsp[i]);
		cnt++;
		// * 중복되는 값은 출력되지않는다
		
		System.out.println(set);
		System.out.println(cnt);	
	}
	
	
}
}
