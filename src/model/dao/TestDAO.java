package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InputFormatException;
import model.vo.RefrigeratorVO;
import util.DbManager;

public class TestDAO { //해결 후 삭제 예정

	public List<String> searchByIngredientNo(List<RefrigeratorVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> ingredientName = new ArrayList<String>();
		String sql = "select Ingredient_name from ingredient where ingredient_no = ?";

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);

			for (RefrigeratorVO refri : list) {
				ps.setInt(1, refri.getIngredientNo());
				rs = ps.executeQuery();

				while (rs.next()) {
					ingredientName.add(rs.getString(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("냉장고뷰-식재료명 조회 중 DB에러 발생하였습니다. 다시 시도해주세요");

		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return ingredientName;
	}
	
}
