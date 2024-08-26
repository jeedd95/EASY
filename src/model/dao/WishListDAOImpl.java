package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InputFormatException;
import model.vo.WishListVO;
import util.DbManager;

public class WishListDAOImpl implements WishListDAO {

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
	public List<WishListVO> searchWishList(int memberNo) throws InputFormatException{
		System.out.println(memberNo);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<WishListVO> list = new ArrayList<WishListVO>();
		String sql = "select * from wishlist";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			//ps.setInt(1, memberNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				WishListVO wishlist = new WishListVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				list.add(wishlist);
			}
			
		} catch (SQLException e) {
			throw new InputFormatException("찜목록 조회 중 DB에러 발생하였습니다. 다시 시도해주세요");
		}
		
		finally {
			DbManager.dbClose(con, ps, rs);
		}
		
		return list;
	}

}
