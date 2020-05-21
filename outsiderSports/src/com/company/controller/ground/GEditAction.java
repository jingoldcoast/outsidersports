package com.company.controller.ground;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.GroundDAO;
import com.company.dto.GroundDTO;
import com.company.inter.Proceed;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GEditAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		GroundDTO dto = new GroundDTO();
		GroundDAO dao = new GroundDAO();
		
		try {
			String path = "/upload/";
			path = request.getServletContext().getRealPath(path); //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
			String file ="";

			if(multi.getFilesystemName("gfile1")==null) {
				file= multi.getParameter("gfilebefore");
			}
			else {
				boolean first = true;
				for(int i = 1; i <=5; i++) {
					String tmp = multi.getFilesystemName("gfile" + i);
					if(tmp==null) {break;}
					if(!first) {
						file+= "/" + tmp;
					}
					else {
						file += tmp;
					}
					first = false;
				}
			}
			
			dto.setGno(Integer.parseInt(multi.getParameter("gno")));
			dto.setGfile(file); 
			dto.setGwriter((String)request.getSession().getAttribute("oid"));
			dto.setGname(multi.getParameter("gname"));
			dto.setGzipcode(multi.getParameter("zipcode"));
			dto.setGregion(multi.getParameter("gregion"));
			dto.setGaddr1(multi.getParameter("address1"));
			dto.setGaddr2(multi.getParameter("address2"));
			dto.setGside(Integer.parseInt(multi.getParameter("gside")));
			String gcategory = multi.getParameter("gcategory");
			if(gcategory.equals("01")) { gcategory = "축구"; } 
			else if(gcategory.equals("02")) { gcategory = "농구"; }
			else { gcategory = "에러"; }
			dto.setGcategory(gcategory);
			dto.setParking(multi.getParameter("parking"));
			dto.setLight(multi.getParameter("light"));
			dto.setIo(multi.getParameter("io"));
			dto.setShower(multi.getParameter("shower"));
			dto.setAir(multi.getParameter("air"));
			dto.setBall(multi.getParameter("ball"));
			dto.setVest(multi.getParameter("vest"));
			dto.setShoes(multi.getParameter("shoes"));
			
			String gdesc = multi.getParameter("gdesc");
			if (gdesc!=null) {
				dto.setGdesc(gdesc);
			}
			
			int a = dao.editGround(dto);//경기장먼저수정
			
			int b = -1;
			
			if(a==1) {
				b = dao.editConv(dto);//편의시설도 수정해준다
			}
			
			if(a>0 && b>0) {
				request.setAttribute("result", 1);
				request.setAttribute("what", "경기장 수정에 성공했습니다!");
			}
			else {
				request.setAttribute("result", -1);
			}
			
		}catch (Exception e) {e.printStackTrace(); }

		
	}

}
