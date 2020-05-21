package com.company.controller.index;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MainDAO;
import com.company.inter.Proceed;

public class IndexAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())); //필터검색할때 date min 쓰려고
		
		MainDAO dao = new MainDAO();
		
		//Best경기장 평점순
		request.setAttribute("bestground", dao.bestGround());
		
		//인기 경기장 좋아요순
		request.setAttribute("popularground", dao.popularGround());
		
		//신규경기장 gdate순
		request.setAttribute("newground", dao.newGround());
		
		//새로개설된경기
		request.setAttribute("newmatch", dao.newMatch());
		
		
		
		String c = request.getHeader("Cookie");
		String dont = null;
		if(c!=null) {
			Cookie[] cookies = request.getCookies();
			for(int i =0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("dont")) {
					dont = "dont";
					break;
				}
			}
		}//end if
		request.setAttribute("dont", dont);
		
	}

}
