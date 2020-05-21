package com.company.controller.match;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dao.MatchDAO;
import com.company.dao.ReviewDAO;
import com.company.dto.GroundDTO;
import com.company.dto.MatchDTO;
import com.company.dto.ReviewDTO;
import com.company.inter.Proceed;

public class MDetailAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		MatchDAO dao = new MatchDAO();
		MatchDTO dto = new MatchDTO();
		dto.setMno(Integer.parseInt(request.getParameter("mno")));
		
		dto = dao.detail(dto);
		request.setAttribute("dto", dto); //매치정보

		request.setAttribute("list", dao.reserveList(dto)); //예약자 리스트
		
		GroundDTO gdto = new GroundDTO();
		GroundDAO gdao = new GroundDAO();
		gdto.setGno(dto.getGno());
		gdto = gdao.detail(gdto);
		request.setAttribute("ground", gdto); //경기장정보
		request.setAttribute("img", gdto.getGfile().split("/"));
		
		ReviewDAO rdao = new ReviewDAO();
		
		ArrayList<ReviewDTO> rlist = new ArrayList<>();
		rlist=rdao.listAll(dto.getMhost());
		if(rlist.size()>0) {
			request.setAttribute("rlist", rlist);
			request.setAttribute("no", rdao.listAllCnt(dto.getMhost()));
		}
	
	}

}
