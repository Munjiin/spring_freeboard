package org.jing.service;

import java.util.List;

import org.jing.domain.BoardVO;
import org.jing.domain.Criteria;
import org.jing.domain.PageParam;
import org.jing.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service //비즈니스 영역을 담당하는 객체
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		log.info("REGISTER SERVICE.." +board);
		mapper.insert(board);
		
	}

	@Override
	public BoardVO get(int bno) {
		log.info("GET SERVICE.."+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("MODIFY SERVICE.." + board);
		return mapper.update(board) ==1; //정상이면 1이 출력 되므로 true false 처리
	}

	@Override
	public boolean remove(int bno) {
		log.info("REMOVE SERVICE.." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(cri);
	}

//	@Override
//	public List<BoardVO> getList() {
//		return mapper.getList();
//	}
//	








}
