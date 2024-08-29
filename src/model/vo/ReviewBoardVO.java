package model.vo;

public class ReviewBoardVO extends BoardVO{
	private int RecipeNo;
	
	public ReviewBoardVO(int no, int RecipeNo ,String title, String content, String postdate) {
		super(no,title,content,postdate);
		this.RecipeNo = RecipeNo;
	
	}
	public ReviewBoardVO() {
	}
	public ReviewBoardVO (int RecipeNo,String title, String content) {
		super(title,content);
		this.RecipeNo = RecipeNo;
	
	}
	public int getRecipeNo() {
		return RecipeNo;
	}

	public void setRecipeNo(int RecipeNo) {
		this.RecipeNo = RecipeNo;
	}
	public int colmun() {
		return RecipeNo;		
	}
	public String getName() {
		return "레시피 후기 게시판";
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("게시판 번호 : ");
		builder.append(super.getNo());
		builder.append(super.toString());
		return builder.toString();
	}

}
