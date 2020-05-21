package com.company.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.AnytalkDTO;

public class AnytalkDAO {

	public int addTalk(AnytalkDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into anytalk (acomment, apass, aip) values (?, ?, ?)");
			pstmt.setString(1, dto.getAcomment());
			pstmt.setString(2, dto.getApass());
			pstmt.setString(3, InetAddress.getLocalHost().getHostAddress());
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public ArrayList<AnytalkDTO> listAll(){
		ArrayList<AnytalkDTO> list = new ArrayList<AnytalkDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.* from (select ano, acomment, adate, @rownum:=@rownum+1 as rownum from anytalk, (select @rownum:=0) tmp order by adate desc limit 0, 10) a order by a.rownum desc");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AnytalkDTO tmp = new AnytalkDTO();
				tmp.setAno(rset.getInt("ano"));
				tmp.setAcomment(rset.getString("acomment"));
				//tmp.setAlike(rset.getInt("alike"));
				//tmp.setAdislike(rset.getInt("adislike"));
				tmp.setAdate(rset.getString("adate").substring(0, 10));
				list.add(tmp);
			}
			
			/*
select a.*
from (select acomment, alike, adislike, adate, @rownum:=@rownum+1 as rownum from anytalk, (select @rownum:=0) tmp order by adate desc limit 0, 10) a
order by a.rownum desc
			 */
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
	}
	public int editTalk(AnytalkDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update anytalk set acomment=? where ano=?");
			pstmt.setString(1, dto.getAcomment());
			pstmt.setInt(2, dto.getAno());
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int checkTalkPass(AnytalkDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as check from anytalk where acomment=? and apass=?");
			pstmt.setString(1, dto.getAcomment());
			pstmt.setString(2, dto.getApass());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("check");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int getAno(AnytalkDTO dto) {
		int ano = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select ano from anytalk where acomment=?");
			pstmt.setString(1, dto.getAcomment());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ano = rset.getInt("ano");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return ano;
	}

}//end class
