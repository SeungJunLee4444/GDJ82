package com.gdu.staff.config;

import com.gdu.staff.domain.StaffDTO;


public class StaffAppContext {
	
	// # 자바 컨테이너 : @repository 대신 만든 컨테이너
	

	public StaffDTO StaffDTO() {
		return new StaffDTO();
	}
	


}
