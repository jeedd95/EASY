package view;

import java.util.Scanner;

import controller.WishListController;

public class MenuView {
	public static Scanner sc = new Scanner(System.in);
	/*
	 * 로그인 성공 했을 때 화면 
	 */
	public static void login(int memberNo) {

		RefrigeratorView refri =RefrigeratorView.getInstance();
		while(true) {
			refri.DrawMap("하하");
			System.out.println("1. 식재료 넣기");
			System.out.println("2. 식재료 빼기");
			System.out.println("3. 식재료 상세보기");
			System.out.println("4. 레시피 추천받기");
			System.out.println("5. 찜 목록");
			System.out.println("6. 게시판 보기");
			System.out.println("7. 통계");
			System.out.println("8. 로그아웃");
			System.out.println("9. 시스템 종료");

			
			int menu = sc.nextInt();
			
			switch(menu){
			
//			case 1: MenuView.login();
//					break;
//			case 2: MenuView.register();
//					break;
//			case 3: MenuView.login();
//					break;
//			case 4: MenuView.login();
//					break;
			case 5: MenuView.wishList(memberNo);
				break;
//			case 6: MenuView.login();
//				break;
//			case 7: MenuView.login();
//				break;
//			case 8: MenuView.login();
//				break;
			case 9:
					System.exit(0);
				
			}
			
			
		}
		
		
		
		
		
	}
	/*
	 * 회원가입 화면
	 */
	public static void register() {
		System.out.println("z");

	}
	
	/*
	 * 식재료 넣기
	 * 식재료 테이블 정보를 갖고와서  큰 카테고리(육류,어류 등)를 식재료DTO에 저장하고 list에 더해서
	 * Controller -> service -> Dao (list 리턴)
	 * Controller -> successview (list) 출력 후
	 * 대분류(육류,어류) 선택하면(입력받고) 그 대분류 참조 하는 식재료를 식재료DTO에 저장하고
	 * list에 담아서 출력
	 * 그후 ex) 돼지고기,소고기 번호 클릭해서 원하는 칸(입력받아서)or 자동 넣기
	 */
	
	/*
	 * 식재료 빼기
	 * 냉장고 현황을 보여주고 해당 칸(기능1) 을  1,3 입력받아서 뺴거나 
	 * (기능2)음식명을 입력해서 유통기한 짧게 남은거 삭제후
	 *  냉장고 상태 보여주기 
	 */
	
	/*
	 * 식재료 상세보기
	 * ?
	 * 
	 */
	
	
	/*
	 * 레시피 추천받기
	 * 추천받기 누르면
	 * 현재 냉장고에 있는 식재료들 갯수 파악해서 레시피 재료와 충족 되면 list에 담아서
	 * list 출력 후 클릭 하면 해당 식재료 뺴고 냉장고 상태 보여주기
	 */
	
	
	/* 
	 * 
	 * 찜 목록 들어가면 
	 * -화면 출력
	 *  찜 목록한걸 찜DTO 에 저장하고 *표시해서 출력 해주고
	 *  1.찜 추가하기 
	 *  2.찜 제거하기
	 *  3.나가기
	 *  
	 *  1번 클릭시
	 *  찜 목록
	 *  1번이랑 비슷하게 대분류(육류,어류) 보여주고 고르면 그 해당 식품 다 *표시 해주기?,
	 *  그 대분류 안에 들어있는 중분류(돼지고기,소고기)등 하나 클릭하면 거기 거기 *표시
	 *  그 후 찜 목록 보여주는 곳으로 이동
	 *  2번 클릭시
	 *  *표시(찜dto)에 있는거 찜dto에 저장하고 리스트에 담아서 출력 -대분류*시 그 하위 출력 x
	 *  번호 클릭하면  찜목록 제거(dao)를 해서 다시 찜 목록을 돌아서 찜 출력
	 *  
	 *  3번 클릭시
	 *  로그인 성공 화면으로 출력
	 */
	public static void wishList(int memberNo) { //추가, 제거, 조회
		System.out.println("---------<찜 목록>----------");
		System.out.println(" 1.추가 |  2.제거 |  3.조회 ");
		System.out.println("----------------------------");
		int botton = Integer.parseInt(sc.next());
		
		switch (botton) {
		case 1: //추가
		case 2: //제거
		case 3: WishListController.searchWishList(memberNo);       //조회
			break;

		default:
			break;
		}
	}
	/*
	 * 게시판 
	 * 클릭 하면 
	 * 1.레시피 후기 게시판
	 * 2.나만의 레시피 게시판
	 * 
	 * 각 번호 클릭시
	 * 1.해당 게시물 목록(제목,평점)(게시판DTO에 저장해서 list) 뜸
	 *  (한 10개나 5개나 묶음하고 다음 누르면 다음 게시물 출력? ) 뒤로가기 기능 
	 * 2-1.제목 누르면 게시물 내용과 댓글(댓글DTO에 저장해서 게시판DTO 안에 list<댓글DTO>)
	 *  나오게 하기 댓글도(10개나 5개 묶음하고 다음 누르면 다음 댓글 출력)뒤로가기
	 * 2-2.댓글 작성하기 버튼 눌러서 입력 받고 
	 *     후 다시 2-1 로 가서 보여주기 
	 *  
	 *  
	 */
	
	
	/*
	 * 통계
	 * 남, 여 구분 통계DTO에서 구분
	 * 식재료 사용량 통계DTO에서 구분
	 */
	
	
	/*
	 * 로그아웃
	 */
	
}
