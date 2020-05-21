package com.company.controller.suggest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.SuggestDAO;
import com.company.dto.SuggestDTO;
import com.company.inter.Proceed;

public class SDeleteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		SuggestDAO dao = new SuggestDAO();
		SuggestDTO dto = new SuggestDTO();
		
		dto.setSno(Integer.parseInt(request.getParameter("sno")));
		dto.setSpass(request.getParameter("spass"));
		
		if(dao.checkPass(dto)==1) {
			request.setAttribute("result", dao.delete(dto));
			request.setAttribute("what", "삭제에 성공했습니다.");
		}
		else {
			request.setAttribute("result", 0);
		}
		
	}

}
