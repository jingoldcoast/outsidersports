package com.company.controller.question;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.QuestionDAO;
import com.company.dto.PagingDTO;
import com.company.dto.QuestionDTO;
import com.company.inter.Proceed;

public class QListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		QuestionDAO dao = new QuestionDAO();
		
		int pageTotal = dao.listTotal();
		int onepagelimit = 15;
		int pageAll = (int) Math.ceil(pageTotal/(float)onepagelimit);
		
		int page = 0;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<QuestionDTO> list = new ArrayList<>();
		list = dao.listAll(page);
		
		int bottomlist = 10;
		
		int current_bottom = (int)Math.ceil((page+1)/(float)onepagelimit);
		
		int pre_bottom = ((int)Math.floor((current_bottom -1)/bottomlist)*bottomlist) + 1;
		
		
		int next_bottom = pre_bottom + bottomlist -1;
		if(pageAll < next_bottom) {
			next_bottom = pageAll;
		}
		
		PagingDTO dto = new PagingDTO(pageTotal, onepagelimit, pageAll, page, bottomlist, pre_bottom, next_bottom, current_bottom);
		request.setAttribute("paging", dto);
		request.setAttribute("list", list);
	}

}

/*
 * for(QuestionDTO dto: list) { System.out.println(dto); }
 */