package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InputFormatException;
import model.vo.StatsVO;
import util.DbManager;

public class StatsDAOImpl implements StatsDAO {

	@Override
	public List<StatsVO> searchIngredientStatsByMine(int memberNo) throws InputFormatException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StatsVO> list = new ArrayList<StatsVO>();
		String sql = "select * from stats where M_NO = ? order by  amount desc, usedate";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatsVO myStats = new StatsVO(0, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				list.add(myStats);
			}
			
		} catch (SQLException e) {
			throw new InputFormatException("나의 현황 조회 중 DB 오류 발생. 다시 시도해주세요.");
			//e.printStackTrace();
			
		} finally {
			DbManager.dbClose(con, ps, rs);
		}
	
		return list;
	}

	@Override
	public List<StatsVO> searchIngredientStatsByGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatsVO> searchIngredientStatsByAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
