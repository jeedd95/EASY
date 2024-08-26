package controller;

import model.service.StatsService;
import model.service.StatsServiceImpl;

public class StatsController {
	public static StatsService sts = new StatsServiceImpl();
	
	//많이 사용한 식재료 통계 보기(나의 현황)
	public static void searchIngredientStatsByMine(int memberNo) {
		
	}
	
	//성별로 통계보기
	public static void searchIngredientStatsByGender() {
		
	}
	
	//식재료별 통계보기
	public static void searchIngredientStatsByAmount() {
		
	}

}
