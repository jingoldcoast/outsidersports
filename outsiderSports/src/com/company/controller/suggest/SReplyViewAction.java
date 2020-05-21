package com.company.controller.suggest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.SuggestDAO;
import com.company.dto.SuggestDTO;
import com.company.inter.Proceed;

public class SReplyViewAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		SuggestDAO dao = new SuggestDAO();
		SuggestDTO dto = new SuggestDTO();
		dto.setSno(Integer.parseInt(request.getParameter("sno")));
		
		request.setAttribute("dto", dao.reply_detail(dto));
	}

}
