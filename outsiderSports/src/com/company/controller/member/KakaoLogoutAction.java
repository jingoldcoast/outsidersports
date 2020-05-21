package com.company.controller.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.inter.Proceed;

public class KakaoLogoutAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String header = (String)request.getSession().getAttribute("header");
		try {
			URL url = new URL("https://kapi.kakao.com/v1/user/logout");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true); 
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", header);
            BufferedReader br;
            if (conn.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
            StringBuffer sb = new StringBuffer();
			sb = new StringBuffer();
			while(true) {
				String line = br.readLine();
				if(line==null) {break;}
				sb.append(line + "\n");
			}
			br.close();
			conn.disconnect();
			request.getSession().invalidate();
			//System.out.println(sb.toString());
            
		}catch (Exception e) {
			   System.out.println("캐치문");
			   e.printStackTrace();
		}
		
	}

}
