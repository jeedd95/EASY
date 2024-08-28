package view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.IngredientVO;
import model.vo.MemberVO;
import model.vo.RefrigeratorVO;
import model.vo.StatsVO;
import model.vo.WishListVO;

public class SuccessView {
	
	public static void printmessage(String message) {
		System.out.println(message);
	}
	
	public static void printWishList(List<WishListVO> list, List<String> ingredientNamelist) {
		System.out.println("식재료 이름 | 관리 수량 ");
		System.out.println("------------------");
		for ( int i = 0 ; i<ingredientNamelist.size() ; i++) {
			WishListVO wishlist = list.get(i);
			String ingredientName = ingredientNamelist.get(i);
			System.out.println(wishlist.toString(ingredientName));
		}
		
	}
	
	public static void printPostByName(List<BoardVO> boardList) {
		DecimalFormat df = new DecimalFormat("0.0");
		System.out.println("============================"+boardList.get(0).getName()+"=============================");
		for(BoardVO board : boardList)
			System.out.println(board.toString()+"평균 평점: "+df.format((board.getRating())));
		System.out.println("====================================================================");

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
		System.out.println("============================"+board.getName()+"=============================");
		System.out.println(board.toStringDetail());
		System.out.println("====================================================================");

		List<CommentVO> commentList = board.getComment();
		for(CommentVO comment : commentList) {
				if(comment==null) {
					System.out.println("댓글이 없습니다");
					break;
				}		
				System.out.println(comment.toString());
			}
		System.out.println("====================================================================");

		}
	
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	public static void printStats(List<StatsVO> list, List<String> ingredientNameList) {
		System.out.println("식재료 이름 | 사용 수량(많은순) | 사용 일자");
		System.out.println("---------------------------------");
		for (int i = 0; i<list.size() ; i++) {
			StatsVO stats = list.get(i);
			String ingredient = ingredientNameList.get(i);
			System.out.println(stats.toString(ingredient));
		}
		
		
	}
	public static void printMember(MemberVO member) {
		System.out.println(member.getMNickname()+"님 환영합니다");
	}

	public static void printAlarmRrfrigetator(List<RefrigeratorVO> ingredients) {
		for(RefrigeratorVO refri : ingredients) {
			System.out.println(refri.getIngredient().getName()+"의 남은 유통기한이 5일 이내 입니다. 남은 수량: "+refri.getAmount());
		}
		
	}
	
}

