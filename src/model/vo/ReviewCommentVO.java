package model.vo;

public class ReviewCommentVO extends CommentVO{
	int recipeNo;
	
	
	public ReviewCommentVO(int commentNo, String content, int rating, String nickName,int recipeNo) {
		super(commentNo,content,rating,nickName);
		this.recipeNo = recipeNo;
	}
	public ReviewCommentVO( String content, int rating, String nickName,int recipeNo) {
		super(content,rating,nickName);
		this.recipeNo = recipeNo;
	}

	public int getrecipeNo() {
		return recipeNo;
	}


	public void setrecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	
		
}
