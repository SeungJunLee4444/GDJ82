package ex05_lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder				// 빌터패턴
@ToString
// => singleton 제외 다 할 수 있음

public class User {
	
	private int userNo;
	private String id;
	private String email;

}
