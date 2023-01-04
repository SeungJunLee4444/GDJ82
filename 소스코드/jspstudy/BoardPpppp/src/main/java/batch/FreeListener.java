package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class FreeListener implements ServletContextListener {
	
	private Scheduler scheduler;
	
	public FreeListener() {
		try {
        	scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
    		if (scheduler != null) {
    			scheduler.shutdown();
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			JobDetail job = JobBuilder.newJob(FreeJob.class)
					.withIdentity("job1", "group1")
					.build();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
					.build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
