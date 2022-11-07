package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Free {
	private Long freeNo;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Long hit;
}
