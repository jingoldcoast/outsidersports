package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.GroundDTO;

public class GroundDAO {
	public int addGround(GroundDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into groundinfo (gname, gregion, gzipcode, gaddr1, gaddr2, gfile, gside, gwriter, gcategory, gdesc) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getGname());
			pstmt.setString(2, dto.getGregion());
			pstmt.setString(3, dto.getGzipcode());
			pstmt.setString(4, dto.getGaddr1());
			pstmt.setString(5, dto.getGaddr2());
			pstmt.setString(6, dto.getGfile());
			pstmt.setInt(7, dto.getGside());
			pstmt.setString(8, dto.getGwriter());
			pstmt.setString(9, dto.getGcategory());
			pstmt.setString(10, dto.getGdesc());
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int editGround(GroundDTO dto) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update groundinfo set gname=?, gregion=?, gzipcode=?, gaddr1=?, gaddr2=?, gfile=?, gside=?, gwriter=?, gcategory=?, gdesc=? where gno=?");
			pstmt.setString(1, dto.getGname());
			pstmt.setString(2, dto.getGregion());
			pstmt.setString(3, dto.getGzipcode());
			pstmt.setString(4, dto.getGaddr1());
			pstmt.setString(5, dto.getGaddr2());
			pstmt.setString(6, dto.getGfile());
			pstmt.setInt(7, dto.getGside());
			pstmt.setString(8, dto.getGwriter());
			pstmt.setString(9, dto.getGcategory());
			pstmt.setString(10, dto.getGdesc());
			pstmt.setInt(11, dto.getGno());
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int getGno() {//편의시설 집어넣을때 groundinfo에 먼저 넣고 gno맥스값 찾아서 그거를 convinfo에 gno로 넣을라고
		int gno = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select max(gno) as max from groundinfo");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				gno = rset.getInt("max");
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return gno;
	}
	public int addConv(GroundDTO dto) {//편의시설정보 insert
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into convinfo (gno, parking, light, io, shower, air, ball, vest, shoes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, dto.getGno());
			pstmt.setString(2, dto.getParking());
			pstmt.setString(3, dto.getLight());
			pstmt.setString(4, dto.getIo());
			pstmt.setString(5, dto.getShower());
			pstmt.setString(6, dto.getAir());
			pstmt.setString(7, dto.getBall());
			pstmt.setString(8, dto.getVest());
			pstmt.setString(9, dto.getShoes());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int editConv(GroundDTO dto) {//편의시설정보 update
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update convinfo set parking=?, light=?, io=?, shower=?, air=?, ball=?, vest=?, shoes=? where gno=?");
			
			pstmt.setString(1, dto.getParking());
			pstmt.setString(2, dto.getLight());
			pstmt.setString(3, dto.getIo());
			pstmt.setString(4, dto.getShower());
			pstmt.setString(5, dto.getAir());
			pstmt.setString(6, dto.getBall());
			pstmt.setString(7, dto.getVest());
			pstmt.setString(8, dto.getShoes());
			pstmt.setInt(9, dto.getGno());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public ArrayList<GroundDTO> listAll(int page){
		ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno order by a.gdate desc limit ?, ?");
			
			/*
			select a.*, b.*, c.gstar
			from groundinfo a
			left join convinfo b 
			on a.gno=b.gno
			left join (select gno, avg(gstar) as gstar from review group by gno) c
			on a.gno = c.gno
			order by a.gdate desc limit ?, ?
			*/
			
			pstmt.setInt(1, page);
			pstmt.setInt(2, 10);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroundDTO tmp = new GroundDTO();
				tmp.setGno(rset.getInt("gno"));
				tmp.setGlike(rset.getInt("glike"));
				tmp.setGname(rset.getString("gname"));
				tmp.setGregion(rset.getString("gregion"));
				tmp.setGzipcode(rset.getString("gzipcode"));
				tmp.setGaddr1(rset.getString("gaddr1"));
				tmp.setGaddr2(rset.getString("gaddr2"));
				tmp.setGfile(rset.getString("gfile")); 
				tmp.setGside(rset.getInt("gside"));
				tmp.setGwriter(rset.getString("gwriter"));
				tmp.setGdate(rset.getString("gdate"));
				tmp.setGcategory(rset.getString("gcategory"));
				tmp.setGdesc(rset.getString("gdesc"));
				tmp.setParking(rset.getString("parking"));
				tmp.setLight(rset.getString("light"));
				tmp.setIo(rset.getString("io"));
				tmp.setShower(rset.getString("shower"));
				tmp.setAir(rset.getString("air"));
				tmp.setBall(rset.getString("ball"));
				tmp.setVest(rset.getString("vest"));
				tmp.setShoes(rset.getString("shoes"));
				tmp.setGstar(rset.getDouble("gstar"));
				
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
	public ArrayList<GroundDTO> listAll(int page, String query){
		ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno "+query+" limit ?, ?");
			/*
			select a.*, b.*, c.gstar
			from groundinfo a
			left join convinfo b 
			on a.gno=b.gno
			left join (select gno, avg(gstar) as gstar from review group by gno) c
			on a.gno = c.gno
			order by a.gdate desc limit ?, ?
			 */
			pstmt.setInt(1, page);
			pstmt.setInt(2, 10);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroundDTO tmp = new GroundDTO();
				tmp.setGno(rset.getInt("gno"));
				tmp.setGlike(rset.getInt("glike"));
				tmp.setGname(rset.getString("gname"));
				tmp.setGregion(rset.getString("gregion"));
				tmp.setGzipcode(rset.getString("gzipcode"));
				tmp.setGaddr1(rset.getString("gaddr1"));
				tmp.setGaddr2(rset.getString("gaddr2"));
				tmp.setGfile(rset.getString("gfile")); 
				tmp.setGside(rset.getInt("gside"));
				tmp.setGwriter(rset.getString("gwriter"));
				tmp.setGdate(rset.getString("gdate"));
				tmp.setGcategory(rset.getString("gcategory"));
				tmp.setGdesc(rset.getString("gdesc"));
				tmp.setParking(rset.getString("parking"));
				tmp.setLight(rset.getString("light"));
				tmp.setIo(rset.getString("io"));
				tmp.setShower(rset.getString("shower"));
				tmp.setAir(rset.getString("air"));
				tmp.setBall(rset.getString("ball"));
				tmp.setVest(rset.getString("vest"));
				tmp.setShoes(rset.getString("shoes"));
				tmp.setGstar(rset.getDouble("gstar"));
				
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
	public ArrayList<GroundDTO> listAll(String like){//경기장 이름으로 검색할 때
		ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno where a.gname like '%"+like+"%' order by a.gname asc");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroundDTO tmp = new GroundDTO();
				tmp.setGno(rset.getInt("gno"));
				tmp.setGlike(rset.getInt("glike"));
				tmp.setGname(rset.getString("gname"));
				tmp.setGregion(rset.getString("gregion"));
				tmp.setGzipcode(rset.getString("gzipcode"));
				tmp.setGaddr1(rset.getString("gaddr1"));
				tmp.setGaddr2(rset.getString("gaddr2"));
				tmp.setGfile(rset.getString("gfile")); 
				tmp.setGside(rset.getInt("gside"));
				tmp.setGwriter(rset.getString("gwriter"));
				tmp.setGdate(rset.getString("gdate"));
				tmp.setGcategory(rset.getString("gcategory"));
				tmp.setGdesc(rset.getString("gdesc"));
				tmp.setParking(rset.getString("parking"));
				tmp.setLight(rset.getString("light"));
				tmp.setIo(rset.getString("io"));
				tmp.setShower(rset.getString("shower"));
				tmp.setAir(rset.getString("air"));
				tmp.setBall(rset.getString("ball"));
				tmp.setVest(rset.getString("vest"));
				tmp.setShoes(rset.getString("shoes"));
				tmp.setGstar(rset.getDouble("gstar"));
				
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
	public int listTotal() {//list의 전체 갯수 출력
		int total = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as total from groundinfo");
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
	public GroundDTO detail(GroundDTO dto) {
		GroundDTO detail = new GroundDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno where a.gno=?");
			
			pstmt.setInt(1, dto.getGno());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				detail.setGno(rset.getInt("gno"));
				detail.setGlike(rset.getInt("glike"));
				detail.setGname(rset.getString("gname"));
				detail.setGregion(rset.getString("gregion"));
				detail.setGzipcode(rset.getString("gzipcode"));
				detail.setGaddr1(rset.getString("gaddr1"));
				detail.setGaddr2(rset.getString("gaddr2"));
				detail.setGfile(rset.getString("gfile")); 
				detail.setGside(rset.getInt("gside"));
				detail.setGwriter(rset.getString("gwriter"));
				detail.setGdate(rset.getString("gdate"));
				detail.setGcategory(rset.getString("gcategory"));
				detail.setGdesc(rset.getString("gdesc"));
				detail.setParking(rset.getString("parking"));
				detail.setLight(rset.getString("light"));
				detail.setIo(rset.getString("io"));
				detail.setShower(rset.getString("shower"));
				detail.setAir(rset.getString("air"));
				detail.setBall(rset.getString("ball"));
				detail.setVest(rset.getString("vest"));
				detail.setShoes(rset.getString("shoes"));
				detail.setGstar(rset.getDouble("gstar"));
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		/*
select a.*, b.*, c.gstar 
from groundinfo a
join convinfo b
on a.gno=b.gno
left join (select gno, avg(gstar) as gstar from review group by gno) c
on a.gno=c.gno
where a.gno=10
		 */
		return detail;
	}
	public ArrayList<GroundDTO> getGdetailtoMatch() {//매치 개설할때 경기장이름 셀렉박스 할라고
		ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select gname, gno from groundinfo order by gname");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroundDTO tmp = new GroundDTO();
				tmp.setGno(rset.getInt("gno"));
				tmp.setGname(rset.getString("gname"));
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
	public int addGlike(GroundDTO dto) {//list의 전체 갯수 출력
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update groundinfo set glike=glike+1 where gno=?");
			pstmt.setInt(1, dto.getGno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int getGlike(GroundDTO dto) {//glike 얻기
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select glike from groundinfo where gno=?");
			pstmt.setInt(1, dto.getGno());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("glike");
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
}//end GroundDAO
