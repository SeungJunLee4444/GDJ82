package com.gdu.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieDTO {
	
	private int no;
	private String title;
	private String genre;
	private String description;
	private int star;

}
