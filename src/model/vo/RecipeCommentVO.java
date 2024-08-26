package model.vo;

public class RecipeCommentVO extends CommentVO{
	int boardNo;
	
	
	public RecipeCommentVO(int commentNo, String content, int rating, String NickName,int boardNo) {
		super(commentNo,content,rating,NickName);
		this.boardNo =boardNo;
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

		
}
