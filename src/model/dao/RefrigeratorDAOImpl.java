package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.IngredientVO;
import model.vo.RefrigeratorVO;
import util.DbManager;

public class RefrigeratorDAOImpl implements RefrigeratorDAO {

	private static  RefrigeratorDAO refriDAO;
	
	
	public static RefrigeratorDAO getInstance() {
		if(refriDAO == null)
			refriDAO = new RefrigeratorDAOImpl();
	
		return refriDAO;
	}

	
    @Override
    public int insertIngredient(List<RefrigeratorVO> refrigeratorList) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "INSERT INTO refrigerator_status VALUES (REFRIGERATOR_SEQ.nextval, ?, ?, ?, sysdate, sysdate+?)";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);

            for (RefrigeratorVO vo : refrigeratorList) {
//                ps.setInt(1, vo.getSerialNumber());  // RefrigeratorVO의 필드와 메서드 이름에 맞게 수정
                ps.setInt(1, vo.getMemberNumber());
                ps.setInt(2, vo.getIngredientNo());
                ps.setInt(3, vo.getAmount());
            	ps.setInt(4, Integer.parseInt(vo.getExpirationDate()));
                
//                ps.setDate(4, vo.getRegistDate());  
//                ps.setDate(4, vo.getExpirationDate());  
                
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
            	System.out.println(vo.toString());
                ps.setInt(1, vo.getMemberNumber());
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
                    rs.getString("registdate"),
                    rs.getString("expirationdate")
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

    
   
	@Override
	public List<IngredientVO> selectCategory() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from INGREDIENT where CATEGORY is null";
		
		List<IngredientVO> categoryList = new ArrayList<IngredientVO>();
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int serialNumber = rs.getInt("INGREDIENT_NO");
				String ingredientName = rs.getString("INGREDIENT_NAME");
				int category = rs.getInt("CATEGORY");
				
				categoryList.add(new IngredientVO(serialNumber, ingredientName, category));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		
		return categoryList;
	}

	@Override
	public List<IngredientVO> selectIngredient(int category) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from INGREDIENT where CATEGORY =?";
		
		List<IngredientVO> ingredientList = new ArrayList<IngredientVO>();
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int serialNumber = rs.getInt("INGREDIENT_NO");
				String ingredientName = rs.getString("INGREDIENT_NAME");
//				int category = rs.getInt("CATEGORY");
				
				ingredientList.add(new IngredientVO(serialNumber, ingredientName, category));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		
		return ingredientList;
	}
	
	@Override
	public List<RefrigeratorVO> alarmExpirationDate(int memberNo) throws SQLException {
		List<RefrigeratorVO> refriList= new ArrayList<RefrigeratorVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select i.INGREDIENT_NAME , r.AMOUNT, r.EXPIRATIONDATE from REFRIGERATOR_STATUS r\r\n"
				+ "join  INGREDIENT i on r.INGREDIENT_NO = i.INGREDIENT_NO AND r.M_NO = ?  AND sysdate+5 > EXPIRATIONDATE";
		try {
			con =DbManager.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, memberNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RefrigeratorVO refri = new RefrigeratorVO(
						rs.getInt(2),
						rs.getString(3)
				);
				refri.getIngredient().setName(rs.getString(1));

				refriList.add(refri);
			}
			
		}catch(SQLException e){
			throw new SQLException("정보가 없습니다");
		}
		
		
		return refriList;
	}


	@Override
	public List<RefrigeratorVO> selectIngredientByMemberNumber(int memberNumber) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from REFRIGERATOR_STATUS where M_NO=? ORDER BY R_NO";

		
		List<RefrigeratorVO> refrigeratorList = new ArrayList<RefrigeratorVO>();
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberNumber);
			rs = ps.executeQuery();

			while (rs.next()) {
				int serialNumber = rs.getInt("R_NO");
				int ingredientNumber = rs.getInt("INGREDIENT_NO");
				int amount = rs.getInt("AMOUNT");
				String registDate = rs.getDate("REGISTDATE").toString();
				String expireDate = rs.getDate("EXPIRATIONDATE").toString();
				
				RefrigeratorVO refrig =  new RefrigeratorVO(serialNumber,memberNumber,ingredientNumber,amount,registDate,expireDate);
				refrigeratorList.add(refrig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}
		
		return refrigeratorList;
	}


	@Override
	public IngredientVO getNameByIngredientNumber(int ingredientNumber) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from INGREDIENT where INGREDIENT_NO= ?";
		
		IngredientVO ingredient=null;
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ingredientNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				int serialNumber= rs.getInt("INGREDIENT_NO");
				String name = rs.getString("INGREDIENT_NAME");
				int category = rs.getInt("CATEGORY");
				
				ingredient = new IngredientVO(serialNumber, name, category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbManager.dbClose(con, ps, rs);
		}
		
		return ingredient;
	}
	
	/**
	 * 냉장고 현황으로 식재료 이름 배열 조회하기
	 */
	public List<String> searchByIngredientNo(List<RefrigeratorVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> ingredientName = new ArrayList<String>();
		String sql = "select Ingredient_name from ingredient where ingredient_no = ?";

		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);

			for (RefrigeratorVO refri : list) {
				ps.setInt(1, refri.getIngredientNo());
				rs = ps.executeQuery();

				while (rs.next()) {
					ingredientName.add(rs.getString(1));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("냉장고뷰-식재료명 조회 중 DB에러 발생하였습니다. 다시 시도해주세요");

		} finally {
			DbManager.dbClose(con, ps, rs);
		}

		return ingredientName;
	}



}