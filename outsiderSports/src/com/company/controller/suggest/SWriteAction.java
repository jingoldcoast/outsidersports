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

public class SWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		SuggestDAO dao = new SuggestDAO();
		SuggestDTO dto = new SuggestDTO();
		
		try {
			String path = "/upload/";
			path = request.getServletContext().getRealPath(path); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			String file = multi.getFilesystemName("sfile");
			//if(file==null) {System.out.println("파일업로드 안되었음");}
			dto.setSfile(file);
			
			dto.setSname(multi.getParameter("sname"));
			dto.setSpass(multi.getParameter("spass"));
			dto.setStitle(multi.getParameter("stitle"));
			dto.setScontent(multi.getParameter("scontent"));
			int sstepMax = dao.sstepMax();
			int sgroup = (int)(Math.ceil(sstepMax/(float)1000)) +1;
			int sstep = sgroup*1000;
			
			dto.setSgroup(sgroup);
			dto.setSstep(sstep);
			
		}catch (Exception e) {e.printStackTrace(); }
		
		
		request.setAttribute("result", dao.write(dto));
		request.setAttribute("what", "건의하기에 성공했습니다!");
		
	}

}
