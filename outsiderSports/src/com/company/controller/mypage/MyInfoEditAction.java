package com.company.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class MyInfoEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setOid(request.getParameter("oid"));
		dto.setOname(request.getParameter("oname"));
		dto.setOcontact(request.getParameter("ocontact"));
		
		request.setAttribute("result", dao.editInfo(dto));
		request.setAttribute("what", "정보수정이 완료되었습니다!");
		
	}

}
