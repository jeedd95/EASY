package model.service;


import java.util.List;
import model.vo.RefrigeratorVO;

public interface RefrigeratorService {
    int insertIngredient(List<RefrigeratorVO> ingredients);
    
    int subtractIngredient(List<RefrigeratorVO> ingredients); 
    
    List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo); 
}