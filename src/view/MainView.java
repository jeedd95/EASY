package view;

import java.util.Scanner;

import controller.MemberController;
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
			int menu = sc.nextInt();
			
			switch(menu){
			case 1: 
					System.out.println("ID를 입력하세요.");
					String id = sc.next();
					System.out.println("PW를 입력하세요");
					String pw = sc.next();
					MemberVO member = new MemberVO(id,pw);
					MemberController.login(member);
					MenuView.login(member);
					break;
					
			case 2: MenuView.register();
			
					while(true) {
						System.out.println("아이디 입력하세요");
						id = sc.next();
						if(!MemberController.checkIdDuplicate(id)) {
							break;
						}

					}
					while(true) {
						System.out.println("비밀번호를 입력하세요");
						pw = sc.next();
						if(!MemberController.checkPw(pw)) {
							break;
						}
					}
					System.out.println("이름을 입력하세요");	
					String name = sc.next();
					String nickName;
					
					while(true) {
						System.out.println("닉네임을 입력하세요");
						nickName = sc.next();
						if(!MemberController.checkNickNameDuplicate(nickName)) {
							break;
						}

					}
					String gender;
					while(true) {
						System.out.println("성별을 입력하세요 (남,여)");
						gender = sc.next();
						if(gender.equals("남") || gender.equals("여")) {
							break;
						}
						System.out.println("남 , 여 한글자 만 입력하세요");

					}
					MemberVO joinMember = new MemberVO(
							id,
							pw,
							name,
							nickName,
							gender				
					);
					
					MemberController.joinMember(joinMember);
							
					
							
							
				
					
				
					
					break;
			case 9:
					System.exit(0);
			default:
				System.out.println("잘못 입력하셨습니다");
				
			}
		}
		
	}
	
}
