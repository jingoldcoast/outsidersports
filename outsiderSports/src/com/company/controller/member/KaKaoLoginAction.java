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
import com.google.gson.*;

public class KaKaoLoginAction implements Proceed {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
        
		//System.out.println("여기야??");
		String code = request.getParameter("code"); //코드를 받아왔다.
		
		String grant_type = "grant_type=authorization_code";
		String client_id = "&client_id=a681026afa8a8a4d931d39ba35758e28";
		//String redirect_uri= "&redirect_uri=http://localhost:8080/outsiderSports/kakao.login"; //★★★★★★★★★
		String redirect_uri= "&redirect_uri=http://jingoldcoast.cafe24.com/outsidersports/kakao.login";
		
		
		   try {
	            String apiURL = "https://kauth.kakao.com/oauth/token?" + grant_type + client_id + redirect_uri + "&code=" + code ;
	            URL url = new URL(apiURL);
	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setDoInput(true); //응답받겠다.
	            conn.setDoOutput(true); //요청하겠다
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
	            //System.out.println(sb);
	            //{"access_token":"nWq6x-3o4ca1Q2dxVwAdzKLaSWxEsdugvG-t7go9c-sAAAFwnsurTQ","token_type":"bearer","refresh_token":"QHei1slTf7PV476IFZf8ajX_SRizjsYWV6fUngo9c-sAAAFwnsurSw","expires_in":21599,"scope":"profile","refresh_token_expires_in":5183999}
	            
	            String access_token = sb.toString();
	            JsonParser jp = new JsonParser();
	            JsonObject job = (JsonObject) jp.parse(access_token); //괄호안에는 String 들어감
	            access_token = job.get("access_token").getAsString();
	            
/*	            String access_token="";
	            access_token = sb.substring(sb.indexOf(":"), sb.indexOf(","));
	            access_token = access_token.substring(2, access_token.length()-1);
	            System.out.println(access_token);*/
	            
	            //////////////////////////////////로그인정보 가져오기
	            
	            
	            String header = "Bearer " + access_token;
	            request.getSession().setAttribute("header", header); //로그아웃할때 필요함
	            url = new URL("https://kapi.kakao.com/v2/user/me");
	            conn = (HttpURLConnection)url.openConnection();
	            conn.setDoInput(true); //응답받겠다.
	            conn.setDoOutput(true); //요청하겠다
	            conn.setRequestMethod("POST");
	            conn.setDoInput(true);
	            conn.setDoOutput(true);
	            conn.setRequestProperty("Authorization", header);
	            	
	          if(conn.getResponseCode()==200) {
	        	  br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
	          }
	          else {
	            	br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            }
	          
	          StringBuffer sb2 = new StringBuffer();
	            String line2 ="";
	          while( (line2=br.readLine()) != null ) {
	        	  sb2.append(line2);
	          }
	          br.close();
	          conn.disconnect();
	          //System.out.println(sb2);
	          //{"id":1291195253,"connected_at":"2020-02-25T06:27:48Z","properties":{"nickname":"김희진 HeeJin","profile_image":"http://k.kakaocdn.net/dn/kW3zR/btqveCbGlkl/uYJbE3eQpWrN6huDEgWHy1/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/kW3zR/btqveCbGlkl/uYJbE3eQpWrN6huDEgWHy1/img_110x110.jpg"},"kakao_account":{"profile_needs_agreement":false,"profile":{"nickname":"김희진 HeeJin","thumbnail_image_url":"http://k.kakaocdn.net/dn/kW3zR/btqveCbGlkl/uYJbE3eQpWrN6huDEgWHy1/img_110x110.jpg","profile_image_url":"http://k.kakaocdn.net/dn/kW3zR/btqveCbGlkl/uYJbE3eQpWrN6huDEgWHy1/img_640x640.jpg"}}}
	         

	          String personal = sb2.toString();
	          jp = new JsonParser();
	          job = (JsonObject)jp.parse(personal);
	          JsonObject properties = job.get("properties").getAsJsonObject();
	          String nickname = properties.get("nickname").getAsString();
	          String image = properties.get("thumbnail_image").getAsString();
	          //System.out.println(sb2.indexOf("nickname"));
	          //String nickname = sb2.substring(sb2.indexOf("nickname")+11);
	          //System.out.println(nickname);
	          
	          //nickname = nickname.substring(0, nickname.indexOf(",")-1);

	         //System.out.println("닉네임최종"+nickname);
	          request.setAttribute("nickname", nickname); //닉네임셋팅
	          
	          //request.getSession().setAttribute("oid", nickname);
	          
	          //String image = sb2.substring(sb2.indexOf("thumbnail_image")+18);
	          //image = image.substring(0, image.indexOf(",")-2);
	          //System.out.println("이미지짜른거"+image);
	          
	          request.setAttribute("image", image); //이미지셋팅

	            /*
	            GET/POST /v2/user/me HTTP/1.1
	            Host: kapi.kakao.com
	            Authorization: Bearer {access_token}
	            */
	          
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
