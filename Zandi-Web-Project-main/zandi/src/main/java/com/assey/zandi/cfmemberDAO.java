package com.assey.zandi;

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
			System.out.println("Connection ���� ����");
		}
		return con;
	}//�����ͺ��̽� ����޼ҵ�
	
	//���̵� �ߺ�üũ
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
	
	
	
	
	
//�����ȣ �˻��ؼ� Vector�� �����Ͽ� ����
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
	
	//�����ͺ��̽��� ȸ������ ����
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
	 * �α��� ��ư�� Ŭ���ϸ� �츮�� �Է��� id/pass�� �����ͺ��̽����� �����ͼ� 
	 * ���ؼ� ������ �α��� ����, �ٸ��� �α��� ���� ó��
	 * �����ͺ��̽����� ���̵�� ��й�ȣ�� ���� ����� ���������� �������ִ� �޼ҵ带 ������
	 * 1:�α��� ����, 0:��й�ȣ Ʋ��, -1: ���̵� �������� ����
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
		
		if(rs.next()) {//���̵�� ��й�ȣ�� ��ȸ
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

	//ȸ������ ������ ���ؼ� �����ͺ��̽����� ȸ�������� �����;�
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
			if(rs.next()) {//��ȸ�� ���̵� �ش��ϴ� ȸ�������� �����Ѵٸ�
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
	
	//ȸ��Ż���ư ������ �����ͺ��̽����� ȸ������ ����
		public int deleteMember(String id, String pass) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String dbPass=""; //�����ͺ��̽��� ����� �н����带 �����ϴ� ����
			int result = -1;//id ����x
			
			try {
				con = getConnection();
				String sql1 = "select mPw from cf_member where mId=?";
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dbPass = rs.getString("mPw");
					if(dbPass.equals(pass)){//���� Ȯ�� ����
						String sql2 = "delete from cf_member where mId=?";
						pstmt = con.prepareStatement(sql2);
						pstmt.setString(1, id);
						pstmt.executeUpdate();
						result = 1; //ȸ��Ż�� ����
					} else{ //���� Ȯ�� ����
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
