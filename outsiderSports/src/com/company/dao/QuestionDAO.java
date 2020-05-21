package com.company.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.company.dbmanager.DBManager;
import com.company.dto.QuestionDTO;

public class QuestionDAO {

	public ArrayList<QuestionDTO> listAll(int page){//최신글 순으로 전체 list 출력 (list페이지에 띄울 data만 출력)
		ArrayList<QuestionDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			//pstmt = conn.prepareStatement("select * from (select rownum as rnum, A.* from (select a.qno, qhit, qcategory, qtitle, rpad(substr(qname, 0, 3), length(qname), '*') as qname, qdate, b.qreplydate, qstatus from question a join questionreply b on a.qno=b.qno order by a.qno desc) A where rownum <=? ) x where x.rnum > ?");
			pstmt = conn.prepareStatement("select a.*, concat(left(qname, 1), repeat('*', length(qname)-2), right(qname, 1) ) as qname2, b.* from question a, questionreply b where a.qno=b.qno order by a.qno desc limit ?, ?");
			
			//페이징전::
			//select a.qno, qhit, qcategory, qtitle, rpad(substr(qname, 0, 3), length(qname), '*') as qname, qdate, b.qreplydate, qstatus from question a join questionreply b on a.qno=b.qno order by a.qno desc
			
			//pstmt.setInt(1, page+15);
			//pstmt.setInt(2, page);
			
			pstmt.setInt(1, page);
			pstmt.setInt(2, 15);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QuestionDTO tmp = new QuestionDTO();
				tmp.setQno(rset.getInt("qno"));
				tmp.setQcategory(rset.getString("qcategory"));
				tmp.setQtitle(rset.getString("qtitle"));
				tmp.setQname(rset.getString("qname2"));
				tmp.setQdate(rset.getString("qdate"));
				tmp.setQreplydate(rset.getString("qreplydate"));
				tmp.setQstatus(rset.getString("qstatus"));
				tmp.setQhit(rset.getInt("qhit"));
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
			pstmt = conn.prepareStatement("select count(*) as total from question");
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
	public int qwrite(QuestionDTO dto) {//문의하기 글쓰기(question에 먼저쓰고, questionreply에도 data 넣어주기)
		int r = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		try {
			conn = db.getConnection();
			//pstmt = conn.prepareStatement("insert into question(qno, qcategory, qcase, qtitle, qname, qpass, qcontent, qemail, qip) values (seq_question.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement("insert into question(qcategory, qcase, qtitle, qname, qpass, qcontent, qemail, qip) values (?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getQcategory());
			pstmt.setString(2, dto.getQcase());
			pstmt.setString(3, dto.getQtitle());
			pstmt.setString(4, dto.getQname());
			pstmt.setString(5, dto.getQpass());
			pstmt.setString(6, dto.getQcontent());
			pstmt.setString(7, dto.getQemail());
			pstmt.setString(8, InetAddress.getLocalHost().getHostAddress());
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			
			int qno = -1;
			//questionreply에 값넣어줄 때 question에 있는 qno값 뽑아올라고
			pstmt=conn.prepareStatement("select qno from question where qcategory=? and qcase=? and qtitle=? and qname=? and qpass=? and qcontent=? and qemail=?");
			pstmt.setString(1, dto.getQcategory());
			pstmt.setString(2, dto.getQcase());
			pstmt.setString(3, dto.getQtitle());
			pstmt.setString(4, dto.getQname());
			pstmt.setString(5, dto.getQpass());
			pstmt.setString(6, dto.getQcontent());
			pstmt.setString(7, dto.getQemail());
			rset = pstmt.executeQuery();
			while(rset.next()) {
				qno = rset.getInt("qno");
			}
			pstmt.close();
			
			pstmt = conn.prepareStatement("insert into questionreply (qno) values (?)");
			pstmt.setInt(1, qno);
			
			r = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return r;
	}
	public QuestionDTO detail(QuestionDTO dto) {//상세보기
		QuestionDTO detail = new QuestionDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			//pstmt = conn.prepareStatement("select a.*, rpad(substr(a.qname, 0, 3), length(a.qname), '*') as qname2, b.* from question a join questionreply b on a.qno=b.qno where a.qno=?");
			pstmt = conn.prepareStatement("select a.*, concat(left(qname, 1), repeat('*', length(qname)-2), right(qname, 1) ) as qname2, b.* from question a, questionreply b where a.qno=b.qno and a.qno=?");
			pstmt.setInt(1, dto.getQno());
			rset= pstmt.executeQuery();
			while(rset.next()) {
				detail.setQno(rset.getInt("qno"));
				detail.setQcategory(rset.getString("qcategory"));
				detail.setQcase(rset.getString("qcase"));
				detail.setQdate(rset.getString("qdate"));
				detail.setQcategory(rset.getString("qcategory"));
				detail.setQhit(rset.getInt("qhit"));
				detail.setQstatus(rset.getString("qstatus"));
				detail.setQtitle(rset.getString("qtitle"));
				detail.setQname(rset.getString("qname2"));
				String a = rset.getString("qcontent");
				detail.setQcontent(a.replaceAll("\n", "<br/>"));
				detail.setQemail(rset.getString("qemail"));
				detail.setQpass(rset.getString("qpass"));
				try {
				detail.setQreplytitle(rset.getString("qreplytitle"));
				detail.setQreplydate(rset.getString("qreplydate"));
				detail.setQreplyname(rset.getString("qreplyname"));
				detail.setQreplypass(rset.getString("qreplypass"));
				detail.setQreplycontent(rset.getString("qreplycontent").replaceAll("\n", "<br/>"));
				}catch (Exception e) {//답변이 없으면 null에러 뜸....그래서 null 값이면 그냥 "" 값 넣었음
					detail.setQreplytitle("");
					detail.setQreplyname("");
					detail.setQreplypass("");
					detail.setQreplycontent("");
				}
			}
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return detail;
	}
	public int addHit(QuestionDTO dto) {//조회수 +1
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update question set qhit=qhit+1 where qno=?");
			pstmt.setInt(1, dto.getQno());

			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int checkPass(QuestionDTO dto) {//비밀번호 맞게 입력했는지
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select count(*) as check_pass from question where qno=? and qpass=?");
			pstmt.setInt(1, dto.getQno());
			pstmt.setString(2, dto.getQpass());
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
	public int edit(QuestionDTO dto) {//수정하기
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update question set qcategory=?, qcase=?, qtitle=?, qcontent=?, qemail=? where qno=?");
			pstmt.setString(1, dto.getQcategory());
			pstmt.setString(2, dto.getQcase());
			pstmt.setString(3, dto.getQtitle());
			pstmt.setString(4, dto.getQcontent());
			pstmt.setString(5, dto.getQemail());
			pstmt.setInt(6, dto.getQno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int delete(QuestionDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("delete from questionreply where qno=?");
			pstmt.setInt(1, dto.getQno());
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = conn.prepareStatement("delete from question where qno=?");
			pstmt.setInt(1, dto.getQno());
			result = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return result;
	}
	public int qreplywrite(QuestionDTO dto) {//문의하기 글쓰기(question에 먼저쓰고, questionreply에도 data 넣어주기)
		int r = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DBManager db = new DBManager();
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("update questionreply set qreplytitle=?, qreplyname=?, qreplypass=?, qreplycontent=?, qstatus=?, qreplydate=current_timestamp where qno=?");
			
			pstmt.setString(1, dto.getQreplytitle());
			pstmt.setString(2, dto.getQreplyname());
			pstmt.setString(3, dto.getQreplypass());
			pstmt.setString(4, dto.getQreplycontent());
			pstmt.setString(5, "답변완료");
			pstmt.setInt(6, dto.getQno());
			
			r = pstmt.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
		finally {
			if(rset!=null) {try{rset.close();}catch (Exception e) { e.printStackTrace(); }}
			if(pstmt!=null) {try{pstmt.close();}catch (Exception e) { e.printStackTrace(); }}
			if(conn!=null) {try{conn.close();}catch (Exception e) { e.printStackTrace(); }}
		}
		return r;
	}
	
	
}//end class
