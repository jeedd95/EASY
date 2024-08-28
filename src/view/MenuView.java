package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.BoardController;
import controller.MemberController;
import controller.MenuController;
import controller.RecipeController;
import controller.RefrigeratorController;
import controller.StatsController;
import controller.WishListController;
import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.IngredientVO;
import model.vo.MemberVO;
import model.vo.RecipeBoardVO;
import model.vo.RecipeCommentVO;
import model.vo.RecipeIngredientVO;
import model.vo.RecipeVO;
import model.vo.RefrigeratorVO;
import model.vo.ReviewCommentVO;
import model.vo.StatsVO;
import model.vo.WishListVO;
import util.Session;

public class MenuView {
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * 로그인 성공했을 때 화면
	 */

	public static void login(MemberVO member) {

		RefrigeratorView refri =RefrigeratorView.getInstance();
		while(true) {
			alarmExpirationDate(member);
			System.out.println("<메뉴를 선택해주세요>");
			System.out.println("[1] 식재료 넣기");
			System.out.println("[2] 식재료 빼기");
			System.out.println("[3] 식재료 상세보기");
			System.out.println("[4] 레시피 추천받기");
			System.out.println("[5] 찜 목록");
			System.out.println("[6] 게시판 보기");
			System.out.println("[7] 통계");
			System.out.println("[8] 로그아웃");
			System.out.println("[9] 시스템 종료");
			System.out.println("[10] 마이 페이지");
			System.out.println("선택 > ");
			
			String selectNo = sc.next();


			if(!MenuController.IsCheckNum(selectNo)) {
				login(member);
			}
			switch(Integer.valueOf(selectNo)){
			
			case 1: MenuView.insertIngredient();
					break;
			case 2: MenuView.removeIngredient();
					break;
//			case 3: MenuView.login();
//					break;
			case 4: 레시피추천받기();
					break;
			case 5: MenuView.wishList(member);
				break;
			case 6: board(member);
				break;
			case 7: MenuView.stats(member);
				break;
			case 8: MenuView.logOut();
				break;
			case 9:
					System.exit(0);
					break;
			case 10: myPage(member);
			default:
				System.out.println("해당하는 숫자를 눌러주세요");
				   
				
			}
			
			
		}
		
		
		
		
		
	}
	/*
	 * 알람
	 */
	public static void alarmExpirationDate(MemberVO member) {
		RefrigeratorController.alarmExpirationDate(member.getMNo());
		
	}
	/*
	 * 회원가입 화면
	 */
	public static void logOut() {
		MainView.LoginMenu();
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
	private static void insertIngredient() {
		System.out.println("식재료 넣기 메뉴에 들어오셨습니다=============");
		System.out.println("넣을 재료의 카테고리를 선택하거나 0번으로 뒤로 돌아갑니다.");
		List<IngredientVO> categoryList= RefrigeratorController.selectCategory();
		for(int i=0; i<categoryList.size(); i++) {
			System.out.println((i+1)+". " + categoryList.get(i).getName());
		}
		
		int category = sc.nextInt();
		if(category ==0) return;
		System.out.println();
		식재료뷰(category);
    }
	
	private static void 식재료뷰(int category) {
		System.out.println("세부 식재료를 선택하거나 0번으로 뒤로 돌아갑니다");
		List<IngredientVO> ingredientList = RefrigeratorController.selectIngredient(category);
		
		Map<Integer, Integer> serialNumberMap = new HashMap<>();
		for(int i=0; i<ingredientList.size(); i++) {
			System.out.println((i+1)+". " + ingredientList.get(i).getName());
			serialNumberMap.put(i+1, ingredientList.get(i).getSerialNumber());
		}
//		System.out.println(serialNumberMap);
		
		int choice = sc.nextInt();
		if(choice ==0) return;
		
		System.out.println();
		RefrigeratorVO refrigerator =수량뷰(serialNumberMap.get(choice));
		날짜뷰(refrigerator);
		
	}
	
	private static RefrigeratorVO 수량뷰(int ingredientNumber) {
		System.out.println("재료의 수량을 1 이상 정수로 입력해주세요"); //0이하일때 안되게
		
		int amount=sc.nextInt();
		while (amount <=0) {
			System.out.println("1 이상의 정수만 입력해주세요");
			amount =sc.nextInt();
		}
		
		RefrigeratorVO refrigerator = new RefrigeratorVO();
		refrigerator.setAmount(amount);
		refrigerator.setIngredientNo(ingredientNumber);
		refrigerator.setMemberNumber(Session.getCurrentMember().getMNo());
		
		return refrigerator;
	}
	
	private static RefrigeratorVO 날짜뷰(RefrigeratorVO refrigerator) {
		System.out.println("오늘부터 남은 유통기한의 일수를 입력해주세요");
		int days = sc.nextInt();
		
//		LocalDate today = LocalDate.now();
//		refrigerator.setRegistDate(LocalDate.now().toString()); //DB에서 해줌
		refrigerator.setExpirationDate(Integer.toString(days)); //DB에서 해줌 더해줄 일수임
		
//		System.out.println(refrigerator);
		
		List<RefrigeratorVO> refrigeratorList= new ArrayList<RefrigeratorVO>();
		refrigeratorList.add(refrigerator);
		RefrigeratorController.insertIngredient(refrigeratorList);
		
		return refrigerator;
	}
	
	/*
	 * 식재료 빼기
	 * 냉장고 현황을 보여주고 해당 칸(기능1) 을  1,3 입력받아서 뺴거나 
	 * (기능2)음식명을 입력해서 유통기한 짧게 남은거 삭제후
	 *  냉장고 상태 보여주기 
	 */
	private static void removeIngredient() {
		// 회원번호로 냉장고 조회해서 식재료 목록 출력
//		  RefrigeratorController
		// → 번호 입력 → 수량 →
		// 유통기한 얼마안남은거부터 자동으로 빼기 → 통계 테이블에 넣기

		System.out.println("식재료 상세보기 메뉴에 들어오셨습니다=============");
		System.out.println("사용할 식재료 번호를 누르거나 0번으로 뒤로 돌아갑니다");
		List<RefrigeratorVO> refrigeratorList = RefrigeratorController.removeIngredient();
		
		Map<Integer, Integer> serialNumberMap = new HashMap<>();
		for(int i =0; i< refrigeratorList.size(); i++) {
			RefrigeratorVO r = refrigeratorList.get(i);
			System.out.println((i+1) + ". ▶ " + r.getIngredient().getName()+"\t" + r.getAmount()+"\t" + r.getRegistDate() + "\t" + r.getExpirationDate());
			serialNumberMap.put(i+1, r.getSerialNumber());
		}
		
		int removeRecipeIngredientNumber = sc.nextInt();
//		System.out.println(serialNumberMap.get(removeRecipeIngredientNumber));
		삭제수량뷰(serialNumberMap.get(removeRecipeIngredientNumber));
		
		
	}
	
	private static void 삭제수량뷰(int removeRecipeIngredientNumber) {
		System.out.println("사용할 재료의 수량을 입력해주세요");
		int amount = sc.nextInt();
		
		RecipeController.removeRecipeIngredient(removeRecipeIngredientNumber, amount);
	}

	
	/*
	 * 레시피 추천받기
	 * 추천받기 누르면
	 * 현재 냉장고에 있는 식재료들 갯수 파악해서 레시피 재료와 충족 되면 list에 담아서
	 * list 출력 후 클릭 하면 해당 식재료 뺴고 냉장고 상태 보여주기
	 */
	public static void 레시피추천받기() {
		System.out.println("=========레시피 추천받기===================");
		System.out.println("1. 냉장고 기반으로 추천받기");
		System.out.println("2. 사용 기반으로 추천받기");
		System.out.println("0. 뒤로가기");
		System.out.println("=======================================");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			냉장고기반으로추천받기();
			break;
		case 2:
			사용기반으로추천받기();
			break;
		default:
			return;
		}
	}
	
	public static void 냉장고기반으로추천받기() {
		System.out.println("냉장고 기반으로 추천 받기 메뉴에 들어오셨습니다");
		System.out.println("현재 냉장고의 재료를 확인 중 입니다 잠시만 기다려주세요...");
		
		//현재 로그인한 회원의 회원번호 가져오기
		List<RecipeVO> recipeList =  RecipeController.recommendRecipeByRefrigerator(5);
		
		System.out.println("냉장고에 있는 재료들로 만들 수 있는 레시피는 아래와 같습니다");
		
		Map<Integer, Integer> serialNumberMap = new HashMap<>();
		for(int i = 0; i< recipeList.size(); i++) {
			System.out.println((i+1)+" ▶ "+recipeList.get(i).getName() + "\s");
			serialNumberMap.put(i+1, recipeList.get(i).getSerialNumber());
		}
		
		System.out.println("레시피를 자세히 보시려면 번호를, 뒤로가시려면 0번을 입력해주세요");
		int choice = sc.nextInt();
		//없는 번호를 입력했을때 오류처리 필요
		if(choice ==0) return;
		레시피상세보기(serialNumberMap.get(choice));

		System.out.println();
		System.out.println("아무 입력으로 뒤로 갑니다");
		sc.next();
		return;
		
	}
	
	public static void 사용기반으로추천받기() {
		System.out.println("사용기반으로 추천 받기 메뉴에 들어오셨습니다");
		System.out.println("통계를 확인 중 입니다 잠시만 기다려주세요...");
		//3대신 현재 회원 받기
		List<StatsVO> statsList = RecipeController.recommendRecipeByMemberUsed(3);
		System.out.println();
		System.out.println("지난 기간동안 사용한 식재료의 순위입니다===========");
		System.out.print("순위" +"\t");
		System.out.print("식재료명" +"\t");
		System.out.println("수량" +"\t");
		
		Map<Integer, Integer> serialNumberMap = new HashMap<>();
		for(int i=0; i<statsList.size(); i++) {
			System.out.print((i+1) + "위" + "\t");
			
			String name= RecipeController.searchIngredientName(statsList.get(i).getIngredientNo());
			System.out.print(name+ "\t");
			System.out.print(statsList.get(i).getAmount());
			System.out.println();
			serialNumberMap.put(i+1, statsList.get(i).getIngredientNo());
		}
		System.out.println();
		
		System.out.println("재료의 레시피를 보시려면 순위를, 뒤로가시려면 0번을 입력해주세요");
		int choice = sc.nextInt();
		if(choice ==0) return;
		사용기반통계로레시피상세보기(serialNumberMap.get(choice));
		
		System.out.println();
		System.out.println("아무 입력으로 뒤로 갑니다");
		sc.next();
		레시피추천받기();
//		return;
		
		
	}
	
	public static void 레시피상세보기(int recipeSerialNumber) {
		System.out.println("레시피를 확인 중입니다 잠시만 기다려주십시오...");
		RecipeVO recipe = RecipeController.recipeDetail(recipeSerialNumber);
		System.out.println(recipe.getName() + "========================");
		
		for (RecipeIngredientVO ri : recipe.getRecipeIngredientList()) {
			System.out.println("▶ " + ri.getIngredientName());
		}
		
		System.out.println();
		System.out.println(recipe.getMethod());

	}
	
	public static void 사용기반통계로레시피상세보기(int ingredientNumber) {
		List<RecipeVO> recipeList= RecipeController.recipeDetailByIngredientNumber(ingredientNumber);
		for (RecipeVO recipeVO : recipeList) {
			System.out.println("▶ " + recipeVO.getName());
			System.out.println(recipeVO.getMethod());
		}
	}
	
	
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
	public static void wishList(MemberVO member) { //조회, 추가, 제거
		System.out.println();
		System.out.println("====<'" + member.getMName() + "'님의 찜 목록>====");
		WishListController.searchWishList(member.getMNo()); //조회
		System.out.println("\n");
		
		System.out.println("---------------------------");
		System.out.println(" 1.추가 |  2.제거 |  3.뒤로가기 ");
		System.out.println("---------------------------");
		System.out.print("선택 > ");
		
		int button = Integer.parseInt(sc.next());
		
		switch (button) {
			case 1: 
				MenuView.addWishList(member.getMNo());  //추가
				MenuView.wishList(member);
				break;
			case 2: 
				MenuView.removeWishList(member.getMNo());  //제거
				MenuView.wishList(member);
				break;
			case 3: 
				MenuView.login(member); //뒤로가기
				break;
			default: {
				System.out.println("1, 2, 3번만 입력해주세요.");
				MenuView.wishList(member);
			}
		}
	}
	
	public static void addWishList (int memberNo) {
		//ingredientVO 만들어서 가져오는 메소드 호출 (일단 27로 셋팅)
		System.out.println("보관 유지 수량 입력 > ");
		int amount = Integer.parseInt(sc.next());
		//★★ 추후 수정 필요(시퀀스, 식재료 번호 연동)
		WishListVO wl = new WishListVO(10, memberNo, 27, amount);
		WishListController.addWishList(wl);
		
		
	}
	
	public static void removeWishList(int memberNo) {
		//ingredientVO 만들어서 가져오는 메소드 호출 (일단 27로 셋팅)
		//★★ 추후 수정 필요(시퀀스, 식재료 번호 연동)
		WishListVO wl = new WishListVO(memberNo, 27);
		WishListController.removeWishList(wl);
	}
	
	
	public static void board(MemberVO member) {
		while(true) {
			System.out.println("------------------------------------------------------");
			System.out.println(" 1.나만의 레시피 게시판 |  2.레시피 후기 게시판 |  3.뒤로가기 ");
			System.out.println("------------------------------------------------------");
	

			String choice = sc.next();
			if(!MenuController.IsCheckNum(choice)) {
					board(member);
					continue;
			}
			boardSelect(member,Integer.valueOf(choice));

		}
		
	}
	
	public static void boardSelect(MemberVO member,int choice) {
		
		
		switch(choice) {
		
		case 1:
				BoardController.searchPostByName("MY_RECIPE");
				displayMyRecipe();
				
				String num = sc.next();
				if(!MenuController.IsCheckNum(num))
					board(member);
				recipeBoard(Integer.valueOf(num),member,"MY_RECIPE",choice);
				
				break;
		case 2:
				BoardController.searchPostByName("RECIPE_REVIEW");
				displayRecipeReview();

				num = sc.next();
				
				if(!MenuController.IsCheckNum(num))
					board(member);
				
				recipeBoard(Integer.valueOf(num)+1,member,"RECIPE_REVIEW",choice);
				
				break;
		case 3:
				login(member);
				break;
		default:
				System.out.println("해당 되는 번호를 입력하세요");
				break;
				
		}
		
	}
	
	static void displayMyRecipe() {
		System.out.println("1. 글 작성하기");
		System.out.println("2. 나만의 레시피 글 상세보기 ");
		System.out.println("3. 돌아가기");
	}
	
	static void displayRecipeReview() {
		System.out.println("1. 레시피 후기 글 상세보기 ");
		System.out.println("2. 돌아가기");
	}
	
	
	static void recipeBoard(int num, MemberVO member,String name,int choice) {
		switch(num) {
			case 1:
				sc.nextLine();
				System.out.println("글 제목");
				String title = sc.nextLine();
				System.out.println("글 내용");
				String content = sc.nextLine();
				BoardVO board = new RecipeBoardVO(member.getMNickname(),title, content);
				BoardController.postBoard(board);
				boardSelect(member, choice);
				break;
			case 2:
				System.out.print("게시판 번호 입력 :");
				int no = sc.nextInt();
				while(true) {
					if(!BoardController.postBoardByNo(no,name))
						board(member);
				
					System.out.println("1.댓글 작성");
					System.out.println("2.뒤로 가기");
					String menu = sc.next();
					
					if(!MenuController.IsCheckNum(menu)) {
						continue;
					}
				
					if(Integer.valueOf(menu)==1) {
						System.out.println("댓글 내용");
						sc.nextLine();
						String commentContent = sc.nextLine();
						System.out.println("평점 : (1~5)점");
						int rating = sc.nextInt();
						CommentVO comment =null;
						if(name.equals("MY_RECIPE"))
							comment = new RecipeCommentVO(commentContent, rating,member.getMNickname(),no);
						if(name.equals("RECIPE_REVIEW"))
							comment = new ReviewCommentVO(commentContent, rating,member.getMNickname(),no);
						BoardController.writeComment(comment,name+"_Comment");
						continue;
				
					}else if(Integer.valueOf(menu)==2){
						boardSelect(member, choice);
					}else {
						System.out.println("해당하는 번호를 입력해주세요");
						continue;
					}
					break;

				}
				break;

			case 3:
				board(member);
				break;
			default:
				System.out.println("해당 되는 번호를 입력해주세요");
				boardSelect(member, choice);

				
				
		}
		
	}
	static void continues() {
		System.out.println("메인 메뉴로 가겠습니까?(아무키나 누르십쇼)");
		String a = sc.next();
		
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
	 * 나의 식재료 사용량 통계DTO에서 구분
	 * 남, 여 구분 통계DTO에서 구분
	 * 식재료 사용량 통계DTO에서 구분
	 */
	public static void stats (MemberVO member) {
		
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("1.나의 현황 | 2. 성별(남) | 3. 성별(여) | 4. 식재료별 | 5. 뒤로가기 ");
		System.out.println("--------------------------------------------------------");
		System.out.println("선택 > ");
		
		int button = Integer.parseInt(sc.next());
		
		switch (button) {
			case 1: 
				StatsController.searchIngredientStatsByMine(member.getMNo());
				MenuView.stats(member);
			case 2: 
				StatsController.searchIngredientStatsByGender("남");
				MenuView.stats(member);
			case 3: 
				StatsController.searchIngredientStatsByGender("여");
				MenuView.stats(member);
			case 4: 
				StatsController.searchIngredientStatsByAmount();
				MenuView.stats(member);
			case 5: 
				MenuView.login(member); //뒤로가기
				break;
			default: {
				System.out.println("1, 2, 3, 4, 5번만 입력해주세요.");
				MenuView.stats(member);
			}
			
		}
	}
	
	/*
	 * 로그아웃
	 */
	
	
	/*
	 * 마이페이지
	 */
	
	static void myPage(MemberVO member) {
		System.out.println(member.getMNickname()+"님 안녕하세요");
		System.out.println("1.내가 쓴 글 보기");
		System.out.println("2.내가 쓴 댓글 보기");
		System.out.println("3.회원 탈퇴");
		int myPageNum = sc.nextInt();
		
		switch(myPageNum) {
			case 1:
				//BoardController.searchMyPost(member.getMNo());
				BoardController.searchMyPost(member.getMNickname());
				System.out.println("1.삭제하기");
				System.out.println("2.이전으로");
				String selectPost = sc.next();
				if(!MenuController.IsCheckNum(selectPost))
					myPage(member);
				switch(Integer.valueOf(selectPost)) 
				{
				case 1:
					System.out.println("삭제할 게시물 번호 고르세요");
					int boardNo = sc.nextInt();
					System.out.println("비번을 입력하세요");
					String pw = sc.next();
					if(member.getMPw().equals(pw)) {
						BoardVO board = new RecipeBoardVO(boardNo,member.getMNickname());

						BoardController.deleteMyPost(board);
					}
					else
						System.out.println("비번이 틀립니다");
				case 2:
					myPage(member);
					break;
				default:
					System.out.println("해당하는 번호를 입력하세요");
					myPage(member);
					break;
				}
				
			case 2:
				//BoardController.searchMyComment(member.getMNickeName());
				BoardController.searchMyComment(member.getMNickname());
				System.out.println("1.삭제하기");
				System.out.println("2.이전으로");
				int selectComment = sc.nextInt();
				if(selectComment==1) {
					System.out.println("삭제할 댓글 번호 고르세요");
					int commentNo = sc.nextInt();
					System.out.println("비번을 입력하세요");
					String pw = sc.next();
					if(member.getMPw().equals(pw)) {//member.getPw.equals(pw)
						//CommentVO comment = new ReviewCommentVO(member.getMNickeName());
						BoardController.deleteMyComment(commentNo,member.getMNickname());
					}
					else
						System.out.println("비번이 틀립니다");
				}
				else
					myPage(member);
				
				
		
				break;
			case 3:
				System.out.println("비밀번호를 입력하세요");
				String pw = sc.next();
				if(member.getMPw().equals(pw)) {
					System.out.println("정말로 탈퇴하시겠습니까?");
					String ans = sc.next();
					if(ans.equals("y"))
						MemberController.removeMember(member);
					else
						login(member);
				}
				else
					login(member);
				break;		
			default:
		}
	}
	
	
	
}
