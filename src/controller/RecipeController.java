package controller;

import java.util.List;

import model.service.RecipeService;
import model.service.RecipeServiceImpl;
import model.vo.RecipeVO;

public class RecipeController {
	RecipeService service = new RecipeServiceImpl();
	
	public RecipeVO  searchRecipeByName(String recipeName) {
		RecipeVO recipe=null;
		
		recipe = service.searchRecipeByName(recipeName);
		
		return recipe;
	}
	
	public List<RecipeVO> recommendRecipeByRefrigerator(int memberNo){
		List<RecipeVO> list = null;
		
		list = service.recommendRecipeByRefrigerator(memberNo);
		
		return list;
	}
	
}
