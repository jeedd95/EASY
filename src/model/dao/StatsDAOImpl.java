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
	public List<StatsVO> searchIngredientStatsByGender(String gender) throws InputFormatException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StatsVO> list = new ArrayList<StatsVO>();
		String Sql = "select * from stats where M_NO in (select m_no from member where M_GENDER=?)";
		
		try {
			
			con = DbManager.getConnection();
			ps = con.prepareStatement(Sql);
			ps.setString(1, gender);	
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatsVO stats = new StatsVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				list.add(stats);
			}
			
		} catch (SQLException e) {
			throw new InputFormatException("성별(" + gender + ") 전체 현황 조회 중 DB 오류 발생. 다시 시도해주세요.");
			//e.printStackTrace();
			
		} finally {
			DbManager.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<StatsVO> searchIngredientStatsByAmount() throws InputFormatException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StatsVO> list = new ArrayList<StatsVO>();
		String sql = "select * from stats order by amount desc, usedate";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs =ps.executeQuery();
			
			while(rs.next()) {
				StatsVO stats = new StatsVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				list.add(stats);
			}
			
		} catch (SQLException e) {
			throw new InputFormatException("전체 현황 조회 중 DB 오류 발생. 다시 시도해주세요.");
			//e.printStackTrace();
			
		} finally {
			DbManager.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<String> searchByIngredientNo(List<StatsVO> list) throws InputFormatException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> ingredientNameList = new ArrayList<String>();
		String sql = "select ingredient_name from ingredient where ingredient_no = ?";

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			
			for( StatsVO stats : list) {
				ps.setInt(1, stats.getIngredientNo());
				rs = ps.executeQuery();
				
				while(rs.next()) {
					ingredientNameList.add(rs.getString(1));
				}
			}//for End

		} catch (SQLException e) {
			throw new InputFormatException("현황-식재료이름 조회 중 DB 오류 발생. 다시 시도해주세요.");
			//e.printStackTrace();
			
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return ingredientNameList;
	}

}
