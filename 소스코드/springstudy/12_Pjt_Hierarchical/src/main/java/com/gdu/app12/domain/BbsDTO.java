package com.gdu.app12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BbsDTO {
	
	private int bbsNo, state, depth, groupNo, groupOrder;
	private String writer, title, ip;
	private Date createDate;
}
