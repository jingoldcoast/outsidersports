package com.company.controller.member;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.MemberDAO;
import com.company.dao.TotalpointDAO;
import com.company.dto.MemberDTO;
import com.company.inter.Proceed;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JoinAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String secret = "6Ldw0N8UAAAAAFbb3bTBMqw8OLJP6agpP2KYB7av";
		String respon = request.getParameter("g-recaptcha-response");
		String success = "";
		try {
		URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");

		conn.setDoInput(true); 
        conn.setDoOutput(true); 
        conn.setUseCaches(false);
        
        String postParams = "secret=" + secret + "&response=" + respon;
		
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
        int responseCode = conn.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String inputLine;
        StringBuffer sb = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
        	sb.append(inputLine);
        }
        br.close();
        //System.out.println(sb.toString());
        
        String ts = sb.toString();
        JsonParser jp = new JsonParser();
        JsonObject job = (JsonObject)jp.parse(ts);
        success = job.get("success").getAsString();
        
        //System.out.println(success);
        //System.out.println(job.get("error-codes").getAsString());
        //{  "success": true,  "challenge_ts": "2020-03-10T14:11:26Z",  "hostname": "localhost"} ==성공시
        // {  "success": false,  "error-codes": [    "missing-input-response"  ]} ==체크안하면 응답
		}catch (Exception e) {
            System.out.println(e);
        }
        if(success.equals("true")) {
        
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setOid(request.getParameter("oid"));
			dto.setOname(request.getParameter("oname"));
			dto.setOcontact(request.getParameter("ocontact"));
			dto.setOpass(request.getParameter("opass1"));
			dto.setObirth(request.getParameter("obirth"));
			
			String osex = request.getParameter("osex");
			if(osex.equals("s1")) { osex="m"; }
			else if(osex.equals("s2")) { osex="f"; }
			dto.setOsex(osex);
			TotalpointDAO tdao = new TotalpointDAO(); //회원 누적 사용포인트 계산하려고
			if(tdao.addData(dto.getOid())==1) {
				request.setAttribute("result", dao.joinMember(dto));
				request.setAttribute("what", "회원가입을 축하드립니다!");
			}else {
				request.setAttribute("result", -1);
			}
        }else {
        	request.setAttribute("result", -1);
        	request.setAttribute("what", "회원가입에 실패했습니다.\n로봇이 아닙니다 체크여부를 확인해주세요!");
        }
        
	}

}
