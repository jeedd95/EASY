package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.RefrigeratorVO;
import util.DbManager;

public class RefrigeratorDAOImpl implements RefrigeratorDAO {

    @Override
    public int insertIngredient(List<RefrigeratorVO> refrigeratorList) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "INSERT INTO refrigerator_status (r_no, m_no, ingredient_no, amount, registdate, expirationdate) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);

            for (RefrigeratorVO vo : refrigeratorList) {
                ps.setInt(1, vo.getrNo());  // RefrigeratorVO의 필드와 메서드 이름에 맞게 수정
                ps.setInt(2, vo.getmNo());
                ps.setInt(3, vo.getIngredientNo());
                ps.setInt(4, vo.getAmount());
                ps.setDate(5, new java.sql.Date(vo.getRegistDate().getTime()));  
                ps.setDate(6, new java.sql.Date(vo.getExpirationDate().getTime()));  
                
                result += ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps);
        }

        return result;
    }

    @Override
    public int subtractIngredient(List<RefrigeratorVO> refrigeratorList) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "DELETE FROM refrigerator_status WHERE m_no = ? AND ingredient_no = ? AND amount >= ?";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);

            for (RefrigeratorVO vo : refrigeratorList) {
                ps.setInt(1, vo.getmNo());
                ps.setInt(2, vo.getIngredientNo());
                ps.setInt(3, vo.getAmount());
                
                result += ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps);
        }

        return result;
    }

    @Override
    public List<RefrigeratorVO> searchIngredientByMemberNo(int memberNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RefrigeratorVO> resultList = new ArrayList<>();

        String sql = "SELECT r_no, m_no, ingredient_no, amount, registdate, expirationdate "
                   + "FROM refrigerator_status WHERE m_no = ?";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, memberNo);
            rs = ps.executeQuery();

            while (rs.next()) {
                RefrigeratorVO vo = new RefrigeratorVO(
                    rs.getInt("r_no"),
                    rs.getInt("m_no"),
                    rs.getInt("ingredient_no"),
                    rs.getInt("amount"),
                    rs.getDate("registdate"),
                    rs.getDate("expirationdate")
                );
                resultList.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps, rs);
        }

        return resultList;
    }
}