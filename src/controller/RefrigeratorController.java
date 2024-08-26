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

    public void insertIngredient(List<RefrigeratorVO> ingredients) {
        int result = refrigeratorService.insertIngredient(ingredients);
        System.out.println("추가된 재료의 수: " + result);
    }


    public void subtractIngredient(List<RefrigeratorVO> ingredients) {
        int result = refrigeratorService.subtractIngredient(ingredients);
        System.out.println("제거된 재료의 수: " + result);
    }

    public void searchIngredientByMemberNo(int memberNo) {
        List<RefrigeratorVO> ingredients = refrigeratorService.searchIngredientByMemberNo(memberNo);
        ingredients.forEach(System.out::println);
    }
}
