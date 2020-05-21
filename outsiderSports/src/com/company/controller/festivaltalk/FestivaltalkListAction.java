package com.company.controller.festivaltalk;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.FestivalDAO;
import com.company.inter.Proceed;

public class FestivaltalkListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		FestivalDAO dao = new FestivalDAO();
		request.setAttribute("list", dao.listAll());
		request.setAttribute("date", new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime()));
	}

}
