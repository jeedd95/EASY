package model.vo;

public class ReviewCommentVO extends CommentVO{
	int recipeBoardNo;
	
	
	public ReviewCommentVO(int commentNo, String content, int rating, String nickName,int recipeBoardNo) {
		super(commentNo,content,rating,nickName);
		this.recipeBoardNo = recipeBoardNo;
	}
	
	public ReviewCommentVO(int commentNo,String NickName) {
		super(commentNo,NickName);
	}
	
	public ReviewCommentVO( String content, int rating, String nickName,int recipeBoardNo) {
		super(content,rating,nickName);
		this.recipeBoardNo = recipeBoardNo;
	}

	public int getBoardNo() {
		return recipeBoardNo;
	}


	public void getBoardNo(int recipeBoardNo) {
		this.recipeBoardNo = recipeBoardNo;
	}
	
	public String getName() {
		return "후기 게시판";
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());

		return builder.toString();
	}
		
}
