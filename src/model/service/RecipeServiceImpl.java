package model.service;

import java.util.ArrayList;
import java.util.List;

import model.dao.RecipeDAO;
import model.dao.RecipeDAOImpl;
import model.dao.RefrigeratorDAO;
import model.dao.StatsDAO;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;
import model.vo.StatsVO;

public class RecipeServiceImpl implements RecipeService {
	RecipeDAO dao = new RecipeDAOImpl();

	@Override
	public List<RecipeVO> recommendRecipeByMemberUsed(int memberNo) {
		StatsDAO statsDao = null; // Impl으로 받기
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		// 회원번호로 통계 테이블에서 가진 식재료 리스트들 가져오기
		List<StatsVO> statsList = statsDao.searchIngredientStatsAmount(memberNo);

		for (StatsVO s : statsList) {
			int ingredientNo = -1; // s에서 식재료 번호 가져오기
			// 식재료 번호로 레시피재료 테이블에서 레시피 일련번호 가져오기
			List<RecipeIngredientVO> recipeIngredientList = dao.searchRecipeIngredientByRecipeName(ingredientNo);
			for (RecipeIngredientVO ri : recipeIngredientList) {
				int recipeNo = ri.getRecipe_No();
				RecipeVO recipe = dao.searchRecipeBySerialNumber(recipeNo);
				recipeList.add(recipe);
			}
		}

		return recipeList; // 중복제거
	}

	@Override
	public List<RecipeVO> recommendRecipeByRefrigerator(int memberNo) {
		RefrigeratorDAO refrigeratorDao = null; // Impl으로 받기
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		// 회원번호로 냉장고현황 테이블에서 가진 식재료 리스트들 가져오기
		List<RefrigeratorVO> refrigeratorList = refrigeratorDao.searchIngredientByMemberNo(Integer.toString(memberNo));

		for (RefrigeratorVO r : refrigeratorList) {
			int ingredientNo = -1; // r에서 식재료 번호 가져오기
			// 식재료 번호로 레시피재료 테이블에서 레시피 일련번호 가져오기
			List<RecipeIngredientVO> recipeIngredientList = dao.searchRecipeIngredientByRecipeName(ingredientNo);
			for (RecipeIngredientVO ri : recipeIngredientList) {
				int recipeNo = ri.getRecipe_No();
				RecipeVO recipe = dao.searchRecipeBySerialNumber(recipeNo);
				recipeList.add(recipe);
			}
		}

		return recipeList; // 중복 제거해주기(set)
	}

	@Override
	public RecipeVO searchRecipeByName(String recipeName) {
		RecipeVO result = null;

		result = dao.searchRecipeByName(recipeName);

		return result;
	}

	@Override
	public int makeRecipe(RecipeVO recipeVo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
