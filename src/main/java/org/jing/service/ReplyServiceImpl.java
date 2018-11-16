package org.jing.service;

import java.util.List;

import org.jing.domain.Criteria;
import org.jing.domain.ReplyPageDTO;
import org.jing.domain.ReplyVO;
import org.jing.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_=@Autowired)//자동 주입
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("register..." + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(int rno) {
		// TODO Auto-generated method stub
		log.info("get.." +  rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		
		log.info("modify..." + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(int rno) {
		// TODO Auto-generated method stub
		
		log.info("remove.." + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		log.info("get Reply list of a board " + bno);
		//return mapper.getListWithPaging(cri, bno);
		return mapper.getListWithPaging(cri, bno);
	}



	@Override
	public ReplyPageDTO getListPage(Criteria cri, int bno) {
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}

}
