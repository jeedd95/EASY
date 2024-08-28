package model.service;

import java.util.List;

import exception.InputFormatException;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;
import model.vo.StatsVO;

public interface RecipeService {
	
	/**
	 * 사용 기반 레시피 추천받기
	 */
	List<StatsVO> recommendRecipeByMemberUsed(int memberNo) throws InputFormatException;
	
	/**
	 * 냉장고 기반 레시피 추천받기
	 */
	List<RecipeVO> recommendRecipeByRefrigerator(int memberNo);
	
	/**
	 * 레시피 상세보기
	 */
	RecipeVO searchRecipeByName(String recipeName);
	
	/**
	 * 식재료 번호로 식재료 명 가져오기
	 */
	String searchIngredientName(int ingredientNo);
	
	/**
	 * 레시피 바로 만들기
	 */
	int makeRecipe(RecipeVO recipeVo);

	/**
	 * 레시피 상세보기
	 * @param recipeSerialNumber 레시피일련번호
	 * @return 레시피
	 */
	RecipeVO recipeDetail(int recipeSerialNumber);

	/**
	 * 사용기반통계로레시피상세보기
	 */
	List<RecipeVO> recipeDetailByIngredientNumber(int ingredientNumber);

	/**
	 * 재료번호로 삭제하기
	 */
	int removeRecipeIngredient(int ingredientNumber, int amount);

	/**
	 * 삭제한 재료 통계에 넣기
	 */
	void addReicpeStats(int addRecipeIngredientNumber, RefrigeratorVO recipe, int amount);
}
