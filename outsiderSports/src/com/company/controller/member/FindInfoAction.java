package com.company.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class FindInfoAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		
		if(request.getParameter("find").equals("id")) {
			dto.setOcontact(request.getParameter("ocontact"));
			request.setAttribute("result", dao.findId(dto));
		}
		else if (request.getParameter("find").equals("pass")) {
			dto.setOid(request.getParameter("oid"));
			request.setAttribute("result", dao.findPass(dto));
		}
		
	}

}
