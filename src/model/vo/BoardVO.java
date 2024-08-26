package model.vo;

public class BoardVO {
	
	int no;
	String title;
	String content;
	String postDate;
	public BoardVO() {
	}
	public BoardVO (int no,String title, String content, String postdate) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.postDate = postdate;
	
	}
	public BoardVO (String title, String content) {
		this.title =title;
		this.content=content;
	
	}
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
		
		
	}
	
	public String colmun() {
		return "";		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", postDate=");
		builder.append(postDate);
		return builder.toString();
	}
	
	
	
}