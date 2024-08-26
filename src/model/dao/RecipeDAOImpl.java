package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import util.DbManager;

public class RecipeDAOImpl implements RecipeDAO {

	@Override
	public List<RecipeVO> recommendRecipeByMemberUsed(int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeVO> recommendRecipeByRefrigerator(int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeVO searchRecipeByName(String recipeName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from RECIPE where RECIPE_NAME = ?";
		RecipeVO recipe = null;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,recipeName);
			rs = ps.executeQuery();

			if (rs.next()) {
				int serialNumber = rs.getInt("RECIPE_NO");
//				String name = rs.getString("RECIPE_NAME");
				String method = rs.getString("METHOD");

				recipe = new RecipeVO(serialNumber, recipeName, method);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return recipe;
	}

	@Override
	public int makeRecipe(RecipeVO recipeVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RecipeIngredientVO> searchRecipeIngredientByRecipeName(int ingredientNo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from RECIPE_INGREDIENT where INGREDIENT_NO = ?";

		List<RecipeIngredientVO> recipeIngredient = new ArrayList<RecipeIngredientVO>();
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ingredientNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				int serialNumber = rs.getInt("RECIPE_INGREDIENT_NO");
				int recipeNo = rs.getInt("RECIPE_NO");
				
				recipeIngredient.add(new RecipeIngredientVO(serialNumber, recipeNo, ingredientNo));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return recipeIngredient;
	}

	@Override
	public RecipeVO searchRecipeBySerialNumber(int serialNumber) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from RECIPE where RECIPE_NO = ?";
		RecipeVO recipe = null;

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,serialNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("RECIPE_NAME");
				String method = rs.getString("METHOD");

				recipe = new RecipeVO(serialNumber, name, method);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return recipe;
	}

}
