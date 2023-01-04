package ex05_throws_o;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot2 {
	
	// # 필드
	private String name;
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	public ParkingLot2(String name) {
	
		this.name = name;
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	private void addCar() throws RuntimeException  {
		System.out.println("현재 등록된 차량" + idx + "대");
		if(idx == cars.length) {
//			System.out.println("더 이상 차량 등록이 불가능합니다");
			throw new RuntimeException("더 이상 차량 등록이 불가능합니다");
		}
		System.out.println("차량번호 >>>");
		String carNo = sc.next().trim();//--------------------------------? 공백없는 문자열처리
		sc.nextLine();
		System.out.println("모델 >>>");
		String model = sc.next().trim();
		sc.nextLine();
		Car car = new Car(carNo, model);	// * 반복문 x
		cars[idx++] = car;	//--------------------------------------------------?
		System.out.println("차량번호" + carNo + "차량의 등록되었습니다"); //----?
		
		// * idx는 인덱스값이지만 최종적으로 배열의 길이와 같은 값을 지니게된다
		
	}
	
	private void deleteCar( ) throws RuntimeException {
		if(idx == 0) {
			throw new RuntimeException("등록된 차량이 없습니다");
//			return; * throw를 쓰면 return 쓸필요 x
			// throw가 발생하면 밑에 코드 실행x
		}
		System.out.println("제거할 차량번호 >>>");
		String carNo = sc.next();
		sc.nextLine();
		
		for(int i = 0; i < idx; i++) {	//------------------------------------?
			// * 배열 전체에서 제거할 차량번호를 찾는 것이니 idx가 아닌 cars.length ?
			
			if(cars[i].getCarNo().equals(carNo)) {
				System.arraycopy(cars, i + 1, cars, i, idx -i -1);
				
				// i + 1 : 삭제하려는 값의 바로 뒤
				// i : 삭제하려는 값의 위치
				// // => 를 덮어씌워서 없앤다
				// idx -i -1 : 삭제하려는 값 뒤에 있는 모든 배열의 길이
				
				
				cars[--idx] = null;
				System.out.println("차량변호" + carNo + "차량이 삭제되었습니다");
				// * 만약 else 문을 사용해서 출력문을 작성했다면,
				// => i값마다 차량이 존재하지 않다는 출력문이 계속 나올것
//			} else {
				return;
			}
				// * return 이 없어서, 성공했는데도 밑에 출력문이 나왔음--------x
			}
		System.out.println("대상 차량이 존재하지 않습니다");
	}
	
	private void printAllCars() throws RuntimeException {
		if(idx == 0) {
			throw new RuntimeException("등록된 차량이 없습니다");
			//return;
		}
		System.out.println(name + "차량 목록");
		for(int i = 0; i < idx; i++) {
			System.out.println(cars[i]); //-------------------------------------?
		// * for 향상문과 null값을 제외한 값을 출력
//		for(Car car : cars) {
//			if(car != null) {
//				System.out.println(car);
//			}
//			}
		}
	}

	public void manage() {
		
		try {
		
		while(true) {
			System.out.println("1. 추가 2. 삭제 3. 전체 0. 종료");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1: addCar(); break;
			case 2: deleteCar(); break;
			case 3: printAllCars(); break;
			case 0: System.out.println("종료"); return;
			default : System.out.println("존재하지 않는 메뉴입니다");
			}
		}
		} catch (InputMismatchException e) {
			sc.next();
			System.out.println("입력될 정수값은 1~4 입니다");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		
		
	}
		
}
	
	public static void main(String[] args) {
		
		new ParkingLot2("대박주차장").manage();
		
	}
	
}


