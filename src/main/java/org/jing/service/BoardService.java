package org.jing.service;

import java.util.List;

import org.jing.domain.BoardVO;
import org.jing.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(int bno); //특정한 게시물 가져오기
	
	public boolean modify(BoardVO board);
	
	public boolean remove(int bno);
	
	//public List<BoardVO> getList();
	
	//public List<BoardVO> listPage(int page);//페이지
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	

}
