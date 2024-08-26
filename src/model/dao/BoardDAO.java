package model.dao;

import java.util.List;
import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardDAO {

	/**
	 * 게시판 작성
	 */
	int postBoard(BoardVO board);
	
	/**
	 * 내가 쓴 글 보기
	 */
	List<BoardVO> searchMyPost(int memberNo);
	/*
	 * 게시판별로 검색
	 */
	 List<BoardVO> searchPostByName(String name);
	/**
	 * 내가 쓴 댓글 보기
	 */
	List<CommentVO> searchMyComment(int memberNo);
	
	/**
	 * 게시판 삭제
	 */
	int deleteBoard(BoardVO board);
	
	/**
	 * 댓글 삭제
	 */
	int deleteComment(CommentVO comment);
	
	/**
	 * 댓글 작성
	 */
	int writeComment(CommentVO comment,String boardName);
	
	BoardVO boardSelectByNo(int boardNO, String boardName);
	
}
