package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.GroundDTO;
import com.company.dto.MatchDTO;

public class MainDAO {
		public ArrayList<GroundDTO> bestGround(){//best경기장 평점순 10개
			ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno order by c.gstar desc, a.gname asc limit 0, 10");

				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroundDTO tmp = new GroundDTO();
					tmp.setGno(rset.getInt("gno"));
					tmp.setGname(rset.getString("gname"));
					tmp.setGregion(rset.getString("gregion"));
					tmp.setGzipcode(rset.getString("gzipcode"));
					tmp.setGaddr1(rset.getString("gaddr1"));
					tmp.setGaddr2(rset.getString("gaddr2"));
					String gfile = rset.getString("gfile");
					tmp.setGfile(gfile.split("/")[0]); //메인에는 첫번째사진만 올려놓을거니깐
					tmp.setGside(rset.getInt("gside"));
					tmp.setGwriter(rset.getString("gwriter"));
					tmp.setGdate(rset.getString("gdate"));
					tmp.setGcategory(rset.getString("gcategory"));
					tmp.setGdesc(rset.getString("gdesc"));
					/*
					tmp.setParking(rset.getString("parking"));
					tmp.setLight(rset.getString("light"));
					tmp.setIo(rset.getString("io"));
					tmp.setShower(rset.getString("shower"));
					tmp.setAir(rset.getString("air"));
					tmp.setBall(rset.getString("ball"));
					tmp.setVest(rset.getString("vest"));
					tmp.setShoes(rset.getString("shoes"));
					*/
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
		public ArrayList<GroundDTO> popularGround(){//인기경기장
			ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno order by a.glike desc, a.gname asc  limit 0, 12");
				
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
					String gfile = rset.getString("gfile");
					tmp.setGfile(gfile.split("/")[0]); //메인에는 첫번째사진만 올려놓을거니깐
					tmp.setGside(rset.getInt("gside"));
					tmp.setGwriter(rset.getString("gwriter"));
					tmp.setGdate(rset.getString("gdate"));
					tmp.setGcategory(rset.getString("gcategory"));
					tmp.setGdesc(rset.getString("gdesc"));
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
		public ArrayList<GroundDTO> newGround(){
			ArrayList<GroundDTO> list = new ArrayList<GroundDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select a.*, b.*, c.gstar from groundinfo a left join convinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno = c.gno order by a.gdate desc, a.gname asc  limit 0, 15");
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroundDTO tmp = new GroundDTO();
					tmp.setGno(rset.getInt("gno"));
					tmp.setGname(rset.getString("gname"));
					tmp.setGregion(rset.getString("gregion"));
					tmp.setGzipcode(rset.getString("gzipcode"));
					tmp.setGaddr1(rset.getString("gaddr1"));
					tmp.setGaddr2(rset.getString("gaddr2"));
					String gfile = rset.getString("gfile");
					tmp.setGfile(gfile.split("/")[0]); //메인에는 첫번째사진만 올려놓을거니깐
					tmp.setGside(rset.getInt("gside"));
					tmp.setGwriter(rset.getString("gwriter"));
					tmp.setGdate(rset.getString("gdate"));
					tmp.setGcategory(rset.getString("gcategory"));
					tmp.setGdesc(rset.getString("gdesc"));
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
		public ArrayList<MatchDTO> newMatch() {//새로 개설된 경기
			ArrayList<MatchDTO> list =  new ArrayList<MatchDTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement("select a.*, b.gfile, b.gcategory, c.gstar, d.cnt from matchinfo a join groundinfo b on a.gno=b.gno left join (select gno, avg(gstar) as gstar from review group by gno) c on a.gno=c.gno left join (select mno, count(*) as cnt from matchinfo_omember group by mno) d on a.mno = d.mno order by a.mcreatedate desc, a.mtitle asc limit 0, 10");
				
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
}//end class
