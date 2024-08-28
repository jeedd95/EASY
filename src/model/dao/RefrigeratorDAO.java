package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.vo.IngredientVO;
import model.vo.RefrigeratorVO;

public interface RefrigeratorDAO {
	/**
	 * 식재료 넣기
	 */
	int insertIngredient(List<RefrigeratorVO> refrigeratorList);
	
	/**
	 * 식재료 빼기
	 */
	int subtractIngredient(List<RefrigeratorVO> refrigeratorList);
	
	/**
	 * 식재료 상세보기
	 */

	List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo);

	/**
	 * 식재료 대분류 보기
	 */
	List<IngredientVO> selectCategory();

	/**
	 * 식재료 번호로 식재료보기
	 */
	List<IngredientVO> selectIngredient(int ingredientNumber);
	
	
	List<RefrigeratorVO> alarmExpirationDate(int memberNo) throws SQLException;

	/**
	 * 회원번호로 식재료찾기
	 */
	List<RefrigeratorVO> selectIngredientByMemberNumber(int memberNumber);

	/**
	 * 식재료 번호로 이름 찾아서 넣어주기
	 */
	IngredientVO getNameByIngredientNumber(int ingredientNumber);
	
}
