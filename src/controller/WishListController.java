package controller;

import java.sql.SQLException;
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
	
	public static void searchWishList (int memberNo) {
		List<WishListVO> list = null;
	
			try {
				list = wls.searchWishList(memberNo);
			} catch (ListNotFoundException | InputFormatException e) {
				FailView.printMessage(e.getMessage());
				e.printStackTrace();
			}

		SuccessView.printWishList(list);
		
	}
	
}
