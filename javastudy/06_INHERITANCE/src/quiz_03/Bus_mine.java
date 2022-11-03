package quiz_03;

public class Bus_mine {
	
	// # quiz 03 : 전형적인 객체지향 프로그램
	
	private Seat[] seats;
	// # 좌석이 여러개
	private int limit;
	// # 좌석 제한수
	
	// Bus 생성자에서 배열 생성을 진행
	public Bus_mine(int cnt) {
		seats = new Seat[cnt];
		// # 배열생성
		// # cnt개 만큼의 좌석이 생성
		limit = cnt;
		for(int i = 0; i< cnt; i++) {
			seats[i] = new Seat();
		}
		// # 좌석 제한수는 cnt개 만큼
	}
	
	// ride 메서드
	public void ride(int seatNo, Person person) {
		// person이 업캐스팅
		// # 세개의 매개변수를 전부 받을 수 있는 person 선언
		
		// # 존재하지 않는 시트번호 
		if(seatNo <= 0 || seatNo > limit) {
			return; // 종료
		}
		// # seat 번호에 탑승한 person 저장하기
		if(seats[seatNo - 1].getPerson() == null) {				// # 자리가 남아있으면 집어넣자
			seats[seatNo - 1].setPerson(person);
		}
		
		// # 편의상 seatNo는 1에서 25까지 사용하고 있다, 인덱스값이니 -1
		// # setperson에 저장된 사람
		
		// # get 값이 뭔지 확인하기 위해 불러오는 메서드 / set 입력한 값
		
		
		
	}
	
	// info() 메서드
	public void info() {
		for(int i = 0; i <limit; i++) {
			// # limit는 seats 배열의 length와 같음
			Seat seat = seats[i];
			if(seat != null) {	// if(seat.getPerson() != null), if(seats[i].getPerson() != null)
				Person person = seat.getPerson(); // seat seat 없애고, Person person = seats[i].getPerson();
				System.out.println((i + 1) + "," + person.getName());
				// # person person = seat.getperson(); 없애고
				// => System.out.println((i + 1) + "," + seats[i].getperson().getName());
			}
		}
	}
	
	
	
	
	
	

}
