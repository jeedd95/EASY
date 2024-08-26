package model.service;

import java.util.List;

import model.vo.BoardVO;

public interface BoardService {
	
	public List<BoardVO> searchPostByName(String name);
	
	public void postBoard(BoardVO board);
}
