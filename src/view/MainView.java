package view;

import java.util.Scanner;

import model.vo.MemberVO;

/**
 * 메인 메뉴를 출력한다 (1. 로그인 |  2.회원가입 | 3. 시스템 종료)
 */
public class MainView {

	private static Scanner sc = new Scanner(System.in);
	/*
	 * 시작 화면
	 */
	public static void LoginMenu() {
		System.out.println("====================================================");
		System.out.println("\r\n"
				+ " _____   ___   _____ __   __\r\n"
				+ "|  ___| / _ \\ /  ___|\\ \\ / /\r\n"
				+ "| |__  / /_\\ \\\\ `--.  \\ V / \r\n"
				+ "|  __| |  _  | `--. \\  \\ /  \r\n"
				+ "| |___ | | | |/\\__/ /  | |  \r\n"
				+ "\\____/ \\_| |_/\\____/   \\_/  \r\n"
				+ "");
		System.out.println("====================================================");
		while(true) {
			System.out.println("1. 로그인 ");
			System.out.println("2. 회원가입 ");
			MemberVO member = new MemberVO();
			int menu = sc.nextInt();
			
			switch(menu){
			case 1: MenuView.login(member);
					break;
					
			case 2: MenuView.register();
					break;
			case 9:
					System.exit(0);
			default:
				System.out.println("잘못 입력하셨습니다");
				
			}
		}
		
	}
	
}
