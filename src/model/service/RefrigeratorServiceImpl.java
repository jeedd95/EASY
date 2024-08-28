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
    	List<RefrigeratorVO> list = dao.searchIngredientByMemberNo(memberNo);

        return list;
    }
    

	@Override
	public List<IngredientVO> selectCategory() {
		
		return dao.selectCategory();
		
	}

	@Override
	public List<IngredientVO> selectIngredient(int category) {
		return dao.selectIngredient(category);
	}
	
	
	public List<RefrigeratorVO> alarmExpirationDate(int memberNo) throws SQLException {
		List<RefrigeratorVO> refriList = dao.alarmExpirationDate(memberNo);
		return refriList;
	}


	@Override
	public List<RefrigeratorVO> removeIngredient(int memberNumber) {
		List<RefrigeratorVO> result = dao.selectIngredientByMemberNumber(memberNumber);
		//IngredientVO로 이름까지 넣어주는 작업 필요 (식재료 번호로 이름찾기)
		for (RefrigeratorVO r : result) {
			r.setIngredient(getNameByIngredientNumber(r.getIngredientNo()));
		}
		
		return result;
	}
	
	/**
	 * 식재료 번호로 이름 찾아서 넣어주기
	 */
	public IngredientVO getNameByIngredientNumber(int ingredientNumber) {
		return dao.getNameByIngredientNumber(ingredientNumber);
	}
}
