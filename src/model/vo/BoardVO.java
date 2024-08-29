package model.vo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BoardVO {
	
	private int no;
	private String title;
	private String content;
	private String postDate;
	private DecimalFormat df = new DecimalFormat("0.0");

	private List<CommentVO> comment = new ArrayList<CommentVO>();
	
	public BoardVO() {}
	
	public BoardVO(int no) {
		this.no = no;
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
	
	public String getColmun() {
		return "";		
	}
	
	
	public List<CommentVO> getComment() {
		return comment;
	}

	public void setComment(List<CommentVO> comment) {
		this.comment = comment;
	}
	public double getRating() {
		int sum =0;
		if(comment.isEmpty() || comment==null)
			return 0;
		
		for(CommentVO commentVo : comment) {
			if(commentVo==null)
				break;
			sum+=commentVo.getRating();
		}
		double avg = (double)sum/comment.size();
		
		return avg;
	}
	public String getName() {
		return "";
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("  | 제목 :");
		builder.append(title);
		builder.append("  | 평균 평점 :");
		builder.append(df.format(getRating()));
		builder.append("| 쓴 날짜=");
		builder.append(postDate);
		return builder.toString();
	}
	
	public String toStringDetail() {
			StringBuilder builder = new StringBuilder();
			builder.append("제목 :");
			builder.append(title);
			builder.append("\n");
			builder.append(content);
			return builder.toString();
		
	}
	
	
}