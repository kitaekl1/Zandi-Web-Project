package com.assey.zandi;

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
			System.out.println("Connection ");
		}
		return con;
	}//�뜲�씠�꽣踰좎씠�뒪 �뿰寃곕찓�냼�뱶
	
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
	 * 湲��쓣 異붽��븳 �썑 由щ떎�씠�젆�듃濡� 由ъ뒪�듃�럹�씠吏�瑜� 蹂댁뿬以�
	 * �쟾泥� 湲��쓽 媛쒖닔瑜� 媛��졇���꽌 紐⑸줉�쓣 蹂댁뿬以섏빞�븿
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
	 * board�뀒�씠釉붿뿉�꽌 �뜲�씠�꽣瑜� 媛��졇���꽌 紐⑸줉�쓣 蹂댁뿬以� 硫붿냼�뱶 援ы쁽
	 * start : �떆�옉踰덊샇, end : �걹踰덊샇
	 */
	public List<BoardVO> getArticles(int start, int end) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<BoardVO> articleList = null;

	    try {
	        con = getConnection(); 
	        String sql = "SELECT * FROM (SELECT ROWNUM rnum, aNum, aTitle, aDate, aAnnounce " +
	                     "FROM (SELECT * FROM cf_announcement ORDER BY aNum DESC)) " +
	                     "WHERE rnum >= ? AND rnum <= ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, start);
	        pstmt.setInt(2, end);

	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            articleList = new ArrayList<BoardVO>(end - start + 1);
	            do {
	                BoardVO article = new BoardVO();
	                article.setaNum(rs.getInt("num"));
	                article.setaTitle(rs.getString("title"));
	                article.setaDate(rs.getString("date"));
	                article.setaAnnounce(rs.getString("announce"));
	                article.setPass(rs.getString("pass"));
	                //article.setPrCode(rs.getInt("prCode"));
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
	}// end getArticles
	
	/*
	 * 湲� �긽�꽭蹂닿린
	 * 湲� �젣紐⑹쓣 �늻瑜대㈃ 湲� �긽�꽭蹂닿린瑜� �븷 �닔 �엳�떎.
	 * 湲��쓽 踰덊샇瑜� 留ㅺ컻蹂��닔濡� �빐�꽌 �븯�굹�쓽 湲��뿉���븳 �꽭遺��젙蹂대�� 媛��졇�삤�뒗
	 * 硫붿냼�뱶瑜� 援ы쁽
	 */
	public BoardVO getArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		
		try {
			con = getConnection();
			String sql1="update cf_announcement set readcount=readcount+1 where num=?";//議고쉶�닔 利앷�
			
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
	
	// �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �떎�젣 湲��씠 �닔�젙�릺�뼱�빞�븿
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
					
					result = 1; // 湲� �궘�젣 �꽦怨�
				}else {
					result = 0; // 鍮꾨�踰덊샇 �삤瑜�
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

	//寃��깋�븳 �궡�슜�씠 紐뉕컻 �엳�뒗吏�瑜� 諛섑솚�븿 (what:寃��깋議곌굔, content:寃��깋�궡�슜)
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
	 * 寃��깋�븳 �궡�슜�쓣 由ъ뒪�듃濡� 諛쏆븘�샂(議곌굔, �궡�슜, �떆�옉踰덊샇, �걹踰덊샇)
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