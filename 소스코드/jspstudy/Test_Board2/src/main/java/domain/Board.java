package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Board {
	
	//# dto : 계층간 데이터 이동
	

	private int boardNo;	
	private String name;
	private String title;
	private String content;
	private Date createDate;
	
	
	
	
}