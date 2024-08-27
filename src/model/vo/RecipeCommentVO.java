package model.vo;

public class RecipeCommentVO extends CommentVO{
	int boardNo;
	
	
	public RecipeCommentVO(int commentNo, String content, int rating, String NickName,int boardNo) {
		super(commentNo,content,rating,NickName);
		this.boardNo =boardNo;
	}
	public RecipeCommentVO(int commentNo,String NickName) {
		super(commentNo,NickName);
	}
	public RecipeCommentVO(String content, int rating, String NickName,int boardNo) {
		super(content,rating,NickName);
		this.boardNo =boardNo;
	}
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("레시피 게시판의 댓글 :");
		builder.append(super.toString());


		return builder.toString();
	}

	
	
		
}
