package model.service;

import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dao.BoardDAOImpl;
import model.vo.BoardVO;
import model.vo.CommentVO;
import util.DbManager;

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

	@Override
	public BoardVO boardSelectByNo(int boardNO, String boardName) {
		BoardVO board = boardDAO.boardSelectByNo(boardNO,boardName);

		return board;
	}

	@Override
	public void writeComment(CommentVO comment,String boardName) {
		int result = boardDAO.writeComment(comment,boardName);
		if(result == 0)
			System.out.println("오류 던지기");
	}

	@Override
	public List<BoardVO> searchMyPost(int memberNo) {
		List<BoardVO> boardList = boardDAO.searchMyPost(memberNo);
		if(boardList==null)
			System.out.println("오류 던지기");
		return boardList;
	}
	
	
	public Map<String, Map<String,Object>>  searchMyComment(String nickName) {
		Map<String, Map<String,Object>> commentList = boardDAO.searchMyComment(nickName);
		if(commentList==null)
			System.out.println("오류 던지기");
		return commentList;
	}

	@Override
	public void deletePost(BoardVO board) {
		int result = boardDAO.deleteBoard(board);
		if(result == 0)
			System.out.println("오류 던지기");
		
	}

	
	@Override
	public void deleteComment(int commentNo,String NickName) {
		int result = boardDAO.deleteComment(commentNo,NickName);
		
	}
	
}
