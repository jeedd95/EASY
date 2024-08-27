package controller;

import java.util.List;
import java.util.Map;

import model.service.BoardService;
import model.service.BoardServiceImpl;
import model.vo.BoardVO;
import model.vo.CommentVO;
import view.SuccessView;

public class BoardController {
	private static BoardService boardService = BoardServiceImpl.getInstance();
	
	public static void selectBoard(String name) {
		List<BoardVO> boardList = boardService.searchPostByName(name);
		SuccessView.printPostByName(boardList);
		
	}
	public static void postBoard(BoardVO board) {
		boardService.postBoard(board);
		System.out.println("작성 완료 ");
	}
	public static void postBoardByNo(int boardNo,String boardName) {
		BoardVO board = boardService.boardSelectByNo(boardNo,boardName);
		SuccessView.printCommentByBoard(board);
	}
	public static void writeComment(CommentVO comment,String boardName) {
		boardService.writeComment(comment,boardName);
		SuccessView.printMessage("댓글 작성 완료");
	}
	
	public static void searchMyPost(int memberNo) {
		List<BoardVO> boardList = boardService.searchMyPost(memberNo);
		SuccessView.printPostByMNo(boardList);

		
	}
	public static void searchMyComment(String nickName) {
		Map<String, Map<String,Object>> commentList = boardService.searchMyComment(nickName);
		SuccessView.printCommentByMNo(commentList);
	}
	
	public static void deleteMyPost(BoardVO board) {
		boardService.deletePost(board);
		SuccessView.printMessage("삭제 성공");
	}
	
	public static void deleteMyComment(int commentNo,String NickName) {
		boardService.deleteComment(commentNo,NickName);
		SuccessView.printMessage("삭제 성공");

	}
}
