package controller;

import java.util.List;

import model.service.BoardService;
import model.service.BoardServiceImpl;
import model.vo.BoardVO;
import view.SuccessView;

public class BoardController {
	private static BoardService boardService = BoardServiceImpl.getInstance();
	
	public static void selectBoard(String name) {
		List<BoardVO> boardList = boardService.searchPostByName(name);
		SuccessView.printPostByName(boardList);
		
	}
}
