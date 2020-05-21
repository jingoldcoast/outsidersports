package com.company.controller.anytalk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.AnytalkDAO;
import com.company.dto.AnytalkDTO;
import com.company.inter.Proceed;

public class NotPapagoAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		AnytalkDTO dto = new AnytalkDTO();
        dto.setAcomment(request.getParameter("acomment"));
        dto.setApass(request.getParameter("apass"));
        AnytalkDAO dao = new AnytalkDAO();
        PrintWriter out = response.getWriter();
        if(dao.addTalk(dto)>0) {
        	out.print(dto.getAcomment());
        }else {
        	out.print("에러발생");
        }
        
	}

}
