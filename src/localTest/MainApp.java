package localTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.RecipeController;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import util.TextColor;

public class MainApp {

	public static void main(String[] args) {
		RecipeController controller = new RecipeController();
		// 1. 레시피 상세보기
//		RecipeVO recipe =  controller.searchRecipeByName("김치찌개");
//		레시피상세보기뷰(recipe);

		// 2. 냉장고 기반 레시피 추천받기
//		List<RecipeVO> recipeList =  controller.recommendRecipeByRefrigerator(3);
//		냉장고기반레시피추천받기(recipeList);

		// 3. 사용기반 레시피 추천받기
//		List<RecipeVO> recipeList = controller.recommendRecipeByMemberUsed(3);
//		사용기반레시피추천받기(recipeList);
		
		// 4. 레시피 바로 만들기
		// 레시피 객체 받기
//		RecipeIngredientVO ingre1 = new RecipeIngredientVO(0,0,0);
//		RecipeIngredientVO ingre2 = new RecipeIngredientVO(0,0,0);
//		List<RecipeIngredientVO> ingredientList = new ArrayList<RecipeIngredientVO>();
//		ingredientList.add(ingre1);
//		ingredientList.add(ingre2);
//		RecipeVO newRecipe = new RecipeVO(21,"임의의 레시피","잘 섞어 만든다",ingredientList);
//
//		int result =  controller.makeRecipe(newRecipe); //회원번호 전달 필요
//		System.out.println(result);
	}

	public static void 레시피상세보기뷰(RecipeVO recipe) {
		System.out.println("=========레시피 추천받기===================");
		System.out.println("1. 냉장고 기반으로 추천받기");
		System.out.println("2. 사용 기반으로 추천받기");
		System.out.println("=======================================");
		
		}
		
		
//		System.out.println(recipe.getName() + "의 주요 재료는 아래와 같습니다");
//		
//		for (RecipeIngredientVO ri : recipe.getRecipeIngredientList()) {
//			System.out.print("▶ "+ri.getIngredientName() + "\s");
//		}
//		System.out.println();
//		System.out.println();
//		System.out.println("제작 방법 : " +  recipe.getMethod());
	
	
	
	public static void 냉장고기반레시피추천받기(List<RecipeVO> recipeList) {
		System.out.println("냉장고에 있는 재료들로 만들 수 있는 레시피는 아래와 같습니다");
		for (RecipeVO recipe : recipeList) {
			System.out.println("▶▶ "+recipe.getName() + "\s");
			for (RecipeIngredientVO ri : recipe.getRecipeIngredientList()) {
				//이 목록들 중에서 내가 무엇을 가지고 있는지 표시 필요
				//식재료 일련번호로 검색해서 냉장고 객체 리턴 -> 요청
				System.out.print("▶ " + ri.getIngredientName() + "\s");
			}
			System.out.println();
			System.out.println();
		}
	}

	public static void 사용기반레시피추천받기(List<RecipeVO> recipeList) {
		System.out.println();
		System.out.println("가장 많이 사용한 재료로 만들 수 있는 레시피는 아래와 같습니다");
		
		
		for(int i=0; i<3; i++) {
			System.out.print(i + " 위 : ");
			System.out.println("(식재료명)");
			System.out.println("▶▶ "+"레시피");
			System.out.println();
		}
	}
}
