package prac4;

public class Main {

	public static void main(String[] args) {
		
		Tour tour1 = new KoreaTour();
		TourGuide guide1 = new TourGuide(tour1);
		guide1.sightseeing();  // 광화문
		guide1.leisure();      // 한강유람선
		
		Tour tour2 = new GuamTour();
		TourGuide guide2 = new TourGuide(tour2);
		guide2.sightseeing();  // 사랑의절벽
		guide2.leisure();      // 패들보트

	}

}
