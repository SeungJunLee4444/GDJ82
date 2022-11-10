package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil {
	
	// # 게시글 목록에 필요한 필드
	private int page;				// 현재 페이지(파라미터로 받아온다)
	private int totalRecord; 		// 전체 레코드 개수(db에서 구해옴)
	private int begin;				// 각 페이지 시작번호(recordPerPage와 page로 계산가능)
	private int end;				// 각 페이지 끝번호(begin과 recordPerPage로 계산가능)
	private int recordPerPage;	    // + 파라미터로 받아왔음
	
	// * begin, end를 구하기 위해 필요한 정보 : page, recordPerPage, totalRecord
	
	// # 페이지 블록에 필요한 필드
	private int totalPage;			// 전체 페이지 개수(totalRecord와 recordPerPage로 계산)
	private int pagePerBlock = 5;	// 블록에 표시할 페이지 개수(임의로 정함)
	private int beginPage;			// 블록의 시작 페이지 번호(계산한다)
	private int endPage;			// 블록의 끝 페이지 번호(계산한다)
	
	
public void setPageUtil(int page, int recordPerPage, int totalRecord) {
		
		// page, totalRecord 필드 저장
		this.page = page;
		this.recordPerPage = recordPerPage;
		this.totalRecord = totalRecord;
		
		// begin, end 계산
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		// totalPage 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		// beginPage, endPage 계산
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		
	}
	
	public String getPaging(String path) {
		
		StringBuilder sb = new StringBuilder();
		
		// # 전달받은 path에 ?가 있는지 확인하고, ?가 존재하면 ?path를 &path로 변경해줘야한다 -------------------------------
		// - 이유 : ?가 2개가 존재하기 때문
		
		// & path에 파라미터가 없는 경우 : /emp/list     		=> /emp/list?page=1 (페이지 앞에 ?를 사용)
		// & path에 파라미터가 있는 경우 : /emp/list?column=emp => /emp/list?column=emp&query=150&page=1 (페이지 앞에 & 사용)
		
		// & contains("?") : 특정 문자가 포함되있는지 확인해주는 메서드
		if(path.contains("?")) {
			path += "&";
		} else {
			path += "?";
		}
		
		
		
		// # 페이지 이동을 위한 블록 생성 ------------------------------------------------------ 
		sb.append("<div class=\"paging\">");
		
		// 이전블록 : 1block이 아니면 이전블록이 있다
		if(beginPage != 1) {
			sb.append("<a class=\"lnk\" href=\"" + path + "page=" + (beginPage-1) + "\">◀</a>");
		} else {
			sb.append("<span class=\"hidden\">◀</span>");
		}
		
		// 페이지번호 : 현재 페이지는 링크가 없어야 한다
		int endPage = beginPage + pagePerBlock - 1;
		for(int p = beginPage; p <= endPage; p++) {	// <- 페이지가 5개만 나오게 되는 이유(계산시 5페이지만 나오게 만들었음 애초에)
			if(p <= totalPage) {
				if(p == page) {
					sb.append("<span class=\"now_page\">" + p + "</span>");
				} else {
					sb.append("<a class=\"lnk\" href=\"" + path + "page=" + p + "\">" + p + "</a>");
				}				
			} else {
				sb.append("<span class=\"hidden\">" + p + "</span>");
			}
		}
		// * 블록 1~5를 수평으로 나열해야하기 때문에 인라인요소인 a태그와 span태그를 사용한다
		
		// 다음블록 : 마지막 블록이 아니면 다음블록이 있다
		if(endPage < totalPage) {
			sb.append("<a class=\"lnk\" href=\"" + path + "page=" + (endPage+1) + "\">▶</a>");
		} else {
			sb.append("<span class=\"hidden\">▶</span>");
		}
		
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
}