package com.company.controller.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NaverLoginAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		
		try {
				String grant_type = "grant_type=authorization_code";
				String client_id = "&client_id=7n0_FK3nbZQcD6HgH7MM";
				String client_secret = "&client_secret=A5OZmXmw_3";
				String code = "&code=" + request.getParameter("code");
				String state = "&state=임의";
				String apiURL = "https://nid.naver.com/oauth2.0/token?" + grant_type + client_id + client_secret + code + state;
				URL url = new URL(apiURL);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setDoInput(true); 
	            conn.setDoOutput(true); 
	            conn.setUseCaches(false);
	            conn.setRequestMethod("POST");
	            
	            BufferedReader br;
	            StringBuffer sb = new StringBuffer();
	            String line ="";
	            
	            if(conn.getResponseCode()==200) {
	            	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            }
	            else {
	            	br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            }
	            
	            while( (line=br.readLine()) != null  ) {
	            	sb.append(line);
	            }
	            br.close();
	            conn.disconnect();
	            System.out.println(sb.toString());
			
			String access_token = sb.toString();
			JsonParser jp = new JsonParser();
			JsonObject job = (JsonObject) jp.parse(access_token);
			access_token = job.get("access_token").getAsString(); // System.out.println(access_token);
			  
			String header = "Bearer " + access_token;
			url = new URL("https://openapi.naver.com/v1/nid/me");
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", header);
			if (conn.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			sb = new StringBuffer();
			line = "";
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			br.close();
			conn.disconnect();
			System.out.println(sb.toString());
			String response_final = sb.toString();
			jp = new JsonParser();
			job = (JsonObject) jp.parse(response_final);
			JsonObject job2 = job.get("response").getAsJsonObject();
			String nickname = job2.get("nickname").getAsString();
			System.out.println(nickname);
			request.setAttribute("nickname", nickname); 
			  //로그인했을때 회원정보있는지 없는지 확인
	          MemberDAO dao = new MemberDAO();
	          MemberDTO dto = new MemberDTO();
	          dto.setOid(nickname);
	          int result = dao.checkId(dto);
				if (result == 0) {
					request.setAttribute("result", "신규회원"); // 사용가능한아이디
				} else if (result > 0) {
					request.setAttribute("result", 1 + ""); // 기존회원 - 로그인진행
					request.getSession().setAttribute("oid", nickname);
					request.setAttribute("what", "로그인 성공!");
				} else {
					request.setAttribute("result", -1 + ""); // 오류
				}
				
				
		}catch (Exception e) {
			System.out.println("캐치문");
			e.printStackTrace();
		}
		
		
		
	}

}
