package com.company.controller.match;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MatchDAO;
import com.company.dto.MatchDTO;
import com.company.dto.PagingDTO;
import com.company.inter.Proceed;

public class MListAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charequest=utf-8");
		//총 2개의 경우가 있음
		//1) 헤더 경기참여 클릭할때
		//   1-1) 그냥
		//   1-2) 00순 클릭할 때
		//2) 메인에서 경기 이름 검색할때
		if(request.getParameter("mtitlesearch")==null) {//메인에서 경기 검색 submit 아닐때
			if(request.getParameter("stype")==null) {////경기참여 클릭햇을때 ▼ (메인.jsp에서 파라미터값 하나 넘겨줄거임)
				MatchDAO dao = new MatchDAO();
				//list 정렬 default order by a.mdate asc limit 0, 10;
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
				
				if(request.getParameter("find")==null) {//00순 파라미터값없으면은..
					ArrayList<MatchDTO> list = new ArrayList<>();
					list = dao.matchList(page);
					
					request.setAttribute("list", list);
				}else {
					String find = request.getParameter("find");
					String query = "";
					if(find.equals("gstar")) {
						query = "order by gstar desc";
					}
					else if(find.equals("mdate")) {
						query = "order by mdate asc";
					}
					else if(find.equals("mcreatedate")) {
						query = "order by mcreatedate desc";
					}
					else if(find.equals("mtitle")) {
						query = "order by mtitle asc";
					}
					else if(find.equals("mprice1")) {
						query = "order by mprice desc";
					}
					else if(find.equals("mprice2")) {
						query = "order by mprice asc";
					}
					ArrayList<MatchDTO> list = new ArrayList<>();
					list = dao.matchListQuery(page, query);
					request.setAttribute("list", list);
					//request.setAttribute("find", "find=" + find);
					request.setAttribute("find", find);
				}
			}////경기참여 클릭햇을때 ▲
			else {//메인에서 필터검색했을 때
				String query = "where ";
				
				String gcategory = request.getParameter("gcategory");
				if(gcategory.equals("01")) {
					query += "b.gcategory='축구' ";
				}
				else if(gcategory.equals("02")) {
					query += "b.gcategory='농구' ";
				}
				else {
					query += " (b.gcategory='축구' or b.gcategory='농구') ";
				}
				
				String mdate1 = request.getParameter("mdate1");
				String mdate2 = request.getParameter("mdate2");
				if( !mdate1.equals("") && !mdate2.equals("")) {
					query += "and a.mdate between '"+mdate1+"' and '"+mdate2+"' ";
				}
	
				String gstar = request.getParameter("gstar");
				if(!gstar.equals("")) {
					query += "and b.gstar>="+gstar+" ";
				}
				
				String[] gregion = request.getParameterValues("gregion");
				if(gregion != null) {
					query += "and (";
					for(int i =0; i < gregion.length; i++) {
						if(gregion[i].equals("01")) { query += "b.gregion='서울' or "; }
						else if(gregion[i].equals("02")) { query += "b.gregion='경기' or "; }
						else if(gregion[i].equals("03")) { query += "b.gregion='인천' or "; }
						else if(gregion[i].equals("04")) { query += "b.gregion='부산' or "; }
						else if(gregion[i].equals("05")) { query += "b.gregion='대구' or "; }
						else if(gregion[i].equals("06")) { query += "b.gregion='광주' or "; }
						else if(gregion[i].equals("07")) { query += "b.gregion='대전' or "; }
						else if(gregion[i].equals("08")) { query += "b.gregion='제주특별자치도' or "; }
						else if(gregion[i].equals("09")) { query += "b.gregion='울산' or "; }
						else if(gregion[i].equals("10")) { query += "b.gregion='강원' or "; }
						else if(gregion[i].equals("11")) { query += "b.gregion='충북' or "; }
						else if(gregion[i].equals("12")) { query += "b.gregion='충남' or "; }
						else if(gregion[i].equals("13")) { query += "b.gregion='전북' or "; }
						else if(gregion[i].equals("14")) { query += "b.gregion='전남' or "; }
						else if(gregion[i].equals("15")) { query += "b.gregion='경북' or "; }
						else if(gregion[i].equals("16")) { query += "b.gregion='경남' or "; }
					}
					query = query.substring(0, query.length()-4);
					query += ") ";
				}
				
				String[] mday = request.getParameterValues("mday");
				if(mday != null) {
					query += "and (";
					for(int i =0; i < mday.length; i++) {
						if(mday[i].equals("0")) { query += "a.mday='일' or "; }
						else if(mday[i].equals("1")) { query += "a.mday='월' or "; }
						else if(mday[i].equals("2")) { query += "a.mday='화' or "; }
						else if(mday[i].equals("3")) { query += "a.mday='수' or "; }
						else if(mday[i].equals("4")) { query += "a.mday='목' or "; }
						else if(mday[i].equals("5")) { query += "a.mday='금' or "; }
						else if(mday[i].equals("6")) { query += "a.mday='토' or "; }
					}
					query = query.substring(0, query.length()-4);
					query += ") ";
				}
				
				String[] mduration = request.getParameterValues("mduration");
				if(mduration != null) {
					query += "and (";
					for(int i =0; i < mduration.length; i++) {
						if(mduration[i].equals("0")) { query += "a.mduration=60 or "; }
						else if(mduration[i].equals("1")) { query += "a.mduration=90 or "; }
						else if(mduration[i].equals("2")) { query += "a.mduration=120 or "; }
					}
					query = query.substring(0, query.length()-4);
					query += ") ";
				}
				
				String[] msex = request.getParameterValues("msex");
				if(msex != null) {
					query += "and (";
					for(int i =0; i < msex.length; i++) {
						if(msex[i].equals("m")) { query += "a.msex='m' or "; }
						else if(msex[i].equals("f")) { query += "a.msex='f' or "; }
						else if(msex[i].equals("c")) { query += "a.msex='c' or "; }
					}
					query = query.substring(0, query.length()-4);
					query += ") ";
				}
				
				String[] gside = request.getParameterValues("gside");
				if(gside != null) {
					query += "and (";
					for(int i =0; i < gside.length; i++) {
						if(gside[i].equals("3")) { query += "b.gside=3 or "; }
						else if(gside[i].equals("4")) { query += "b.gside=4 or "; }
						else if(gside[i].equals("5")) { query += "b.gside=5 or "; }
						else if(gside[i].equals("9")) { query += "b.gside=9 or "; }
						else if(gside[i].equals("10")) { query += "b.gside=10 or "; }
						else if(gside[i].equals("11")) { query += "b.gside=11 or "; }
					}
					query = query.substring(0, query.length()-4);
					query += ") ";
				}
				
				String[] conv = request.getParameterValues("conv");
				if(conv != null) {
					query += "and (";
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
					query += ") ";
				}
				MatchDAO dao = new MatchDAO();
				request.setAttribute("list", dao.matchListMainFilter(query));
				//System.out.println(query);
			}//메인 필터검색 끝
		}
		else {//메인에서 경기이름으로 검색할때
			MatchDAO dao = new MatchDAO();
			request.setAttribute("list", dao.matchSearch(request.getParameter("mtitlesearch")));
		}
		
		
	}//end execute
}//end class
