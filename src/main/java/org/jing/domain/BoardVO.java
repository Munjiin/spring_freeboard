package org.jing.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private int bno,hit;
	private String title,content,writer,del;
	private Date regdate,updatedate;
	
	private int replyCnt;
	
	private List<BoardAttachVO> attachList;

}
