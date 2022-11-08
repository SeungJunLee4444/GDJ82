package com.gdu.app09.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data	// * getter + setter + tostring


public class BoardDTO {
	
	// # DTO : 계층간 데이터 이동용 클래스
	
	private int boardNo;
	private String title, content, writer, createDate, modifyDate;
	
	
	
	

	
	
	

}
