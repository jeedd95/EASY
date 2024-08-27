package model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.BoardException;
import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardService {
	
	public List<BoardVO> searchPostByName(String name) throws BoardException, SQLException;
	
	public void postBoard(BoardVO board) throws BoardException, SQLException;
	
	public BoardVO boardSelectByNo(int boardNO, String boardName) throws BoardException,SQLException;
	
	public void writeComment(CommentVO comment, String boardName) throws BoardException,SQLException;
	
	public List<BoardVO> searchMyPost(int memberNo) throws BoardException,SQLException;
	
	public Map<String, Map<String,Object>> searchMyComment(String nickName) throws BoardException,SQLException;
	
	public void deletePost(BoardVO board) throws BoardException,SQLException;
	
	public void deleteComment(int commentNo,String NickName) throws BoardException,SQLException;
}

