package model.service;

import java.util.ArrayList;
import java.util.List;

import model.dao.RecipeDAO;
import model.dao.RecipeDAOImpl;
import model.dao.RefrigeratorDAO;
import model.dao.RefrigeratorDAOImpl;
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
		List<StatsVO> statsList = statsDao.searchIngredientStatsByMine(memberNo);

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
		RefrigeratorDAO refrigeratorDao = new RefrigeratorDAOImpl();
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		// 회원번호로 냉장고현황 테이블에서 가진 식재료 리스트들 가져오기
		List<RefrigeratorVO> refrigeratorList = refrigeratorDao.searchIngredientByMemberNo(memberNo);
		
		for (RefrigeratorVO r : refrigeratorList) {
			int ingredientNo = r.getIngredientNo();
			// 식재료 번호로 레시피재료 테이블에서 레시피 일련번호 가져오기
			List<RecipeIngredientVO> recipeIngredientList = dao.searchRecipeIngredientByRecipeName(ingredientNo);
			for (RecipeIngredientVO ri : recipeIngredientList) {
				int recipeNo = ri.getRecipe_No();
				RecipeVO recipe = dao.searchRecipeBySerialNumber(recipeNo);
				recipeList.add(recipe);
			}
		}
		
		recipeList= recipeList.stream().distinct().toList();  // 중복 제거
		
		return recipeList;
	}

	@Override
	public RecipeVO searchRecipeByName(String recipeName) {
		RecipeVO result = null;

		result = dao.searchRecipeByName(recipeName);

		return result;
	}

	@Override
	public int makeRecipe(RecipeVO recipeVo) {
		int result = 0;
		
		RefrigeratorDAO refrigDao = new RefrigeratorDAOImpl();
		
		//레시피 바로 만들기
		//인수로 들어온 레시피의 재료들의 목록을 냉장고 현황에서 빼줌
		int recipeSerialNumber = recipeVo.getSerialNumber();
		System.out.println(recipeSerialNumber);
		
		List<RecipeIngredientVO> ingredientList= dao.searchRecipeIngredientByRecipeName(recipeSerialNumber);
		for (RecipeIngredientVO r : ingredientList) {
			//식재료 일련번호
			int ingredientNumber = r.getIngredient_No();

			//식재료 일련번호로 냉장고 현황에서 식재료 일련번호에 해당하는 데이터 삭제(빼기)
			//refrigDao.식재료 일련번호로 냉장고 객체(RefrigeratorVO) 리턴
		}
		//null 자리에 위에서 한거 모은 리스트 넣어주기
		result = refrigDao.subtractIngredient(null);
		
		return result;
	}

}
