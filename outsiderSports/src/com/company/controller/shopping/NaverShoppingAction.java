package com.company.controller.shopping;

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

public class NaverShoppingAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String clientId = "7n0_FK3nbZQcD6HgH7MM";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "A5OZmXmw_3";//애플리케이션 클라이언트 시크릿값";
		
        try {
        String text = URLEncoder.encode(request.getParameter("query"), "UTF-8");
		URL url = new URL("https://openapi.naver.com/v1/search/shop.json?query=" + text + "&display=20");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("GET"); //대문자 사용 주의!! 꼭 대문자로!!
		
		conn.setRequestProperty("X-Naver-Client-Id", clientId);
		conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	
		int responseCode = conn.getResponseCode(); //호출이 문제없이 잘 이뤄졌는지 확인하기위해
		 BufferedReader br; //값들을 읽어들이기 위해
		 
		 if(responseCode==200) { //200이 떠야 정상호출
			 br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		 }else {
			 br = new BufferedReader(new InputStreamReader(conn.getErrorStream())); //에러가생기면 error스트림을 가져오겠다
		 }
		 
		 StringBuffer sb = new StringBuffer();
		 while(true) {
				String line = br.readLine();
				if(line==null) {break;}
				sb.append(line + "\n"); //데이터를 stringbuffer에 담음
		}
		br.close();
		conn.disconnect();
		PrintWriter out = response.getWriter();
		out.print(sb.toString()); //웹에도 찍어보고
		//System.out.println(sb.toString()); //콘솔에도 찍어보고
		
		
        }catch (Exception e) {
        	e.printStackTrace();
        }
		
		
	}

}
