package com.company.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchOmemberDAO;
import com.company.inter.Proceed;

public class MyHostAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MatchOmemberDAO dao = new MatchOmemberDAO();
		
		Object oid = request.getSession().getAttribute("oid");
		request.setAttribute("list", dao.getMyHost(oid));
		request.setAttribute("cnt", dao.hostCnt(oid));
		request.setAttribute("myhstar", dao.myHstar(oid));
		
		
		
	}

}
