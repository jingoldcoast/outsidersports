package com.company.controller.ground;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dao.ReviewDAO;
import com.company.dto.GroundDTO;
import com.company.dto.ReviewDTO;
import com.company.inter.Proceed;

public class GDetailAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		GroundDTO dto = new GroundDTO();
		GroundDAO dao = new GroundDAO();
		
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		
		dto = dao.detail(dto);
		
		request.setAttribute("dto", dto);
		request.setAttribute("img", dto.getGfile().split("/")); //이미지 짜를라고
		
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewDTO> rlist = new ArrayList<>();
		rlist=rdao.listAll(dto.getGno());
		if(rlist.size()>0) {
			request.setAttribute("rlist", rlist);
			request.setAttribute("no", rdao.listAllCnt(dto.getGno()));
			request.setAttribute("crcn", "\r\n");
			request.setAttribute("br", "<br/>");
		}
		
	}

}
