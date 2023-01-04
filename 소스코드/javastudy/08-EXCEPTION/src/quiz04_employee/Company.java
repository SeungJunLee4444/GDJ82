package quiz04_employee;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Company {
	
	private Employee[] employees;
	private int idx;
	private Scanner sc;

	
	public Company() {
		employees = new Employee[5];
		sc = new Scanner(System.in);
	}
	
	public void addEmployee() throws EmployeeException {
		
		if(idx == employees.length) {
			throw new EmployeeException("Full", 1);
		}
		System.out.println("고용형태 선택(1. 정규 2. 비정규");
		
		// #1 	정규직과 비정규직을 구분하기 위한 int 변수 선언
		//		공통된 사원번호와 사원명은 입력
		// 		정규직과 비정규직의 차이인 월급과 시급은 switch문 안에서 구분
		
		int kind = sc.nextInt();
		sc.nextLine();
		int empNo = sc.nextInt();
		sc.nextLine();
		String name = sc.next();
		sc.nextLine();
		switch(kind) {									
		case 1 : System.out.println("기본급 >>>");
				int salary = sc.nextInt();				// # 직원마다 월급은 전부 다를것
				employees[idx++] = new Regular(empNo, name, salary);
				break;									// # 아래 실행될 문이 하나 있음(returnx)
		case 2 : System.out.println("시급 >>>");
				double hourpay = sc.nextDouble();
				sc.nextLine();
				System.out.println("근무시간 >>>");
				int workTimes = sc.nextInt();
				sc.nextLine();
				Temporary temporary = new Temporary(empNo, name);
				temporary.setHourpay(hourpay);
				temporary.setWorkTime(workTimes);
				employees[idx++] = temporary;
				break;
		default : throw new EmployeeException("Bad Request", 3);
		}
		System.out.println("사원 등록 완료. 현재 등록된 사원" + idx + "명");
	}
	
	
	public void dropEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		System.out.println("삭제할 사원번호 >>>");
		int empNo = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i < idx++; i++) {
			if(employees[i].getEmpNo() == empNo) {
				System.arraycopy(employees, i + 1, employees, i, idx - i - 1);
				employees[idx--] = null;
				System.out.println("사원삭제 완료. 현재 등록된 사원" + idx + "명");
				return;
			}
		} throw new EmployeeException("Not Found", 4);
	}
	
	public void findEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		System.out.println("조회할 사원번호 >>>");
		int empNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {					// # employees[i]와 employees[i].getEmpNo는 다른거알지?
				System.out.println("사원 조회완료. 조회한 사원정보");
				System.out.println(employees[i]);
				return;
			}
		}
		throw new EmployeeException("Not Found", 4);
		
	}
	
	public void printAllEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		int total = 0;
		System.out.println("전체사원목록" + idx + "명");
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i] + "[pay: " + employees[i].getPay() + "]");
			total += employees[i].getPay();
			System.out.println("Total salary:" + total);		// # 직원 월급 총액 구하기
		}
		
	}
	
	public void manage() {
	
		while(true) {
			try {
				System.out.println("1. 추가 2. 삭제 3. 조회 4. 목록 0. 종료 >>>");
				int choice = sc.nextInt();
				switch(choice) {
				case 1 : addEmployee(); break;
				case 2 : dropEmployee(); break;
				case 3 : findEmployee(); break;
				case 4 : printAllEmployee(); break;
				case 0 : return;
				default : throw new RuntimeException("잘못된 입력");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("정수만 입력 가능합니다");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (EmployeeException e) {
				System.out.println(e.getMessage() +"[" + e.getErrorCode() + "]");
		
	}

}
}
}
