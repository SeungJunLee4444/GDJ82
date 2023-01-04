package com.gdu.app03.domain;

import java.sql.Date;

public class Board {
	
	
	// # DTO 
	// * 롬복은 아직 사용안한 상태
	
	// # dto를 xml 또는 java를 자바빈으로 만들어 가져오는 방법
	
	
	
	// 필드
	private int boardNo;
	private String title;
	private String createDate;
	
	
	
	// 생성자
	public Board() {
		super();
	}

	public Board(int boardNo, String title, String createDate) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.createDate = createDate;
	}

	
	
	// getter/setter
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
