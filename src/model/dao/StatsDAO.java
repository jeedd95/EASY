package model.dao;

import java.util.List;
import model.vo.StatsVO;

public interface StatsDAO {
	
	/**
	 * 많이 사용한 식재료 통계 보기
	 */
	List<StatsVO> searchIngredientStatsAmount(int memberNo);

	/**
	 * 성별로 통계보기
	 */
	List<StatsVO> searchIngredientStatsByGender();
	
	/**
	 * 식재료별 통계보기
	 */
	List<StatsVO> searchIngredientStats();
}
