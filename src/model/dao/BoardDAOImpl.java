package model.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public int postBoard(BoardVO board) throws SQLException{
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into my_recipe_board values(board_seq.nextval,?,?,?,sysdate)";
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			
			if(board instanceof RecipeBoardVO recipeBoard) {
				ps.setString(1, recipeBoard.getMNickname());
				ps.setString(2, recipeBoard.getTitle());
				ps.setString(3, recipeBoard.getContent());
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
		}
		finally {
			DbManager.dbClose(con,ps);
		}
		
		return result;
		
	}
	
	/*
	 * 내가 쓴 글 목록 출력
	 */
	@Override
	public List<BoardVO> searchMyPost(String memberNickName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		String sql = "select * from MY_RECIPE_BOARD WHERE M_NICKNAME = ? ORDER BY BOARD_NO";
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberNickName);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new RecipeBoardVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));		
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
	public Map<String, Map<String,Object>> searchMyComment(String nickName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//user 테이블에 있는 board 포함 테이블 이름 갖고오기
		String sql ="SELECT initcap(TABLE_NAME) FROM USER_TABLES where TABLE_NAME like '%COMMENT%'";
		
		Map<String, Map<String,Object>> commentList = new HashMap<String, Map<String,Object>>();
		
		try {
			con = DbManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				commentList = commentFindByMNo(con,name,commentList,nickName);
			}
			
						
		} catch (SQLException e) {
			throw new SQLException("댓글이 없습니다");
		}finally{
			DbManager.dbClose(con,ps,rs);
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
		String sql = "select * from "+name+" where M_NICKNAME = ? ORDER BY COMMENT_NO";
		
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
	
	//게시물삭제
	@Override
	public int deleteBoard(BoardVO board) throws SQLException {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "delete from My_recipe_Board where BOARD_NO = ? AND M_NICKNAME = ?";
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			if(board instanceof RecipeBoardVO recipeBoard) {
				ps.setInt(1, recipeBoard.getNo());
				ps.setString(2, recipeBoard.getMNickname());
			}
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new SQLException("삭제되지 않음");
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
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps, rs);
		}
		
		return result;
	}
	/*
	 * 삭제완료
	 */
	public int deleteCommentTable(Connection con, String tableName,CommentVO comment) throws SQLException {
		PreparedStatement ps = null;
		
		int result = 0;
		
		//삭제 쿼리
		String sql = "delete from "+tableName+" where COMMENT_NO = ? and M_NICKNAME = ?";
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,comment.getCommentNo());
			ps.setString(2, comment.getMemberNickName());
			result = ps.executeUpdate();
			
						
		} catch (SQLException e) {
			throw new SQLException("정보가 일치해서 삭제되지 않습니다");
		}finally {
			DbManager.dbClose(ps);
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
	
	/*
	 * 댓글 작성 시작
	 */
	@Override
	public int writeComment(CommentVO comment,String commentName) throws SQLException {
		int result=0;
		Connection con = null;
		PreparedStatement ps =null;
		
		String sql = "insert into "+commentName+" values(comment_seq.nextval,?,?,?,?)";
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
				ps.setInt(1,reviewComment.getBoardNo()); 
				ps.setString(2,reviewComment.getContent());
				ps.setInt(3, reviewComment.getRating());
				ps.setString(4, reviewComment.getMemberNickName());
			}
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
//			throw new SQLException("평점은 1~5사이를 입력하세요");
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps);
		}
		
		return result;
	}
	
	/*
	 * 보드 뷰
	 */
	public int	 createBoardView(Connection con) {
		PreparedStatement ps = null;
		int result = 0 ;
		String sql ="CREATE OR REPLACE VIEW BOARD_VIEW as\r\n"
				+ "select 'MY_RECIPE_BOARD' AS BOARD_TYPE, BOARD_NO\r\n"
				+ "from MY_RECIPE_BOARD\r\n"
				+ "union ALL\r\n"
				+ "select 'RECIPE_REVIEW_BOARD' AS BOARD_TYPE, BOARD_NO\r\n"
				+ "from RECIPE_REVIEW_BOARD;";
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
	
	/*
	 * 해당 게시판 출력 인데 name를 받아서 name에 해당하는 테이블 조회와
	 * 클래스를 동적 할당하여 생성하여 출력한다.
	 * boardSelect으로감
	 * 시작 REVICE_REIVE 슛
	 */
	@Override
	public List<BoardVO> searchPostByName(String table) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//테이블 이름 갖고 온걸로 그 테이블 정보 검색
		
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			con=DbManager.getConnection();
            createBoardView(con);
    		String sql = "select * from Board_VIEW where BOARD_TYPE = ? ORDER BY BOARD_NO";

			ps=con.prepareStatement(sql);
			ps.setString(1, table+"_BOARD");
			rs = ps.executeQuery();
			
            while(rs.next()) {
	        // 클래스를 로드하고 객체를 생성
            String tableName  = rs.getString(1);
            int boardNo = rs.getInt(2);
	        
	       
            BoardVO board = boardSelectByNo(con,boardNo, table);
            boardList.add(board);
            }
			
		} catch (Exception e) {
			throw new SQLException("게시물이 없습니다");
		}finally {
			DbManager.dbClose(con, ps);
		}
		return boardList;
		
	}
	
	/*
	 * 게시물 상세보기
	 * searchBoard에서 옴 373번 
	 *그냥 상세 보기 용 
	 */
	@Override
	public BoardVO boardSelectByNo(int boardNO,String boardName) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from "+boardName +"_BOARD where BOARD_NO = ? ORDER BY BOARD_NO";
		List<CommentVO> comment;
		BoardVO board = null;
		try {
			con=DbManager.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, boardNO);
			rs = ps.executeQuery();
           
            if(rs.next()) {
            	int boardNo =0;
            	if(boardName.equals("MY_RECIPE")) {
                	boardNo = rs.getInt(1);
                	String nickName = rs.getString(2);
                	String title  = rs.getString(3);
                	String content = rs.getString(4);
                	String date = rs.getString(5);
            		board = new RecipeBoardVO(boardNo,nickName, title, content, date);
            	
            	}	
            	if(boardName.equals("RECIPE_REVIEW")) {
            		boardNo = rs.getInt(1);
                	int recipeNo = rs.getInt(2);
                	String title  = rs.getString(3);
                	String content = rs.getString(4);
                	String date = rs.getString(5);
            		board = new ReviewBoardVO(boardNo,recipeNo, title, content, date);
            		
            	}
            	comment = replyBoardByNo(con,boardNo,boardName);
            	if(comment ==null)
            		throw new Exception("댓글이 없습니다");
            	board.setComment(comment);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(con, ps,rs);
		}
		
		return board;
		
	}
	/*
	 * con을 갖고와서 (이름 가지고 비교하는거) 
	 */
	public BoardVO boardSelectByNo(Connection con,int boardNO,String boardName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from "+boardName +"_BOARD where BOARD_NO = ? ORDER BY BOARD_NO";
		List<CommentVO> comment;
		BoardVO board = null;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, boardNO);
			rs = ps.executeQuery();
           
            if(rs.next()) {
            	int boardNo =0;
            	if(boardName.equals("MY_RECIPE")) {
                	boardNo = rs.getInt(1);
                	String nickName = rs.getString(2);
                	String title  = rs.getString(3);
                	String content = rs.getString(4);
                	String date = rs.getString(5);
            		board = new RecipeBoardVO(boardNo,nickName, title, content, date);
            	
            	}	
            	if(boardName.equals("RECIPE_REVIEW")) {
            		boardNo = rs.getInt(1);
                	int recipeNo = rs.getInt(2);
                	String title  = rs.getString(3);
                	String content = rs.getString(4);
                	String date = rs.getString(5);
            		board = new ReviewBoardVO(boardNo,recipeNo, title, content, date);
            		
            	}
            	comment = replyBoardByNo(con,boardNo,boardName);
            	if(comment ==null)
            		throw new Exception("댓글이 없습니다");
            	board.setComment(comment);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(ps,rs);
		}
		
		return board;
		
	}
	
	
	private List<CommentVO> replyBoardByNo(Connection con, int boardNo,String commentName) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] name = commentName.split("_");
		commentName = name[0]+"_"+name[1];
		String sql = "select * from "+commentName+"_Comment where BOARD_NO = ? ORDER BY COMMENT_NO";
		List<CommentVO> comment = new ArrayList<CommentVO>();
		try {
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
	        
	        if(commentName.equals("MY_RECIPE"))
		        commentVO= new RecipeCommentVO(relpyNo,cotent,rating,M_nickName,tableNo);
	        if(commentName.equals("RECIPE_REVIEW"))
		        commentVO= new ReviewCommentVO(relpyNo,cotent,rating,M_nickName,tableNo);

	        comment.add(commentVO);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.dbClose(ps, rs);
		}
		return comment;
		
	}

}
