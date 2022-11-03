package com.gdu.app06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data	// * getter + setter + tostring


public class BoardDTO {
	
	// # DTO : 계층간 데이터 이동용 클래스
	
	private int board_no;
	private String title, content, writer, create_date, modify_date;
	
	
	
	

	
	
	

}
