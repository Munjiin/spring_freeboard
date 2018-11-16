package org.jing.domain;

import org.springframework.web.util.UriComponentsBuilder;


//boardmid project
import lombok.Data;

@Data
public class PageParam {

	private static final double PER = 10.0;
	private int page;
	private int display;

	private int total, start, end, bno;
	private boolean prev, next;

	private String[] types;
	private String type;
	private String keyword;


	
	public PageParam() {
		this.page = 1;
		this.display = 10;
	}
	
	public void setType(String type) {
		
		this.type = type;
		if(type == null || type.trim().length() == 0) {
			
		}
		this.types = type.split("");
		
	}
	
	public void setTotal(int total) {
		this.total = total;
		this.end = (int) (Math.ceil(this.page / PER)) * 10;
		this.start = this.end - 9;

		// end는 다시 계산해준다.
		if ((this.end * 10) > total) {
			this.end = (int) (Math.ceil(total / PER));
			this.next = false;
		} else {
			this.next = true;
		}

		this.prev = this.start != 1;

	}
	
	
	public int getSkip() {
		
		return (this.page -1 ) * 10;
	}


	public String getLink(String path) {
		return UriComponentsBuilder.fromPath(path)
				.queryParam("bno", this.bno)
				.queryParam("page", this.page)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword)
				.toUriString();
	}
	

	public static void main(String[] args) {

		PageParam obj = new PageParam();
		obj.setPage(7);
		obj.setBno(123);
		obj.setTotal(200);
		System.out.println(obj.getLink("redirect:/board/read"));

	}
}
