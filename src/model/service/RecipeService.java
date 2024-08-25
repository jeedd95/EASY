package model.service;

import java.util.List;

import model.vo.RecipeVO;

public interface RecipeService {
	
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

}
