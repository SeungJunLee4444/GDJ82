package com.gdu.mysql.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDTO {
	private int uploadNo;
	private String title;
	private String content;
	private Date createDate;	// mysql의 DATETIME은 DATE로 변환
	private Date modifyDate;	// mysql의 TIMESTAMP는 TIMESTAMP로 변환
	private int attachCnt;
}
