package model.service;

import java.util.List;

import model.vo.BoardVO;
import model.vo.CommentVO;

public interface BoardService {
	
	public List<BoardVO> searchPostByName(String name);
	
	public void postBoard(BoardVO board);
	
	public BoardVO boardSelectByNo(int boardNO, String boardName);
	
	public void writeComment(CommentVO comment, String boardName);
}
