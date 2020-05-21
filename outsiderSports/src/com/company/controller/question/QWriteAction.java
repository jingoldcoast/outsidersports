package com.company.controller.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = new QuestionDTO();
		
		String a =request.getParameter("qcategory"); //카테고리
		String b=request.getParameter("qcase"); //케이스


		/*
		 * System.out.println(a); System.out.println(b)
		 */;
		
		if(a.equals("01")) { a = "축구"; }
		else if(a.equals("02")) { a = "농구"; }
		else { a="카테고리 값 에러"; }

		if(b.equals("01")) { b = "참여/예약"; }
		else if(b.equals("02") ) { b = "개설"; }
		else if(b.equals("03") ) { b = "결제"; }
		else if(b.equals("04") ) { b = "기타"; }
		else { b="케이스 값 에러"; }

		dto.setQcategory(a);
		dto.setQcase(b);
		dto.setQtitle(request.getParameter("qtitle"));
		dto.setQname(request.getParameter("qname"));
		dto.setQpass(request.getParameter("qpass"));
		dto.setQcontent(request.getParameter("qcontent"));
		dto.setQemail(request.getParameter("qemail1")+"@"+request.getParameter("qemail2"));
		
		
		/*
		 * System.out.println(request.getParameter("qcategory") ); System.out.println(
		 * request.getParameter("qcase") ); System.out.println(
		 * request.getParameter("qtitle") ); System.out.println(
		 * request.getParameter("qname") ); System.out.println(
		 * request.getParameter("qcontent") ); System.out.println(
		 * request.getParameter("qemail1")+"@"+request.getParameter("qemail2") );
		 */
		
		
		request.setAttribute("result", dao.qwrite(dto));
		request.setAttribute("what", "문의하기에 성공했습니다!");
	}

}
