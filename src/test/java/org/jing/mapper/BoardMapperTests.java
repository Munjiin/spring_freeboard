package org.jing.mapper;

import java.util.List;

import org.jing.domain.BoardVO;
import org.jing.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_=@Autowired)//자동 주입
	private BoardMapper mapper;
	
	//리스트 테스트
	@Test
	public void testGetList() {
		log.info("조회 테스트 실행");
	//	mapper.getList().forEach(board->log.info(board));
	}
	
	@Test
	public void testPaging() {
		Criteria cri  = new Criteria();
		
		cri.setPageNum(5);
		cri.setAmount(10);
		
		log.info(cri);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
	
	@Test
	public void testPagingCount() {
		Criteria cri  = new Criteria();
		
		cri.setPageNum(5);
		cri.setAmount(10);
		
		log.info(cri);
		
		log.info(mapper.getTotalCount(cri));
		
	}	
	
	//페이지 테스트
	
		
//		log.info("페이지 테스트");
//		
//		Criteria cri = new Criteria();
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		
//		list.forEach(board->log.info(board));
	
	
	
	//등록 테스트	
	@Test
	public void testInsert() {
		log.info("등록 테스트 실행");
		
		BoardVO board = new BoardVO();
		board.setTitle("2등록테스트함다");
		board.setContent("한번에 됐으면 허는게 솔직헌 바람,,");
		board.setWriter("user00");
		
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectkey() {
		BoardVO board = new BoardVO();
		board.setTitle("인설트셀렉트");
		board.setContent("제발 한번에 얍");
		board.setWriter("user00");
		
		mapper.insertSelectKey(board);
	
		
		log.info(board);
	}
	
	//조회 테스트
	@Test
	public void testRead() {
		log.info("조회 테스트");
		
		BoardVO board = mapper.read(13);
		log.info(board);
	}
	
	//삭제 테스트. 올바른 삭제일 시 1 출력
	@Test
	public void testDelete() {
		log.info("삭제 테스트");
		
		log.info("DELETE COUNT: " + mapper.delete(5));
	}
	
	//수정 테스트
	@Test
	public void testUpdate() {
		log.info("수정 테스트");
		
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정테서터2");
		board.setContent("수정된 내횽");
		
		
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("한번");
		cri.setType("t");
		
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
		
	}
	
	

}
