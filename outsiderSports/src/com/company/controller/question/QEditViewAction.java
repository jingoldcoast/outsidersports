package com.company.controller.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QEditViewAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		
		dto = dao.detail(dto);
		
		String content = dto.getQcontent();
		content = content.replaceAll("<br/>", "\n");
		dto.setQcontent(content);
		
		request.setAttribute("dto", dto);
		request.setAttribute("qemail1", dto.getQemail().substring(0, dto.getQemail().indexOf("@")));
		request.setAttribute("qemail2", dto.getQemail().substring(dto.getQemail().indexOf("@")+1));
	
		String a = dto.getQcategory();
		if(a.equals("축구")) {a="01";}
		else if(a.equals("농구")) {a="02";}
		
		String b = dto.getQcase();

		if(b.equals("참여/예약")) {b="01";}
		else if(b.equals("개설")) {b="02";}
		else if(b.equals("결제")) {b="03";}
		else if(b.equals("기타")) {b="04";}
		
		request.setAttribute("qcategory", a);
		request.setAttribute("qcase", b);
		
	}

}
