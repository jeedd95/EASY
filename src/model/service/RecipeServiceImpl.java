package model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.InputFormatException;
import model.dao.RecipeDAO;
import model.dao.RecipeDAOImpl;
import model.dao.RefrigeratorDAO;
import model.dao.RefrigeratorDAOImpl;
import model.dao.StatsDAO;
import model.dao.StatsDAOImpl;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;
import model.vo.StatsVO;

public class RecipeServiceImpl implements RecipeService {
	RecipeDAO dao = new RecipeDAOImpl();

	@Override
	public List<RecipeVO> recommendRecipeByMemberUsed(int memberNo) throws InputFormatException {
		StatsVO first;
		StatsVO second;
		StatsVO third;

		StatsDAO statsDao = new StatsDAOImpl();
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		// 회원번호로 통계 테이블에서 가진 통계 리스트들 가져오기
		List<StatsVO> statsList = statsDao.searchIngredientStatsByMine(memberNo);
		
		Map<Integer,Integer> ingredientAmount = new HashMap<Integer, Integer>();
		for (StatsVO s : statsList) {
			System.out.println(s.getIngredientNo()+ " / " +s.getAmount());
			ingredientAmount.put(s.getIngredientNo(), s.getAmount());
		}
		
		List<Integer> keyList = new ArrayList<Integer>(ingredientAmount.keySet());
		Collections.sort(keyList, (v1, v2) -> (ingredientAmount.get(v2).compareTo(ingredientAmount.get(v1))));
		
		for (Integer i : keyList) {
			System.out.println(i + " : " + ingredientAmount.get(i));
		}
		
//		List<Integer> valueList = ingredientAmount.values().stream().toList();
//		System.out.println(keyList);
//		System.out.println(valueList);
		

//		for (StatsVO s : statsList) {
//			int ingredientNo = s.getIngredientNo(); //식재료 번호
//			
//			// 식재료 번호로 레시피재료 테이블에서 레시피 리스트 가져오기
//			List<RecipeIngredientVO> recipeIngredientList = dao.searchRecipeIngredientListByIngredientNumber(ingredientNo);
//			for (RecipeIngredientVO ri : recipeIngredientList) {
//				int recipeNo = ri.getRecipe_No(); 
//				RecipeVO recipe = dao.searchRecipeBySerialNumber(recipeNo);
//				//레시피 객체에 레시피 재료들을 넣어야함
//				//레시피 재료들을 다 찾는 메소드 호출
//				List<RecipeIngredientVO> ingredientList = dao.searchRecipeIngredientListByRecipeSerialNumber(recipeNo);
//				//레시피 일련번호로 레시피재료테이블에서 레시피재료객체 만들기
//				recipe = setIngredientToRecipe(recipe);
//				recipeList.add(recipe);
//			}
//		}

		recipeList = recipeList.stream().distinct().toList(); // 중복 제거

		return recipeList;
	}

	@Override
	public List<RecipeVO> recommendRecipeByRefrigerator(int memberNo) {
		RefrigeratorDAO refrigeratorDao = new RefrigeratorDAOImpl();
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		// 회원번호로 냉장고현황 테이블에서 가진 식재료 리스트들 가져오기
		List<RefrigeratorVO> refrigeratorList = refrigeratorDao.searchIngredientByMemberNo(memberNo);

		for (RefrigeratorVO r : refrigeratorList) {
			int ingredientNo = r.getIngredientNo();
			// 식재료 번호로 레시피재료 테이블에서 레시피 일련번호 가져오기
			List<RecipeIngredientVO> recipeIngredientList = dao
					.searchRecipeIngredientListByIngredientNumber(ingredientNo);
			for (RecipeIngredientVO ri : recipeIngredientList) {
				int recipeNo = ri.getRecipe_No();
				RecipeVO recipe = dao.searchRecipeBySerialNumber(recipeNo);
				recipe = searchRecipeByName(recipe.getName()); // 레시피 이름으로 레시피 찾기
				recipeList.add(recipe);
			}
		}

		recipeList = recipeList.stream().distinct().toList(); // 중복 제거

		return recipeList;
	}

	@Override
	public RecipeVO searchRecipeByName(String recipeName) {
		RecipeVO result = null;

		result = dao.searchRecipeByName(recipeName); // 재료 리스트는 없는 데이터
		setIngredientToRecipe(result);

		return result;
	}

	/**
	 * 레시피에 레시피재료들 찾아서넣어주기
	 * 
	 * @param recipe
	 * @return
	 */
	private RecipeVO setIngredientToRecipe(RecipeVO recipe) {
		// 재료 리스트 까지 넣기
		recipe.setRecipeIngredientList(dao.searchRecipeIngredientListByRecipeSerialNumber(recipe.getSerialNumber()));

		// 재료 리스트에 레시피명도 넣어주기
		for (RecipeIngredientVO ri : recipe.getRecipeIngredientList()) {
			ri.setIngredientName(dao.searchIngredientName(ri.getIngredient_No()));
		}

		return recipe;
	}

	@Override
	public int makeRecipe(RecipeVO recipeVo) {
		int result = 0;

		RefrigeratorDAO refrigDao = new RefrigeratorDAOImpl();

		// 레시피 바로 만들기
		// 인수로 들어온 레시피의 재료들의 목록을 냉장고 현황에서 빼줌
		int recipeSerialNumber = recipeVo.getSerialNumber();
		System.out.println(recipeSerialNumber);

		List<RecipeIngredientVO> ingredientList = dao.searchRecipeIngredientListByIngredientNumber(recipeSerialNumber);
		for (RecipeIngredientVO r : ingredientList) {
			// 식재료 일련번호
			int ingredientNumber = r.getIngredient_No();

			// 식재료 일련번호로 냉장고 현황에서 식재료 일련번호에 해당하는 데이터 삭제(빼기)
			// refrigDao.식재료 일련번호로 냉장고 객체(RefrigeratorVO) 리턴
		}
		// null 자리에 위에서 한거 모은 리스트 넣어주기
		result = refrigDao.subtractIngredient(null);

		return result;
	}

}
