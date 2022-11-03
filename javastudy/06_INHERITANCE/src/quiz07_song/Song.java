package quiz07_song;

public class Song {
	
	private String title;
	private double playTime;
	
	public Song(String title, double playTime) {
		this.title = title;
		this.playTime = playTime;
	}

	@Override
	public String toString() {
		return "Song [title=" + title + ", playTime=" + playTime + "]";
	}
	
	// # 배열: 객체값 songs[i]를 문자열으로 변환해주는 toString 메서드
	
	

}
