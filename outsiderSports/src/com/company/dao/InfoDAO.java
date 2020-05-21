package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.InfoDTO;

public class InfoDAO {

	public int write(InfoDTO dto) {
		int result = -1;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into info(ncategory, ntitle, ncontent) values (?, ?, ?)");
			pstmt.setString(1, dto.getNcategory());
			pstmt.setString(2, dto.getNtitle());
			pstmt.setString(3, dto.getNcontent());

			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int delete(InfoDTO dto) {
		int result = -1;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from info where ntitle=?");
			pstmt.setString(1, dto.getNtitle());
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public String getData(InfoDTO dto) {//카테고리에 맞는 값 가져오기
		String txt = "";
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		int cnt = 0;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from info where ncategory=? order by ndate desc");
			pstmt.setString(1, dto.getNcategory());
			rset = pstmt.executeQuery();
			boolean first = true;
			while(rset.next()) {
				txt+= "<div class='panel panel-default'><div class='panel-heading-info'><h4 class='panel-title'>";
				txt +="<a data-toggle='collapse' data-parent='#accordion' href='#collapse"+cnt+"'>";
				txt += (rset.getString("ntitle") + "</a> </h4> </div>");
				if(first) {
					txt+= "<div id='collapse"+cnt+"' class='panel-collapse collapse in'> <div class='panel-body'>";
				}else {
					txt += "<div id='collapse"+cnt+"' class='panel-collapse collapse'> <div class='panel-body'>";
				}
				txt += (rset.getString("ncontent").replaceAll("\n", "<br/>") + "</div></div></div>");
				first = false;
				cnt++;
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return txt;
	}
	public ArrayList<InfoDTO> listAll(String ncategory){
		ArrayList<InfoDTO> list =  new ArrayList<InfoDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from info where ncategory=? order by ndate desc");
			pstmt.setString(1, ncategory);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				InfoDTO tmp = new InfoDTO();
				tmp.setNtitle(rset.getString("ntitle"));
				tmp.setNcontent(rset.getString("ncontent").replaceAll("\n", "<br/>"));
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
	
}
