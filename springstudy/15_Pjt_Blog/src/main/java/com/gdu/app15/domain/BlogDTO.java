package com.gdu.app15.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BlogDTO {
	private int blogNo;
	private String title;
	private String content;
	private int hit;
	private String ip;
	private Date createDate;
	private Date modifyDate;
}
