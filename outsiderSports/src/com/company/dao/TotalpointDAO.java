package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.company.dbmanager.DBManager;

public class TotalpointDAO {

	public int addData(String oid) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into totalpoint (oid) values (?)");
			pstmt.setString(1, oid);
		
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int accumPoint(String oid, int mprice) {//누적포인트 추가 (예약할때)
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update totalpoint set usedpoint=usedpoint+? where oid=?");
			pstmt.setInt(1, mprice);
			pstmt.setString(2, oid);
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int deletePoint(String oid, int mprice) {//누적포인트 차감 (예약 취소할때)
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update totalpoint set usedpoint=usedpoint-? where oid=?");
			pstmt.setInt(1, mprice);
			pstmt.setString(2, oid);
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
}
