package model.vo;

public class RecipeCommentVO extends CommentVO{
	int boardNo;
	
	
	public RecipeCommentVO(int commentNo, String content, int rating, String memberNickName,int boardNo) {
		super(commentNo,content,rating,memberNickName);
		this.boardNo =boardNo;
	}
	public RecipeCommentVO(String content, int rating, String memberNickName,int boardNo) {
		super(content,rating,memberNickName);
		this.boardNo =boardNo;
	}
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

		
}
