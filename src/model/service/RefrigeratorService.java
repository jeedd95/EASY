package model.service;


import java.util.List;
import model.vo.RefrigeratorVO;

public interface RefrigeratorService {
    int addIngredients(List<RefrigeratorVO> ingredients);
    
    int removeIngredients(List<RefrigeratorVO> ingredients); 
    
    List<RefrigeratorVO> getIngredientsByMemberNo(int memberNo); 
}