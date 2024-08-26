package model.vo;

public class RecipeBoardVO extends BoardVO{
	
	int memberNo;
	
	public RecipeBoardVO (int no,int memberNo,String title, String content, String postdate) {
		super(no,title,content,postdate);
		this.memberNo = memberNo;
	
	}
	
	public RecipeBoardVO (int memberNo, String title, String content) {
		super(title,content);
		this.memberNo = memberNo;
	
	}

	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	public String colmun() {
		return "m_no";
		
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("나만의 레시피 게시판 ");
		builder.append(": 회원 닉네임: ");
		builder.append(memberNo);
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
