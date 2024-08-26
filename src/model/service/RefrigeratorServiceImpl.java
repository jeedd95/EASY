package model.service;

import java.util.List;
import model.dao.RefrigeratorDAO;
import model.dao.RefrigeratorDAOImpl;
import model.vo.RefrigeratorVO;


public class RefrigeratorServiceImpl implements RefrigeratorService {

    private RefrigeratorDAO refrigeratorDAO;

    
    public RefrigeratorServiceImpl() {
        this.refrigeratorDAO = new RefrigeratorDAOImpl(); 
    }

    @Override
    public int addIngredients(List<RefrigeratorVO> ingredients) {
        
        return refrigeratorDAO.insertIngredient(ingredients);
    }

    @Override
    public int removeIngredients(List<RefrigeratorVO> ingredients) {
        
        return refrigeratorDAO.subtractIngredient(ingredients);
    }

    @Override
    public List<RefrigeratorVO> getIngredientsByMemberNo(int memberNo) {
        
        return refrigeratorDAO.searchIngredientByMemberNo(memberNo);
    }
}
