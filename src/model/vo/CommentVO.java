package model.vo;

public class CommentVO {
	int commentNo;
	String content;
	int rating;
	String memberNickName;
	public CommentVO(String content, int rating, String memberNickName){
		this.content = content;
		this.rating = rating;
		this.memberNickName = memberNickName;
	}
	
	public CommentVO(int commentNO,String content, int rating, String memberNickName){
		this(content,rating,memberNickName);
		this.memberNickName = memberNickName;

	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(memberNickName);
		builder.append(": ");
		builder.append(content);
		builder.append(" 별점 =");
		builder.append(rating);
		return builder.toString();
	}
		
}
