package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.MatchDTO;
import com.company.dto.MemberDTO;
import com.company.dto.MyMatchDTO;

public class MatchOmemberDAO {
	public int matchReserve(int mno, String oid) {//사용자가 경기 예약할 떄
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into matchinfo_omember(mno, oid) values (?, ?)");
			pstmt.setInt(1, mno);
			pstmt.setString(2, oid);

			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int setPoint(MemberDTO dto) {//경기예약할 때 포인트차감 //컨트롤러에서 이미 opoint -= mprice; //포인트에 경기비용 차감 이렇게 차감하고 호출함
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update omember set opoint=? where oid=?");
			pstmt.setInt(1, dto.getOpoint());
			pstmt.setString(2, dto.getOid());

			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public ArrayList<MyMatchDTO> getMyMatch(Object oid) {//경기예약할 때 포인트차감
		ArrayList<MyMatchDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.mtitle, a.mdate, b.gcategory, b.gaddr1, a.mno, a.gno, a.mhost, b.gname, a.mhour, a.mprice from matchinfo a, groundinfo b where a.gno = b.gno and mno in (select mno from  matchinfo_omember where oid=?) order by a.mdate");
			pstmt.setObject(1, oid);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MyMatchDTO tmp = new MyMatchDTO();
				tmp.setMtitle(rset.getString(1));
				tmp.setMdate(rset.getString(2));
				tmp.setGcategory(rset.getString(3));
				tmp.setGaddr1(rset.getString(4));
				tmp.setMno(rset.getInt(5));
				tmp.setGno(rset.getInt(6));
				tmp.setMhost(rset.getString(7));
				tmp.setGname(rset.getString(8));
				tmp.setMhour(rset.getInt(9));
				tmp.setMprice(rset.getInt(10));
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
	public int reserveCnt(Object oid) {//내가 총 몇번의 경기를 예약했는지
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select oid, count(*) as cnt from matchinfo_omember where oid=?");
			pstmt.setObject(1, oid);
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
	public ArrayList<MatchDTO> getMyHost(Object oid){//내가 개설한 경기
		 ArrayList<MatchDTO> list = new ArrayList<MatchDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select a.*, b.gcategory, d.cnt from matchinfo a join groundinfo b on a.gno=b.gno and a.mhost=? left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno order by a.mtitle");
				pstmt.setObject(1, oid);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					MatchDTO tmp = new MatchDTO();
					tmp.setMno(rset.getInt("mno"));
					tmp.setMtitle(rset.getString("mtitle"));
					tmp.setMhost(rset.getString("mhost"));
					tmp.setMsex(rset.getString("msex"));
					tmp.setMday(rset.getString("mday"));
					tmp.setMdate(rset.getString("mdate"));
					tmp.setMhour(rset.getInt("mhour"));
					tmp.setMduration(rset.getInt("mduration"));
					tmp.setMprice(rset.getInt("mprice"));
					tmp.setMcreatedate(rset.getString("mcreatedate"));
					tmp.setGno(rset.getInt("gno"));
					tmp.setMtotal(rset.getInt("mtotal"));
					tmp.setCnt(rset.getInt("mtotal")-rset.getInt("cnt"));
					tmp.setGcategory(rset.getString("gcategory"));
					list.add(tmp);
				}
				
				/*
select a.*, b.gfile, b.gcategory, d.cnt
from matchinfo a
join groundinfo b 
on a.gno=b.gno and a.mhost=?
left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d
on a.mno = d.mno
				 */
				
			}catch (Exception e) { e.printStackTrace(); }
			finally {
				if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
				if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
				if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
			}
		 return list;
	}
	public int hostCnt(Object oid) {//내가 총 몇번의 경기를 개설했는지
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from matchinfo where mhost=?");
			pstmt.setObject(1, oid);
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
	public double myHstar(Object oid) {//나의 평균 평점
		double myhstar= -1.1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select avg(hstar) as avg from review where hid=?");
			pstmt.setObject(1, oid);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				myhstar = rset.getDouble("avg");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return myhstar;
	}
	public int deleteMatch(String oid, int mno) {//예약취소
		int r= -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from matchinfo_omember where oid=? and mno=?");
			pstmt.setObject(1, oid);
			pstmt.setInt(2, mno);
			r = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return r;
	}
	public int checkReserve(String oid, int mno) {//나의 평균 평점
		int result= -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) cnt from matchinfo_omember where oid=? and mno=?");
			pstmt.setString(1, oid);
			pstmt.setInt(2, mno);
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
}//end class
