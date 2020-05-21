package com.company.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchOmemberDAO;
import com.company.inter.Proceed;

public class MyMatchAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//System.out.println("mymatchAction 오긴와?");
		
		MatchOmemberDAO dao = new MatchOmemberDAO();
		
		
		//select a.mtitle, a.mdate, b.gcategory, b.gaddr1, a.mno  
		//           0         1           2            3          4
		//from matchinfo a, groundinfo b where a.gno = b.gno and mno in (select mno from  matchInfo_omember where oid=?
		
		
		request.setAttribute("list", dao.getMyMatch(request.getSession().getAttribute("oid")));
		request.setAttribute("cnt", dao.reserveCnt(request.getSession().getAttribute("oid")));
		
		
	}

}
