package controller;

import java.util.List;
import model.vo.RefrigeratorVO;
import model.service.RefrigeratorService;
import model.service.RefrigeratorServiceImpl;

public class RefrigeratorController {

    private RefrigeratorService refrigeratorService;

    public RefrigeratorController() {
        this.refrigeratorService = new RefrigeratorServiceImpl();
    }

    public void addNewIngredients(List<RefrigeratorVO> ingredients) {
        int result = refrigeratorService.addIngredients(ingredients);
        System.out.println("추가된 재료의 수: " + result);
    }

    public void removeIngredients(List<RefrigeratorVO> ingredients) {
        int result = refrigeratorService.removeIngredients(ingredients);
        System.out.println("제거된 재료의 수: " + result);
    }

   
}
