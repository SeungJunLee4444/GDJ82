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

import lombok.Builder;

/* 
 * & 리스너
 * - 해당 리스너(servletcontextlistener)가 job(servletcontextevent)을 처리
 * - job은 클래스(인터페이스job 상속) , listener은 리스너 파일
 * 
  ServletContextListener 인터페이스
  - 정의 : 웹 에플리케이션(프로젝트)의 LifeCycle을 가진다
  = 웹 에플리케이션과 함께 동작한다(웹 에플리케이션이 켜지면 같이 켜지고, 꺼지면 같이 꺼진다)
  
  - 전용 메서드
  (1) contextInitialized 메서드 : 웹 에플리케이션 시작시 동작
  (2) contextDestroyed 메서드
 
 */

/* -------------------------------------------------------------------------
 * & 정리
 * [ quartz ]: java schedulling 라이브러리, 작업들을 단발성, 주기성으로 실행할 수 있음
 * 
 * 1. 구성요소
 * (1) job : 실제 작업을 수행
 * (2) trigger : job을 수행하기 위한 조건(스케줄)을 정의한 객체
 * 		(1) simpletrigger	: 특정시간 및 횟수
 * 		(2) crontrigger 	: 주기적 반복
 * 		* 같은 그룹 내에는 동일한 이름을 가진 job을 생성할 수 없음
 * 		* 한개의 job은 복수의 trigger을 지닐 수 있다
 * 		=>  job : trigger = 1: n;
 * (3) listener : 작업의 시작, 중간, 끝, 에러를 처리할 수 있는 객체
 * 		(1) scheduleListener
 * 		(2) triggerListener
 * -------------------------------------------------------------------------
 * */

@WebListener
public class StudentListener implements ServletContextListener {
	
	// # 필드
	private Scheduler scheduler;
	
	// # 생성자										1. 리스너가 동작하면 scheduler가 생성된다
	// schedular 생성
    public StudentListener() {
    	SchedulerFactory factory = null;
    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
  
    }

	// # contextDestroyed 메서드
    // 2. schedular의 종료
    public void contextDestroyed(ServletContextEvent arg0)  { 
         try {
        	 if(scheduler != null) {
        		 scheduler.shutdown();
        	 }
         } catch (Exception e) {
			e.printStackTrace();
		}
    }

	// # contextInitialized 메서드
    // 3. scheduler의 job, trigger을 생성
    // - job, trigger 등록
    // - scheduler 시작
    public void contextInitialized(ServletContextEvent arg0)  { 
         try {
        	 
        	 /* 
        	  * # Cron expression(크론식) -- http://www.cronmaker.com/;jsessionid=node08y7sv32qjg4pfz1qkl8tjkhn729548.node0?0 참고
        	  * - 구성 : 초 분 시 일 월 요일 [년도]
        	  * => 왼쪽에서 초부터 나열
        	  * - 상세
        	  * 	1) 초 : 0 ~ 59
        	  * 	2) 분 : 0 ~ 59
        	  * 	3) 시 : 0 ~ 23
        	  * 	4) 일 : 1 ~ 31
        	  * 	5) 월 : 0 ~ 11 또는 JAN, FEB, MAR, APR, MAY, JUN, JUL, AUS, SEP, OCT, NOV, DEC
        	  		6) 요일 : 1(MON) - 7(SUN) 또는 MON, TUE, WED, THR, FRI, SAT, SUN
        	  * 
        	  * - 작성
        	  * 	1) * : 매번
        	  * 	2) ? : 설정없음(일, 요일에 사용)
        	  * 	3) / : 주기 (ex a/b a부터 b마다 동작, 동작주기를 의미)
        	  * 	ex) ex) 1 : 1초에 동작, 0/1 1초마다 동작
        	  * 
        	  * - 작성예시
        	  * 	1) 10초마다 매일 	: 0/10 * * * * ?
        	  * 	2) 매일 1분마다	 	: 0 0/1 * * * ?
        	  * 	3) 금요일 12시마다	: 0 0 12 ? * FRI(5)
        	  * 	& ? 를 일/요일에 사용하는 이유 : 날짜에따라 요일이 바뀌기떄문에 지정할수없음
        	  * 	4) 주말 12시마다 	: 0 0 12 ? * SAT,SUN
        	  * 
        	  * */
        	 
        	 
        	 
        	 // # job 생성
        	 JobDetail job = JobBuilder.newJob(StudentTop3Job.class)
        			 .withIdentity("job", "group1")
        			 .build();
        	 
        	 // # trigger 생성
        	 Trigger trigger = TriggerBuilder.newTrigger()
        			 .withIdentity("trigger1", "group1")
        			 .startNow()
        			 .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
        			 .build();
        	 
        	 // # scheduler에 job, trigger 등록
        	 scheduler.scheduleJob(job, trigger);
        	 
        	 // # scheduler 실행
        	 scheduler.start();

         } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
