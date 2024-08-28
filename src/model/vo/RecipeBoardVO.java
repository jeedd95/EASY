package model.vo;

public class RecipeBoardVO extends BoardVO{
	
	String mNickname;
	
	public RecipeBoardVO (int no,String mNickname,String title, String content, String postdate) {
		super(no,title,content,postdate);
		this.mNickname = mNickname;
	
	}
	public RecipeBoardVO (int no,String mNickname) {
		super(no);
		this.mNickname = mNickname;
	
	}
	public RecipeBoardVO (String mNickname, String title, String content) {
		super(title,content);
		this.mNickname = mNickname;
	
	}

	
	public String getMNickname() {
		return mNickname;
	}

	public void setMNickname(String mNickname) {
		this.mNickname = mNickname;
	}
	
	public String getColmun() {
		return mNickname;
		
	}
	
	public String getName() {
		return "나만의 레시피 게시판";
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("게시판 번호 : ");
		builder.append(super.getNo());
		builder.append(" | 회원 닉네임: ");
		builder.append(mNickname);
		builder.append(super.toString());
		return builder.toString();
	}
	
	
}
