package com.company.controller.match;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchDAO;
import com.company.dto.MatchDTO;
import com.company.inter.Proceed;

public class MWriteAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		try {
		MatchDTO dto = new MatchDTO();
		MatchDAO dao = new MatchDAO();
		String mdate = request.getParameter("mdate"); //요일도 바꿔줄라고 넣어둠 현재 yyyy-MM-dd 형식임 - EEE로 바꿔야함
		//System.out.println("여기는 MWriteAction");
		//System.out.println(mdate);
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		Date mdateToDate = f1.parse(mdate);
		
		SimpleDateFormat f2 = new SimpleDateFormat("EEE");
		String mday = f2.format(mdateToDate);
		
		dto.setMtitle(request.getParameter("mtitle"));
		dto.setMhost(request.getParameter("mhost"));
		dto.setMdate(mdate);
		dto.setMhour(Integer.parseInt(request.getParameter("mhour").substring(0, 2)));
		dto.setMduration(Integer.parseInt(request.getParameter("mduration")));
		dto.setMprice(Integer.parseInt(request.getParameter("mprice")));
		dto.setMtotal(Integer.parseInt(request.getParameter("mtotal")));
		dto.setMsex(request.getParameter("msex"));
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		dto.setMday(mday);
		
		//System.out.println(dto);
		
		request.setAttribute("result", dao.write(dto));
		request.setAttribute("what", "경기개설을 완료하였습니다!");
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", -1);
		}
		
		
		
		/*
		 create table matchinfo(
mno number primary key,
mtitle varchar2(250) not null,
mhost varchar2(250) not null,
msex char(2) not null, //남성여성믹스 매치 m/f/c
mday varchar(50) not null, 요일
mdate timestamp not null, 날짜
mhour number not null,
mduration number not null, 60/90/120 1시간/1시간반/2시간
mprice number not null,
mcreatedate timestamp default current_timestamp,
gno number, ##외래키
mtotal number not null //수용인원
constraint matchinfo_fk foreign key(gno) 
references groundinfo(gno)
);
		 */
		
	}

}
