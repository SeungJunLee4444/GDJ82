package quiz07_song;

public class Singer {
	
	private String name;
	private Song[] songs;
	private int idx;	// 저장개수(인덱스값) 0 1 2
	
	public Singer(String name, int cnt) {
		this.name = name;
		songs = new Song[cnt];
		// # Song 객체에 Song 클래스 객체 생성
	}
	
	public void addSong(Song song) {
		if(idx == songs.length) {	// # 저장된 곡이 length와 같으면 리턴
			return;					// # 
		}
		for(int i = 0; i < idx; i++) {
			if(songs[i].equals(song)) {
				return;
			}
		}
		songs[idx++] = song; // # 입력된 곡을 앨범에 저장하고 개수 늘리기
	}
	
	public void info() {
		System.out.println("가수이름" + name);
		System.out.println("대표곡");
		for(int i = 0; i < idx; i++) {		// # idx는 최종적으로 length값과 같아지기 때문에
			System.out.println(songs[i]);
		}
	
	}
	
	
	
	

}
