package model.dao;

import java.util.List;

import exception.InputFormatException;
import model.vo.StatsVO;
import model.vo.WishListVO;

public interface StatsDAO {
	
	/**
	 * 많이 사용한 식재료 통계 보기(나의 현황)
	 */
	List<StatsVO> searchIngredientStatsByMine(int memberNo)throws InputFormatException;

	/**
	 * 성별로 통계보기(전체 현황)
	 */
	List<StatsVO> searchIngredientStatsByGender(String gender) throws InputFormatException;
	
	/**
	 * 식재료별 통계보기(전체 현황)
	 */
	List<StatsVO> searchIngredientStatsByAmount() throws InputFormatException;
	
	/**
	 * 조회된 통계의 식재료 번호로 식재료 이름 조회
	 */
	List<String> searchByIngredientNo(List<StatsVO> list) throws InputFormatException;
	
}
