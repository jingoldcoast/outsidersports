package com.company.controller.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QDetailAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		
		if(dao.addHit(dto)<0) {request.setAttribute("result", -1);}
		request.setAttribute("dto", dao.detail(dto));
	}

}
