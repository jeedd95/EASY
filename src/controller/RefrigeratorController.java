package controller;

import java.util.List;

import model.service.RefrigeratorService;
import model.service.RefrigeratorServiceImpl;
import model.vo.IngredientVO;
import model.vo.RefrigeratorVO;
import view.FailView;
import view.SuccessView;

public class RefrigeratorController {

    private static RefrigeratorService service = RefrigeratorServiceImpl.getInstance();

    public static List<IngredientVO> selectCategory() {
    	List<IngredientVO> result =null;
    	
    	result = service.selectCategory();
    	
    	return result;
    }
    public static int insertIngredient(List<RefrigeratorVO> ingredients) {
         int result = service.insertIngredient(ingredients);
         
         return result;
    }

    public static void subtractIngredient(List<RefrigeratorVO> ingredients) {
        try {
            int result = service.subtractIngredient(ingredients);
            System.out.println("제거된 재료의 수: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("재료 제거 실패: " + e.getMessage());
        }
    }

    public void searchIngredientByMemberNo(int memberNo) {
        try {
            List<RefrigeratorVO> ingredients = service.searchIngredientByMemberNo(memberNo);
            ingredients.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("재료 검색 실패: " + e.getMessage());
        }
    }

	public static List<IngredientVO> selectIngredient(int ingredientNumber) {
		List<IngredientVO> result = null;

		result = service.selectIngredient(ingredientNumber);
		
		return result;
	}
    
    public static void alarmExpirationDate(int memberNo) {
        try {
            List<RefrigeratorVO> ingredients = service.alarmExpirationDate(memberNo);
            SuccessView.printAlarmRrfrigetator(ingredients);
        } catch (Exception e) {
        	FailView.printMessage(e.getMessage());
        }
    }
}
