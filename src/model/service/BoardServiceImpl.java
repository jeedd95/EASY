package model.service;

import java.util.List;

import model.dao.BoardDAO;
import model.dao.BoardDAOImpl;
import model.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	
	private static BoardService boardService;
	
	BoardDAO boardDAO = BoardDAOImpl.getInstance();
	
	public static BoardService getInstance() {
		if(boardService == null)
			return new BoardServiceImpl();
	
		return boardService;
	}
	
	public List<BoardVO> searchPostByName(String name){
		System.out.println("서비스 시작");

		List<BoardVO> boardList = boardDAO.searchPostByName(name);
 		
		if(boardList == null)
 			System.out.println("오류 던지기 해야함");
 		
		System.out.println("서비스 종료");
		return boardList;
	}
	
	public void postBoard(BoardVO board) {
		
		int result = boardDAO.postBoard(board);
		if(result ==0)
			System.out.println("오류 던지기");
			
	}
	
}
