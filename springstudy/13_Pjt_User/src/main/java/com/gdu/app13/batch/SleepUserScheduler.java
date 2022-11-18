package com.gdu.app13.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app13.service.UserService;

@Component	
@EnableScheduling
public class SleepUserScheduler {
	
	// [[[ 스프링의 스케줄러 : 하루에 한번 돌게 만들겠다
	@Autowired
	private UserService userService;
	
	// 매일 새벽 1시 : @Scheduled(cron="0 0 1 * * *");
	
	// * 크론식 설정을 통해 매번 시간에 따른 휴먼계정 처리가 자동으로 가능하다
	
	// # 크론식 
	@Scheduled(cron="0 37 12 * * *")	// 10시 17분에 동작
	public void execute() {
		userService.sleepUserHandle();
		
	// * 스케줄러는 실행한 서비스를 자동으로 커밋한다
		
		
		
	}

}
