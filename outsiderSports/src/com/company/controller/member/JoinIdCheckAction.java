package com.company.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class JoinIdCheckAction implements Proceed {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//System.out.println(request.getParameter("oid"));
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		PrintWriter out = response.getWriter();
		
		dto.setOid(request.getParameter("oid"));
		
		int result = dao.checkId(dto);
		if(result==0) {
			out.print("0"); //사용가능한아이디
		}
		else if(result>0) {
			out.print("1"); //중복아이디있음
		}
		else {
			out.print("-1"); //어딘가모르게 에러남
		}
	}

}//end class
