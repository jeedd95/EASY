package view;

import java.util.List;

import model.vo.BoardVO;
import model.vo.WishListVO;

public class SuccessView {

	public static void printWishList(List<WishListVO> list) {
		System.out.println(list);
	}
	
	public static void printPostByName(List<BoardVO> boardList) {
		for(BoardVO board : boardList)
			System.out.println(board.toString());
	}
	
}
