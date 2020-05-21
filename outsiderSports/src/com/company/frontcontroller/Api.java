package com.company.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.controller.anytalk.NaverPapagoAction;
import com.company.controller.anytalk.NotPapagoAction;
import com.company.controller.member.KaKaoLoginAction;
import com.company.controller.member.KakaoLogoutAction;
import com.company.controller.member.NaverLoginAction;
import com.company.controller.shopping.NaverShoppingAction;
import com.company.inter.Proceed;

/**
 * Servlet implementation class ApiLogin
 */
@WebServlet({ "*.login", "*.logout", "*.api" })
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Api() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionPath(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionPath(request, response);
	}
	
	private void actionPath(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		Proceed command = null; // 인터페이스
		
		if (path.equals("/kakao.login")) {// 카톡 로그인
			command = new KaKaoLoginAction();
			command.execute(request, response);
			String result = (String)request.getAttribute("result");
			if(result.equals("신규회원")) {
				request.getRequestDispatcher("/member/api_join.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/main.index").forward(request, response);
			}
		}
		else if (path.equals("/kakao.logout")) {//카톡 로그아웃
			command = new KakaoLogoutAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.index").forward(request, response);
		}
		else if (path.equals("/shopping_view.api")) {//쇼핑하기 view페이지
			request.getRequestDispatcher("/shopping/shopping.jsp").forward(request, response);
		}
		else if (path.equals("/navershopping.api")) {//쇼핑하기에서 검색했을 때 ajax
			command = new NaverShoppingAction();
			command.execute(request, response);
		}
		else if (path.equals("/naver.login")) {// 지금 아직 네이버에서 승인안떨어짐 ★★★★
			command = new NaverLoginAction();
			command.execute(request, response);
			String result = (String)request.getAttribute("result");
			if(result.equals("신규회원")) {
				request.getRequestDispatcher("/member/api_join2.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/main.index").forward(request, response);
			}
		}
		else if (path.equals("/papago.api")) {//하고싶은말에서 영어로번역해서 등록하고 싶을 때
			command = new NaverPapagoAction();
			command.execute(request, response);
		}
		else if (path.equals("/notpapago.api")) {//이건 api아닌데 그냥 경로 맞출라고 함. 번역안하고 그냥 한글로 등록하고 싶을 때
			command = new NotPapagoAction();
			command.execute(request, response);
		}
		
	}//actionPath

}//end class
