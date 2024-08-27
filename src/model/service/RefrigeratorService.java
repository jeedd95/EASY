package model.service;


import java.util.List;
import model.vo.RefrigeratorVO;

public interface RefrigeratorService {
	/**
	 * 식재료 추가 기능
	 */
    int insertIngredient(List<RefrigeratorVO> ingredients);
    /**
	 * 식재료 빼기 기능
	 */
    int subtractIngredient(List<RefrigeratorVO> ingredients); 
    
    /**
     * 식재료 상세보기 기능
     */
    
    List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo); 
}