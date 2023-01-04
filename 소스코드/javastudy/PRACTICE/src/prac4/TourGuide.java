package prac4;

public class TourGuide {

	// 필드
	private Tour tour;

	// 생성자
	public TourGuide(Tour tour) {
		super();
		this.tour = tour;
	}
	
	// 메소드
	public void sightseeing() {
		tour.sightseeing();
	}
	
	public void leisure() {
		tour.leisure();
	}
	
}
