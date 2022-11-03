package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Member {
	
	// [dto]
	
	private int memberNo;
	private String id, pw, name, email, registedDate; 

}
