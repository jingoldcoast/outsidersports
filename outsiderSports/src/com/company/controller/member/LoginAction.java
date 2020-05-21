package com.company.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class LoginAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		//로그인할때 일단 쿠키 지우기
		String cookie = request.getHeader("Cookie");
		if(cookie!=null) {
			Cookie[] cookies = request.getCookies();
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("oid")) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
				if(cookies[i].getName().equals("remember")) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
		
		dto.setOid(request.getParameter("oid"));
		dto.setOpass(request.getParameter("opass"));
		
		int r = dao.checkPass(dto); //로그인정보 일치여부
		
		if(r==1) {//로그인 정보가 일치하다면
			request.getSession().setAttribute("oid", dto.getOid());
			request.setAttribute("what", "로그인 성공!\n반갑습니다!");
			
			//정보 일치한 다음에, 아이디저장하기 체크했는지 확인하기
			String remember = request.getParameter("remember");
			if(remember != null) {//아이디 저장하기를 체크했다면
				Cookie cookieOid = new Cookie("oid", dto.getOid());
				Cookie cookieRemember = new Cookie("remember", "remember");
				cookieOid.setMaxAge(86400*7);//7일동안 쿠키에 저장
				response.addCookie(cookieOid);
				cookieRemember.setMaxAge(86400*7);//7일동안 쿠키에 저장
				response.addCookie(cookieRemember);
			}
		}else {//이거지금 jsp에서 안먹음 ㅠㅠ 왜인지 모르겠음.. 그래서 그냥 자바스크립트에서 메세지 띄움 (member/mlogin.jsp 확인필요)
			request.setAttribute("what", "로그인 정보가 일치하지않습니다.\n다시 시도해주세요!");
		}
		request.setAttribute("result", r);
		
	}

}
