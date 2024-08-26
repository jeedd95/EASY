package model.vo;

public class RecipeBoardVO extends BoardVO{
	
	int memberNo;
	
	public RecipeBoardVO (int no,int memberNo,String title, String content, String postdate) {
		super(no,title,content,postdate);
		this.memberNo = memberNo;
	
	}
	
	public RecipeBoardVO (String title, String content) {
		super(title,content);
	
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
		builder.append("CboardVO ");
		builder.append(super.toString());
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
