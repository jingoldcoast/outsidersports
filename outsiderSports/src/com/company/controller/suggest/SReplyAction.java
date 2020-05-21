package com.company.controller.suggest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.SuggestDAO;
import com.company.dto.SuggestDTO;
import com.company.inter.Proceed;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class SReplyAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		SuggestDAO dao = new SuggestDAO();
		SuggestDTO dto = new SuggestDTO();
		
		try {
		     String path = "/upload/";
			 path = request.getServletContext().getRealPath(path); //////////////////////★★★★★★★★★★
			 MultipartRequest multi = new MultipartRequest(request , path, 1024*1024*5,"utf-8", new DefaultFileRenamePolicy());
			 String sfile = multi.getFilesystemName("sfile");  
			 
			 	dto.setSno(Integer.parseInt(multi.getParameter("sno")));
				dto.setSgroup(Integer.parseInt(multi.getParameter("sgroup")));
				dto.setSstep(Integer.parseInt(multi.getParameter("sstep")));
				dto.setSindent(Integer.parseInt(multi.getParameter("sindent")));
				dto.setSname(multi.getParameter("sname"));
				dto.setSpass(multi.getParameter("spass"));
				dto.setStitle(multi.getParameter("stitle"));
				dto.setScontent(multi.getParameter("scontent"));
				dto.setSfile(sfile);
				
				if(dao.reply1(dto)>-1) {
						request.setAttribute("result", dao.reply2(dto));
						request.setAttribute("what", "답글쓰기를 완료하였습니다!");
					}
				else {
					request.setAttribute("result", -1);
				}
			
			}catch (Exception e) { e.printStackTrace(); }
	}

}
