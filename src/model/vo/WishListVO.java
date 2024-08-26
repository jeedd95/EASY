package model.vo;

public class WishListVO {
	private int wishListNo;
	private int memberNo;
	private int ingredientNo;
	private int amount;
	
	public WishListVO() {} //기본 생성자

	public WishListVO(int wishListNo, int memberNo, int ingredientNo, int amount) {
		super();
		this.wishListNo = wishListNo;
		this.memberNo = memberNo;
		this.ingredientNo = ingredientNo;
		this.amount = amount;
	}

	public int getWishListNo() {
		return wishListNo;
	}

	public void setWishListNo(int wishListNo) {
		this.wishListNo = wishListNo;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WishListVO [wishListNo=");
		builder.append(wishListNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", ingredientNo=");
		builder.append(ingredientNo);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

	
	
}
