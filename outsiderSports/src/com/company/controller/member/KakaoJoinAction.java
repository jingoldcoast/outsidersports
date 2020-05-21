package com.company.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class KakaoJoinAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		dto.setOid(request.getParameter("oid"));
		dto.setOname(request.getParameter("oname"));
		dto.setOcontact(request.getParameter("ocontact"));
		dto.setOpass("kakaologin"); //카카오로그인이란거 표시
		dto.setObirth(request.getParameter("obirth"));
		
		String osex = request.getParameter("osex");
		if(osex.equals("s1")) { osex="m"; }
		else if(osex.equals("s2")) { osex="f"; }
		dto.setOsex(osex);
		
		request.setAttribute("result", dao.joinMember(dto));
		request.getSession().setAttribute("oid", dto.getOid());
		request.setAttribute("what", "회원가입을 축하드립니다!");

	}

}
