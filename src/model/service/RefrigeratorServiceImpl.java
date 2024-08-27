package model.service;

import java.util.List;
import model.dao.RefrigeratorDAO;
import model.vo.RefrigeratorVO;

public class RefrigeratorServiceImpl implements RefrigeratorService {

    private final RefrigeratorDAO refrigeratorDAO;

    // RefrigeratorDAO 주입을 생성자로 처리
    public RefrigeratorServiceImpl(RefrigeratorDAO refrigeratorDAO) {
        this.refrigeratorDAO = refrigeratorDAO;
    }

    @Override
    public int insertIngredient(List<RefrigeratorVO> ingredients) {
        // 입력된 재료 리스트가 null인지 확인
        if (ingredients == null) {
            throw new IllegalArgumentException("추가할 재료 목록이 비어 있습니다.");
        }
        // 입력된 재료 리스트가 비어있는지 확인
        if (ingredients.isEmpty()) {
            throw new IllegalArgumentException("추가할 재료 목록이 비어 있습니다.");
        }
        return refrigeratorDAO.insertIngredient(ingredients);
    }

    @Override
    public int subtractIngredient(List<RefrigeratorVO> ingredients) {
        // 입력된 재료 리스트가 null인지 확인
        if (ingredients == null) {
            throw new IllegalArgumentException("제거할 재료 목록이 비어 있습니다.");
        }
        // 입력된 재료 리스트가 비어있는지 확인
        if (ingredients.isEmpty()) {
            throw new IllegalArgumentException("제거할 재료 목록이 비어 있습니다.");
        }
        return refrigeratorDAO.subtractIngredient(ingredients);
    }

    @Override
    public List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo) {
        // 회원 번호가 0 이하인지 확인
        if (memberNo <= 0) {
            throw new IllegalArgumentException("유효하지 않은 회원 번호입니다.");
        }
        return refrigeratorDAO.searchIngredientByMemberNo(memberNo);
    }
}
