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
	public int addWishList(WishListVO wishList) throws InputFormatException{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into wishlist values (7, ? , ? , ?)";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, wishList.getMemberNo());
			ps.setInt(2, wishList.getIngredientNo());
			ps.setInt(3, wishList.getAmount());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new InputFormatException("찜목록 추가 중 DB에러 발생하였습니다. 다시 시도해주세요");
			//e.printStackTrace();
			
		} finally {
			DbManager.dbClose(con, ps);
		}
		
		return result;
	}

	@Override
	public int removeWishList(WishListVO wishList) throws InputFormatException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from wishlist where ingredient_no = ?";
			
		try {
			
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, wishList.getIngredientNo());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new InputFormatException("찜목록 삭제 중 DB에러 발생하였습니다. 다시 시도해주세요");
			//e.printStackTrace();
		
		} finally {
			DbManager.dbClose(con, ps);
		}
				
		return result;
	}

	@Override
	public List<WishListVO> searchWishList(int memberNo) throws InputFormatException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<WishListVO> list = new ArrayList<WishListVO>();
		String sql = "select* from wishlist where M_NO = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberNo);
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
