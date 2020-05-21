package com.company.controller.index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.inter.Proceed;

public class EventDontAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		Cookie cookie = new Cookie("dont", "dont");
		cookie.setMaxAge(1*60*60*24); //1초 - 1분에 60초 - 1시간에 60분 - 1일에 24시간
		response.addCookie(cookie);
		
	}

}
