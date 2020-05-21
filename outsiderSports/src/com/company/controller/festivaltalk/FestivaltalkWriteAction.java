package com.company.controller.festivaltalk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.FestivalDAO;
import com.company.dto.FestivaltalkDTO;
import com.company.inter.Proceed;

public class FestivaltalkWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		FestivalDAO dao = new FestivalDAO();
		FestivaltalkDTO dto = new FestivaltalkDTO();
		dto.setFcontent(request.getParameter("fcontent"));
		dto.setFpass(request.getParameter("fpass"));
		
		int r = dao.add(dto);
		if(r==1) {
			PrintWriter out = response.getWriter();
			out.print(dao.fnoMax());
		}
	}

}
