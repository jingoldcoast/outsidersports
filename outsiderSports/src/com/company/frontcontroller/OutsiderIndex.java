package com.company.frontcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.controller.index.IndexAction;
import com.company.inter.Proceed;

/**
 * Servlet implementation class OutsiderIndex
 */
@WebServlet("*.index")
public class OutsiderIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutsiderIndex() {
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
		
		if (path.equals("/main.index")) {// 문의하기 글쓰기 폼으로가기
			command = new IndexAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}

}//end class
