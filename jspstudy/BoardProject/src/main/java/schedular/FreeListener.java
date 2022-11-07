package schedular;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

// # 리스너 어노테이션

// * 스케줄 조건
// 1) 조회수가 가장 높은 게시글의 정보를 저장한 'top.txt' 파일을 생성하는 Quartz Scheduler를 작성하시오.
// 2) 1분마다 실행

@WebListener
public class FreeListener implements ServletContextListener {
	
	private Scheduler scheduler;
	
	public FreeListener() {
		
		// # 리스너 객체 생성 --------------------
		SchedulerFactory factory = null;	
		try {
			factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// # contextInitialized 메서드 생성 : schedular 종료
	 public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
        	 if(scheduler != null) {
        		 scheduler.shutdown();
        	 }
         } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	// # contextInitialized 메서드 생성 : schedular의 jobm trigger 생성, 등록, 시작
	 public void contextInitialized(ServletContextEvent arg0)  { 
         try {
        	 // & job 생성
        	 JobDetail job = JobBuilder.newJob(TopHitJob.class)
        			 .withIdentity("job", "group2")
        			 .build();
        	 
        	 // & trigger 생성 : 1분마다
        	 Trigger trigger = TriggerBuilder.newTrigger()
        			 .withIdentity("trigger2", "group2")
        			 .startNow()
        			 .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
        			 .build();
        	 
        	
        	 // & schedular에 job, trigger 등록 
        	 scheduler.scheduleJob(job, trigger);
        	 
        	 // & schedular 실행
        	 scheduler.start();
        	 
         } catch (Exception e) {
			e.printStackTrace();
		}
         
	 }
}
