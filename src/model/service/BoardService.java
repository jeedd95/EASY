package model.service;

import java.util.List;
import java.util.Map;

import exception.BoardException;
import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardService {
	
	public List<BoardVO> searchPostByName(String name) throws BoardException;
	
	public void postBoard(BoardVO board) throws BoardException;
	
	public BoardVO boardSelectByNo(int boardNO, String boardName) throws BoardException;
	
	public void writeComment(CommentVO comment, String boardName) throws BoardException;
	
	public List<BoardVO> searchMyPost(int memberNo) throws BoardException;
	
	public Map<String, Map<String,Object>> searchMyComment(String nickName) throws BoardException;
	
	public void deletePost(BoardVO board) throws BoardException;
	
	public void deleteComment(int commentNo,String NickName) throws BoardException;
}

