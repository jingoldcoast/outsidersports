package com.company.controller.anytalk;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.AnytalkDAO;
import com.company.dto.AnytalkDTO;
import com.company.inter.Proceed;
import com.google.gson.*;

public class AnytalkEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		AnytalkDAO dao = new AnytalkDAO();
		AnytalkDTO dto = new AnytalkDTO();
		dto.setAcomment(request.getParameter("acomment"));
		dto.setApass(request.getParameter("apass"));
		
		int checkPass = dao.checkTalkPass(dto);
		
		PrintWriter out = response.getWriter();
		if(checkPass==1) {
			dto.setAno(dao.getAno(dto));
			dao.editTalk(dto);
			
			Gson  gson = new Gson();
			JsonObject job = new JsonObject();
			job.addProperty("check", 1);
			job.addProperty("ano", dto.getAno());
			job.addProperty("acomment", dto.getAcomment());
			String json = gson.toJson(job);
			out.print(json);
		}else {
			Gson  gson = new Gson();
			JsonObject job = new JsonObject();
			job.addProperty("check", 1);
			String json = gson.toJson(job);
			out.print(json);
		}
		//System.out.println(dto);
		
	}//end execute
}//end class
