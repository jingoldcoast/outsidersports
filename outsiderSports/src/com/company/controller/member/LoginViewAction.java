package com.company.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class LoginViewAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDTO dto = new MemberDTO();
		
		String cookie = request.getHeader("Cookie");
		if(cookie != null) {
			Cookie[] cookies = request.getCookies();
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("oid")) {
					dto.setOid(cookies[i].getValue());
					request.setAttribute("dto", dto);
				}
				if(cookies[i].getName().equals("remember")) {
					request.setAttribute("remember", cookies[i].getValue());
				}
			}//end for
		}//end if
		
		
	}//end execute

}//end class
