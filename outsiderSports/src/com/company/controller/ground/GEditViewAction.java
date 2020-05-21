package com.company.controller.ground;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dto.GroundDTO;
import com.company.inter.Proceed;

public class GEditViewAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		GroundDTO dto = new GroundDTO();
		GroundDAO dao = new GroundDAO();
		
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		
		dto = dao.detail(dto);
		
		request.setAttribute("dto", dto);

	}

}
