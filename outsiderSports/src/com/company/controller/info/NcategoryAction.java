package com.company.controller.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.InfoDAO;
import com.company.dto.InfoDTO;
import com.company.inter.Proceed;

public class NcategoryAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		InfoDTO dto = new InfoDTO();
		InfoDAO dao = new InfoDAO();
		
		String txt = "";
		dto.setNcategory(request.getParameter("ncategory"));
		txt = dao.getData(dto);
		
		PrintWriter out = response.getWriter();
		out.println(txt);
		out.close();
		
		
	}

}
