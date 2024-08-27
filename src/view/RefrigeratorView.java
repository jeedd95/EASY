package view;

import model.vo.MemberVO;

public class RefrigeratorView {
	private StringBuffer map[] = new StringBuffer[25];	
	private static RefrigeratorView refri = new RefrigeratorView();
	
	public static RefrigeratorView getInstance() {
		return refri;
	}
	
	public void DrawMap(MemberVO member) {
		map[0]=new StringBuffer("            E.A.S.Y :" +member.getMNickname());
		map[1]=new StringBuffer("  ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
		for(int i = 2; i < 22; i++) {
			if(i%2 != 0)
				map[i]=new StringBuffer("  ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
			else
				{if(i/2<10)
					map[i]=new StringBuffer((i/2)+" │   │   │   │   │   │   │   │   │   │   │   │");
				 else
					 map[i]=new StringBuffer((i/2)+"│   │   │   │   │   │   │   │   │   │   │   │");}
			map[22]=new StringBuffer("11│   │   │   │   │   │   │   │   │   │   │   │");
			map[23]=new StringBuffer("  └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘");
		}	
		map[2].setCharAt(3, 'a');

		for(int i=0; i<24;i++)
			System.out.println(map[i]);
	}

}
