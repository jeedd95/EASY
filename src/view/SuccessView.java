package view;

import java.util.List;

import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.WishListVO;

public class SuccessView {

	public static void printmessage(String message) {
		System.out.println(message);
	}
	
	public static void printWishList(List<WishListVO> list) {
		System.out.println("=========<찜 목록>=========");
		for ( WishListVO wishlist : list) {
			System.out.println(wishlist);
		}
		
	}
	
	public static void printPostByName(List<BoardVO> boardList) {
		for(BoardVO board : boardList)
			System.out.println(board.toString()+"평균 평점: "+board.getRating());
		
	}
	public static void printCommentByBoard(BoardVO board) {
		System.out.println(board.toStringDetail());
		System.out.println("==================");
	
		List<CommentVO> commentList = board.getComment();
		if(commentList == null)
			System.out.println("댓글이 없습니다");
		else {
			for(CommentVO comment : commentList) {
				System.out.println(comment.toString());
			}
		}
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	

}

