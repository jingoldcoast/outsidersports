package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.MemberDTO;

public class MemberDAO {

	public int joinMember(MemberDTO dto) {//회원가입
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into omember (oid, opass, oname, obirth, osex, ocontact) values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getOid());
			pstmt.setString(2, dto.getOpass());
			pstmt.setString(3, dto.getOname());
			pstmt.setString(4, dto.getObirth());
			pstmt.setString(5, dto.getOsex());
			pstmt.setString(6, dto.getOcontact());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int checkId(MemberDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as check_id from omember where oid=?");
			pstmt.setString(1, dto.getOid());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("check_id");
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int checkPass(MemberDTO dto) {//로그인할때 아이디와 비밀번호 일치여부
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from omember where oid=? and opass=?");
			pstmt.setString(1, dto.getOid());
			pstmt.setString(2, dto.getOpass());
			
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
	public MemberDTO getMember(MemberDTO dto) {//마이페이지에 띄워줄 회원별 정보 추출
		MemberDTO detail = new MemberDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from omember where oid=?");
			pstmt.setString(1, dto.getOid());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				detail.setOid(rset.getString("oid"));
				detail.setOpass(rset.getString("opass"));
				detail.setOname(rset.getString("oname"));
				detail.setObirth(rset.getString("obirth"));
				detail.setOsex(rset.getString("osex"));
				detail.setOcontact(rset.getString("ocontact"));
				detail.setOphoto(rset.getString("ophoto"));
				detail.setOgrade(rset.getString("ograde"));
				detail.setOdate(rset.getString("odate"));
				detail.setOpoint(rset.getInt("opoint"));
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return detail;
	}
	public int editInfo(MemberDTO dto) {//회원정보 수정(이름, 연락처 수정가능)
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update omember set oname=?, ocontact=? where oid=?");
			
			pstmt.setString(1, dto.getOname());
			pstmt.setString(2, dto.getOcontact());
			pstmt.setString(3, dto.getOid());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int editPass(MemberDTO dto) {//비밀번호 수정하기
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update omember set opass=? where oid=?");
			
			pstmt.setString(1, dto.getOpass());
			pstmt.setString(2, dto.getOid());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int addPoint(MemberDTO dto) {//포인트 추가하기
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update omember set opoint=opoint+? where oid=?");
			
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
	public String findId(MemberDTO dto) {
		String oid="";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select oid from omember where ocontact=?");
			pstmt.setString(1, dto.getOcontact());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				oid = rset.getString("oid");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return oid;
	}
	public String findPass(MemberDTO dto) {
		String opass="";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select opass from omember where oid=?");
			pstmt.setString(1, dto.getOid());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				opass = rset.getString("opass");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return opass;
	}
	public ArrayList<MemberDTO> listAll(int page) {//회원전체리스트출력
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, ifnull(b.matchcnt, 0) as matchcnt, ifnull(c.usedpoint, 0) as usedpoint, ifnull(d.hstar, 0) as hstar, ifnull(e.cnt, 0) as hostcnt from omember a left join (select oid, count(*) as matchcnt from matchinfo_omember group by oid) b on a.oid = b.oid left join (select * from totalpoint group by oid) c on a.oid = c.oid left join (select hid, avg(hstar) as hstar from review group by hid) d on a.oid = d.hid left join (select mhost, count(*) as cnt from matchinfo group by mhost) e on a.oid = e.mhost order by matchcnt desc, usedpoint desc limit ?, ?");
			pstmt.setInt(1, page);
			pstmt.setInt(2, 50);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberDTO tmp = new MemberDTO();
				tmp.setOid(rset.getString("oid"));
				tmp.setOname(rset.getString("oname"));
				tmp.setObirth(rset.getString("obirth"));
				String osex = rset.getString("osex");
				if(osex.equals("m")) {
					tmp.setOsex("남성");
				}else {
					tmp.setOsex("여성");
				}
				tmp.setOcontact(rset.getString("ocontact"));
				tmp.setOphoto(rset.getString("ophoto"));
				tmp.setOgrade(rset.getString("ograde"));
				tmp.setOdate(rset.getString("odate"));
				tmp.setOpoint(rset.getInt("opoint"));
				tmp.setHstar(rset.getDouble("hstar"));
				tmp.setUsedpoint(rset.getInt("usedpoint"));
				tmp.setMatchcnt(rset.getInt("matchcnt"));
				tmp.setMatchAsHost(rset.getInt("hostcnt"));
				list.add(tmp);
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
		/*
select a.*, ifnull(b.matchcnt, 0) as matchcnt, ifnull(c.usedpoint, 0) as usedpoint, ifnull(d.hstar, 0) as hstar, ifnull(e.cnt, 0) as hostcnt
from
omember a
left join (select oid, count(*) as matchcnt from matchinfo_omember group by oid) b
on a.oid = b.oid
left join (select * from totalpoint group by oid) c
on a.oid = c.oid
left join (select hid, avg(hstar) as hstar from review group by hid) d
on a.oid = d.hid
left join (select mhost, count(*) as cnt from matchinfo group by mhost) e
on a.oid = e.mhost
order by matchcnt desc, usedpoint desc
limit ?, ?

		 */
	}
	public int listTotal() {//페이징때매
		int total = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as total from omember");
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
	
	
}//end class
