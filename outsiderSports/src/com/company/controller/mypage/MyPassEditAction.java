package com.company.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class MyPassEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setOid(request.getParameter("oid"));
		dto.setOpass(request.getParameter("opass"));
		
		if(dao.checkPass(dto)==1) {
			dto.setOpass(request.getParameter("newopass1"));
			request.setAttribute("result", dao.editPass(dto));
			request.setAttribute("what", "비밀번호 수정이 완료되었습니다!");
		}else {
			request.setAttribute("result", 0);
			request.setAttribute("what", "기존 비밀번호를 다시 한 번 확인해주세요!");
		}
		
	}

}
