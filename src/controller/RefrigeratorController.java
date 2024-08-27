package controller;

import java.util.List;
import model.vo.RefrigeratorVO;
import model.service.RefrigeratorService;

public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    // RefrigeratorService 주입을 생성자로 처리
    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    public void insertIngredient(List<RefrigeratorVO> ingredients) {
        try {
            int result = refrigeratorService.insertIngredient(ingredients);
            System.out.println("추가된 재료의 수: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("재료 추가 실패: " + e.getMessage());
        }
    }

    public void subtractIngredient(List<RefrigeratorVO> ingredients) {
        try {
            int result = refrigeratorService.subtractIngredient(ingredients);
            System.out.println("제거된 재료의 수: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("재료 제거 실패: " + e.getMessage());
        }
    }

    public void searchIngredientByMemberNo(int memberNo) {
        try {
            List<RefrigeratorVO> ingredients = refrigeratorService.searchIngredientByMemberNo(memberNo);
            ingredients.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("재료 검색 실패: " + e.getMessage());
        }
    }
}
