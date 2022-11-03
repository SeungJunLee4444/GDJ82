package quiz_03;

public class Bus {

	private Seat[] seats;      // # 배열 선언
	private int limit;         // # 버스 정원
	
	// Bus 생성자에서 배열 생성을 진행함
	public Bus(int cnt) {
		seats = new Seat[cnt];  // # cnt 길이의 배열생성 
		limit = cnt;			// # 버스정원은 cnt다?
		for(int i = 0; i < cnt; i++) {
			seats[i] = new Seat(); // # seat 배열에 각각 seat 생성자 넣기
		}
	}
	
	// ride() 메소드
	public void ride(int seatNo, Person person) { // # seatNo는 좌석수(indx값 +1)
		// 존재하지 않는 시트번호
		if(seatNo <= 0 || seatNo > limit) {
			return;  // ride() 메소드 종료
		}
		// 시트에 사람이 없으면, 시트번호에 Person 저장하기
		Seat seat = seats[seatNo - 1];	// 
		Person p = seat.getPerson();	// # 사람을 데려온다
		if(p == null) {
			seat.setPerson(person);		// # seat 배열에 사람을 앉힌다
		}
	}
	
	// info() 메소드
	public void info() {
		for(int i = 0; i < limit; i++) {  // limit은 seats 배열의 length와 같음
			Seat seat = seats[i];
			Person person = seat.getPerson();  // Person person = seats[i].getPerson();
			if(person != null) {  // if(seat.getPerson() != null), if(seats[i].getPerson() != null)
				System.out.println((i + 1) + "," + person.getName());
				// System.out.println((i + 1) + "," + seat.getPerson().getName());
				// System.out.println((i + 1) + "," + seats[i].getPerson().getName());
			} else {
				System.out.println((i + 1) + ", 비어 있음");
			}
		}
	}
	
}