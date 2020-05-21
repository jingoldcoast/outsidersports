package com.company.controller.index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.inter.Proceed;

public class NaverNewsAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		try {
				String query = URLEncoder.encode("스포츠", "UTF-8");
				String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + query + "&sort=sim";
				URL url = new URL(apiURL);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setDoInput(true); 
	            conn.setDoOutput(true); 
	            conn.setUseCaches(false);
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("X-Naver-Client-Id", "7n0_FK3nbZQcD6HgH7MM");
	    		conn.setRequestProperty("X-Naver-Client-Secret", "A5OZmXmw_3");
	    		
	            BufferedReader br;
	            StringBuffer sb = new StringBuffer();
	            
	            if(conn.getResponseCode()==200) {
	            	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            else {
	            	br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            }
	            

	            while(true) {
	   				String line = br.readLine();
	   				if(line==null) {break;}
	   				sb.append(line + "\n"); //데이터를 stringbuffer에 담음
	   		}
	            br.close();
	            conn.disconnect();
	            //System.out.println(sb.toString());
			
	            PrintWriter out = response.getWriter();
	            out.println(sb);
	       
				
				
		}catch (Exception e) {
			System.out.println("캐치문");
			e.printStackTrace();
		}

		
	}

}
