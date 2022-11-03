package quiz07_song;

public class Producer {
	
	private String name;

	public Producer(String name) {
		this.name = name;
	}
	
	public void produce(Singer singer, Song song) {
		singer.addSong(song);	
		
		// # song을 singer에게 준다
	}
	
	
	

}
