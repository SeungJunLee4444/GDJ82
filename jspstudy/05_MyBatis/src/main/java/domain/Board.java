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
	
	// dto : 계층간 데이터 이동
	// => controller, repository, service 내에 각 board 타입을 이용해 데이터 전달을 했음

	private int boardNo;	// => 자바의 카멜케이스, sql의 _작성법중에 자바식 작성법으로 작성
	private String title;
	private String content;
	private Date createDate;
	
	
}