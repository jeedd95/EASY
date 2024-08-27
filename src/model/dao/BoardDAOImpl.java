package model.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.vo.BoardVO;
import model.vo.CommentVO;
import model.vo.RecipeBoardVO;
import model.vo.RecipeCommentVO;
import model.vo.ReviewBoardVO;
import model.vo.ReviewCommentVO;
import util.DbManager;

public class BoardDAOImpl implements  BoardDAO {
	private static BoardDAO boardDAO;
	
	/*
	 * 싱글톤
	 */
	public static BoardDAO getInstance() {
		if(boardDAO ==null)
			boardDAO = new BoardDAOImpl();
		return boardDAO;
	}
	/*
	 * 게시물 작성
	 * 유저는 내 레시피 게시판 밖에 못함
	 */
	@Override
	public int postBoard(BoardVO board) {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into my_recipe_board values(1,?,?,?,sysdate)";
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
	
	/*
	 * 내가 쓴 글 목록 출력
	 */
	@Override
	public List<BoardVO> searchMyPost(int memberNo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		String sql = "select * from MY_RECIPE_BOARD WHERE M_NO = ?";
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new RecipeBoardVO(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));		
				boardList.add(board);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DbManager.dbClose(con, ps,rs);
		}
		return boardList;
	}
	/*
	 * 내가 쓴 댓글들 출력
	 * 댓글은 2 게시판에 다 쓸 수 있어서
	 * TableName를 DB에서 불러와 TABLENAME를 조회해서 댓글 정보를 갖고와 
	 * TableName를 String(key)로 쓰고 댓글들의 번호와 Object(클래스 동적할당으로) 받아 
	 * Map<String(댓글번호,Object(CommentVO))써서
	 * Map 안에 Map 구조를 이룸 
	 */
	@Override
	public Map<String, Map<String,Object>> searchMyComment(String nickName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//user 테이블에 있는 board 포함 테이블 이름 갖고오기
		String sql ="SELECT initcap(TABLE_NAME) FROM USER_TABLES where TABLE_NAME like '%COMMENT%'";
		
		Map<String, Map<String,Object>> commentList = new HashMap<String, Map<String,Object>>();
		System.out.println(sql);
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				System.out.println(name+"1");
				commentList = commentFindByMNo(con,name,commentList,nickName);
			}
			
						
		} catch (Exception e) {
			// TODO: handle exception
		}
		return commentList;
	}
	

	/*
	 * 테이블 이름과 닉네임을 받아 해당 테이블 이름에 닉네임이 일치하면 댓글들 출력
	 */
	public Map<String, Map<String,Object>> commentFindByMNo(Connection con,
		String name, Map<String, Map<String,Object>> comment,String nickName){
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//테이블 이름 갖고 온걸로 그 테이블 정보 검색
		String sql = "select * from "+name+" where M_NICKNAME = ?";
		
		//name 당 map 생성해서 <게시판번호,게시판> 해서 map에 넣고 그걸 board에 넣을 것이다.
        Map<String,Object> map = new HashMap<String, Object>();
		try {
			String[] boardName = name.split("_");
			String className = "model.vo."+boardName[1]+"CommentVO"; // 전체 패키지 경로와 클래스 이름
			ps=con.prepareStatement(sql);
			ps.setString(1, nickName);
			rs = ps.executeQuery();

            
            while(rs.next()) {
	        // 클래스를 로드하고 객체를 생성
	        Class<?> clazz = Class.forName(className);
	        int no = rs.getInt(1);
	        int tableNo = rs.getInt(2);
	        String content  = rs.getString(3);
	        int rating = rs.getInt(4);
	        //생성자에 넣을 필드 선언
	        Object[] constructorArgs = { no,content,rating,nickName,tableNo};
            //클래스에 넣을 생성자 필드 타입 설정
	        Class<?>[] paramTypes = { int.class,  String.class, int.class, String.class,int.class, };
            
            Constructor<?> constructor = clazz.getConstructor(paramTypes);
	        
            Object obj = constructor.newInstance(constructorArgs);
            map.put(String.valueOf(no),obj);
            comment.put(name,map);
            
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(ps, rs);
		}
		return comment;
		
	}
	@Override
	public int deleteBoard(BoardVO board) {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "delete from My_recipe_Board where BOARD_NO = ? AND M_NO = ?";
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, board.getNo());
			ps.setInt(2, board.getColmun());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteComment(int commentNo, String nickName) {
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		//댓글 전체를 뷰로 만들어서 출력
		createView(con);

		//뷰 에서 테이블 이름과 해당 댓글번호 출력
		String sql = "select COMMENT_TYPE from  ALL_COMMENTS where COMMENT_NO = ?";
		
		Map<String, Map<String,Object>> commentList = new HashMap<String, Map<String,Object>>();
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,commentNo);
			rs = ps.executeQuery();
			//rs은 하나 댓글 
			if(rs.next()) {
				String tableName = rs.getString(1);
				
				//해당 테이블에 닉네임 비교(자기 댓글인지)와 해당 번호 비교해서 삭제
				CommentVO comment = new CommentVO(commentNo,nickName);
				result = deleteCommentTable(con,tableName,comment);
			}
			
						
		} catch (Exception e) {
			e.getMessage();
		}
		
		return 0;
	}
	/*
	 * 삭제완료
	 */
	public int deleteCommentTable(Connection con, String tableName,CommentVO comment) {
		PreparedStatement ps = null;
		
		int result = 0;
		
		//삭제 쿼리
		String sql = "delete from "+tableName+" where COMMENT_NO = ? and M_NICKNAME = ?";
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,comment.getCommentNo());
			ps.setString(2, comment.getMemberNickName());
			System.out.println(sql);
			result = ps.executeUpdate();
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps);
		}
		
		return result;
	}
	
	//view 현재 레시피 게시판 댓글, 후기 게시판 댓글을 모아서 만듬
	// 각테이블 이름과 번호를 받고 그걸 테이블에 번호로 삭제
	public int createView(Connection con) {
		PreparedStatement ps = null;
		int result = 0 ;
		String sql ="\r\n"
				+ "CREATE or replace VIEW ALL_COMMENTS AS\r\n"
				+ "SELECT 'MY_RECIPE_COMMENT' AS COMMENT_TYPE, COMMENT_NO AS COMMENT_NO\r\n"
				+ "FROM MY_RECIPE_COMMENT\r\n"
				+ "UNION ALL\r\n"
				+ "SELECT 'RECIPE_REVIEW_COMMENT' AS COMMENT_TYPE, COMMENT_NO AS COMMENT_NO\r\n"
				+ "FROM RECIPE_REVIEW_COMMENT;\r\n"
				+ "";
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
			
			
						
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			DbManager.dbClose(ps);
		}
		return result;
	}
	
	
	@Override
	public int writeComment(CommentVO comment,String commentName) {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into "+commentName+" values(comment_seq,?,?,?,?)";
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
	/*
	 * 해당 게시판 출력 인데 name를 받아서 name에 해당하는 테이블 조회와
	 * 클래스를 동적 할당하여 생성하여 출력한다.
	 * 
	 */
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
            BoardVO board = (BoardVO)obj;
            board.setComment(replyBoardByNo(con,no,name));;
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
            	
            	if(boardName.equals("My_Recipe_Board"))
            		board = new RecipeBoardVO(boardNo,tableNo, title, content, date);
            	if(boardName.equals("Recipe_Review_Board"))
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
	
	private List<CommentVO> replyBoardByNo(Connection con, int boardNo,String commentName) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] name = commentName.split("_");
		String tableName = name[0]+"_"+name[1]+"_Comment";
		String sql = "select * from "+tableName+" where "+commentName+"_NO = ?";
		
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
	        String M_nickName = rs.getString(5);
	        
	        CommentVO commentVO=null;
	        
	        if(commentName.equals("My_Recipe_Board"))
		        commentVO= new RecipeCommentVO(relpyNo,cotent,rating,M_nickName,tableNo);
	        if(commentName.equals("Recipe_Review_Board1"))
		        commentVO= new ReviewCommentVO(relpyNo,cotent,rating,M_nickName,tableNo);

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
