package com.company.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.FestivaltalkDTO;


public class FestivalDAO {
	public int fnoMax(){
		int max = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select max(fno) as max from festivaltalk");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				max = rset.getInt("max");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return max;
	}
	public ArrayList<FestivaltalkDTO> listAll(){//현재 날짜의 해당 달에 입력된 data만 뽑아오기
		ArrayList<FestivaltalkDTO> list = new ArrayList<FestivaltalkDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from festivaltalk where fdate > (select last_day(now()-interval 1 month) from dual) order by fdate");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FestivaltalkDTO tmp = new FestivaltalkDTO();
				tmp.setFno(rset.getInt("fno"));
				tmp.setFcontent(rset.getString("fcontent"));
				tmp.setFpass(rset.getString("fpass"));
				tmp.setFdate(rset.getString("fdate"));
				list.add(tmp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
	}
	public int add(FestivaltalkDTO dto){
		int r = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into festivaltalk (fcontent, fpass, fip) values (?, ?, ?)");
			pstmt.setString(1, dto.getFcontent());
			pstmt.setString(2, dto.getFpass());
			pstmt.setString(3, InetAddress.getLocalHost().getHostAddress());
			r = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return r;
	}
	public int checkpass(FestivaltalkDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from festivaltalk where fno=? and fpass=?");
			pstmt.setInt(1, dto.getFno());
			pstmt.setString(2, dto.getFpass());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt("cnt");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int edit(FestivaltalkDTO dto) {//수정
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update festivaltalk set fcontent=?, fpass=? where fno=?");
			
			pstmt.setString(1, dto.getFcontent());
			pstmt.setString(2, dto.getFpass());
			pstmt.setInt(3, dto.getFno());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int delete(FestivaltalkDTO dto) {//삭제
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from festivaltalk where fno=?");
			pstmt.setInt(1, dto.getFno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	/*
		public JsonArray matchListFilter(String query) {//왼쪽 메뉴에서 필터링 페이징처리 X
		JsonArray list = new JsonArray();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, b.gregion, b.gside, c.gstar, d.cnt, f.* from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno left join (select * from convinfo) f on a.gno=f.gno "+query);
			

			rset = pstmt.executeQuery();
			while(rset.next()) {
				JsonObject tmp = new JsonObject();
				tmp.addProperty("mno", rset.getInt("mno"));
				tmp.addProperty("mtitle", rset.getString("mtitle"));
				tmp.addProperty("mhost", rset.getString("mhost"));
				tmp.addProperty("msex", rset.getString("msex"));
				tmp.addProperty("mday", rset.getString("mday"));
				tmp.addProperty("mdate", rset.getString("mdate").substring(0, 10));
				tmp.addProperty("mhour", rset.getInt("mhour"));
				tmp.addProperty("mduration", rset.getInt("mduration"));
				
				DecimalFormat df = new DecimalFormat("###,###");
				
				tmp.addProperty("mprice", df.format(rset.getInt("mprice")));
				tmp.addProperty("mcreatedate", rset.getString("mcreatedate"));
				tmp.addProperty("gno", rset.getInt("gno"));
				tmp.addProperty("mtotal", rset.getInt("mtotal"));
				
				String gfile = rset.getString("gfile");
				String thumbnail = gfile.split("/")[0];
				
				tmp.addProperty("thumbnail", thumbnail);
				tmp.addProperty("cnt", rset.getInt("mtotal")-rset.getInt("cnt"));
				tmp.addProperty("gstar", rset.getDouble("gstar"));
				tmp.addProperty("gcategory", rset.getString("gcategory"));
				
				list.add(tmp);
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
	 */
	
}
