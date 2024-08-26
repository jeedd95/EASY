package model.vo;

import java.util.Date;

public class RefrigeratorVO {
    private int rNo;            
    private int mNo;           
    private int ingredientNo;   
    private int amount;         
    private Date registDate;    
    private Date expirationDate; 

  
    public RefrigeratorVO() {}

    
    public RefrigeratorVO(int rNo, int mNo, int ingredientNo, int amount, Date registDate, Date expirationDate) {
        this.rNo = rNo;
        this.mNo = mNo;
        this.ingredientNo = ingredientNo;
        this.amount = amount;
        this.registDate = registDate;
        this.expirationDate = expirationDate;
    }

   
    public int getrNo() {
        return rNo;
    }

    public void setrNo(int rNo) {
        this.rNo = rNo;
    }

    public int getmNo() {
        return mNo;
    }

    public void setmNo(int mNo) {
        this.mNo = mNo;
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
}