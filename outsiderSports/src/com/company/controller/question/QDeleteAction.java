package com.company.controller.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QDeleteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		dto.setQpass(request.getParameter("qpass"));
		if(dao.checkPass(dto)==1) {
			request.setAttribute("result", dao.delete(dto));
			request.setAttribute("what", "삭제에 성공했습니다.");
		}
		else {
			request.setAttribute("result", 0);
		}
	}

}
