package controller;

import java.util.regex.Pattern;

import view.FailView;

public class MenuController {
	public static String checkNum = "^[\\d]*$";
	
	public static boolean checkNum(String selectNo) {
		if(Pattern.matches(checkNum, selectNo)) {
			return true;
		}
		else {
			FailView.printMessage("숫자만 입력하세요");
			return false;
		}
	}
}
