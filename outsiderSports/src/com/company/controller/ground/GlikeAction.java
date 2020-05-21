package com.company.controller.ground;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dto.GroundDTO;
import com.company.inter.Proceed;
import com.google.gson.*;

public class GlikeAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		GroundDTO dto = new GroundDTO();
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		
		GroundDAO dao = new GroundDAO();
		
		int r = dao.addGlike(dto);
		PrintWriter out = response.getWriter();
		if(r>0) {
			int glike = dao.getGlike(dto);
			Gson gson = new Gson();
			JsonObject job = new JsonObject();
			job.addProperty("glike", glike);
			String json = gson.toJson(job);
			out.print(json);
		}
		else {
			out.print("{\"glike\" : -1}");
		}
	
		
		
	}

}
