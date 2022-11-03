package ex06_exception_class_o;

import java.util.InputMismatchException;
import java.util.Scanner;

import ex05_throws_o.Car;

public class ParkingLot {
	
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	public ParkingLot() {
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	public void addCar() throws MyException {
		if(idx == cars.length) {
			throw new MyException("Full", 1000);	
			// #1 오류발생 : myexception은 exception의 직속이기 때문에 try-catch 생략x
			// throws도 생략 불가능?
			// => 메서드 옆에 throws를 꼭 써야한다
		}
	}
	public void deleteCar() throws MyException {
		if(idx == 0) {
			throw new MyException("EMPTY", 2000);	
		}
	}
	public void manage() {
		while(true) {
			try {
				System.out.println("1. 추가 2. 삭제 3. 종료 >>>");	
				int choice = sc.nextInt();
				switch(choice) {
				case 1 : addCar(); break;
				case 2 : deleteCar(); break;
				case 0 : return;
				default : throw new RuntimeException("Bad Request");
				}
			} catch (MyException e) {
				// #2 객체 e에는 메시지와 , 숫자 두 값이 포함되있다
				System.out.println(e.getMessage() + "[" + e.getErrorCode() + "]");
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("처리 명령은 정수만 가능");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
	
		new ParkingLot().manage();
		
		
	}

}
