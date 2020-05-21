package com.company.controller.anytalk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.AnytalkDAO;
import com.company.dto.AnytalkDTO;
import com.company.inter.Proceed;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NaverPapagoAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String clientId = "7n0_FK3nbZQcD6HgH7MM";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "A5OZmXmw_3";//애플리케이션 클라이언트 시크릿값";
        
		        try {
		            String text = URLEncoder.encode(request.getParameter("acomment"), "UTF-8");
		            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		            URL url = new URL(apiURL);
		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setRequestMethod("POST");
		            con.setRequestProperty("X-Naver-Client-Id", clientId);
		            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		            // post request
		            String postParams = "source=ko&target=en&text=" + text;
		            con.setDoOutput(true);
		            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		            wr.writeBytes(postParams);
		            wr.flush();
		            wr.close();
		            int responseCode = con.getResponseCode();
		            BufferedReader br;
		            if(responseCode==200) { // 정상 호출
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            } else {  // 에러 발생
		                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		            }
		            String inputLine;
		            StringBuffer sb = new StringBuffer();
		            while ((inputLine = br.readLine()) != null) {
		            	sb.append(inputLine);
		            }
		            br.close();
		           // System.out.println(sb.toString());
		            
		            String ts = sb.toString();
		            PrintWriter out = response.getWriter();
		            out.print(ts);
		            JsonParser jp = new JsonParser();
		            JsonObject job = (JsonObject)jp.parse(ts);
		           
		            JsonObject msg = job.get("message").getAsJsonObject();
		            JsonObject result = msg.get("result").getAsJsonObject();
		            ts = result.get("translatedText").getAsString(); //최종적으로 번역된 말
		            
		            //System.out.println(ts);
		            //db 저장하기
		            AnytalkDTO dto = new AnytalkDTO();
		            dto.setAcomment(ts);
		            dto.setApass(request.getParameter("apass"));
		            AnytalkDAO dao = new AnytalkDAO();
		            dao.addTalk(dto);
		            
		            
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		
	}

}
