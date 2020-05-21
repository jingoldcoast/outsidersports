package com.company.controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.InfoDAO;
import com.company.inter.Proceed;

public class NListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		InfoDAO dao = new InfoDAO();

		request.setAttribute("list", dao.listAll("n1"));
		

	}

}
