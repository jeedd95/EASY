package controller;

import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.service.StatsService;
import model.service.StatsServiceImpl;
import model.vo.StatsVO;
import view.FailView;
import view.SuccessView;

public class StatsController {
	public static StatsService sts = new StatsServiceImpl();
	
	//많이 사용한 식재료 통계 보기(나의 현황)
	public static void searchIngredientStatsByMine(int memberNo) {
		try {
			List<StatsVO> list = sts.searchIngredientStatsByMine(memberNo);
			//SuccessView.
		} catch (ListNotFoundException | InputFormatException e) {
			FailView.printMessage(e.getMessage());
			//e.printStackTrace();
		}
	}
	
	//성별로 통계보기
	public static void searchIngredientStatsByGender() {
		//sts.searchIngredientStatsByGender();
	}
	
	//식재료별 통계보기
	public static void searchIngredientStatsByAmount() {
		//sts.searchIngredientStatsByAmount();
	}

}
