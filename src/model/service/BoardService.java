package model.service;

import java.util.List;
import java.util.Map;

import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardService {
	
	public List<BoardVO> searchPostByName(String name);
	
	public void postBoard(BoardVO board);
	
	public BoardVO boardSelectByNo(int boardNO, String boardName);
	
	public void writeComment(CommentVO comment, String boardName);
	
	public List<BoardVO> searchMyPost(int memberNo);
	
	public Map<String, Map<String,Object>> searchMyComment(String nickName);
	
	public void deletePost(BoardVO board);
	
	public void deleteComment(int commentNo,String NickName);
}

