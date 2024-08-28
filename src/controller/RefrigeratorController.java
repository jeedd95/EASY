package controller;

import java.util.List;

import model.service.RefrigeratorService;
import model.service.RefrigeratorServiceImpl;
import model.vo.IngredientVO;
import model.vo.MemberVO;
import model.vo.RefrigeratorVO;
import util.Session;
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

    public static void searchIngredientByMemberNo(int memberNo) {
        try {
            List<RefrigeratorVO> ingredients = service.searchIngredientByMemberNo(memberNo);
            ingredients.forEach(System.out::println);
        } 	catch (IllegalArgumentException e) {
            System.out.println("재료 검색 실패: " + e.getMessage());
        }
    }
    
  


	public static List<IngredientVO> selectIngredient(int category) {
		List<IngredientVO> result = null;

		result = service.selectIngredient(category);
		
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
    
    /**
     * 식재료 빼기
     */
	public static List<RefrigeratorVO> removeIngredient() {
		 List<RefrigeratorVO> result = null;
		 
		 result = service.removeIngredient(Session.getCurrentMember().getMNo());
		 
		 return result;
	}
}
