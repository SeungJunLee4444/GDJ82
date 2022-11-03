package schedular;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.Student;
import repository.StudentDAO;

public class StudentTop3Job implements Job {
	
	// # scheduler의 job 인터페이스
	// - 정의 : 스케줄러가 처리하는 작업
	

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		List<Student> top3 = StudentDAO.getInstance().selectStudentTop3();
		
		for(Student s : top3) {
			System.out.println(s.getName() + "(" + s.getAve() + "점)" );
		}

	}

}
