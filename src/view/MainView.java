package view;

import java.util.Scanner;

import controller.MemberController;
import controller.MenuController;
import model.vo.MemberVO;
import util.Session;

/**
 * 메인 메뉴를 출력한다 (1. 로그인 |  2.회원가입 | 3. 시스템 종료)
 */
public class MainView {

	private static Scanner sc = new Scanner(System.in);
	

	/*
	 * 시작 화면
	 */
	public static void LoginMenu() {
		System.out.print("====================================================");
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
			String menu = sc.nextLine();
			
			if(!MenuController.IsCheckNum(menu))
				LoginMenu();
			switch(Integer.valueOf(menu)){
			case 1: 
					System.out.println("ID를 입력하세요.");
					String id = sc.nextLine();
					if(!checkSpace(id))
						LoginMenu();
					System.out.println("PW를 입력하세요");
					String pw = sc.nextLine();
					if(!checkSpace(pw))
						LoginMenu();
					MemberVO member = new MemberVO(id,pw);
					MemberVO loginMember = MemberController.login(member);
					if(loginMember!=null) {
						Session.setCurrentMember(loginMember);
						MenuView.login(loginMember);
					}
					else
						LoginMenu();
					
					break;
					
			case 2: 
					while(true) {
						System.out.println("아이디 입력하세요");
						id = sc.nextLine();
						if(!MemberController.checkIdDuplicate(id)&& checkSpace(id) ) {
							break;
						}

					}
					while(true) {
						System.out.println("비밀번호를 입력하세요");
						pw = sc.nextLine();
						if(!MemberController.checkPw(pw) && checkSpace(pw)) {
							break;
						}
					}
					String name;
					while(true) {
						System.out.println("이름을 입력하세요");
						name = sc.nextLine();
						if(!MemberController.checkNickNameDuplicate(name) && checkSpace(name)) {
							break;
						}

					}
					
					String nickName;
					
					while(true) {
						System.out.println("닉네임을 입력하세요");
						nickName = sc.nextLine();
						if(!MemberController.checkNickNameDuplicate(nickName) && checkSpace(nickName)) {
							break;
						}

					}
					String gender;
					while(true) {
						System.out.println("성별을 입력하세요 (남,여)");
						gender = sc.nextLine();
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
	/*
	 * 정규식으로 공백이 포함되는걸 허용 안함
	 */
	public static boolean checkSpace(String memberInfo) {
		return MenuController.IsCheckSpace(memberInfo);
	}
	
}
