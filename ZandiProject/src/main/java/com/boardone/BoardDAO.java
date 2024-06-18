package com.boardone;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private static BoardDAO instance = null;
	
	private BoardDAO() {}

	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/myoracle");
			con = ds.getConnection();
			
		}catch(Exception e) {
			System.out.println("Connection 생성 실패");
		}
		return con;
	}//데이터베이스 연결메소드
	
	public void insertArticle(BoardVO article) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();	
			String sql="INSERT INTO cf_announcement(aNum, aTitle, aDate, aAnnounce, pass, readcount) " +
                    "VALUES(cf_announcement_seq.nextval, ?, SYSDATE, ?, ?, 0)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, article.getaTitle());
			pstmt.setString(2, article.getaAnnounce());
			pstmt.setString(3, article.getPass());
			
			pstmt.executeUpdate(); // insert
			
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}
		
	} // end insertArticle
	
	/*
	 * 글을 추가한 후 리다이렉트로 리스트페이지를 보여줌
	 * 전체 글의 개수를 가져와서 목록을 보여줘야함
	 */
	
	public int getArticleCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x=0;
		
		try {
			con = getConnection();
			String sql="select count(*) from cf_announcement";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
			
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException s){s.printStackTrace();};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}
		return x;
	} //end getArticleCount
	
	/*
	 * board테이블에서 데이터를 가져와서 목록을 보여줄 메소드 구현
	 * start : 시작번호, end : 끝번호
	 */
	public List<BoardVO> getArticles(int start, int end) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<BoardVO> articleList = new ArrayList<>();

	    try {
	        con = getConnection();
	        String sql = "SELECT * FROM (SELECT ROWNUM rnum, aNum, aTitle, TO_CHAR(aDate, 'YYYY-MM-DD') AS aDate, aAnnounce, readcount " +
	                     "FROM (SELECT * FROM cf_announcement ORDER BY aNum DESC)) " +
	                     "WHERE rnum >= ? AND rnum <= ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, start);
	        pstmt.setInt(2, end);

	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            BoardVO article = new BoardVO();
	            article.setaNum(rs.getInt("aNum"));
	            article.setaTitle(rs.getString("aTitle"));
	            article.setaDate(rs.getString("aDate"));
	            article.setaAnnounce(rs.getString("aAnnounce"));
	            article.setReadcount(rs.getInt("readcount"));
	            articleList.add(article);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // close resources
	    }
	    return articleList;
	} 

	
	/*
	 * 글 상세보기
	 * 글 제목을 누르면 글 상세보기를 할 수 있다.
	 * 글의 번호를 매개변수로 해서 하나의 글에대한 세부정보를 가져오는
	 * 메소드를 구현
	 */
	public BoardVO getArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = getConnection();
			String sql1="update cf_announcement set readcount=readcount+1 where num=?";//조회수 증가
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			String sql2="select * from cf_announcement where num=?";
			
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setaNum(rs.getInt("aNum"));
				article.setaTitle(rs.getString("aTitle"));
				article.setaDate(rs.getString("aDate"));
				article.setaAnnounce(rs.getString("aAnnounce"));
			}
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException s){s.printStackTrace();};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}
		return article;
	}// end getArticle
	
	public BoardVO updateGetArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = getConnection();
			
			String sql="select * from cf_announcement where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new BoardVO();
				article.setaNum(rs.getInt("aNum"));
				article.setaTitle(rs.getString("aTitle"));
				article.setaDate(rs.getString("aDate"));
				article.setaAnnounce(rs.getString("aAnnounce"));
			}
			
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException s){s.printStackTrace();};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}
		return article;
	}//end updateGetArticle
	
	// 데이터베이스에서 실제 글이 수정되어야함
	public int updateArticle(BoardVO article) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String dbpasswd = "";
	    int result = -1; // -1: no article to update, 1: success, 0: password error

	    try {
	        con = getConnection(); // Make sure this method is correctly defined
	        String sql1 = "select pass from cf_announcement where aNum=?";
	        pstmt = con.prepareStatement(sql1);
	        pstmt.setInt(1, article.getaNum());
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            dbpasswd = rs.getString("pass");
	            if (dbpasswd.equals(article.getPass())) {
	                String sql2 = "update cf_announcement set aTitle=?, aAnnounce=?, where aNum=?";
	                pstmt = con.prepareStatement(sql2);
	                pstmt.setString(1, article.getaTitle());
	                pstmt.setString(2, article.getaAnnounce());
	                pstmt.setInt(4, article.getaNum());
	                pstmt.executeUpdate();
	                result = 1;
	            } else {
	                result = 0; // Password mismatch
	            }
	        }
	    } catch (SQLException ss) {
	        ss.printStackTrace();
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException s) { s.printStackTrace(); }
	        if (pstmt != null) try { pstmt.close(); } catch (SQLException s) { s.printStackTrace(); }
	        if (con != null) try { con.close(); } catch (SQLException s) { s.printStackTrace(); }
	    }
	    return result;
	}
	
	public int deleteArticle(int num,String pass) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbpasswd = "";
		String sql1="";
		String sql2="";
		int result = -1;
		
		try {
			con = getConnection();
			
			sql1="select pass from cf_announcement where num=?";
			
			pstmt=con.prepareStatement(sql1);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
				
				if(dbpasswd.equals(pass)) {
					sql2="delete from cf_announcement where num=?";
					
					pstmt = con.prepareStatement(sql2);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					
					result = 1; // 글 삭제 성공
				}else {
					result = 0; // 비밀번호 오류
				}
			}
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException s){s.printStackTrace();};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}		
		return result;
	}//end deleteArticle

	//검색한 내용이 몇개 있는지를 반환함 (what:검색조건, content:검색내용)
	public int getArticleCount(String what,String content) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x=0;
		
		try {
			con = getConnection();
			String sql="select count(*) from cf_announcement where "+what+" like '%"+content+"%'";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
			
		} catch (SQLException ss) {
			ss.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException s){s.printStackTrace();};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException s){s.printStackTrace();};
			if(con!=null) try {con.close();}catch(SQLException s){s.printStackTrace();};
		}
		return x;
	} //end getArticleCount
	
	/*
	 * 검색한 내용을 리스트로 받아옴(조건, 내용, 시작번호, 끝번호)
	 */
	public List<BoardVO> getArticles(String what, String content, int start, int end) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    List<BoardVO> articleList = null;

	    try {
	        con = getConnection(); // Make sure this method is correctly defined
	        String sql = "select * from (select rownum rnum, num, title, to_char(aDate, 'YYYY-MM-DD') as aDate, readcount, announce, prCode from (select * from cf_announcement where " + what + " like ? order by ref desc, step asc)) where rnum >=? and rnum <=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + content + "%");
	        pstmt.setInt(2, start);
	        pstmt.setInt(3, end);

	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            articleList = new ArrayList<BoardVO>(end - start + 1);
	            do {
	                BoardVO article = new BoardVO();
	                article.setaNum(rs.getInt("num"));
	                article.setaTitle(rs.getString("title"));
	                article.setaDate(rs.getString("aDate"));
	                article.setaAnnounce(rs.getString("announce"));
	                article.setPrCode(rs.getInt("prCode"));

	                articleList.add(article);
	            } while (rs.next());
	        }

	    } catch (SQLException ss) {
	        ss.printStackTrace();
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException s) { s.printStackTrace(); }
	        if (pstmt != null) try { pstmt.close(); } catch (SQLException s) { s.printStackTrace(); }
	        if (con != null) try { con.close(); } catch (SQLException s) { s.printStackTrace(); }
	    }
	    return articleList;
	}
} 