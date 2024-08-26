package model.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.RecipeBoardVO;
import model.vo.RecipeCommentVO;
import model.vo.ReviewBoardVO;
import model.vo.ReviewCommentVO;
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
	public int writeComment(CommentVO comment,String boardName) {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into "+boardName+" values(1,?,?,?,?)";
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);

			if(comment instanceof RecipeCommentVO recipeComment) {
				ps.setInt(1,recipeComment.getBoardNo()); 
				ps.setString(2,recipeComment.getContent());
				ps.setInt(3, recipeComment.getRating());
				ps.setString(4, recipeComment.getMemberNickName());
			}
			
			if(comment instanceof ReviewCommentVO reviewComment) {
				ps.setInt(1,reviewComment.getrecipeNo()); 
				ps.setString(2,reviewComment.getContent());
				ps.setInt(3, reviewComment.getRating());
				ps.setString(4, reviewComment.getMemberNickName());
			}
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
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
	@Override
	public BoardVO boardSelectByNo(int boardNO,String boardName) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from "+boardName +" where "+boardName+"_NO = ?";
		List<CommentVO> comment;
		BoardVO board = null;
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, boardNO);
			rs = ps.executeQuery();
           
            if(rs.next()) {
	        int boardNo = rs.getInt(1);
	        int tableNo = rs.getInt(2);
	        String title  = rs.getString(3);
	        String content = rs.getString(4);
	        String date = rs.getString(5);
	        if(boardName.equals("MY_RECIPE_BOARD"))
	        	board = new RecipeBoardVO(boardNo,tableNo, title, content, date);
	        if(boardName.equals("RECIPE_REVIEW_BOARD"))
	        	board = new ReviewBoardVO(boardNo,tableNo, title, content, date);
	        
	        comment = replyBoardByNo(con,boardNo,boardName);
	        board.setComment(comment);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps,rs);
		}
		
		return board;
		
	}
	
	private List<CommentVO> replyBoardByNo(Connection con, int boardNo,String boardName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] name = boardName.split("_");
		String tableName = name[0]+"_"+name[1]+"_Comment";
		String sql = "select * from "+tableName+" where "+boardName+"_NO = ?";
		List<CommentVO> comment = new ArrayList<CommentVO>();
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, boardNo);
			rs = ps.executeQuery();
           
            while(rs.next()) {
	        
            int relpyNo = rs.getInt(1);
	        int tableNo = rs.getInt(2);
	        String cotent  = rs.getString(3);
	        int rating = rs.getInt(4);
	        String M_Id = rs.getString(5);
	        
	        CommentVO commentVO=null;
	        if(boardName.equals("MY_RECIPE_BOARD"))
		        commentVO= new RecipeCommentVO(relpyNo,cotent,rating,M_Id,tableNo);
	        if(boardName.equals("RECIPE_REVIEW_BOARD"))
		        commentVO= new ReviewCommentVO(relpyNo,cotent,rating,M_Id,tableNo);
	        
            comment.add(commentVO);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con,ps, rs);
		}
		return comment;
		
	}

}
