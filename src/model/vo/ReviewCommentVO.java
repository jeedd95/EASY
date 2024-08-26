package model.vo;

public class ReviewCommentVO extends CommentVO{
	int recipeNo;
	
	
	public ReviewCommentVO(int commentNo, String content, int rating, String memberId,int recipeNo) {
		super(commentNo,content,rating,memberId);
		this.recipeNo = recipeNo;
	}
	public ReviewCommentVO( String content, int rating, String memberId,int recipeNo) {
		super(content,rating,memberId);
		this.recipeNo = recipeNo;
	}

	public int getrecipeNo() {
		return recipeNo;
	}


	public void setrecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	
		
}
