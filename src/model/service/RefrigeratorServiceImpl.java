package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.RefrigeratorDAO;
import model.dao.RefrigeratorDAOImpl;

import model.vo.IngredientVO;

import model.vo.RefrigeratorVO;

public class RefrigeratorServiceImpl implements RefrigeratorService {


    private static RefrigeratorService service;
    RefrigeratorDAO dao = RefrigeratorDAOImpl.getInstance();

    
    
    public static RefrigeratorService getInstance() {
		if(service == null)
			service = new RefrigeratorServiceImpl();
	
		return service;
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
        return dao.insertIngredient(ingredients);
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
        return dao.subtractIngredient(ingredients);
    }

    @Override
    public List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo) {
        // 회원 번호가 0 이하인지 확인
        if (memberNo <= 0) {
            throw new IllegalArgumentException("유효하지 않은 회원 번호입니다.");
        }
        return dao.searchIngredientByMemberNo(memberNo);
    }

	@Override
	public List<IngredientVO> selectCategory() {
		
		return dao.selectCategory();
		
	}

	@Override
	public List<IngredientVO> selectIngredient(int ingredientNumber) {
		return dao.selectIngredient(ingredientNumber);
	}
	
	
	public List<RefrigeratorVO> alarmExpirationDate(int memberNo) throws SQLException {
		List<RefrigeratorVO> refriList = dao.alarmExpirationDate(memberNo);
		return refriList;
	}
}
