package model.vo;

public class RecipeIngredientVO {
	private int serialNumber;
	private int recipe_No;
	private int ingredient_No;
	
	public RecipeIngredientVO() {
	
	}
	
	public RecipeIngredientVO(int serialNumber, int recipe_No, int ingredient_No) {
		this.serialNumber = serialNumber;
		this.recipe_No = recipe_No;
		this.ingredient_No = ingredient_No;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getRecipe_No() {
		return recipe_No;
	}

	public void setRecipe_No(int recipe_No) {
		this.recipe_No = recipe_No;
	}

	public int getIngredient_No() {
		return ingredient_No;
	}

	public void setIngredient_No(int ingredient_No) {
		this.ingredient_No = ingredient_No;
	}
	
	
	

}
