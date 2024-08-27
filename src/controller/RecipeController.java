package controller;

import java.util.List;

import exception.InputFormatException;
import model.service.RecipeService;
import model.service.RecipeServiceImpl;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;

public class RecipeController {
	RecipeService service = new RecipeServiceImpl();
	
	public RecipeVO  searchRecipeByName(String recipeName) {
		RecipeVO recipe=null;
		
		recipe = service.searchRecipeByName(recipeName);
		
		return recipe;
	}
	
	public List<RecipeVO> recommendRecipeByRefrigerator(int memberNo){
		List<RecipeVO> list = null;
		
		list = service.recommendRecipeByRefrigerator(memberNo); //레시피에서 내가 재료를 하나라도 가지고 있는 리스트
		
		return list;
	}
	
	
	public List<RecipeVO> recommendRecipeByMemberUsed(int memberNo){
		List<RecipeVO> list = null;
		
		try {
			list = service.recommendRecipeByMemberUsed(memberNo);
		} catch (InputFormatException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int makeRecipe(RecipeVO recipeVo) {
		int result=0;
		
		result = service.makeRecipe(recipeVo);
		
		return result;
	};
	
}
