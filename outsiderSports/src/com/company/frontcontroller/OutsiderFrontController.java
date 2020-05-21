package com.company.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.controller.anytalk.AnytalkEditAction;
import com.company.controller.anytalk.AnytalkListAction;
import com.company.controller.festivaltalk.FestivaltalkAction;
import com.company.controller.festivaltalk.FestivaltalkListAction;
import com.company.controller.festivaltalk.FestivaltalkWriteAction;
import com.company.controller.ground.*;
import com.company.controller.index.EventDontAction;
import com.company.controller.index.NaverNewsAction;
import com.company.controller.info.*;
import com.company.controller.match.*;
import com.company.controller.member.*;
import com.company.controller.mypage.*;
import com.company.controller.question.*;
import com.company.controller.review.*;
import com.company.controller.suggest.*;
import com.company.inter.Proceed;
import com.company.reserve.*;

/**
 * Servlet implementation class QuestionFrontController
 */
@WebServlet("*.do")
public class OutsiderFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OutsiderFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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

		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);

		String path = request.getRequestURI().substring(request.getContextPath().length());
		Proceed command = null; // 인터페이스

		if (path.equals("/qwrite_view.do")) {// 문의하기 글쓰기 폼으로가기
			request.getRequestDispatcher("/question/qwrite.jsp").forward(request, response);
		} else if (path.equals("/qwrite.do")) {// 글쓰기 action 이후 전체페이지 보기
			command = new QWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/qlist.do").forward(request, response);
		} else if (path.equals("/qlist.do")) {// 전체페이지 보기
			command = new QListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/question/qlist.jsp").forward(request, response);
		} else if (path.equals("/qdetail.do")) {// 상세페이지 보기
			command = new QDetailAction();
			command.execute(request, response);
			request.getRequestDispatcher("/question/qdetail.jsp").forward(request, response);
		} else if (path.equals("/qedit_view.do")) {// 상세페이지에서 수정하기 눌렀을 때
			command = new QEditViewAction();
			command.execute(request, response);
			request.getRequestDispatcher("/question/qedit.jsp").forward(request, response);
		} else if (path.equals("/qedit.do")) {// 수정하기페이지에서 수정하기 눌렀을 때 (수정액션하고 상세페이지로)
			command = new QEditAction();
			command.execute(request, response);

			request.getRequestDispatcher("/qdetail.do").forward(request, response);
		} else if (path.equals("/delete_view.do")) {// 상세페이지에서 삭제하기 누르면
			request.getRequestDispatcher("/question/qdelete.jsp").forward(request, response);
		} else if (path.equals("/qdelete.do")) {// 비밀번호 확인 후 삭제하기
			command = new QDeleteAction();
			command.execute(request, response);

			int result = (int) request.getAttribute("result");

			if (result == 0) {// 비밀번호 틀리면은 다시 입력받게
				request.setAttribute("result", 0);
				request.getRequestDispatcher("/question/qdelete.jsp").forward(request, response);
			} else {// 맞으면은 list로
				request.getRequestDispatcher("/qlist.do").forward(request, response);
			}
		} else if (path.equals("/qreply_view.do")) {// 상세페이지에서 삭제하기 누르면
			request.getRequestDispatcher("/question/qreply.jsp").forward(request, response);
		} else if (path.equals("/qreplywrite.do")) {// 답변하기 다음에 리스트보기
			command = new QReplyWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/qdetail.do").forward(request, response);
		}
		///////////////////////// member
		else if (path.equals("/join_view.do")) {// 회원가입 폼가기
			request.getRequestDispatcher("/member/mjoin.jsp").forward(request, response);
		}
		else if (path.equals("/oidcheck.do")) {//회원가입할때 아이디중복여부 = ajax■■■■
			command = new JoinIdCheckAction();
			command.execute(request, response);
		}
		else if (path.equals("/join.do")) {// 회원가입 하고 나서
			command = new JoinAction();
			command.execute(request, response);
			if((int)request.getAttribute("result")==1) {
				request.getRequestDispatcher("/main.index").forward(request, response);
			}else {
				request.getRequestDispatcher("/member/mjoin.jsp").forward(request, response);//회원가입처리실패하면
			}
		} else if (path.equals("/login_view.do")) {// 로그인 폼가기 (아이디저장하기 확인여부 후)
			command = new LoginViewAction();
			command.execute(request, response);
			request.getRequestDispatcher("/member/mlogin.jsp").forward(request, response);
		} else if (path.equals("/login.do")) {// 로그인
			command = new LoginAction();
			command.execute(request, response);
			if ((int) request.getAttribute("result") == 1) {
				request.getRequestDispatcher("/main.index").forward(request, response);
			} else {
				request.getRequestDispatcher("/login_view.do").forward(request, response);
			}
		} else if (path.equals("/logout.do")) {// 로그아웃
			command = new LogoutAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.index").forward(request, response);
		} else if (path.equals("/myprofile.do")) {// 마이페이지 눌렀을때 default로 '나의설정'으로 가기
			command = new MyprofileAction();
			command.execute(request, response);
			request.getRequestDispatcher("/mypage/myprofile.jsp").forward(request, response);
		} else if (path.equals("/myinfoedit.do")) {// 나의설정에서 정보수정(이름, 연락처 수정가능)
			command = new MyInfoEditAction();
			command.execute(request, response);
			request.getRequestDispatcher("/myprofile.do").forward(request, response);
		} else if (path.equals("/mypassedit_view.do")) {// 나의설정에서 비밀번호 수정 눌렀을때 비밀번호 수정폼으로
			request.getRequestDispatcher("/member/meditpass.jsp").forward(request, response);
		} else if (path.equals("/mypassedit.do")) {// 회원 비밀번호 수정 command = new
			command = new MyPassEditAction();
			command.execute(request, response);
			if ((int) request.getAttribute("result") == 1) {
				request.getRequestDispatcher("/myprofile.do").forward(request, response);// 수정완료후 나의설정으로
			} else {
				request.getRequestDispatcher("/mypassedit_view.do").forward(request, response);
			}
		}
		//////////////////////건의하기 게시판//////////////
		else if (path.equals("/swrite_view.do")) {// 글쓰기 action 이후 전체페이지 보기
			request.getRequestDispatcher("/suggest/swrite.jsp").forward(request, response);
		}
		 else if (path.equals("/swrite.do")) {// 글쓰기 action 이후 전체페이지 보기
			command = new SWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/slist.do").forward(request, response);
		}
		 else if (path.equals("/slist.do")){
			command = new SListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/suggest/slist.jsp").forward(request, response);
		 }
		 else if (path.equals("/sdetail.do")){
				command = new SDetailAction();
				command.execute(request, response);
				request.getRequestDispatcher("/suggest/sdetail.jsp").forward(request, response);
		 }
		 else if (path.equals("/sedit_view.do")){
			 command = new SEditViewAction();
			 command.execute(request, response);
			 request.getRequestDispatcher("/suggest/sedit.jsp").forward(request, response);
		 }
		 else if (path.equals("/sedit.do")){
			 command = new SEditAction();
			 command.execute(request, response);
			 request.getRequestDispatcher("/sdetail.do").forward(request, response);
		 }
		 else if (path.equals("/sdelete_view.do")){
			 request.getRequestDispatcher("/suggest/sdelete.jsp").forward(request, response);
		 }
		 else if (path.equals("/sdelete.do")){
			 command = new SDeleteAction();
			 command.execute(request, response);
			 
			int result = (int) request.getAttribute("result");

			if (result == 0) {// 비밀번호 틀리면은 다시 입력받게
				request.setAttribute("result", 0);
				request.getRequestDispatcher("/sdelete_view.do").forward(request, response);
			} else {// 맞으면은 list로
				request.getRequestDispatcher("/slist.do").forward(request, response);
			}
		 }
		else if (path.equals("/sreply_view.do")) {// 답글달기 폼
			command = new SReplyViewAction();
			command.execute(request, response);
			request.getRequestDispatcher("/suggest/sreply.jsp").forward(request, response);
		} else if (path.equals("/sreply.do")) {// 답글달기
			command = new SReplyAction();
			command.execute(request, response);
			request.getRequestDispatcher("/slist.do").forward(request, response);
		}
		else if (path.equals("/spoint_charge.do")) {//나의 포인트 충전하기
			command = new SPointChargeAction();
			command.execute(request, response);
			request.getRequestDispatcher("/myprofile.do").forward(request, response);
		}
		else if (path.equals("/find_view.do")) {//아이디or비번찾기 폼
			request.getRequestDispatcher("/member/mfind.jsp").forward(request, response);
		}
		else if (path.equals("/find.do")) {//아이디or비번찾기 액션
			command = new FindInfoAction();
			command.execute(request, response);
			request.getRequestDispatcher("/member/mfindresult.jsp").forward(request, response);
		}
		else if (path.equals("/info.do")) {//이용안내
			command = new NListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/info/info.jsp").forward(request, response);
		}
		else if (path.equals("/nwrite_view.do")) {//이용안내 내용쓰기 폼
			request.getRequestDispatcher("/info/nwrite.jsp").forward(request, response);
		}
		else if (path.equals("/ndelete_view.do")) {//이용안내 내용삭제 폼
			request.getRequestDispatcher("/info/ndelete.jsp").forward(request, response);
		}
		else if (path.equals("/nwrite.do")) {//이용안내 내용쓰기
			command = new NWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/info.do").forward(request, response);
		}
		else if (path.equals("/ndelete.do")) {//이용안내 내용삭제
			command = new NDeleteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/info.do").forward(request, response);
		}
		else if (path.equals("/ncategory.do")) {//이용안내 이미지클릭때마다 아코디언 바꾸기 ajax■■■■
			command = new NcategoryAction();
			command.execute(request, response);
		}
		else if (path.equals("/admin.do")) {//admin으로 로그인해서 관리자메뉴 클릭했을 때
			request.getRequestDispatcher("/admin/adminpage.jsp").forward(request, response);
		}
		else if (path.equals("/gwrite_view.do")) {//경기장 추가하기 폼 -only admin
			request.getRequestDispatcher("/ground/gwrite.jsp").forward(request, response);
		}
		else if (path.equals("/gedit_select.do")) {//경기장 수정하기 전에 무슨 경기장 수정할지 select
			command = new GEditSelectAction();
			command.execute(request, response);
			request.getRequestDispatcher("/ground/gedit_select.jsp").forward(request, response);
		}
		else if (path.equals("/gedit_view.do")) {//경기장 수정하기 전에 무슨 경기장 수정할지 select
			command = new GEditViewAction();
			command.execute(request, response);
			request.getRequestDispatcher("/ground/gedit.jsp").forward(request, response);
		}
		else if (path.equals("/gedit.do")) {//경기장 수정하기 전에 무슨 경기장 수정할지 select
			command = new GEditAction();
			command.execute(request, response);
			request.getRequestDispatcher("/admin.do").forward(request, response);
		}
		else if (path.equals("/gwrite.do")) {//경기장 추가하기 / 추가하고나서 관리자메뉴 메인으로
			command = new GWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/admin.do").forward(request, response);
		}
		else if (path.equals("/glist.do")) {//경기장 예약하기 (전체보기)
			command = new GListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/ground/glist.jsp").forward(request, response);
		}
		else if (path.equals("/gdetail.do")) {//경기장 추가하기
			command = new GDetailAction();
			command.execute(request, response);
			request.getRequestDispatcher("/ground/gdetail.jsp").forward(request, response);
		}
		else if (path.equals("/mwrite_view.do")) {//매치 추가하기 폼 
			if(request.getSession().getAttribute("oid") != null) {
				command = new MWriteViewAction();
				command.execute(request, response);
				request.getRequestDispatcher("/match/mwrite.jsp").forward(request, response);
			}else {
				request.setAttribute("result", 1);
				request.setAttribute("what", "경기개설은 로그인 후 이용가능합니다!");
				request.getRequestDispatcher("/login_view.do").forward(request, response);
			}
		}
		else if (path.equals("/selectgdetail.do")) {//매치 추가하기에서 경기장선택하면 ajax로 경기장이름 자동설정 ajax■■■■
			command = new MWriteInfoAction();
			command.execute(request, response);
		}
		else if (path.equals("/mwrite.do")) {//매치 추가하기
			command = new MWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.index").forward(request, response);
		}
		else if (path.equals("/mlist.do")) {//매치 전체보기
			command = new MListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/match/mlist.jsp").forward(request, response);
		}
		else if (path.equals("/mdetail.do")) {//매치 전체보기
			command = new MDetailAction();
			command.execute(request, response);
			request.getRequestDispatcher("/match/mdetail.jsp").forward(request, response);
		}
		else if (path.equals("/match_reserve.do")) {//매치 예약하기
			command = new MReserveAction();
			command.execute(request, response);
			if((int)(request.getAttribute("result"))==0) {//포인트부족하면 마이페이지로가기
				request.getRequestDispatcher("/myprofile.do").forward(request, response);
			}else {
				request.getRequestDispatcher("/main.index").forward(request, response);
			}
		}
		else if (path.equals("/mymatch.do")) {//내가 예약한 경기
			command = new MyMatchAction();
			command.execute(request, response);
			request.getRequestDispatcher("/mypage/mymatch.jsp").forward(request, response);
		}
		else if (path.equals("/rwrite_view.do")) {//이용후기 쓰기 폼
			request.getRequestDispatcher("/review/review.jsp").forward(request, response);
		}
		else if (path.equals("/rwrite.do")) {//이용후기 쓰기 폼
			command = new RWriteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/myprofile.do").forward(request, response);
		}
		else if (path.equals("/mlist_filter.do")) {//개설된 경기 검색 필터 (사이트바) ajax
			command = new MListFilterAction();
			command.execute(request, response);
		}
		else if (path.equals("/myhost.do")) {//나의 경기 (내가 개설한 경기목록)
			command = new MyHostAction();
			command.execute(request, response);
			request.getRequestDispatcher("/mypage/myhost.jsp").forward(request, response);
		}
		else if (path.equals("/kakaojoin.do")) {//카톡로그인 했을때 회원가입안되어있으면 열로옴
			command = new KakaoJoinAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.index").forward(request, response);
		}
		else if (path.equals("/naverjoin.do")) {//네이버로그인 했을때 회원가입안되어있으면 열로옴
			command = new NaverJoinAction();
			command.execute(request, response);
			request.getRequestDispatcher("/main.index").forward(request, response);
		}
		else if (path.equals("/eventdont.do")) {//메인에서 오늘하루동안 보지 않기 누르면오는곳 ajax사용했는데.. 굳이?
			command = new EventDontAction();
			command.execute(request, response);
		}
		else if (path.equals("/anytalk.do")) {//고객센터 - 하고싶은말
			command = new AnytalkListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/anytalk/anytalk.jsp").forward(request, response);
		}
		else if (path.equals("/olist.do")) {//관리자 - 회원관리
			command = new OListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/admin/omemberlist.jsp").forward(request, response);
		}
		else if (path.equals("/policy.do")) {//개인정보처리방침
			request.getRequestDispatcher("/agree/agree.jsp").forward(request, response);
		}
		else if (path.equals("/editanytalk.do")) {//anytalk 수정  == 반영안함
			command = new AnytalkEditAction();
			command.execute(request, response);
			request.getRequestDispatcher("/agree/agree.jsp").forward(request, response);
		}
		else if (path.equals("/mymatchdelete.do")) {//마이페이지 mypage 예약 취소하기
			command = new MyMatchDeleteAction();
			command.execute(request, response);
			request.getRequestDispatcher("/mymatch.do").forward(request, response);
		}
		else if (path.equals("/glike.do")) {//경기장 좋아요 누르기
			command = new GlikeAction();
			command.execute(request, response);
		}
		else if (path.equals("/introduce.do")) {//프로젝트 소개
			request.getRequestDispatcher("/intro/introduction.jsp").forward(request, response);
		}
		else if (path.equals("/myinfo.do")) {//프로젝트 소개
			request.getRequestDispatcher("/intro/myinfo.jsp").forward(request, response);
		}
		else if (path.equals("/navernews.do")) {//경기장 좋아요 누르기
			command = new NaverNewsAction();
			command.execute(request, response);
		}
		else if (path.equals("/festivaltalk.do")) {//아무말대잔치 누르기
			command = new FestivaltalkListAction();
			command.execute(request, response);
			request.getRequestDispatcher("/festivaltalk/festivaltalk.jsp").forward(request, response);
		}
		else if (path.equals("/addfestivaltalk.do")) {//아무말대잔치 등록하기
			command = new FestivaltalkWriteAction();
			command.execute(request, response);
		}
		else if (path.equals("/festivaltalkeditordelete.do")) {//아무말대잔치 수정/삭제하기
			command = new FestivaltalkAction();
			command.execute(request, response);
		}

	}// end actionPath

}// end class
