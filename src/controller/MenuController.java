package controller;

import java.util.regex.Pattern;

import view.FailView;

public class MenuController {
	public static String checkNum = "^[\\d]*$";
	
	public static String checkSpace  ="^[\\w]*$";
	
	public static boolean IsCheckNum(String selectNo) {
		if(Pattern.matches(checkNum, selectNo)) {
			return true;
		}
		else {
			FailView.printMessage("숫자만 입력하세요(공백이나 문자를 쓰지 마세요)");
			return false;
		}
	}
	/*
	 * 공백이 들어가면 true 나옴
	 */
	public static boolean IsCheckSpace(String memberInfo) {
		System.out.println(Pattern.matches(checkSpace, memberInfo));
		if(Pattern.matches(checkSpace, memberInfo)) {
			return true;
		}
		else {
			FailView.printMessage("공백이나 특수 문자를 쓰지 마세요");
			return false;
		}
	}
	
}
