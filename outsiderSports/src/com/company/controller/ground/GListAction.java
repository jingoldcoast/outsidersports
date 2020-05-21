package com.company.controller.ground;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dto.GroundDTO;
import com.company.dto.PagingDTO;
import com.company.inter.Proceed;

public class GListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//총 2개의 경우가 있음
		//1) 헤더 경기장 클릭할때
		//   1-1) 그냥
		//   1-2) 00순 클릭할 때
		//2) 경기장 대관 클릭하고 경기장 이름 검색할때
		
		
		if(request.getParameter("gnamesearch")==null) {//경기장 검색 submit 아닐때
			GroundDAO dao = new GroundDAO();
			//list default order by a.gdate desc
			int pageTotal = dao.listTotal();
			int onepagelimit = 10;
			int pageAll = (int) Math.ceil(pageTotal/(float)onepagelimit);
			
			int page = 0;
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int bottomlist = 10;
			int current_bottom = (int)Math.ceil((page+1)/(float)onepagelimit);
			int pre_bottom = ((int)Math.floor((current_bottom -1)/bottomlist)*bottomlist) + 1;
			
			int next_bottom = pre_bottom + bottomlist -1;
			if(pageAll < next_bottom) {
				next_bottom = pageAll;
			}
			
			if(request.getParameter("find")==null) {//00순 파라미터값 없으면은
				ArrayList<GroundDTO> list = new ArrayList<>();
				list = dao.listAll(page);
				PagingDTO dto = new PagingDTO(pageTotal, onepagelimit, pageAll, page, bottomlist, pre_bottom, next_bottom, current_bottom);
				request.setAttribute("paging", dto);
				request.setAttribute("list", list);
			}else {
				String find = request.getParameter("find");
				String query = "";
				if(find.equals("gstar")) {
					query = "order by gstar desc, gname asc";
				}
				if(find.equals("glike")) {
					query = "order by glike desc, gname asc";
				}
				else if(find.equals("gdate")) {
					query = "order by gdate desc, gname asc";
				}
				else if(find.equals("gname")) {
					query = "order by gname asc";
				}
				ArrayList<GroundDTO> list = new ArrayList<>();
				list = dao.listAll(page, query);
				PagingDTO dto = new PagingDTO(pageTotal, onepagelimit, pageAll, page, bottomlist, pre_bottom, next_bottom, current_bottom);
				request.setAttribute("paging", dto);
				request.setAttribute("list", list);
				request.setAttribute("find", "find=" + find);
			}
		}//경기장 검색 submit 아닐때 end
		else {
			GroundDAO dao = new GroundDAO();
			request.setAttribute("list", dao.listAll(request.getParameter("gnamesearch")));
		}
		
		
		
	}//end execute
}//end class
