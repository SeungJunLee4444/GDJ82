package domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	private long no;  // 게시글이 많은 경우를 대비해 long 처리하였음
	private String writer;
	private String title;
	private String content;
	private String ip;
	private long hit;  // 조회수가 많은 경우를 대비해 long 처리하였음
	private Date create_date;
	private Date modify_date;
}
