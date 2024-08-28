package model.dao;

import java.util.List;

import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;

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
	List<RecipeIngredientVO> searchRecipeIngredientListByIngredientNumber(int ingredientNo);
	
	/**
	 * 일련번호로 레시피 찾기
	 */
	RecipeVO searchRecipeBySerialNumber(int serialNumber);
	
	/**
	 * 레시피 일련번호로 레시피재료테이블에서 레시피재료 리스트 찾기
	 */
	List<RecipeIngredientVO> searchRecipeIngredientListByRecipeSerialNumber(int recipeSerialNumber);
	
	/**
	 * 식재료번호로 식재료테이블에서 재료이름찾기
	 */
	String searchIngredientName(int ingredientNumber);
	
	/**
	 * 식재료번호로 냉장고현황에서 냉장고객체가져오기
	 */
	List<RefrigeratorVO> getRefrigeratorByIngredientNumber(int ingredientNumber);

	/**
	 * 레시피 상세보기
	 * @param recipeSerialNumber 레시피일련번호
	 * @return 레시피
	 */
	RecipeVO recipeDetail(int recipeSerialNumber);

	/**
	 * 식재료로 레시피 보기
	 */
	List<RecipeIngredientVO> searchRecipeByIngredientNumber(int ingredientNumber);

	/**
	 * 식재료 삭제
	 */
	int removeRecipeIngredient(int ingredientNumber, int amount);

	/**
	 * 삭제한 식재료 통계에 넣기
	 */
	void addReicpeStats(int addRecipeIngredientNumber, RefrigeratorVO recipe,int amount);

}
