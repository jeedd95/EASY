package model.service;

import java.util.List;
import java.util.Map;

import exception.BoardException;
import model.dao.BoardDAO;
import model.dao.BoardDAOImpl;
import model.vo.BoardVO;
import model.vo.CommentVO;

public class BoardServiceImpl implements BoardService {
	
	private static BoardService boardService;
	
	BoardDAO boardDAO = BoardDAOImpl.getInstance();
	

	public static BoardService getInstance() {
		if(boardService == null)
			return new BoardServiceImpl();
	
		return boardService;
	}

	public List<BoardVO> searchPostByName(String name) throws BoardException{
		List<BoardVO> boardList = boardDAO.searchPostByName(name);
 		
		if(boardList == null)
			throw new BoardException("글이 없습니다");
		
		return boardList;
	}
	
	public void postBoard(BoardVO board) throws BoardException {
		
		int result = boardDAO.postBoard(board);
		if(result ==0)
			throw new BoardException("글이 등록 되지 않았습니다");
			
	}

	@Override
	public BoardVO boardSelectByNo(int boardNO, String boardName) throws BoardException {
		
		BoardVO board = boardDAO.boardSelectByNo(boardNO,boardName);
		if(board == null)
			throw new BoardException("선택한 글이 없습니다");

		return board;
		
	}

	@Override
	public void writeComment(CommentVO comment,String boardName) throws BoardException {
		int result = boardDAO.writeComment(comment,boardName);
		if(result == 0)
			throw new BoardException("댓글 작성이 되지 않았습니다");
	}

	@Override
	public List<BoardVO> searchMyPost(int memberNo) throws BoardException {
		List<BoardVO> boardList = boardDAO.searchMyPost(memberNo);
		if(boardList==null)
			throw new BoardException("내가 쓴 글이 없습니다");
		return boardList;
	}
	
	
	public Map<String, Map<String,Object>>  searchMyComment(String nickName) throws BoardException {
		Map<String, Map<String,Object>> commentList = boardDAO.searchMyComment(nickName);
		if(commentList==null)
			throw new BoardException("글에 해당하는 댓글이 없습니다");
		return commentList;
	}

	@Override
	public void deletePost(BoardVO board) throws BoardException {
		int result = boardDAO.deleteBoard(board);
		if(result == 0)
			throw new BoardException("글이 삭제 되지 않았습니다");
		
	}

	
	@Override
	public void deleteComment(int commentNo,String NickName) throws BoardException {
		int result = boardDAO.deleteComment(commentNo,NickName);
		if(result == 0)
			throw new BoardException("댓글이 삭제 되지 않았습니다");
		
	}
	
}
