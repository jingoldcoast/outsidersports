package com.company.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class SPointChargeAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setOid(request.getParameter("oid"));
		try {
			dto.setOpoint(Integer.parseInt(request.getParameter("opoint")));
			request.setAttribute("result", dao.addPoint(dto));
			request.setAttribute("what", "고맙습니다!\n포인트 충전이 완료되었습니다!");
		}catch (Exception e) {
			request.setAttribute("result", 1);
			request.setAttribute("what", "포인트는 숫자만 입력해주세요!");
		}
		
	}

}
