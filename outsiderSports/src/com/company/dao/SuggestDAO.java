package com.company.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.SuggestDTO;

public class SuggestDAO {
	public int sstepMax() {
		int max = -1;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select max(sstep) as max from suggest");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				max = rset.getInt("max");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return max;
	}
	public int write(SuggestDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into suggest (sname, spass, stitle, scontent, sip, sgroup, sstep, sfile) values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getSname());
			pstmt.setString(2, dto.getSpass());
			pstmt.setString(3, dto.getStitle());
			pstmt.setString(4, dto.getScontent());
			pstmt.setString(5, InetAddress.getLocalHost().getHostAddress());
			pstmt.setInt(6, dto.getSgroup());
			pstmt.setInt(7, dto.getSstep());
			pstmt.setString(8, dto.getSfile());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public ArrayList<SuggestDTO> list(int page){
		ArrayList<SuggestDTO> list = new ArrayList<SuggestDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, concat(left(sname, 3), repeat('*', length(sname)-3) ) as sname2 from suggest a where a.sgroup in (select sgroup from suggest where sstep=sgroup*1000) order by a.sstep desc, a.sindent asc limit ?, ?");
			
			pstmt.setInt(1, page);
			pstmt.setInt(2, 15);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				SuggestDTO tmp = new SuggestDTO();
				tmp.setSno(rset.getInt("sno"));
				tmp.setSname(rset.getString("sname2"));
				tmp.setSpass(rset.getString("spass"));
				tmp.setStitle(rset.getString("stitle"));
				tmp.setScontent(rset.getString("scontent"));
				tmp.setSdate(rset.getString("sdate"));
				tmp.setShit(rset.getInt("shit"));
				tmp.setSip(rset.getString("sip"));
				tmp.setSgroup(rset.getInt("sgroup"));
				tmp.setSstep(rset.getInt("sstep"));
				tmp.setSindent(rset.getInt("sindent"));
				tmp.setSfile(rset.getString("sfile"));
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
	public int listTotal() {
		int total = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as total from suggest where sgroup in (select sgroup from suggest where sstep=sgroup*1000)");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				total = rset.getInt("total");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return total;
	}
	public int addHit(SuggestDTO dto) {//조회수 +1
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update suggest set shit=shit+1 where sno=?");
			pstmt.setInt(1, dto.getSno());

			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public SuggestDTO detail(SuggestDTO dto) {
		SuggestDTO detail = new SuggestDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from suggest where sno=?");
			pstmt.setInt(1, dto.getSno());
			rset= pstmt.executeQuery();
			while(rset.next()) {
				detail.setSno(rset.getInt("sno"));
				detail.setSname(rset.getString("sname"));
				detail.setSpass(rset.getString("spass"));
				detail.setStitle(rset.getString("stitle"));
				detail.setScontent(rset.getString("scontent"));
				detail.setSdate(rset.getString("sdate"));
				detail.setShit(rset.getInt("shit"));
				detail.setSip(rset.getString("sip"));
				detail.setSgroup(rset.getInt("sgroup"));
				detail.setSstep(rset.getInt("sstep"));
				detail.setSindent(rset.getInt("sindent"));
				detail.setSfile(rset.getString("sfile"));
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return detail;
	}
	public int edit(SuggestDTO dto) {//수정하기(업데이트)
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update suggest set stitle=?, scontent=?, sfile=? where sno=?");
			pstmt.setString(1, dto.getStitle());
			pstmt.setString(2, dto.getScontent());
			pstmt.setString(3, dto.getSfile());
			pstmt.setInt(4, dto.getSno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int checkPass(SuggestDTO dto) {//비밀번호확인
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as check_pass from suggest where sno=? and spass=?");
			pstmt.setInt(1, dto.getSno());
			pstmt.setString(2, dto.getSpass());
			rset= pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("check_pass");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int delete(SuggestDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from suggest where sno=?");
			pstmt.setInt(1, dto.getSno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int reply1(SuggestDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update suggest set sstep=sstep-1 where sstep > ? and sstep < ?");
			
			int sstep = dto.getSstep();
			int prev_step = ((int)(Math.ceil(sstep/(float)1000))-1)*1000;
			
			pstmt.setInt(1, prev_step);
			pstmt.setInt(2, sstep);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int reply2(SuggestDTO dto) {//답글 삽입하기
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into suggest (sname, spass, stitle, scontent, sip, sgroup, sstep, sindent, sfile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setString(1, dto.getSname());
			pstmt.setString(2, dto.getSpass());
			pstmt.setString(3, dto.getStitle());
			pstmt.setString(4, dto.getScontent());
			pstmt.setString(5, InetAddress.getLocalHost().getHostAddress());
			pstmt.setInt(6, dto.getSgroup());
			pstmt.setInt(7, dto.getSstep()-1); 
			pstmt.setInt(8, dto.getSindent()+1); 
			pstmt.setString(9, dto.getSfile());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public SuggestDTO reply_detail(SuggestDTO dto) {
		SuggestDTO detail = new SuggestDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from suggest where sno=?");
			pstmt.setInt(1, dto.getSno());
			rset= pstmt.executeQuery();
			while(rset.next()) {
				detail.setSno(rset.getInt("sno"));
				detail.setSname(rset.getString("sname"));
				detail.setSpass(rset.getString("spass"));
				detail.setStitle("ㄴ답글: "+rset.getString("stitle"));
				detail.setScontent("\n\n>"+rset.getString("scontent").replaceAll("\n", "▷"));
				detail.setSdate(rset.getString("sdate"));
				detail.setShit(rset.getInt("shit"));
				detail.setSip(rset.getString("sip"));
				detail.setSgroup(rset.getInt("sgroup"));
				detail.setSstep(rset.getInt("sstep"));
				detail.setSindent(rset.getInt("sindent"));
				detail.setSfile(rset.getString("sfile"));
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return detail;
	}
}//end class
