package dy.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.LoggableStatement;

import dy.db.DbcpBean;


public class MemberDAO {

	DbcpBean dbcp;
	boolean logEnabled = true;
	
	public MemberDAO() {
		dbcp = new DbcpBean();
	}
	
	public boolean insertMember(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
		
			conn = dbcp.getConnection();
			
			sql = "INSERT INTO member(id, pwd, filename, filesize, datetime) VALUES (?, password(?), ?, ?, now())";
			
			if (logEnabled) {
				pstmt = new LoggableStatement(conn, sql);
			} else {
				pstmt = conn.prepareStatement(sql);
			}
				
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPwd());
			pstmt.setString(3, mvo.getFilename());
			pstmt.setInt(4, mvo.getFilesize());
			
			if (logEnabled) {
				System.out.println("Executing query: " + ((LoggableStatement)pstmt).getQueryString());
			}
			
			int result = pstmt.executeUpdate();
		
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public MemberVO selectMember(String idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO mvo = new MemberVO();
		
		try {
			
			conn = dbcp.getConnection();
			
			String sql = "SELECT id, pwd, filename, filesize FROM member WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String filename = rs.getString("filename");
			
			int filesize = rs.getInt("filesize");
			filesize /= 1024;
				
			mvo.setId(id);
			mvo.setPwd(pwd);
			mvo.setFilename(filename);
			mvo.setFilesize(filesize);
			
			return mvo;
		
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) 
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public List<MemberVO> selectAllMember() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = dbcp.getConnection();
		
		List<MemberVO> mvoList = new ArrayList<MemberVO>();
		
		try {
		
			String sql = "SELECT filename, filesize, id, pwd, datetime FROM member ORDER BY datetime DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			MemberVO mvo = null;
			
			while (rs.next()) {
				mvo = new MemberVO();
				
				mvo.setFilename(rs.getString("filename"));
				mvo.setFilesize(rs.getInt("filesize"));
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setDatetime(rs.getTimestamp("datetime"));
				
				mvoList.add(mvo);
			}
			
			return mvoList;
				
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) 
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean checkLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = dbcp.getConnection();		
		
		try {
		
			String sql = "SELECT id FROM member WHERE id = ? AND pwd = PASSWORD(?)";
			
			if (logEnabled) {
				pstmt = new LoggableStatement(conn, sql);
			} else {
				pstmt = conn.prepareStatement(sql);
			}
			
			System.out.println("id = " + id);
			System.out.println("pwd = " + pwd);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			if (logEnabled) {
				System.out.println("Executing query: " + ((LoggableStatement)pstmt).getQueryString());
			}			
			rs = pstmt.executeQuery();			
			
			if (rs.next()) {
				return true;
			}			
					
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (rs != null) 
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}