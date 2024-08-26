package model.service;

import java.util.List;

import exception.InputFormatException;
import model.dao.StatsDAO;
import model.dao.StatsDAOImpl;
import model.vo.StatsVO;

public class StatsServiceImpl implements StatsService {
	StatsDAO stsdao = new StatsDAOImpl();
	
	@Override
	public List<StatsVO> searchIngredientStatsByMine(int memberNo) throws InputFormatException {
		List<StatsVO> list = stsdao.searchIngredientStatsByMine(memberNo);
		return null;
	}

	@Override
	public List<StatsVO> searchIngredientStatsByGender() throws InputFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatsVO> searchIngredientStatsByAmount() throws InputFormatException {
		// TODO Auto-generated method stub
		return null;
	}

}
