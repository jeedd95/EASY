package model.service;

import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.vo.WishListVO;

public interface WishListService {

	/**
	 * 찜목록 추가
	 */
	int addWishList(WishListVO wishList) throws InputFormatException, ListNotFoundException;
	
	/**
	 * 찜목록 제거
	 */
	int removeWishList(WishListVO wishList) throws InputFormatException, ListNotFoundException;
	
	/**
	 * 찜목록 조회
	 */
	List<WishListVO> searchWishList(int memberNo) throws InputFormatException, ListNotFoundException;
	
	
}
