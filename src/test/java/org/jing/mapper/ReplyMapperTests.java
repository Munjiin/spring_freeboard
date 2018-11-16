package org.jing.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.jing.domain.BoardVO;
import org.jing.domain.Criteria;
import org.jing.domain.ReplyPageDTO;
import org.jing.domain.ReplyVO;
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
public class ReplyMapperTests {

	private int[] bnoArr = { 138, 137, 136, 135, 134 };

	@Setter(onMethod_ = @Autowired) // 자동 주입
	private ReplyMapper mapper;

	// 매퍼 테스트
	@Test
	public void testMapper() {
		log.info("............" + mapper);
	}

	// 댓글등록
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {

			ReplyVO vo = new ReplyVO();

			// 게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer " + i);

			mapper.insert(vo);
		});
	}

	// 댓글 읽기
	@Test
	public void testRead() {

		int targetRno = 5;
		ReplyVO vo = mapper.read(targetRno);

		log.info(vo);
	}

	// 댓글 삭제
	@Test
	public void testDelete() {
		int targetRno = 1;
		mapper.delete(targetRno);
	}

	// 댓글 수정
	@Test
	public void testUpdate() {

		int targetRno = 9;

		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("update RE..");
		int count = mapper.update(vo);
		log.info("update count: " + count);

	}

	// 댓글 페이지
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

		replies.forEach(reply -> log.info(reply));
	}

	@Test
	public void testList2() {

		Criteria cri = new Criteria(2, 10);

		log.info(cri);

		List<ReplyVO> list = mapper.getListWithPaging(cri, 34);
		list.forEach(board -> log.info(board));
	}

}
