package model.service;

import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.dao.WishListDAO;
import model.dao.WishListDAOImpl;
import model.vo.WishListVO;

public class WishListServiceImpl implements WishListService {
	WishListDAO wldao = new WishListDAOImpl();

	@Override
	public int addWishList(WishListVO wishList) throws InputFormatException, ListNotFoundException {
		int result = wldao.addWishList(wishList);
		if (result == 0)
			throw new ListNotFoundException("찜목록 추가에 실패하였습니다.");
		return result;
	}

	@Override
	public int removeWishList(WishListVO wishList) throws InputFormatException, ListNotFoundException {
		int result = wldao.removeWishList(wishList);
		if (result == 0) {
			throw new ListNotFoundException("찜목록 삭제에 실패하였습니다.");
		}
		return result;
	}

	@Override
	public List<WishListVO> searchWishList(int memberNo) throws InputFormatException, ListNotFoundException {
		List<WishListVO> list = wldao.searchWishList(memberNo);
		if (list.isEmpty())
			throw new ListNotFoundException("찜목록 조회 실패 : 생성된 찜목록이 없습니다.");

		return list;
	}

	@Override
	public List<String> searchByIngredientNo(List<WishListVO> list) throws InputFormatException, ListNotFoundException {
		List<String> ingredientName = wldao.searchByIngredientNo(list);
		if (ingredientName.isEmpty())
			throw new ListNotFoundException("찜목록-식재료명 조회 실패 : 생성된 찜목록이 없습니다.");
		return ingredientName;
	}

}
