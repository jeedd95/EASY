package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.SqlBoardException;
import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardDAO {

	/**
	 * 게시판 작성
	 * @throws SQLException 
	 * @throws SqlBoardException 
	 */
	int postBoard(BoardVO board) throws  SQLException;
	
	/**
	 * 내가 쓴 글 보기
	 */
	List<BoardVO> searchMyPost(String memberNickName) throws SQLException;
	/*
	 * 게시판별로 검색
	 */
	 List<BoardVO> searchPostByName(String name) throws SQLException ;
	/**
	 * 내가 쓴 댓글 보기
	 */
	 Map<String, Map<String,Object>> searchMyComment(String nickName) throws SQLException;
	
	/**
	 * 게시판 삭제
	 */
	int deleteBoard(BoardVO board) throws SQLException;
	
	/**
	 * 댓글 삭제
	 */
	int deleteComment(int commentNo,String NickName) throws SQLException;
	
	/**
	 * 댓글 작성
	 */
	int writeComment(CommentVO comment,String boardName) throws SQLException;
	
	BoardVO boardSelectByNo(int boardNO, String boardName) throws SQLException;
	
}
