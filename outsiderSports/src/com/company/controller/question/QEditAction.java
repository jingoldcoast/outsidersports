package com.company.controller.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		QuestionDTO dto = new QuestionDTO();
		QuestionDAO dao = new QuestionDAO();
		
		String a =request.getParameter("qcategory"); //카테고리
		String b=request.getParameter("qcase"); //케이스
		
		if(a.equals("01")) { a = "축구"; } //자바스크립트에서 강제로 select 하려고 값 바꿔줌 (qedit.jsp 확인)
		else if(a.equals("02")) { a = "농구"; }
		else { a="카테고리 값 에러"; }

		if(b.equals("01")) { b = "참여/예약"; }
		else if(b.equals("02") ) { b = "개설"; }
		else if(b.equals("03") ) { b = "결제"; }
		else if(b.equals("04") ) { b = "기타"; }
		else { b="케이스 값 에러"; }

		dto.setQno(Integer.parseInt(request.getParameter("qno")));
		dto.setQcategory(a);
		dto.setQcase(b);
		dto.setQtitle(request.getParameter("qtitle"));
		dto.setQname(request.getParameter("qname"));
		dto.setQpass(request.getParameter("qpass"));
		dto.setQcontent(request.getParameter("qcontent"));
		dto.setQemail(request.getParameter("qemail1")+"@"+request.getParameter("qemail2"));
		
		request.setAttribute("result", dao.edit(dto));
		request.setAttribute("what", "수정에 성공했습니다.");
	}

}
