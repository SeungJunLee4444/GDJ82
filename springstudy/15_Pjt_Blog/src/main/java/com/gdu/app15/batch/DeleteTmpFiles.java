package com.gdu.app14.batch;

import java.io.File;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class DeleteTmpFiles {

	// 삭제되는지 테스트는 아래 크론식으로 확인
	// cron="0 0/1 * * * *"(1분마다 storage/temp 디렉터리의 파일을 지움)
	
	@Scheduled(cron="0 0 3 * * *")  // 새벽 3시마다 동작
	public void execute() {
		
		String tmpPath = "storage" + File.separator + "temp";
		File tmpDir = new File(tmpPath);
		
		if(tmpDir.exists()) {
			File[] tmpFiles = tmpDir.listFiles();
			for(File tmp : tmpFiles) {
				tmp.delete();
			}
		}
		
	}
	
}
