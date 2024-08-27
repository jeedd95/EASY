package controller;

import java.util.List;

import exception.InputFormatException;
import model.service.RecipeService;
import model.service.RecipeServiceImpl;
import model.vo.RecipeVO;
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
	
	
	public static List<RecipeVO> recommendRecipeByMemberUsed(int memberNo){
		List<RecipeVO> list = null;
		
		try {
			List<StatsVO> statsList = service.recommendRecipeByMemberUsed(memberNo);
			for (StatsVO statsVO : statsList) {
				System.out.println("재료 번호" + statsVO.getIngredientNo());
				System.out.println("양 "+statsVO.getAmount());
			}
			//받은 
		} catch (InputFormatException e) {
			e.printStackTrace();
		}
		
		return list;
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
	
}
