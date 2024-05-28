package com.member;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
public class cfmemberDAO {

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
	
	//아이디 중복체크
	public boolean idCheck(String id) {
		
		boolean result = true;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from cf_member where mId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				result = false;
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
		
		return result;
	}
	
	
	
	
public boolean nickCheck(String nickname) {
		
		boolean result = true;
		
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select * from cf_member where mNickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				result = false;
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
		
		return result;
	}
	
	
	
	
	
	//우편번호 검색해서 Vector에 저장하여 리턴
	public Vector<ZipCodeVO> zipcodeRead(String dong){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		
		try {
			con= getConnection();
			
			String sql ="select * from zipcode where dong like '"+dong+"%'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipCodeVO tempZipCode = new ZipCodeVO();
				tempZipCode.setZipcode(rs.getString("zipcode"));
				tempZipCode.setSido(rs.getString("sido"));
				tempZipCode.setGugun(rs.getString("gugun"));
				tempZipCode.setDong(rs.getString("dong"));
				tempZipCode.setRi(rs.getString("ri"));
				tempZipCode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipCode);
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
		
		return vecList;
	}
	
	//데이터베이스에 회원정보 저장
	public boolean membereInsert(cfmemberVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = getConnection();
			String sql="insert into cf_member values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getmId());
			pstmt.setString(2, vo.getmPw());
			pstmt.setString(3, vo.getmNickname());
			pstmt.setString(4, vo.getmName());
			pstmt.setString(5, vo.getmPhone());
			pstmt.setString(6, vo.getmMail());
			pstmt.setString(7, vo.getmPost());
			pstmt.setString(8, vo.getmAddress());
			pstmt.setString(9, vo.getmSaddress());
			

			int count = pstmt.executeUpdate();
			if(count>0) flag= true;
			
		}catch(SQLException ss) {
		ss.printStackTrace();
	}finally {
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
	
	return flag;

	}
	
	/*
	 * 로그인 버튼을 클릭하면 우리가 입력한 id/pass를 데이터베이스에서 가져와서 
	 * 비교해서 같은면 로그인 성공, 다르면 로그인 실패 처리
	 * 데이터베이스에서 아이디와 비밀번호를 비교한 결과를 정수형으로 리턴해주는 메소드를 구현함
	 * 1:로그인 성공, 0:비밀번호 틀림, -1: 아이디가 존재하지 않음
	 */
	
	public int loginCheck(String id, String pass) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		
		try {
		con = getConnection();
		String sql = "select mPw from cf_member where mId=?";
		pstmt = con.prepareStatement(sql);	
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {//아이디로 비밀번호를 조회
			String dbPass = rs.getString("mPw");
			if(pass.equals(dbPass))
				check = 1;
			else check = 0;
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
		
		return check;
	}

	//회원정보 수정을 위해선 데이터베이스에서 회원정보를 가져와야
	public cfmemberVO getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		cfmemberVO vo=null;
		
		try {
			con = getConnection();
			String sql="select * from cf_member where mId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {//조회한 아이디에 해당하는 회원정보가 존재한다면
				vo = new cfmemberVO();
				vo.setmId(rs.getString("mId"));
				vo.setmPw(rs.getString("mPw"));
				vo.setmName(rs.getString("mName"));
				vo.setmNickname(rs.getString("mNickname"));
				vo.setmPhone(rs.getString("mPhone"));
				vo.setmMail(rs.getString("mMail"));
				vo.setmPost(rs.getString("mPost"));
				vo.setmAddress(rs.getString("mAddress"));
				vo.setmSaddress(rs.getString("mSaddress"));
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
		return vo;
	}
	
	public void updateMember(cfmemberVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql="update cf_member set mPw=?, mPhone=?, mMail=?, mPost=?, mAddress=?, mSaddress=? where mId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getmPw());
			pstmt.setString(2, vo.getmPhone());
			pstmt.setString(3, vo.getmMail());
			pstmt.setString(4, vo.getmPost());
			pstmt.setString(5, vo.getmAddress());
			pstmt.setString(6, vo.getmSaddress());
			pstmt.setString(7, vo.getmId());

			pstmt.executeUpdate();
			
		}catch(SQLException ss) {
			ss.printStackTrace();
		}finally {
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
	}
	
	//회원탈퇴버튼 누르면 데이터베이스에서 회원정보 삭제
	public int deleteMember(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPass=""; //데이터베이스에 저장된 패스워드를 저장하는 변수
		int result = -1;//id 존재x
		
		try {
			con = getConnection();
			String sql1 = "select mPw from cf_member where mId=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPass = rs.getString("mPw");
				if(dbPass.equals(pass)){//본인 확인 성공
					String sql2 = "delete from cf_member where mId=?";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1; //회원탈퇴 성공
				} else{ //본인 확인 실패
					result = 0;
				}
				
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

		return result;
	}
	
}
