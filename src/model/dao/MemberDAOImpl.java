package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.MemberVO;
import util.DbManager;

public class MemberDAOImpl implements MemberDAO {
	private static MemberDAO memberDAO;
	
	public static MemberDAO getInstance() {
		if(memberDAO == null)
			memberDAO = new MemberDAOImpl();
		return memberDAO;
	}
    

    @Override
    public int joinMember(MemberVO member) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String password = member.getMPw();
        if (password.length() >= 5 && password.length() <= 20) {            
        } else {
            throw new IllegalArgumentException("비밀번호는 5자 이상 20자 이하로 설정해야 합니다.");
        }

        String sql = "INSERT INTO member (m_no, m_id, m_pw, m_name, m_nickname, m_gender, joindate) VALUES (MEMBER_SEQ.nextval,?, ?, ?, ?, ?,sysdate)";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, member.getMId());
            ps.setString(2, member.getMPw());
            ps.setString(3, member.getMName());
            ps.setString(4, member.getMNickname());
            ps.setString(5, member.getMGender());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps);
        }
        
        return result;
    }
    /*
     * 아이디 중복
     */

    @Override
    public boolean checkIdDuplicate(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean isDuplicate = false;

        String sql = "SELECT * FROM member WHERE m_id = ?";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
            	return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps, rs);
        }

        return isDuplicate;
    }
    /*
     * 닉네임 중복
     */
	@Override
	public boolean checkNickNameDuplicate(String nickName) {
		 Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
	        boolean isDuplicate = false;

	        String sql = "SELECT * FROM member WHERE m_nickName = ?";

	        try {
	            con = DbManager.getConnection();
	            ps = con.prepareStatement(sql);
	            ps.setString(1, nickName);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	            	return true;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DbManager.dbClose(con, ps, rs);
	        }

	        return isDuplicate;
	}
    @Override
    public MemberVO login(MemberVO member) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        MemberVO storedMember=null;

        String sql = "SELECT * FROM member WHERE m_id = ? AND m_pw = ?";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, member.getMId());
            ps.setString(2, member.getMPw());  
  
            rs = ps.executeQuery();

            if (rs.next()) {
            	storedMember = new MemberVO(
                	rs.getInt("m_no"),
                    rs.getString("m_id"),
                    rs.getString("m_pw"),  
                    rs.getString("m_name"),
                    rs.getString("m_nickname"),
                    rs.getString("m_gender"),
                    rs.getString("joindate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();  
        } finally {
            DbManager.dbClose(con, ps, rs);
        }

        return storedMember;  
    }
    @Override
    public int removeMember(String pw) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        String sql = "DELETE FROM member WHERE m_pw = ?";

        try {
            con = DbManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pw);
            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.dbClose(con, ps);
        }

        return result;
    }



}
