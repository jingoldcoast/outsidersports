package com.company.controller.ground;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.inter.Proceed;

public class GEditSelectAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		GroundDAO dao = new GroundDAO();
		request.setAttribute("list", dao.getGdetailtoMatch()); //gno랑 gname밖에없음
		
	}

}
