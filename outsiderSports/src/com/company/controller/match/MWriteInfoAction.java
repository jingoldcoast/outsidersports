package com.company.controller.match;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dto.GroundDTO;
import com.company.inter.Proceed;

public class MWriteInfoAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		GroundDTO dto = new GroundDTO();
		GroundDAO dao = new GroundDAO();
		
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		
		dto = dao.detail(dto);
		//System.out.println("여긴 MWriteInfoAction입니다");
		//System.out.println(dto);
		/*
		mwrite - 매치개설할때 ajax로 jason형식 data 불러오기
		경기장 선택했을 때 경기장정보 저절로 밑에 뜨게하기
		JSON 형식>>
		[
		{"gname":"값1", "gregion":"값2", "gside":"값3"},  "key":"value"
		{"gname":"", "gregion":"", "gside":""},
		]
		[ {'gname':'', 'gregion':'', 'gside':''} ]
		*/
		
		PrintWriter out = response.getWriter();
		String result = "[";
		result += "{'gname':'"+dto.getGname()+"', 'gregion':'"+dto.getGregion()+"', 'gside':'"+dto.getGside()+"'}";
		result = result.replaceAll("'", "\"");
		result += "]";
		
		out.println(result);
		out.close();
		
		
	}

}
