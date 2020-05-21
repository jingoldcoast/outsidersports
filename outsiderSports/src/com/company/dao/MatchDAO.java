package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.MatchDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MatchDAO {
	public int write(MatchDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into matchinfo (mtitle, mhost, msex, mday, mdate, mhour, mduration, mprice, gno, mtotal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getMtitle());
			pstmt.setString(2, dto.getMhost());
			pstmt.setString(3, dto.getMsex());
			pstmt.setString(4, dto.getMday());
			pstmt.setString(5, dto.getMdate());
			pstmt.setInt(6, dto.getMhour());
			pstmt.setInt(7, dto.getMduration());
			pstmt.setInt(8, dto.getMprice());
			pstmt.setInt(9, dto.getGno());
			pstmt.setInt(10, dto.getMtotal());
			
			result = pstmt.executeUpdate();
			/*
			 * mtitle, mhost, msex, mday, mdate, mhour, mduration, mprice, gno, mtotal
			 create table matchinfo(
	mno number primary key,
	mtitle varchar2(250) not null,
	mhost varchar2(250) not null,
	msex char(2) not null, //남성여성믹스 매치 m/f/c
	mday varchar(50) not null,
	mdate timestamp not null,
	mhour number not null,
	mduration number not null, 60/90/120 1시간/1시간반/2시간
	mprice number not null,
	mcreatedate timestamp default current_timestamp,
	gno number, ##외래키
	mtotal number not null //수용인원
	constraint matchinfo_fk foreign key(gno) 
	references groundinfo(gno)
	);
			 */
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public ArrayList<MatchDTO> matchList(int page) {
		ArrayList<MatchDTO> list =  new ArrayList<MatchDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, c.gstar, d.cnt from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno order by a.mdate asc limit ?, ?");
			
			pstmt.setInt(1, page);
			pstmt.setInt(2, 10);
			
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
				String gfile = rset.getString("gfile");
				String thumbnail = gfile.split("/")[0];
				tmp.setThumbnail(thumbnail);
				tmp.setCnt(rset.getInt("mtotal")-rset.getInt("cnt"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcategory(rset.getString("gcategory"));
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
select a.*, b.gfile, b.gcategory, c.gstar, d.cnt
from matchinfo a
join groundinfo b 
on a.gno=b.gno
left join (select gno, avg(gstar) as gstar from review group by gno) c
on a.gno=c.gno
left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d
on a.mno = d.mno
order by a.mdate asc limit 0, 10;
		 */
	}
	public ArrayList<MatchDTO> matchListQuery(int page, String query) {//경기장 평점순, 경기장 진행날짜순, 경기장 가나다순, 최신순
		ArrayList<MatchDTO> list =  new ArrayList<MatchDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, c.gstar, d.cnt from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno "+query+" limit ?, ?");
			
			pstmt.setInt(1, page);
			pstmt.setInt(2, 10);
			
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
				String gfile = rset.getString("gfile");
				String thumbnail = gfile.split("/")[0];
				tmp.setThumbnail(thumbnail);
				tmp.setCnt(rset.getInt("mtotal")-rset.getInt("cnt"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcategory(rset.getString("gcategory"));
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
	public JsonArray matchListFilter(String query) {//왼쪽 메뉴에서 필터링 페이징처리 X
		JsonArray list = new JsonArray();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, b.gregion, b.gside, c.gstar, d.cnt, f.* from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno left join (select * from convinfo) f on a.gno=f.gno "+query);
			
			/*
select a.*, b.gfile, b.gcategory, b.gregion, b.gside, c.gstar, d.cnt, f.*
from matchinfo a
join groundinfo b 
on a.gno=b.gno
left join (select gno, avg(gstar) as gstar from review group by gno) c
on a.gno=c.gno
left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d
on a.mno = d.mno
left join (select * from convinfo) f
on a.gno=f.gno
			 */
			
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
		return list;
	}
	public ArrayList<MatchDTO> matchListMainFilter(String query) {//메인에서 필터링 페이징처리 X
		ArrayList<MatchDTO> list = new ArrayList<MatchDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, b.gregion, b.gside, c.gstar, d.cnt, f.* from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno left join (select * from convinfo) f on a.gno=f.gno "+query);
			//String query에 order by 까지 다 있음
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
				String gfile = rset.getString("gfile");
				String thumbnail = gfile.split("/")[0];
				tmp.setThumbnail(thumbnail);
				tmp.setCnt(rset.getInt("mtotal")-rset.getInt("cnt"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcategory(rset.getString("gcategory"));
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
	public int listTotal() {//페이징할때 필요함 전체 몇줄인지
		int total = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as total from matchinfo");
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
	public MatchDTO detail(MatchDTO dto) {
		MatchDTO detail = new MatchDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, c.gstar, d.cnt, f.hstar from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno left join (select hid, avg(hstar) as hstar from review group by hid) f on a.mhost=f.hid where a.mno=?");
			pstmt.setInt(1, dto.getMno());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				detail.setMno(rset.getInt("mno"));
				detail.setMtitle(rset.getString("mtitle"));
				detail.setMhost(rset.getString("mhost"));
				detail.setMsex(rset.getString("msex"));
				detail.setMday(rset.getString("mday"));
				detail.setMdate(rset.getString("mdate"));
				detail.setMhour(rset.getInt("mhour"));
				detail.setMduration(rset.getInt("mduration"));
				detail.setMprice(rset.getInt("mprice"));
				detail.setMcreatedate(rset.getString("mcreatedate"));
				detail.setGno(rset.getInt("gno"));
				detail.setMtotal(rset.getInt("mtotal"));
				detail.setCnt(rset.getInt("mtotal") - rset.getInt("cnt"));
				detail.setGstar(rset.getDouble("gstar"));
				detail.setHstar(rset.getDouble("hstar"));
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return detail;
		/*
select a.*, b.gfile, c.gstar, d.cnt, f.hstar
from matchinfo a
join groundinfo b 
on a.gno=b.gno
left join (select gno, avg(gstar) as gstar from review group by gno) c
on a.gno=c.gno
left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d
on a.mno = d.mno
left join (select hid, avg(hstar) as hstar from review group by hid) f
on a.mhost=f.hid
where a.mno=?
		 */
	}
	public ArrayList<String> reserveList(MatchDTO dto){//경기 예약한 사람들 list
		ArrayList<String> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select oid from matchinfo_omember where mno=?");
			pstmt.setInt(1, dto.getMno());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("oid"));
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return list;
	}
	public ArrayList<MatchDTO> matchSearch(String like) { //경기 검색할때 like '%%'로 뽑기
		ArrayList<MatchDTO> list =  new ArrayList<MatchDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, c.gstar, d.cnt from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno where mtitle like '%"+like+"%' order by a.mtitle asc");
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
				String gfile = rset.getString("gfile");
				String thumbnail = gfile.split("/")[0];
				tmp.setThumbnail(thumbnail); //list 전체 페이지에 1개사진 띄워줘야해서 첫번째 이미지만 가져옴
				tmp.setCnt(rset.getInt("mtotal")-rset.getInt("cnt"));
				tmp.setGstar(rset.getDouble("gstar"));
				tmp.setGcategory(rset.getString("gcategory"));
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
	
}//end MatchDAO
