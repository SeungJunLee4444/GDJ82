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
public class CommentDTO {
	private int commentNo;
	private int blogNo;
	private String content;
	private int state;
	private int depth;
	private int groupNo;
	private Date createDate;
}
