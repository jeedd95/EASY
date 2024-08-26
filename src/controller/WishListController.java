package controller;

import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.service.WishListService;
import model.service.WishListServiceImpl;
import model.vo.WishListVO;
import view.FailView;
import view.SuccessView;

public class WishListController {
	public static WishListService wls = new WishListServiceImpl();

	// 추가
	public static void addWishList(WishListVO wishList) {
		try {
			wls.addWishList(wishList);
			SuccessView.printmessage( wishList.getIngredientNo() + "번 찜목록에서 추가 성공");

		} catch (InputFormatException e) {
			FailView.printMessage(e.getMessage());
			// e.printStackTrace();
		}
		
	}

	// 제거
	public static void removeWishList(WishListVO wishList) {
		try {
			wls.removeWishList(wishList);
			SuccessView.printmessage( wishList.getIngredientNo() + "번 찜목록에서 삭제 성공");
			
		} catch (InputFormatException e) {
			FailView.printMessage(e.getMessage());
			// e.printStackTrace();
		}
	}

	// 조회
	public static void searchWishList(int memberNo) {
		List<WishListVO> list = null;

		try {
			list = wls.searchWishList(memberNo);
			SuccessView.printWishList(list);
			
		} catch (ListNotFoundException | InputFormatException e) {
			FailView.printMessage(e.getMessage());
			e.printStackTrace();
		}

	}

}
