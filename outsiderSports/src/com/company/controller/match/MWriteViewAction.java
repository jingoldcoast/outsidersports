package com.company.controller.match;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.inter.Proceed;

public class MWriteViewAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		GroundDAO dao = new GroundDAO();
		request.setAttribute("list", dao.getGdetailtoMatch());
		
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
	}

}
