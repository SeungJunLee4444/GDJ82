package schedular;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Free;
import repository.FreeDAO;

public class TopHitJob implements Job {	// * job : schedular 
	
	// # scheduler의 job 인터페이스
	// - 정의 : 스케줄러가 처리하는 작업
	

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		Free free = FreeDAO.getInstance().selectBoardTopHit();
		String strFreeNo = free.getFreeNo().toString();
		String strHit = free.getHit().toString();

		
		// # 파일생성
		File dir = new File("c:\\BoardProject");
		if(dir.exists() == false) {	
			dir.mkdirs();		
		} 
		
		File file = new File(dir, "top.txt");
		
		BufferedWriter bf = null;

		try {
			
			bf = new BufferedWriter(new FileWriter(file));
			bf.write("게시글번호 " + strFreeNo +"\n");
			bf.write("작성자  " + free.getWriter() +"\n");
			bf.write("제목 " + free.getTitle() + "\n");
			bf.write("작성IP " + free.getIp() + "\n");
			bf.write("조회수 " + strHit + "\n");
			bf.write("내용\n");
			bf.write(free.getContent());
			bf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
			
			
		
	
		
		
	
	}
			
			
			
	
		

	

}
