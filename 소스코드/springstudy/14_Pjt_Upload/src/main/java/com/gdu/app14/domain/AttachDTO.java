package com.gdu.app14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttachDTO {
	private int attachNo;
	private String path;
	private String origin;
	private String filesystem;
	private int downloadCnt;
	private int uploadNo;		// * pk로 가진 uploaddto와 동일한 uploadno를 fk로 가져야하는데,
								// 문제발생
}
