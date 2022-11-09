package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil_ {
	
	// # 게시글 목록에 필요한 필드
	private int page;				// 현재 페이지(파라미터로 받아온다)
	private int totalRecord; 		// 전체 레코드 개수(db에서 구해옴)
	private int begin;				// 각 페이지 시작번호(recordPerPage와 page로 계산가능)
	private int end;				// 각 페이지 끝번호(begin과 recordPerPage로 계산가능)
	private int recordPerPage = 10;	// 페이지별 레코드 개수(임의로 정함)
	
	// * begin, end를 구하기 위해 필요한 정보 : page, recordPerPage, totalRecord
	
	// # 페이지 블록에 필요한 필드
	private int totalPage;			// 전체 페이지 개수(totalRecord와 recordPerPage로 계산)
	private int pagePerBlock = 5;	// 블록에 표시할 페이지 개수(임의로 정함)
	private int beginPage;			// 블록의 시작 페이지 번호(계산한다)
	private int endPage;			// 블록의 끝 페이지 번호(계산한다)
	
	
	
	public void setPageUtil(int page, int totalRecord) {
		// # 게시글 목록에 필요한 값 --------------------------------------------------------
		// & 필드저장
		this.page = page;
		this.totalRecord = totalRecord;
		
		// & begin, end 계산
		begin = (page -1) * recordPerPage + 1; 
		end = begin + recordPerPage -1;
		// * 마지막 페이지 레코드 처리 : 페이지의 마지막 게시글 번호를 totalRecord로 만든다
		if(end > totalRecord) {
			end = totalRecord;
		
		}

		// # 페이지 블록에 필요한 값 ---------------------------------------------------------
		/* 
		  	pagePerBlock : 한 블록에 표시할 페이지 개수 : 5
			각 블록의 시작페이지 : beginPage
			각 블록의 끝 페이지  : endPage
			전체 페이지 개수     : totalPage
		  
		   - block : 하단 페이지 목록
			1block : 1 2 3 4 5 
			2block : 6 7 8 9 10
			3block : 11  
		 */
		// & 전체 페이지 수 계산
		totalPage = totalRecord / recordPerPage;	// 페이지 수 : 몫으로 구한다
		if(totalRecord % recordPerPage != 0) {		// 게시글의 나머지가 존재하면 1페이지 증가
			totalPage++;
		}
		
		// & beginPage, endPage 계산
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;	// * totalPage는 11까지 있기 때문에, endPage가 12 이상이 되면 11로 고정
		}
		// page(1~5) : beginpage = 1 , endpage = 5
		// page(6~10): beginPage = 6 , endpage = 10
		
		// page(11)  : 
		
	}
	
	// # jsp 영역 경로의 변수처리 
	public String getPaging(String path) {	// * 매개변수에는 path에는 컨텍스트패스 + mapping이 들어와야한다
		StringBuilder sb = new StringBuilder();
		
		// & 이전블록 : 1block이 아니면 이전블록이 없다
		if(beginPage != 1) {
			
		// - 형태 : <a href="path?page=beginPage-1">◀</a>
			sb.append("<a href=\"" + path + "?page=" + (beginPage-1) + "\">◀</a>");
		}
		
		// & 페이지번호 : 현재 페이지는 링크가 없다
		for(int p = beginPage; p <= endPage; p++) {
			if(p == page) {
				sb.append(p);
			} else {
				sb.append("<a href=\"" + path + "?page=" + p + "\">" + p + "</a>");
			}
		}
	
		// & 다음블록 : 마지막 블록이 아니면 다음블록이 있다
		// - 형태 : <a href="path?page=endPage + 1">▶</a>
		if(endPage != totalPage) {
			sb.append("<a href=\"" + path + "?page=" + (endPage+1) + "\">▶</a>");
		}
		
		return sb.toString();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
