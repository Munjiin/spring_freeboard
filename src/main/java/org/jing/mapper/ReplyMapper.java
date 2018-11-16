package org.jing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jing.domain.Criteria;
import org.jing.domain.ReplyVO;

public interface ReplyMapper {
	
	//등록
	public int insert(ReplyVO vo);
	
	//조회
	public ReplyVO read(int bno);
	
	//삭제
	public int delete(int bno);
	
	//수정
	public int update(ReplyVO reply);
	
	//페이징
	public List<ReplyVO> getListWithPaging(
		@Param("cri") Criteria cri,
		@Param("bno") int bno);
	
	
	
	public int getCountByBno(int bno);
	
	

}
