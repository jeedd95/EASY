package model.dao;

import java.util.List;

import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;

public interface RecipeDAO {

	/**
	 * 사용 기반 레시피 추천받기
	 */
	List<RecipeVO> recommendRecipeByMemberUsed(int memberNo);
	
	/**
	 * 냉장고 기반 레시피 추천받기
	 */
	List<RecipeVO> recommendRecipeByRefrigerator(int memberNo);
	
	/**
	 * 레시피 상세보기
	 */
	RecipeVO searchRecipeByName(String recipeName);
	
	/**
	 * 레시피 바로 만들기
	 */
	int makeRecipe(RecipeVO recipeVo);

	/**
	 * 식재료 번호로 레시피재료 가져오기
	 */
	List<RecipeIngredientVO> searchRecipeIngredientByRecipeName(int ingredientNo);
	
	/**
	 * 일련번호로 레시피 찾기
	 */
	RecipeVO searchRecipeBySerialNumber(int serialNumber);
}
