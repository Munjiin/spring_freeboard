package org.jing.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;

	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("user00");
		
		service.register(board);	
	}
	
	@Test
	public void testGetList() {
		//service.getList().forEach(board->log.info(board));
		//service.getList(new Criteria(2,10)).forEach(board->log.info(board));
		
		service.getList(new Criteria(2,10)).forEach(board->log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(10));
	}
	
	@Test
	public void testDelete() {
		log.info("remoce result: " + service.remove(2));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1);
		
		if(board == null) {
			return;
		}
		
		board.setTitle("제목 수정함돠");
		log.info("MODIFY RESULT: " + service.modify(board));
	}

}
