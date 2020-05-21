package com.company.controller.question;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QReplyWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		
		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		dto.setQreplytitle(request.getParameter("qreplytitle"));
		dto.setQreplyname(request.getParameter("qreplyname"));
		dto.setQreplypass(request.getParameter("qreplypass"));
		dto.setQreplycontent(request.getParameter("qreplycontent"));
		dto.setQreplydate(new SimpleDateFormat("yyyy-mm-dd").format(Calendar.getInstance().getTime()));
		
		request.setAttribute("what", "답변 달기 성공!");
		request.setAttribute("result", dao.qreplywrite(dto));
		

	}

}
