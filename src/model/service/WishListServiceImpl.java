package model.service;

import java.sql.SQLException;
import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.dao.WishListDAO;
import model.dao.WishListDAOImpl;
import model.vo.WishListVO;

public class WishListServiceImpl implements WishListService {
	WishListDAO wldao = new WishListDAOImpl();
	
	@Override
	public int addWishList(WishListVO wishList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeWishList(WishListVO wishList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<WishListVO> searchWishList(int memberNo) throws InputFormatException, ListNotFoundException{
		
		List<WishListVO> list = wldao.searchWishList(memberNo);
		if(list.isEmpty()) throw new ListNotFoundException("생성된 찜목록이 없습니다.");
		return list;
	}

}
