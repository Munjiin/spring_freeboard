package org.jing.mapper;

import java.util.List;

import org.jing.domain.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(int bno); //특정 게시물의 번호로 첨부파일을 찾는 작업.

}
