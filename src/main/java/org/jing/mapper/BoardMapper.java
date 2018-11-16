package org.jing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.jing.domain.BoardVO;
import org.jing.domain.Criteria;
import org.jing.domain.PageParam;

public interface BoardMapper {
	
	//전체리스트
	//@Select("select * from tbl_board order by bno desc")
	public List<BoardVO> getList();

	
	//등록
	public void insert(BoardVO board);
	public void insertSelectKey(BoardVO board);
	
	//읽기
	public BoardVO read(int bno);
	
	//삭제
	public int delete(int bno);
	
	//수정
	public int update(BoardVO board);

	//페이징
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);	

}
