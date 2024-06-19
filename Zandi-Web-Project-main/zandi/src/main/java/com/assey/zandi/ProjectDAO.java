package com.zandiproject;

import java.sql.*;
import javax.sql.*;
import javax.websocket.Session;


import javax.naming.*;
import java.util.*;

public class ProjectDAO {
	
private static ProjectDAO instance = null;
	

//싱글톤 디자인 패턴임, addProjectProc에서사용
//이 코드를 통해 ProjectDAO 클래스의 인스턴스가 오직 하나만 생성되며, 이를 효과적으로 관리할 수 있습니다. 이 패턴은 리소스 낭비를 줄이고 객체의 일관성을 보장합니다.
	public static ProjectDAO getInstance() {
		if(instance == null) {
			synchronized (ProjectDAO.class) {
				instance = new ProjectDAO();
			}
		}
		return instance;
	}
	
	
	//데이터베이스 연결
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
	}
	
	
	//프로젝트 올리기 했을때 DB에 추가 addProject.jsp에서 사용
	public boolean addProject(ProjectVO project) {
		
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    boolean isSuccess = false;

	    try {
	        con = getConnection();
	        String sql = "INSERT INTO cf_project (prCode, prName, prDescription, prTeam, prId, prCategory, prGoal, prCurrent, prLikecount, prStartdate, prEnddate) VALUES (cf_project_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, project.getPrName());
	        pstmt.setString(2, project.getPrDescription());
	        pstmt.setString(3, project.getPrTeam());
	        pstmt.setString(4, project.getPrId());
	        pstmt.setString(5, project.getPrCategory());
	        pstmt.setString(6, project.getPrGoal());
	        pstmt.setString(7, project.getPrCurrent());
	        pstmt.setInt(8, project.getPrLikecount());
	        pstmt.setTimestamp(9, project.getPrStartdate());
	        pstmt.setTimestamp(10, project.getPrEnddate());
	        
	        int rowsInserted = pstmt.executeUpdate();
	        if (rowsInserted > 0) {
	            isSuccess = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return isSuccess;
	}

	
	//모든 프로젝트의 개수가 몇개 있는지 표현 listProjects.jsp에서 사용
		public List<ProjectVO> getAllProjects() {
	        List<ProjectVO> projectList = new ArrayList<ProjectVO>();
	        String sql = "SELECT * FROM cf_project"; 

	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                ProjectVO project = new ProjectVO();
	                project.setPrCode(rs.getInt("prCode"));
	                project.setPrName(rs.getString("prName"));
	                project.setPrDescription(rs.getString("prDescription"));
	                project.setPrStartdate(rs.getTimestamp("prStartdate"));
	                project.setPrEnddate(rs.getTimestamp("prEnddate"));
	                project.setPrLikecount(rs.getInt("prLikecount"));
	                // 필요한 필드를 추가로 설정

	                projectList.add(project);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return projectList;
	    }
	 
		
		//프로젝트테이블에서 데이터를 가져와서 목록을 보여줄 메소드 구현 listProjects.jsp에서 사용
		//start : 시작번호, end : 끝번호
	 public List<ProjectVO> getProject(int start, int end) {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    List<ProjectVO> articleList = new ArrayList<ProjectVO>();

		    try {
		        con = getConnection();
		        String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, prCode, prName, prDescription, prTeam, prId, prCategory, prGoal, prCurrent, prLikecount, prStartdate, prEnddate FROM (SELECT * FROM cf_project ORDER BY prCode DESC)) WHERE rnum BETWEEN ? AND ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, start);
		        pstmt.setInt(2, end);
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            ProjectVO article = new ProjectVO();
		            article.setPrCode(rs.getInt("prCode"));
		            article.setPrName(rs.getString("prName"));
		            article.setPrDescription(rs.getString("prDescription"));
		            article.setPrTeam(rs.getString("prTeam"));
		            article.setPrId(rs.getString("prId"));
		            article.setPrCategory(rs.getString("prCategory"));
		            article.setPrGoal(rs.getString("prGoal"));
		            article.setPrCurrent(rs.getString("prCurrent"));
		            article.setPrLikecount(rs.getInt("prLikecount"));
		            article.setPrStartdate(rs.getTimestamp("prStartdate"));
		            article.setPrEnddate(rs.getTimestamp("prEnddate"));
		            articleList.add(article);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		        try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
		    }

		    return articleList;
		}
	 
	 
	 //글 상세보기(글의 번호를 매개변수로 글의 세부정보 가져오기) 아직 안만듬
	 public ProjectVO getArticle(int prCode) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ProjectVO article = null;
			
			try {
				con = getConnection();
		        String sql = "SELECT * FROM cf_project WHERE prCode = ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, prCode);
		        rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
				article = new ProjectVO();
	            article.setPrCode(rs.getInt("prCode"));
	            article.setPrName(rs.getString("prName"));
	            article.setPrDescription(rs.getString("prDescription"));
	            article.setPrTeam(rs.getString("prTeam"));
	            article.setPrId(rs.getString("prId"));
	            article.setPrCategory(rs.getString("prCategory"));
	            article.setPrGoal(rs.getString("prGoal"));
	            article.setPrCurrent(rs.getString("prCurrent"));
	            article.setPrLikecount(rs.getInt("prLikecount"));
	            article.setPrStartdate(rs.getTimestamp("prStartdate"));
	            article.setPrEnddate(rs.getTimestamp("prEnddate"));
				}
				
			}catch(SQLException ss) {
				ss.printStackTrace();
			}finally {
				if(rs != null)
					try{rs.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
				if(pstmt != null)
					try{pstmt.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
				if(con != null)
					try{con.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
			}
		
		return article;
		}
	 
	 
	//검색한 내용이 있었는지 0개였는지를 반환(what:검색조건, content:검색내용 listProject.jsp 에서 사용
	 public int getProjectCount(String what, String content){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			
			try {
				con = getConnection();
				String sql ="select count(*) from cf_project where "+what+" like '%"+content+"%'";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(SQLException ss) {
				ss.printStackTrace();
			}finally {
				if(rs != null)
					try{rs.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
				if(pstmt != null)
					try{pstmt.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
				if(con != null)
					try{con.close();
					}catch(SQLException s){
						s.printStackTrace();
					}
			}
			return x;
		}
	 
	 
	 
	 
	 //검색한 내용을 리스트로 받아옴(조건, 내용, 시작번호, 끝번호) listProjects.jsp에서 사용
	 
	 public List<ProjectVO> getProject(String what, String content, int start, int end) {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    
		    List<ProjectVO> articleList = new ArrayList<>(); // articleList 초기화
		    
		    try {
		        con = getConnection();
		        
		        String sql = "SELECT * FROM (SELECT prCode, prName,prDescription, prTeam, prId, prCategory, prGoal, prLikecount, prStartdate, prEnddate, ROWNUM AS rnum FROM (SELECT * FROM cf_project WHERE "+what+" LIKE ?)) WHERE rnum >= ? AND rnum <= ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, "%" + content + "%");
		        pstmt.setInt(2, start);
		        pstmt.setInt(3, end);
		        rs = pstmt.executeQuery();
		        
		        while (rs.next()) { // ResultSet에서 데이터를 반복하여 읽어옴
		            ProjectVO article = new ProjectVO();
		            article.setPrCode(rs.getInt("prCode"));
		            article.setPrName(rs.getString("prName"));
		            article.setPrDescription(rs.getString("prDescription"));
		            article.setPrTeam(rs.getString("prTeam"));
		            article.setPrId(rs.getString("prId"));
		            article.setPrCategory(rs.getString("prCategory"));
		            article.setPrGoal(rs.getString("prGoal"));
		            article.setPrLikecount(rs.getInt("prLikecount"));
		            article.setPrStartdate(rs.getTimestamp("prStartdate"));
		            article.setPrEnddate(rs.getTimestamp("prEnddate"));
		            articleList.add(article); // 읽어온 데이터를 articleList에 추가
		        }
		        
		    } catch (SQLException ss) {
		        ss.printStackTrace();
		    } finally {
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException s) {
		                s.printStackTrace();
		            }
		        }
		        if (pstmt != null) {
		            try {
		                pstmt.close();
		            } catch (SQLException s) {
		                s.printStackTrace();
		            }
		        }
		        if (con != null) {
		            try {
		                con.close();
		            } catch (SQLException s) {
		                s.printStackTrace();
		            }
		        }
		    }
		    return articleList;
		}
	   
		
	 
	 //listProjects.jsp에서 AJAX로 사용,  특정 프로젝트 코드에 해당하는 프로젝트 정보를 가져와서 ProjectVO 객체로 만들어 반환하는 메소드
	 public boolean isBookmarked(int prCode, String loginID) {
	        boolean isBookmarked = false;
	        String sql = "SELECT * FROM cf_likelist WHERE prCode = ? AND mId = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, prCode);
	            pstmt.setString(2, loginID);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                isBookmarked = rs.next(); // 결과가 있으면 북마크가 되어있는 것
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return isBookmarked;
	    }	 
	 
	 
		//북마크 토글 형식 listProjects.jsp에서 AJAX로 사용(toggleBookmark.jsp에서 JSON)
		public boolean toggleBookmark(int prCode, String loginID) {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    boolean isSuccess = false;

		    try {
		        con = getConnection();
		        // 해당 회원이 존재하는지 확인
		        String checkMemberSql = "SELECT * FROM cf_member WHERE mId = ?";
		        pstmt = con.prepareStatement(checkMemberSql);
		        pstmt.setString(1, loginID);
		        rs = pstmt.executeQuery();

		        if (rs.next()) { // 회원이 존재하는 경우에만 북마크 추가 또는 삭제 수행
		            // 해당 프로젝트에 대한 북마크가 이미 있는지 확인
		            String checkSql = "SELECT * FROM cf_likelist WHERE prCode = ? AND mId = ?";
		            pstmt = con.prepareStatement(checkSql);
		            pstmt.setInt(1, prCode);
		            pstmt.setString(2, loginID);
		            rs = pstmt.executeQuery();

		            if (rs.next()) { // 이미 북마크가 있는 경우 => 삭제
		                String deleteSql = "DELETE FROM cf_likelist WHERE prCode = ? AND mId = ?";
		                pstmt = con.prepareStatement(deleteSql);
		                pstmt.setInt(1, prCode);
		                pstmt.setString(2, loginID);
		                int rowsDeleted = pstmt.executeUpdate();
		                isSuccess = rowsDeleted > 0;
		            } else { // 북마크가 없는 경우 => 추가
		                String insertSql = "INSERT INTO cf_likelist (prCode, mId) VALUES (?, ?)";
		                pstmt = con.prepareStatement(insertSql);
		                pstmt.setInt(1, prCode);
		                pstmt.setString(2, loginID);
		                int rowsInserted = pstmt.executeUpdate();
		                isSuccess = rowsInserted > 0;
		            }
		        } else { // 해당 회원이 존재하지 않는 경우
		            isSuccess = false;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        isSuccess = false; // 예외 발생 시 실패 처리
		    } finally {
		        // 리소스 해제
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (pstmt != null) {
		            try {
		                pstmt.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (con != null) {
		            try {
		                con.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return isSuccess;
		}
		
		 //toggleBookmark.jsp에서 프로젝트 코드에 해당하는 업데이트 된 북마크 수를 조회하고 반환
		 public int getBookmarkCount(int prCode) {
		        int likeCount = 0;
		        String sql = "SELECT prLikecount FROM cf_project WHERE prCode = ?";
		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setInt(1, prCode);
		            try (ResultSet rs = pstmt.executeQuery()) {
		                if (rs.next()) {
		                    likeCount = rs.getInt("prLikecount");
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return likeCount;
		    }
		 
	
	 //dibsview.jsp에서 사용, 사용자가 북마크한 프로젝트의 총 개수를 반환
		 public int getBookmarkedProjectCount(String loginID) {
			    Connection con = null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    int count = 0;

			    try {
			        con = getConnection();
			        String sql = "SELECT COUNT(*) FROM cf_likelist WHERE mId = ?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, loginID);
			        rs = pstmt.executeQuery();
			        if (rs.next()) {
			            count = rs.getInt(1);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        if (rs != null) {
			            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			        if (pstmt != null) {
			            try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			        if (con != null) {
			            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			    }

			    return count;
			}
	 
		 
		 
		//dibsview.jsp에서 사용, 사용자가 북마크한 프로젝트의 총 개수를 반환
		 public List<ProjectVO> getBookmarkedProjects(String loginID, int startRow, int endRow) {
			    List<ProjectVO> bookmarkedProjects = new ArrayList<>();
			    Connection con = null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;

			    try {
			        String sql = "SELECT * FROM (" +
			                     "  SELECT ROWNUM AS rnum, prCode" +
			                     "  FROM (SELECT prCode FROM cf_likelist WHERE mId = ? ORDER BY prCode DESC)" +
			                     ") WHERE rnum BETWEEN ? AND ?";
			        con = getConnection();
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, loginID);
			        pstmt.setInt(2, startRow);
			        pstmt.setInt(3, endRow);
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            int prCode = rs.getInt("prCode");
			            ProjectVO project = getProjectInfo(prCode);
			            bookmarkedProjects.add(project);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        if (rs != null) {
			            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			        if (pstmt != null) {
			            try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			        if (con != null) {
			            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
			        }
			    }

			    return bookmarkedProjects;
			}
		 
		 
	 	//글 상세보기 메소드 listProjects.jsp에서 getProject.jsp파일불러오기임 getProject.jsp작성해야함
	 private ProjectVO getProjectInfo(int prCode) {
		    ProjectVO project = null;
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        String sql = "SELECT * FROM cf_project WHERE prCode = ?";
		        con = getConnection();
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, prCode);
		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            project = new ProjectVO();
		            project.setPrCode(prCode);
		            project.setPrName(rs.getString("prName"));
		            project.setPrDescription(rs.getString("prDescription"));
		            project.setPrStartdate(rs.getTimestamp("prStartdate"));
		            project.setPrEnddate(rs.getTimestamp("prEnddate"));
		            project.setPrLikecount(rs.getInt("prLikecount"));
		            // 필요한 필드를 추가로 설정
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (pstmt != null) {
		            try {
		                pstmt.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (con != null) {
		            try {
		                con.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }

		    return project;
		}
	 
	 //찜목록 리스트를 보여주는 removeBookmark.jsp에서 사용 찜목록 삭제및 카운트 감소
	 public boolean removeBookmarkAndDecrementLikeCount(String loginID, int prCode) {
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    boolean success = false;

		    try {
		        con = getConnection();
		        con.setAutoCommit(false); // 트랜잭션 시작

		        // 북마크 삭제
		        String deleteBookmarkSQL = "DELETE FROM cf_likelist WHERE mId = ? AND prCode = ?";
		        pstmt = con.prepareStatement(deleteBookmarkSQL);
		        pstmt.setString(1, loginID);
		        pstmt.setInt(2, prCode);
		        int result = pstmt.executeUpdate();

		        if (result > 0) {
		            // 북마크 삭제 성공 시 prlikecount 감소
		            String decrementLikeCountSQL = "UPDATE cf_project SET prLikecount = prLikecount - 1 WHERE prCode = ?";
		            pstmt = con.prepareStatement(decrementLikeCountSQL);
		            pstmt.setInt(1, prCode);
		            int updateResult = pstmt.executeUpdate();

		            if (updateResult > 0) {
		                // prlikecount 감소 성공
		                success = true;
		                con.commit(); // 트랜잭션 커밋
		            } else {
		                // prlikecount 감소 실패
		                con.rollback(); // 트랜잭션 롤백
		            }
		        } else {
		            // 북마크 삭제 실패
		            con.rollback(); // 트랜잭션 롤백
		        }
		    } catch (SQLException e) {
		        try {
		            if (con != null) {
		                con.rollback(); // SQLException 발생 시 롤백
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmt != null) pstmt.close();
		            if (con != null) {
		                con.setAutoCommit(true); // 트랜잭션이 끝났으므로 다시 오토커밋으로 설정
		                con.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    			//데이터 일관성 땜에 트랙잭션
		    return success;
		}

	 
	 //listProjects.jsp에서 버튼 누르면 toggleBookmark.jsp에서 메소드 호출 북마크 추가
	 public void addBookmark(int prCode, String Id) {
	        String sql = "INSERT INTO cf_likelist (prCode, mId) VALUES (?, ?)";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, prCode);
	            pstmt.setString(2, Id);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        updateBookmarkCount(prCode, 1);
	    }
	 
	 //listProjects.jsp에서 버튼 누르면 toggleBookmark.jsp에서 메소드 호출 북마크 삭제
	 public void removeBookmark(int prCode, String Id) {
	        String sql = "DELETE FROM cf_likelist WHERE prCode = ? AND mId = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, prCode);
	            pstmt.setString(2, Id);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        updateBookmarkCount(prCode, -1);
	    }
	 
	 
	//addBookmark()일시 +, removeBookmark()일시 -
	 private void updateBookmarkCount(int prCode, int delta) {
	        String sql = "UPDATE cf_project SET prLikecount = prLikecount + ? WHERE prCode = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, delta);
	            pstmt.setInt(2, prCode);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}
	 
	
	 
	 
	 
	 
}

