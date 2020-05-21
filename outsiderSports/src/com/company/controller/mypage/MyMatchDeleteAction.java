package com.company.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchOmemberDAO;
import com.company.dao.MemberDAO;
import com.company.dao.TotalpointDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class MyMatchDeleteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		dto.setOid((String)request.getSession().getAttribute("oid"));
		dto.setOpoint(Integer.parseInt(request.getParameter("mprice")));
		
		
		int r = dao.addPoint(dto);
		if(r>0) {
			MatchOmemberDAO mdao = new MatchOmemberDAO();
			int rr = mdao.deleteMatch(dto.getOid(), Integer.parseInt(request.getParameter("mno")));
			TotalpointDAO tdao = new TotalpointDAO();
			int rrr = tdao.deletePoint(dto.getOid(), dto.getOpoint());
			if(rr>0 && rrr>0) {
				request.setAttribute("result", rr);
				request.setAttribute("what", "경기예약을 취소하였습니다!");
			}else {
				request.setAttribute("result", -1);
			}
		}else {
			request.setAttribute("result", -1);
		}
		

	}

}
