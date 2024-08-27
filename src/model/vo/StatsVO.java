package model.vo;

public class StatsVO {

	private int statsNo;
	private int memberNo;
	private int ingredientNo;
	private int amount;
	private String usedDate;
	
	public StatsVO() {} //기본생성자
	
	public StatsVO(int statsNo, int memberNo, int ingredientNo, int amount, String usedDate) {
		super();
		this.statsNo = statsNo;
		this.memberNo = memberNo;
		this.ingredientNo = ingredientNo;
		this.amount = amount;
		this.usedDate = usedDate;
	}

	public int getStatsNo() {
		return statsNo;
	}

	public void setStatsNo(int statsNo) {
		this.statsNo = statsNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getIngredientNo() {
		return ingredientNo;
	}

	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[일련번호: ");
		builder.append(statsNo);
		builder.append(" | ");
		builder.append(", 회원번호: ");
		builder.append(memberNo);
		builder.append(" | ");
		builder.append(", 식재료 번호: ");
		builder.append(ingredientNo);
		builder.append(" | ");
		builder.append(", 수량: ");
		builder.append(amount);
		builder.append(" | ");
		builder.append(", 사용한날짜: ");
		builder.append(usedDate);
		builder.append("]");
		return builder.toString();
	}
	
	public String toString(String ingredientName) {
		StringBuilder builder = new StringBuilder();
		builder.append("[일련번호: ");
		builder.append(statsNo);
		builder.append(" | ");
		builder.append(", 회원번호: ");
		builder.append(memberNo);
		builder.append(" | ");
		builder.append(", 식재료 이름: ");
		builder.append(ingredientNo);
		builder.append(" | ");
		builder.append(", 수량: ");
		builder.append(amount);
		builder.append(" | ");
		builder.append(", 사용한날짜: ");
		builder.append(usedDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
