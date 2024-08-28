package controller;

import java.util.List;

import exception.InputFormatException;
import model.service.RecipeService;
import model.service.RecipeServiceImpl;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;
import model.vo.StatsVO;

public class RecipeController {
	static RecipeService service = new RecipeServiceImpl();
	
	public static RecipeVO  searchRecipeByName(String recipeName) {
		RecipeVO recipe=null;
		
		recipe = service.searchRecipeByName(recipeName);
		
		return recipe;
	}
	
	public static List<RecipeVO> recommendRecipeByRefrigerator(int memberNo){
		List<RecipeVO> list = null;
		
		list = service.recommendRecipeByRefrigerator(memberNo); //레시피에서 내가 재료를 하나라도 가지고 있는 리스트
		
		return list;
	}
	
	
	public static List<StatsVO> recommendRecipeByMemberUsed(int memberNo){
		List<StatsVO> statsList = null;
		
		try {
			statsList = service.recommendRecipeByMemberUsed(memberNo);
			
//			for (StatsVO statsVO : statsList) {
//				//통계의 식재료 번호로 식재료 가져오기
//				
//				//통계의 양은 킵
//				System.out.println("재료 번호" + statsVO.getIngredientNo());
//				System.out.println("양 "+statsVO.getAmount());
//			}
		} catch (InputFormatException e) {
			e.printStackTrace();
		}
		
		return statsList;
	}
	
	public static int makeRecipe(RecipeVO recipeVo) {
		int result=0;
		
		result = service.makeRecipe(recipeVo);
		
		return result;
	};
	
	/**
	 * 레시피 일련번호로 레시피 상세보기
	 */
	public static RecipeVO recipeDetail(int recipeSerialNumber) {
		RecipeVO result=null;
		
		result =service.recipeDetail(recipeSerialNumber);
		
		return result;
	}

	/**
	 * 식재료 번호로 식재료 명 가져오기
	 */
	public static String searchIngredientName(int ingredientNo) {
		return service.searchIngredientName(ingredientNo);
	}

	/**
	 * 사용기반통계로레시피상세보기
	 */
	public static List<RecipeVO> recipeDetailByIngredientNumber(int ingredientNumber) {
		return service.recipeDetailByIngredientNumber(ingredientNumber);
	}

	public static int removeRecipeIngredient(int removeRecipeIngredientNumber, int amount) {
		return service.removeRecipeIngredient(removeRecipeIngredientNumber,amount);
	}

	public static void addReicpeStats(int addRecipeIngredientNumber, RefrigeratorVO recipe,int amount) {
		service.addReicpeStats(addRecipeIngredientNumber, recipe,amount);
	}
	
}
