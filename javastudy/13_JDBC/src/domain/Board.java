package domain;

import java.sql.Date;

public class Board {
	
	// 2) 게시판 1개의 정보를 담을 수 있는 객체를 생성하는 클래스
	// (이런 클래스를 Bean, VO(ValueObject), DTO라 한다)
	// => 테이블의 칼럼을 1대1로 변수로 매칭
	// (가급적 칼럼이름이 변수이름과 같은게 좋음)
	// => 형태 : 생성자, getter, setter만 만듬
	
	private int board_no; 
	private String title;
	private String content;

	private int hit;
	private Date create_date;	// 오라클의 _를 자바에서는 사용x
	// sql의 date사용
	
	public Board() {}
	
	
	public Board(int board_no, String title, String content, int hit, Date create_date) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.create_date = create_date;
	}


	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", create_date=" + create_date + "]";
	}
	
	
	
}
