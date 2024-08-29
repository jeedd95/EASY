package view;

import java.util.List;

import controller.RefrigeratorController;
import model.dao.RefrigeratorDAO;
import model.dao.RefrigeratorDAOImpl;
import model.vo.MemberVO;
import model.vo.RefrigeratorVO;

public class RefrigeratorView {
	private StringBuffer map[] = new StringBuffer[25];
	private static RefrigeratorView refri = new RefrigeratorView(); // 싱글톤으로 만들기

	private static RefrigeratorDAO refriDAO = RefrigeratorDAOImpl.getInstance();
	public static RefrigeratorView getInstance() {
		return refri;
	}

	public void DrawMap(MemberVO member) {
		RefrigeratorDAO refriDao = new RefrigeratorDAOImpl();
		List<RefrigeratorVO> refriStatus = refriDao.selectIngredientByMemberNumber(member.getMNo());
		List<String> ingredientNameList = null;
		if(refriDao instanceof RefrigeratorDAOImpl) {
			ingredientNameList = ((RefrigeratorDAOImpl) refriDao).searchByIngredientNo(refriStatus);
		}
		
		List<RefrigeratorVO> expireList = RefrigeratorController.findAlarmExpirationDate(member.getMNo());
		
		for(int k=0; k<ingredientNameList.size(); k++) {
			for(int expire=0; expire<expireList.size(); expire++) {
				if(expireList.get(expire).getIngredient().getName().equals(ingredientNameList.get(k))) {
					ingredientNameList.set(k,ingredientNameList.get(k)+"*");
				}
			}
		}
		
		System.out.println();
		System.out.println();
		map[0] = new StringBuffer("            E.A.S.Y :" +member.getMNickname());
		map[1] = new StringBuffer("  ┌───────────────────────────────────────────┐");
		map[2] = new StringBuffer("    식료품 | 수량                                 ");
		map[3] = new StringBuffer("  ├───────────────────────────────────────────┤");
		for(int i = 4; i < 22; i++) {
			if(i%2 != 0)
				map[i]=new StringBuffer("  ├───────────────────────────────────────────┤");
			else
				{if(i/2<=10)
					map[i]=new StringBuffer((i/2)+" ");
				 else
					 map[i]=new StringBuffer((i/2)+"                                             ");}
			map[22]=new StringBuffer("11 ");
			map[23]=new StringBuffer("  └───────────────────────────────────────────┘");
		}	
	
		int max = 0;
		for (int i = 0; i < refriStatus.size(); i++) { // 냉장고 현황(보유 품목) 개수만큼 반복
			RefrigeratorVO refre = refriStatus.get(i); // 냉장고 현황 한줄씩 꺼내기
	
			// 식자재 이름 list에서 하나씩 꺼내서 char[]로 변환(String > char[])
			String ingredientName = ingredientNameList.get(i); // 식자재 이름도 하나씩 꺼내고

			// 식자재 이름 char 타입으로 꺼내 set으로 대입 (char[] > char)
			map[4+2*max].append("    " +ingredientName+" "); // set으로 대입하기
			

			// 냉장고 현황에서 식자재 수량 꺼내서 char[]로 변환 (int > String > char[])
			int amount = refre.getAmount(); // 냉장고 현황에서 수량 꺼내오기
			String amountString = Integer.toString(amount); // 수량 int타입을 String타입으로 변환(10이상도 출력 위해)

			// 식자재 수량 char타입으로 변환해 출력 (char[] > char)
			map[4+2*max].append(amountString+ "   "); // set으로 대입하기
			
			
	        max+=1;
	        if(4+2*max>22) {
	              max=0;
	              continue;
	     }


		}
		for (int i = 0; i < 24; i++)
			System.out.println(map[i]);
		
	}

}

