package domain;

public class Board {

	private int boardNo;
	private String title;
	private int hit;
	
	public Board() {
	
	}
	public Board(int boardNo, String title, int hit) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.hit = hit;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
