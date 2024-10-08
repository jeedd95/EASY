package model.vo;

import java.util.List;
import java.util.Map;

public class WishListVO {
	private int wishListNo; //PK
	private int memberNo; //FK
	private int ingredientNo; //FK
	private int amount;
	
	
	public WishListVO() {} //기본 생성자

	public WishListVO (int memberNo, int ingredientNo) { //삭제 시 필요한 생성자
		this.memberNo = memberNo;
		this.ingredientNo = ingredientNo;
	}
	
	public WishListVO(int memberNo, int ingredientNo, int amount) { //조회 시 필요한 생성자
		this(memberNo, ingredientNo);
		this.amount = amount;
	}
	
	public WishListVO(int wishListNo, int memberNo, int ingredientNo, int amount) { //추가 시 필요한 생성자
		this(memberNo, ingredientNo, amount);
		this.wishListNo = wishListNo;
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
		builder.append("[찜번호: ");
		builder.append(wishListNo);
		builder.append(" | ");
		builder.append(", 회원번호: ");
		builder.append(memberNo);
		builder.append(" | ");
		builder.append(", 식재료 번호: ");
		builder.append(ingredientNo);
		builder.append(" | ");
		builder.append(", 관리 수량: ");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
	public String toString(String ingredientName) {
		StringBuilder builder = new StringBuilder();
		builder.append(ingredientName);
		builder.append("\t | ");
		builder.append(amount);
		return builder.toString();
	}
	
	
}
