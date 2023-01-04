package ex05_throws_o;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot1 {
	
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	public ParkingLot1() {
		cars = new Car[10];
		sc = new Scanner(System.in);
		
	}
	
	public void addCar() throws RuntimeException {
		
		// 1. 기존의 throw
		if(idx == cars.length) {
			throw new RuntimeException("FULL");	// #1 예외 발생 시 throw 작성
													//    runtimeexception이 catch의 e로 가고
		}
	}
	
	public void deleteCar() throws RuntimeException {	//#1 예외던지기
		if(idx == 0) {
			throw new RuntimeException("EMPTY");
		}
	}
	
	public void findCar() throws RuntimeException {
		if(idx == 0) {
			throw new RuntimeException("EMPTY");
		}
	}
	
	public void printAllCars() throws RuntimeException {
		if(idx == 0) {
			throw new RuntimeException("EMPTY");
		}
	}
	
	public void manage() {
		
		while(true) {
			try {
					System.out.println("1. 추가 2. 제거 3. 조회 4. 전체목록 0. 종료 >>>");
					int choice = sc.nextInt(); 
					switch(choice) {
					case 1: addCar(); break;
					case 2: deleteCar(); break;
					case 3: findCar(); break;
					case 4: printAllCars(); break;	
					case 0: return;
					default : throw new RuntimeException("Bad Request"); 
					// #2 Runtimeexception을 여기서 받아서 다시 던진다
					}
			} catch (InputMismatchException e) {
				sc.next();	// * 잘못된 입력을 없애고, 아래 출력문으로 수정
				System.out.println("처리명령은 정수(1~4)");	
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
				
			
		// trycatch문을 while안에 넣으면 예외사항이 발생해도 계속 진행된다
	}
	
	public static void main(String[] args) {
	
			new ParkingLot1().manage();
	


}
}
