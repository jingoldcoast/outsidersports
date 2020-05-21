package com.company.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.ReviewDAO;
import com.company.dto.ReviewDTO;
import com.company.inter.Proceed;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = new ReviewDTO();
		dto.setRwriter((String)request.getSession().getAttribute("oid"));
		
		try {
			String path = "/upload/";
			path = request.getServletContext().getRealPath(path); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			String file = multi.getFilesystemName("rfile");
			
			dto.setRfile(file);
			dto.setHid(multi.getParameter("hid"));
			dto.setHstar(Integer.parseInt(multi.getParameter("hstar")));
			dto.setHcomment(multi.getParameter("hcomment"));
			dto.setGno(Integer.parseInt(multi.getParameter("gno")));
			dto.setGstar(Integer.parseInt(multi.getParameter("gstar")));
			dto.setGcomment(multi.getParameter("gcomment"));
			dto.setMno(Integer.parseInt(multi.getParameter("mno")));
			
			request.setAttribute("result", dao.write(dto));
			request.setAttribute("what", "후기작성이 완료되었습니다!");
			
		}catch (Exception e) {e.printStackTrace(); }
		
		
		
	}

}
