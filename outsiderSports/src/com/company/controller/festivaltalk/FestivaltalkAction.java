package com.company.controller.festivaltalk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.FestivalDAO;
import com.company.dto.FestivaltalkDTO;
import com.company.inter.Proceed;

public class FestivaltalkAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		FestivalDAO dao = new FestivalDAO();
		FestivaltalkDTO dto = new FestivaltalkDTO();
		
		dto.setFno(Integer.parseInt(request.getParameter("fno")));
		dto.setFcontent(request.getParameter("fcontent"));
		dto.setFpass(request.getParameter("fpass"));

		//System.out.println(dto);
		
		int check = dao.checkpass(dto);
		System.out.println(check);
		
		PrintWriter out = response.getWriter();
		if (check==1) {
			char what = request.getParameter("what").charAt(0);
			int rr=-1;
			if(what=='e') {
				rr = dao.edit(dto);
			}else {
				rr = dao.delete(dto);
			}
			if(rr==1) { out.print("success"); }
			else { out.print("failure"); }
			
		}else {
			out.print("failure");
		}
		
	}

}
