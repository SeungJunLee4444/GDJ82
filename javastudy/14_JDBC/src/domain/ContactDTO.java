package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data					// (getter, setter, tostring 전부 들어가있음)
@NoArgsConstructor		// 디폴트 생성자
@AllArgsConstructor		// 매개변수가 있는 생성자
@Builder

// 롬복으로 추가된 여부 확인 : windows - showview - 'outline'

public class ContactDTO {
	
	private int contact_no;
	private String name;
	private String tel;
	private String email;
	private Date reg_date;
	
	
	
	



}
