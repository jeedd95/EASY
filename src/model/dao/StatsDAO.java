package model.dao;

import java.util.List;

import exception.InputFormatException;
import model.vo.StatsVO;

public interface StatsDAO {
	
	/**
	 * 많이 사용한 식재료 통계 보기(나의 현황)
	 */
	List<StatsVO> searchIngredientStatsByMine(int memberNo)throws InputFormatException;

	/**
	 * 성별로 통계보기(전체 현황)
	 */
	List<StatsVO> searchIngredientStatsByGender() throws InputFormatException;
	
	/**
	 * 식재료별 통계보기(전체 현황)
	 */
	List<StatsVO> searchIngredientStatsByAmount() throws InputFormatException;
}
