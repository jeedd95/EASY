package view;

import java.util.List;

import model.vo.BoardVO;
import model.vo.WishListVO;

public class SuccessView {

	public static void printWishList(List<WishListVO> list) {
		System.out.println("=========<찜 목록>=========");
		for ( WishListVO wishlist : list) {
			System.out.println(wishlist);
		}
		
	}
	
	public static void printPostByName(List<BoardVO> boardList) {
		for(BoardVO board : boardList)
			System.out.println(board.toString());
	}
	
}
