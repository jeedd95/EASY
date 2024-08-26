package model.dao;

import java.util.List;
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
	
}
