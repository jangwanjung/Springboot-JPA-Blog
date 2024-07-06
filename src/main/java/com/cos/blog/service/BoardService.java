package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void 글쓰기(Board board,User user) {  //board는 title이랑 content 두개를 받는다 그러니 count와 user정보를 추가해줘야한다
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){ //원래 글목록은 list타입이지만 pageable을 사용하면 Page타입이된다
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id,Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다");
				});  //영속화 완료
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시(즉 서비스가 종료될때) 트랜잭션이 종료됩니다. 이때 더티체킹이 일어나면서 자동 업데이트가 됨. db flush
	}
	
	@Transactional
	public void 댓글쓰기(User user, int id , Reply requestReply) {
		Board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패 : 게시물을 찾을 수 없습니다");
		}); 
		requestReply.setBoard(board);
		requestReply.setUser(user);	
		replyRepository.save(requestReply);
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}
