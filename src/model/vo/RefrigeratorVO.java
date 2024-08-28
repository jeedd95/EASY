package model.vo;

import java.util.Date;

public class RefrigeratorVO {
    private int serialNumber;            
    private int memberNumber;           
    private int ingredientNo;   
    private int amount;         
    private String registDate;    
    private String expirationDate; 

  
    public RefrigeratorVO() {}
    
  
    public RefrigeratorVO(int serialNumber, int memberNumber, int ingredientNo, int amount, String registDate, String expirationDate) {
    	this.ingredientNo = ingredientNo;
    	this.expirationDate = expirationDate;
    	this.ingredientNo=ingredientNo;
    	this.serialNumber = serialNumber;
        this.memberNumber = memberNumber;
        this.amount = amount;
  
    }

   
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int rNo) {
        this.serialNumber = rNo;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int mNo) {
        this.memberNumber = mNo;
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

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("식재료 이름: ?");
		builder.append(", ingredientNo=");
		builder.append(ingredientNo);
		builder.append(", amount=");
		builder.append("]");
		return builder.toString();
	}
    
    
    
}