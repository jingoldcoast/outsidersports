package com.company.controller.match;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchDAO;
import com.company.dto.PagingDTO;
import com.company.inter.Proceed;

public class MListFilterAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		
		MatchDAO dao = new MatchDAO();
		
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
		PagingDTO dto = new PagingDTO(pageTotal, onepagelimit, pageAll, page, bottomlist, pre_bottom, next_bottom, current_bottom);
		request.setAttribute("paging", dto);
		
		String[] gcategory = request.getParameter("gcategory").split(",");
		String[] gregion = request.getParameter("gregion").split(",");
		String[] mday = request.getParameter("mday").split(",");
		String[] mduration = request.getParameter("mduration").split(",");
		String[] msex = request.getParameter("msex").split(",");
		String[] gstar = request.getParameter("gstar").split(",");
		String[] conv = request.getParameter("conv").split(",");
		String[] gside = request.getParameter("gside").split(",");
		String[] mprice = request.getParameter("mprice").split(",");
		String find = "";
		if(  !request.getParameter("find").equals("") ) {
			//find = request.getParameter("find").split("=")[1]; //find 파람에는 find=mprice1 예시로 값이 들어가있음
			find = request.getParameter("find");
		}
		//System.out.println(Arrays.toString(gcategory));
		//System.out.println("0번째>> " + gcategory[0]);
		//System.out.println(gcategory[0].equals(""));
		//System.out.println("길이>> "+gcategory.length); //값을 선택안하면 length는 1이고 값이 ""으로 들어온다
		
		String query = "where ";
		if(gcategory.length ==1 && (!gcategory[0].equals(""))  ) {
			if(gcategory[0].equals("01")) {
				query += ("b.gcategory = '축구' ");
			}else {
				query += ("b.gcategory = '농구' ");
			}
		}
		else {
			query += " (b.gcategory='축구' or b.gcategory='농구') ";
		}
		if( !gregion[0].equals("") ) {
			query += "and (b.gregion = ";
			if(gregion.length==1) {
				if( gregion[0].equals("01") ) { query += "'서울' "; }
				else if( gregion[0].equals("02") ) { query += "'경기' "; }
				else if( gregion[0].equals("03") ) { query += "'인천' "; }
				else if( gregion[0].equals("04") ) { query += "'부산' "; }
				else if( gregion[0].equals("05") ) { query += "'대구' "; }
				else if( gregion[0].equals("06") ) { query += "'광주' "; }
				else if( gregion[0].equals("07") ) { query += "'대전' "; }
				else if( gregion[0].equals("08") ) { query += "'제주특별자치도' "; }
				else if( gregion[0].equals("09") ) { query += "'울산' "; }
				else if( gregion[0].equals("10") ) { query += "'강원' "; }
				else if( gregion[0].equals("11") ) { query += "'충북' "; }
				else if( gregion[0].equals("12") ) { query += "'충남' "; }
				else if( gregion[0].equals("13") ) { query += "'전북' "; }
				else if( gregion[0].equals("14") ) { query += "'전남' "; }
				else if( gregion[0].equals("15") ) { query += "'경북' "; }
				else if( gregion[0].equals("16") ) { query += "'경남' "; }
				query+= ") ";
			}
			else {
				for(int i =0; i < gregion.length; i++) {
					if( gregion[i].equals("01") ) { query += "'서울' or b.gregion= "; }
					else if( gregion[i].equals("02") ) { query += "'경기' or b.gregion= "; }
					else if( gregion[i].equals("03") ) { query += "'인천' or b.gregion= "; }
					else if( gregion[i].equals("04") ) { query += "'부산' or b.gregion= "; }
					else if( gregion[i].equals("05") ) { query += "'대구' or b.gregion= "; }
					else if( gregion[i].equals("06") ) { query += "'광주' or b.gregion= "; }
					else if( gregion[i].equals("07") ) { query += "'대전' or b.gregion= "; }
					else if( gregion[i].equals("08") ) { query += "'제주특별자치도' or b.gregion= "; }
					else if( gregion[i].equals("09") ) { query += "'울산' or b.gregion= "; }
					else if( gregion[i].equals("10") ) { query += "'강원' or b.gregion= "; }
					else if( gregion[i].equals("11") ) { query += "'충북' or b.gregion= "; }
					else if( gregion[i].equals("12") ) { query += "'충남' or b.gregion= "; }
					else if( gregion[i].equals("13") ) { query += "'전북' or b.gregion= "; }
					else if( gregion[i].equals("14") ) { query += "'전남' or b.gregion= "; }
					else if( gregion[i].equals("15") ) { query += "'경북' or b.gregion= "; }
					else if( gregion[i].equals("16") ) { query += "'경남' or b.gregion= "; }
				}
				query = query.substring(0, query.length()-14);
				query+=") ";
			}
		}
		if ( !mday[0].equals("") ) {
			query += "and (a.mday = ";
			if(mday.length==1) {
				if( mday[0].equals("0") ) { query += "'일' "; }
				else if( mday[0].equals("1") ) { query += "'월' "; }
				else if( mday[0].equals("2") ) { query += "'화' "; }
				else if( mday[0].equals("3") ) { query += "'수' "; }
				else if( mday[0].equals("4") ) { query += "'목' "; }
				else if( mday[0].equals("5") ) { query += "'금' "; }
				else if( mday[0].equals("6") ) { query += "'토' "; }
				query+=") ";
			}
			else {
				for(int i =0; i < mday.length; i++) {
					if( mday[i].equals("0") ) { query += "'일' or a.mday= "; }
					else if( mday[i].equals("1") ) { query += "'월' or a.mday= "; }
					else if( mday[i].equals("2") ) { query += "'화' or a.mday= "; }
					else if( mday[i].equals("3") ) { query += "'수' or a.mday= "; }
					else if( mday[i].equals("4") ) { query += "'목' or a.mday= "; }
					else if( mday[i].equals("5") ) { query += "'금' or a.mday= "; }
					else if( mday[i].equals("6") ) { query += "'토' or a.mday= "; }
				}
				query = query.substring(0, query.length()-11);
				query+=") ";
			}
		}
		if ( !mprice[0].equals("") ) {
			query += "and (";
			for(int i =0; i < mprice.length; i++) {
				if( mprice[i].equals("4999") ) { query += "a.mprice < 5000 or "; }
				else if( mprice[i].equals("9999") ) { query += "a.mprice between 5000 and 9999 or "; }
				else if( mprice[i].equals("14999") ) { query += "a.mprice between 10000 and 14999 or "; }
				else if( mprice[i].equals("19999") ) { query += "a.mprice between 15000 and 19999 or "; }
				else if( mprice[i].equals("20000") ) { query += "a.mprice >= 20000 or "; }
			}
				query = query.substring(0, query.length()-4);
				query+=") ";
		}
		if ( !mduration[0].equals("") ) {
			query += "and (a.mduration = ";
			if(mday.length==1) {
				if( mduration[0].equals("0") ) { query += "60 "; }
				else if( mduration[0].equals("1") ) { query += "90 "; }
				else if( mduration[0].equals("2") ) { query += "120 "; }
				query+=") ";
			}
			else {
				for(int i =0; i < mduration.length; i++) {
					if( mduration[i].equals("0") ) { query += "60 or a.mduration= "; }
					else if( mduration[i].equals("1") ) { query += "90 or a.mduration= "; }
					else if( mduration[i].equals("2") ) { query += "120 or a.mduration= "; }
				}
				query = query.substring(0, query.length()-16);
				query+=") ";
			}
		}
		if ( !msex[0].equals("") ) {
			query += "and (a.msex = ";
			if(mday.length==1) {
				if( msex[0].equals("m") ) { query += "'m' "; }
				else if( msex[0].equals("f") ) { query += "'f' "; }
				else if( msex[0].equals("c") ) { query += "'c' "; }
				query+=") ";
			}
			else {
				for(int i =0; i < msex.length; i++) {
					if( msex[i].equals("0") ) { query += "'m' or a.msex= "; }
					else if( msex[i].equals("1") ) { query += "'f' or a.msex= "; }
					else if( msex[i].equals("2") ) { query += "'c' or a.msex= "; }
				}
				query = query.substring(0, query.length()-11);
				query+=") ";
			}
		}
		if ( !gstar[0].equals("") ) {
			query += "and (gstar <= ";
			int gstarMax = -1;
			for(int i =0; i < gstar.length; i++) {
				if(gstarMax<= Integer.parseInt(gstar[i])) {
					gstarMax = Integer.parseInt(gstar[i]);
				}
			}
			query += gstarMax + " ";
			query+=") ";
		}
		if ( !conv[0].equals("") ) {
			query += " and (";
			for(int i = 0; i < conv.length; i++) {
				if( conv[i].equals("parking") ) { query += "parking='y' and "; }
				else if( conv[i].equals("light") ) { query += "light='y' and "; }
				else if( conv[i].equals("in") ) { query += "io='y' and "; }
				else if( conv[i].equals("out") ) { query += "io='n' and "; }
				else if( conv[i].equals("shower") ) { query += "shower='y' and "; }
				else if( conv[i].equals("air") ) { query += "air='y' and "; }
				else if( conv[i].equals("ball") ) { query += "ball='y' and "; }
				else if( conv[i].equals("vest") ) { query += "vest='y' and "; }
				else if( conv[i].equals("shoes") ) { query += "shoes='y' and "; }
			}
			query = query.substring(0, query.length()-4);
			query+=") ";
		}
		if ( !gside[0].equals("") ) {
			query += "and (gside=";
			if(gside.length==1) {
				if( gside[0].equals("3") ) { query += "3 "; }
				else if( gside[0].equals("4") ) { query += "4 "; }
				else if( gside[0].equals("5") ) { query += "5 "; }
				else if( gside[0].equals("9") ) { query += "9 "; }
				else if( gside[0].equals("10") ) { query += "10 "; }
				else if( gside[0].equals("11") ) { query += "11 "; }
				query+=") ";
			}
			else {
				for(int i =0; i < gside.length; i++) {
					if( gside[i].equals("3") ) { query += "3 or gside="; }
					else if( gside[i].equals("4") ) { query += "4 or gside="; }
					else if( gside[i].equals("5") ) { query += "5 or gside="; }
					else if( gside[i].equals("9") ) { query += "9 or gside="; }
					else if( gside[i].equals("10") ) { query += "10 or gside="; }
					else if( gside[i].equals("11") ) { query += "11 or gside="; }
				}
				query = query.substring(0, query.length()-9);
				query+=") ";
			}
		}

			if(!find.equals("")) {
				if(find.equals("gstar")) {
					query += "order by gstar desc, mtitle asc";
				}
				else if(find.equals("mdate")) {
					query += "order by mdate asc, mtitle asc";
				}
				else if(find.equals("mcreatedate")) {
					query += "order by mcreatedate desc, mtitle asc";
				}
				else if(find.equals("mtitle")) {
					query += "order by mtitle asc";
				}
				else if(find.equals("mprice1")) {
					query += "order by mprice desc, mtitle asc";
				}
				else if(find.equals("mprice2")) {
					query += "order by mprice asc, mtitle asc";
				}
			}
			else {
				query += "order by mtitle asc, mdate asc";
			}
		
		//System.out.println(query);
		
		PrintWriter out = response.getWriter();
		out.print(dao.matchListFilter(query)); //mlist.jsp에서 ajax로 값받을꺼임
		out.close();
		//ArrayList<MatchDTO> list = new ArrayList<>();
		//list = dao.matchListFilter(page, query);
		//request.setAttribute("list", list);
		//request.setAttribute("find", "find=" + find);
	}
}
