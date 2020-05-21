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

public class SEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		SuggestDAO dao = new SuggestDAO();
		SuggestDTO dto = new SuggestDTO();
		
		 String path = "/upload/";
		  path = request.getServletContext().getRealPath(path); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		  MultipartRequest multi = new MultipartRequest(request , path, 1024*1024*5,"utf-8", new DefaultFileRenamePolicy());
		  String sfile = multi.getFilesystemName("sfile");  
		  
		  dto.setSno(Integer.parseInt(multi.getParameter("sno")));
		  dto.setSpass(multi.getParameter("spass"));
		  dto.setStitle(multi.getParameter("stitle"));
		  dto.setScontent(multi.getParameter("scontent"));
		  
		  if(sfile == null) {
		   dto.setSfile(multi.getParameter("sfilebefore"));
		  } else {
		   dto.setSfile(sfile);
		  }
		  
		  if (dao.checkPass(dto) == 1) {
			  request.setAttribute("result", dao.edit(dto));
				request.setAttribute("what", "수정이 완료되었습니다!");
		  }
		  else {
				request.setAttribute("result", 0);
			}
		
		
		
	}

}
