package model.vo;

public class ReviewBoardVO extends BoardVO{
	int RecipeNo;
	
	public ReviewBoardVO(int no, int RecipeNo ,String title, String content, String postdate) {
		super(no,title,content,postdate);
		this.RecipeNo = RecipeNo;
	
	}
	public ReviewBoardVO() {
	}
	public ReviewBoardVO (String title, String content) {
		super(title,content);
	
	}
	public int getRecipeNo() {
		return RecipeNo;
	}

	public void setRecipeNo(int RecipeNo) {
		this.RecipeNo = RecipeNo;
	}
	public String colmun() {
		return "Recipe_no	";		
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AboardVO ");
		builder.append(super.toString());
		builder.append(", RecipeNo=");
		builder.append(RecipeNo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
