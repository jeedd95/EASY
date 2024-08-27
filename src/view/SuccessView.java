package view;

import java.util.List;
import java.util.Map;

import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.StatsVO;
import model.vo.WishListVO;

public class SuccessView {

	public static void printmessage(String message) {
		System.out.println(message);
	}
	
	public static void printWishList(List<WishListVO> list, List<String> ingredientNamelist) {
	
		for ( int i = 0 ; i<ingredientNamelist.size() ; i++) {
			WishListVO wishlist = list.get(i);
			String ingredientName = ingredientNamelist.get(i);
			System.out.println(wishlist.toString(ingredientName));
		}
		
	}
	
	public static void printPostByName(List<BoardVO> boardList) {
		for(BoardVO board : boardList)
			System.out.println(board.toString()+"평균 평점: "+board.getRating());
		
	}
	
	
	public static void printPostByMNo(List<BoardVO> boardList) {
		for(BoardVO board : boardList)
			System.out.println(board.toString());
		
	}
	
	public static void printCommentByMNo(Map<String,Map<String,Object>> commentList) {
		for(String k : commentList.keySet()) {
			Map<String,Object> a = commentList.get(k);
			
			for(String key : a.keySet()) {
				System.out.println(a.get(key).toString());
			}
			System.out.println("=====================================================");
		}
		
	}
	
	public static void printCommentByBoard(BoardVO board) {
		System.out.println(board.toStringDetail());
		System.out.println("==================");
	
		List<CommentVO> commentList = board.getComment();
		for(CommentVO comment : commentList) {
				if(comment==null) {
					System.out.println("댓글이 없습니다");
					break;
				}		
				System.out.println(comment.toString());
			}
		}
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	public static void printStats(List<StatsVO> list) {
		for ( StatsVO stats : list) {
			System.out.println(stats);
		}
		
		
	}
	
}

