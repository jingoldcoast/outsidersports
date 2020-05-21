package com.company.controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.InfoDAO;
import com.company.dto.InfoDTO;
import com.company.inter.Proceed;

public class NDeleteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		InfoDTO dto = new InfoDTO();
		InfoDAO dao = new InfoDAO();
		dto.setNtitle(request.getParameter("ntitle"));
		request.setAttribute("result", dao.delete(dto));
		request.setAttribute("what", "내용삭제에 성공했습니다!");
	}

}
