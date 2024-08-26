package model.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.service.BoardService;
import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.RecipeBoardVO;
import model.vo.ReviewBoardVO;
import util.DbManager;

public class BoardDAOImpl implements  BoardDAO {
	private static BoardDAO boardDAO;
	
	public static BoardDAO getInstance() {
		if(boardDAO ==null)
			boardDAO = new BoardDAOImpl();
		return boardDAO;
	}
	@Override
	public int postBoard(BoardVO board) {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into my_recipe_board values(?,?,?,?,sysdate)";
		System.out.println(sql);
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			
			if(board instanceof RecipeBoardVO recipeBoard) {
				ps.setInt(1, 5);
				ps.setInt(2, recipeBoard.getMemberNo());
				ps.setString(3, recipeBoard.getTitle());
				ps.setString(4, recipeBoard.getContent());
			}
			
			/* 관리자 모드 할꺼면 
			if(board instanceof ReviewBoardVO reviewBoard) {
				ps.setInt(1, 2);
				ps.setInt(2, 2);
				ps.setString(3, reviewBoard.getTitle());
				ps.setString(4, reviewBoard.getContent());
			}
			*/
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public List<BoardVO> searchMyPost(int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentVO> searchMyComment(int memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteComment(CommentVO comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writeComment(CommentVO comment) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<BoardVO> searchPostByName(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from "+name;
		//테이블 이름 갖고 온걸로 그 테이블 정보 검색
		
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
            
            String[] boardName = name.split("_");
			String className = "model.vo."+boardName[1]+"BoardVO"; // 전체 패키지 경로와 클래스 이름
			
            while(rs.next()) {
	        // 클래스를 로드하고 객체를 생성
	        Class<?> clazz = Class.forName(className);
	        int no = rs.getInt(1);
	        int tableNo = rs.getInt(2);
	        String title  = rs.getString(3);
	        String content = rs.getString(4);
	        String date = rs.getString(5);
	        
	        //생성자에 넣을 필드 선언
	        Object[] constructorArgs = { no,tableNo,title,content,date };
            //클래스에 넣을 생성자 필드 타입 설정
	        Class<?>[] paramTypes = { int.class, int.class, String.class, String.class, String.class };
            
            Constructor<?> constructor = clazz.getConstructor(paramTypes);
	        
            Object obj = constructor.newInstance(constructorArgs);
            
            boardList.add((BoardVO)obj);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps);
		}
		System.out.println("DAO 확인");
		return boardList;
		
	}

}
