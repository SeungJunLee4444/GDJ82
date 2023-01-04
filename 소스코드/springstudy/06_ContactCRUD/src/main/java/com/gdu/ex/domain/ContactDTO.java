package com.gdu.ex.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ContactDTO {
	
	// # dto
	
	private int no;
	private String name, tel, addr, email, note;
	
	

}


