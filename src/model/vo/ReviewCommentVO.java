package model.vo;

public class ReviewCommentVO extends CommentVO{
	int recipeNo;
	
	
	public ReviewCommentVO(int commentNo, String content, int rating, String nickName,int recipeNo) {
		super(commentNo,content,rating,nickName);
		this.recipeNo = recipeNo;
	}
	
	public ReviewCommentVO(int commentNo,String NickName) {
		super(commentNo,NickName);
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("후기 게시판의 댓글 :");
		builder.append(super.toString());


		return builder.toString();
	}
		
}
