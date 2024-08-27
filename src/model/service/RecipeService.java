package model.service;

import java.util.List;

import exception.InputFormatException;
import model.vo.RecipeVO;
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
	 * 레시피 바로 만들기
	 */
	int makeRecipe(RecipeVO recipeVo);

	/**
	 * 레시피 상세보기
	 * @param recipeSerialNumber 레시피일련번호
	 * @return 레시피
	 */
	RecipeVO recipeDetail(int recipeSerialNumber);
}
