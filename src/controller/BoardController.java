package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.BoardException;
import exception.SqlBoardException;
import model.service.BoardService;
import model.service.BoardServiceImpl;
import model.vo.BoardVO;
import model.vo.CommentVO;
import view.FailView;
import view.SuccessView;

public class BoardController {
	private static BoardService boardService = BoardServiceImpl.getInstance();
	
	public static void searchPostByName(String name) {
		try {
			List<BoardVO> boardList = boardService.searchPostByName(name);
			SuccessView.printPostByName(boardList);
		}catch(BoardException | SQLException e){
			FailView.printMessage(e.getMessage());
		}
		
		
	}
	public static void postBoard(BoardVO board) {
		try {
			boardService.postBoard(board);
			System.out.println("작성 완료 ");

		} catch (Exception e) {
			FailView.printMessage(e.getMessage());
		}
	}
	public static boolean postBoardByNo(int boardNo,String boardName) {
		boolean findBoard=false;
		try {
			BoardVO board = boardService.boardSelectByNo(boardNo,boardName);
			SuccessView.printCommentByBoard(board);
			return true;
		} catch (BoardException | SQLException e) {
			FailView.printMessage(e.getMessage());

		}
		return false;
	}
	public static void writeComment(CommentVO comment,String boardName) {
		try {
			boardService.writeComment(comment,boardName);
			SuccessView.printMessage("댓글 작성 완료");

		} catch (Exception e) {
			FailView.printMessage(e.getMessage());
		}
	}
	
	public static void searchMyPost(String memberNickName) {
		List<BoardVO> boardList;
		try {
			boardList = boardService.searchMyPost(memberNickName);
			SuccessView.printPostByMNo(boardList);

		} catch (Exception e) {
			FailView.printMessage(e.getMessage());
		}
		

		
	}
	public static void searchMyComment(String nickName) {

		try {
			Map<String, Map<String, Object>> commentList;

			commentList = boardService.searchMyComment(nickName);
			SuccessView.printCommentByMNo(commentList);

		} catch (Exception e) {
			FailView.printMessage(e.getMessage());
		}
	}
	
	public static void deleteMyPost(BoardVO board) {
		try {
			boardService.deletePost(board);
			SuccessView.printMessage("삭제 성공");

		} catch (Exception e) {
			FailView.printMessage(e.toString());
		}
	}
	
	public static void deleteMyComment(int commentNo,String NickName) {
		try {
			boardService.deleteComment(commentNo,NickName);
			SuccessView.printMessage("삭제 성공");

		} catch (Exception e) {
			FailView.printMessage(e.toString());
		}

	}
}
