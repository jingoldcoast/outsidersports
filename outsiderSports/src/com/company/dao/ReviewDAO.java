package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.*;

public class ReviewDAO {
	public int write(ReviewDTO dto) {//후기 쓰기
		int r = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into review(rwriter, rfile, hid, hstar, hcomment, gno, gstar, gcomment, mno) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getRwriter());
			pstmt.setString(2, dto.getRfile());
			pstmt.setString(3, dto.getHid());
			pstmt.setDouble(4, dto.getHstar());
			pstmt.setString(5, dto.getHcomment());
			pstmt.setInt(6, dto.getGno());
			pstmt.setDouble(7, dto.getGstar());
			pstmt.setString(8, dto.getGcomment());
			pstmt.setInt(9, dto.getMno());
			
			r = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return r;
	}
	public ArrayList<ReviewDTO> listAll(int gno){//특정 경기장 이용후기 list
		ArrayList<ReviewDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, concat(left(rwriter, 2), repeat('*', length(rwriter)-2) ) as rwriter2 from review a where gno=? order by rdate desc");
			pstmt.setInt(1, gno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewDTO tmp = new ReviewDTO();
				tmp.setRno(rset.getInt("rno"));
				tmp.setRwriter(rset.getString("rwriter2"));
				tmp.setRfile(rset.getString("rfile"));
				tmp.setRdate(rset.getString("rdate"));
				tmp.setHid(rset.getString("hid"));
				tmp.setHstar(rset.getDouble("hstar"));
				tmp.setHcomment(rset.getString("hcomment"));
				tmp.setGno(rset.getInt("gno"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcomment(rset.getString("gcomment"));
				tmp.setMno(rset.getInt("mno"));
				list.add(tmp);
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
	}
	public int listAllCnt(int gno){//특정 경기장 이용후기 count - 후기에 번호 매겨줄라고
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from review where gno=?");
			pstmt.setInt(1, gno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cnt = rset.getInt("cnt");
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return cnt;
	}
	public ArrayList<ReviewDTO> listAll(String hid){//특정 host 후기 list
		ArrayList<ReviewDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, concat(left(rwriter, 2), repeat('*', length(rwriter)-2) ) as rwriter2 from review a where hid=? order by rdate desc");
			pstmt.setString(1, hid);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewDTO tmp = new ReviewDTO();
				tmp.setRno(rset.getInt("rno"));
				tmp.setRwriter(rset.getString("rwriter2"));
				tmp.setRfile(rset.getString("rfile"));
				tmp.setRdate(rset.getString("rdate"));
				tmp.setHid(rset.getString("hid"));
				tmp.setHstar(rset.getDouble("hstar"));
				tmp.setHcomment(rset.getString("hcomment"));
				tmp.setGno(rset.getInt("gno"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcomment(rset.getString("gcomment"));
				tmp.setMno(rset.getInt("mno"));
				list.add(tmp);
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
	}
	public int listAllCnt(String hid){//특정 경기장 이용후기 cnt - 후기에 번호 메겨줄라고
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from review where hid=?");
			pstmt.setString(1, hid);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cnt = rset.getInt("cnt");
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return cnt;
	}

}
