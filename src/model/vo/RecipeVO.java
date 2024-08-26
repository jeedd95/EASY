package model.vo;

import java.util.List;

public class RecipeVO {
	private int serialNumber;
	private String name;
	private String method;
	
	private List<RecipeIngredientVO> recipeIngredientList;
	
	public RecipeVO() {
	}

	public RecipeVO(int serialNumber, String name, String method) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.method = method;
	}
	
	public RecipeVO(int serialNumber, String name, String method, List<RecipeIngredientVO>  recipeIngredientList) {
		this(serialNumber, name, method);
		this.recipeIngredientList = recipeIngredientList;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<RecipeIngredientVO> getRecipeIngredientList() {
		return recipeIngredientList;
	}

	public void setRecipeIngredientList(List<RecipeIngredientVO> recipeIngredientList) {
		this.recipeIngredientList = recipeIngredientList;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		RecipeVO other = (RecipeVO)obj;
		return name.equals(other.getName());
	}

	@Override
	public String toString() {
		return "RecipeVO [serialNumber=" + serialNumber + ", name=" + name + ", method=" + method
				+ ", recipeIngredientList=" + recipeIngredientList + "]";
	}
	

}
