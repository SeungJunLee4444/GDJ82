package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Board {
	
	// # Dto : 계층간 데이터 전달을 위한 클래스
	
	private int boardNo;
	private String title;
	private String content;
	private Date createDate;
	
	
	// & util.date와 sql.date의 차이-------------------------------------------------&
	// (1) util.date : 날짜, 시간정보 저장
	// (2) sql.date	 : 날짜 정보만 저장, jdbc에서 사용
	// * 자바 8 이후에는 util.date의 사용을 권장하지않는다
	//-------------------------------------------------------------------------------&
	
	
	// * 해당 필드값은 db의 칼럼명과 동일해야한다
	// * 자바의 카멜케이스방식과 db의 칼럼명 방식은 mybatis의 setting에서 자동으로 매핑해준다
	

	
	/*
	 * public Board(int boardNo, String title, String content, Date createDate) {
	 * super(); this.boardNo = boardNo; this.title = title; this.content = content;
	 * this.createDate = createDate; } public Board() { super(); // TODO
	 * Auto-generated constructor stub } public int getBoardNo() { return boardNo; }
	 * public void setBoardNo(int boardNo) { this.boardNo = boardNo; } public String
	 * getTitle() { return title; } public void setTitle(String title) { this.title
	 * = title; } public String getContent() { return content; } public void
	 * setContent(String content) { this.content = content; } public Date
	 * getCreateDate() { return createDate; } public void setCreateDate(Date
	 * createDate) { this.createDate = createDate; }
	 * 
	 */
	
	
	

}
