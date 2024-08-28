package model.service;


import java.util.List;

import model.vo.IngredientVO;
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
    
    /**
     * 대분류 보기
     */
	List<IngredientVO> selectCategory();
	
	/**
	 * 식재료 번호로 식재료 가져오기
	 */
	List<IngredientVO> selectIngredient(int ingredientNumber); 
}