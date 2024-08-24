package model.dao;

import java.util.List;
import model.vo.WishListVO;

public interface WishListDAO {

	/**
	 * 찜목록 추가
	 */
	int addWishList(WishListVO wishList);
	
	/**
	 * 찜목록 제거
	 */
	int removeWishList(WishListVO wishList);
	
	/**
	 * 찜목록 조회
	 */
	List<WishListVO> searchWishList(int memberNo);
	
	
}
