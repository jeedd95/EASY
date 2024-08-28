package model.vo;

public class IngredientVO {
	private int serialNumber;
	private String name;
	private int category;
	
	
	public IngredientVO(int serialNumber, String name, int category) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.category = category;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	

}
