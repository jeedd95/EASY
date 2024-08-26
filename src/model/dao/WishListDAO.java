package model.dao;

import java.sql.SQLException;
import java.util.List;

import exception.InputFormatException;
import model.vo.WishListVO;

public interface WishListDAO {

	/**
	 * 찜목록 추가
	 */
	int addWishList(WishListVO wishList) throws InputFormatException;
	
	/**
	 * 찜목록 제거
	 */
	int removeWishList(WishListVO wishList) throws InputFormatException;
	
	/**
	 * 찜목록 조회
	 */
	List<WishListVO> searchWishList(int memberNo) throws InputFormatException;
	
	
}
