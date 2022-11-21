package com.gdu.app14.domain;

import java.sql.Timestamp;

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
	private Timestamp createDate;	// * db의 timestamp와 일치시키기
	private Timestamp modifyDate;
	private int attachCnt;			// * 업로드 개수 : 조인으로 해결
}
