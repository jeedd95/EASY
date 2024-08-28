package model.vo;

import java.util.Date;

public class RefrigeratorVO {
    private int serialNumber;            
    private int memberNumber;           
    private int ingredientNo;   
    private int amount;         
    private Date registDate;    
    private Date expirationDate; 

  
    public RefrigeratorVO() {}

    
    public RefrigeratorVO(int rNo, int mNo, int ingredientNo, int amount, Date registDate, Date expirationDate) {
        this.serialNumber = rNo;
        this.memberNumber = mNo;
        this.ingredientNo = ingredientNo;
        this.amount = amount;
        this.registDate = registDate;
        this.expirationDate = expirationDate;
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

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("RefrigeratorVO [serialNumber=");
//		builder.append(serialNumber);
//		builder.append(", memberNumber=");
//		builder.append(memberNumber);
//		builder.append(", ingredientNo=");
//		builder.append(ingredientNo);
//		builder.append(", amount=");
//		builder.append(amount);
//		builder.append(", registDate=");
//		builder.append(registDate);
//		builder.append(", expirationDate=");
//		builder.append(expirationDate);
//		builder.append("]");
//		return builder.toString();
//	}
    
	
	public String toString(String ingredientName) {
		StringBuilder builder = new StringBuilder();
		builder.append(ingredientName);
		builder.append(amount);
		return builder.toString();
	}
    
    
}