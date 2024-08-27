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

	// 많이 사용한 식재료 통계 보기(나의 현황)
	public static void searchIngredientStatsByMine(int memberNo) {
		try {
			List<StatsVO> list = sts.searchIngredientStatsByMine(memberNo);
			List<String> ingredient = sts.searchByIngredientNo(list);
			System.out.println("========<나의 통계>========");
			SuccessView.printStats(list, ingredient);
		} catch (ListNotFoundException | InputFormatException e) {
			FailView.printMessage(e.getMessage());
			// e.printStackTrace();
		}
	}

	// 성별로 통계보기
	public static void searchIngredientStatsByGender(String gender) {
		try {
			List<StatsVO> list = sts.searchIngredientStatsByGender(gender);
			List<String> ingredient = sts.searchByIngredientNo(list);
			System.out.println("======<전체 통계(" + gender + ")>======");
			System.out.println();
			SuccessView.printStats(list, ingredient);
		} catch (ListNotFoundException | InputFormatException e) {
			FailView.printMessage(e.getMessage());
			// e.printStackTrace();
		}
	}

	// 식재료별 통계보기
	public static void searchIngredientStatsByAmount() {
		try {
			List<StatsVO> list = sts.searchIngredientStatsByAmount();
			List<String> ingredient = sts.searchByIngredientNo(list);
			System.out.println("====<전체 통계(식재료별)>====");
			SuccessView.printStats(list, ingredient);

		} catch (ListNotFoundException | InputFormatException e) {
			FailView.printMessage(e.getMessage());
			// e.printStackTrace();
		}

	}

}
