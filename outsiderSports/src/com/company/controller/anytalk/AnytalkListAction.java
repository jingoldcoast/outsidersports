package com.company.controller.anytalk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.AnytalkDAO;
import com.company.inter.Proceed;

public class AnytalkListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		AnytalkDAO dao = new AnytalkDAO();
		request.setAttribute("list", dao.listAll());
		
	}

}
