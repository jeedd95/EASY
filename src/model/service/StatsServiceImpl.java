package model.service;

import java.util.List;

import exception.InputFormatException;
import exception.ListNotFoundException;
import model.dao.StatsDAO;
import model.dao.StatsDAOImpl;
import model.vo.StatsVO;

public class StatsServiceImpl implements StatsService {
	StatsDAO stsdao = new StatsDAOImpl();
	
	@Override
	public List<StatsVO> searchIngredientStatsByMine(int memberNo) throws InputFormatException, ListNotFoundException {
		List<StatsVO> list = stsdao.searchIngredientStatsByMine(memberNo);
		if(list.isEmpty()) throw new ListNotFoundException("나의 현황 조회 실패 : 사용 내역이 없습니다.");
		return list;
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
