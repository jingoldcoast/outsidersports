package com.company.reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchOmemberDAO;
import com.company.dao.MemberDAO;
import com.company.dao.TotalpointDAO;
import com.company.dto.MatchDTO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;

public class MReserveAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		MatchDTO matchDto = new MatchDTO();
		
		MemberDTO odto = new MemberDTO();
		MemberDAO odao = new MemberDAO();
		
		MatchOmemberDAO dao = new MatchOmemberDAO(); //결제, matchinfo_omember에 삽입
		//aaa라는 사용자가 matchinfo mno=1인 경기를 예약할 때
		//insert into matchinfo_omember values (1, 'aaa');
		
		int mno = Integer.parseInt(request.getParameter("mno")); //매치번호
		String oid = request.getParameter("oid"); //예약자
		int mprice = Integer.parseInt(request.getParameter("mprice")); //경기비용
		
		
		int checkReserve = dao.checkReserve(oid, mno); //예약했는지 안했는지 볼라고
		if(checkReserve==0) { //예약하지 않았다면
			matchDto.setMno(mno);
			matchDto.setMprice(mprice);
			
			
			odto.setOid(oid);
			odto = odao.getMember(odto); //유저정보 담기
			int opoint = odto.getOpoint(); //유저가 가지고있는 포인트
				
				
				if(opoint>=mprice) {
					opoint -= mprice; //포인트에 경기비용 차감
					odto.setOpoint(opoint);
					int r = dao.setPoint(odto);
					TotalpointDAO tdao = new TotalpointDAO();
					int t = tdao.accumPoint(oid, mprice);
					
					
					if(r >0 && t>0) {//포인트가 정상적으로 차감되고 누적테이블에도 잘 들어갔다면
						request.setAttribute("result", dao.matchReserve(mno, oid));
						request.setAttribute("what", "경기 예약이 완료되었습니다!");
					}else {
						request.setAttribute("result", -1);
					}
				}
				else {//포인트부족하면
					request.setAttribute("result", 0);
					request.setAttribute("what", "포인트가 부족합니다! 충전해주세요!");
				}
		}else {
			request.setAttribute("result", 1);
			request.setAttribute("what", "이미 예약하신 경기입니다!");
		}
		
	}//end execute

}//end class
