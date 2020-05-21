<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<div class="container myfirst">
<h3>프로젝트 소개</h3>
<div class="container">
<strong class="starcolor">1. 소개</strong>
<dl>
	<dt>프로젝트명</dt>
	<dd>JSP Web Project Outsider Sports</dd>
</dl>	
<dl>
	<dt>제작기간</dt>
	<dd>2020.02.03 ~ 2020.03.30 <strong>(자체제작 100%)</strong></dd>
</dl>
<dl>
	<dt>프로그램 기획의도 및 기대효과</dt>
	<dd>오프라인에서 즐기는 축구, 농구를 소속된 팀, 혹은 같이할 팀원이 없어도, 누구나 언제나 내가 원할 때 원하는 곳에서 즐길 수 있는 환경을 구축하여 스포츠 활동 증진에 기여</dd>
</dl>
<dl>
	<dt>사용 프로그램</dt>
	<dd>서버환경: tomcat 8.5</dd>
	<dd>Jsp: 2.3/servlet3.1 / Jdk8.0</dd>
	<dd>언어: Java, Javascript, JavaServerPage</dd>
	<dd>DB: MySQL 5.7 (호스팅), Oracle 11g (로컬)</dd>
	<dd>O/S: Windows</dd>
	<dd>Tool: Eclipse</dd>
</dl>
<dl>
	<dt>달성목표</dt>
	<dd>웹 프로젝트에 필요한 내용을 익히고, 프로젝트의 시작부터 끝까지 100% 직접 진행해봄으로써 설계, 디자인, 코딩 등에 필요한 기술활용 능력을 배양</dd>
</dl>

</div>
<div class="container">
<strong class="starcolor">2. 사이트맵</strong>
	<div class="row">
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/mlist.do">경기참여</a></div>
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/mwrite_view.do">경기개설</a></div>
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/glist.do">경기장보기</a></div>
		<div class="col-sm-3"><a href="${pageContext.request.contextPath}/join_view.do">회원가입</a></div>
	</div>
	<div class="row">
		<div class="col-sm-3">
			<a href="${pageContext.request.contextPath}/login_view.do">로그인</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/find_view.do?find=id">아이디찾기</a></li>
				<li><a href="${pageContext.request.contextPath}/find_view.do?find=pass">비밀번호찾기</a></li>
			</ul>
		</div>
		<div class="col-sm-3">
			<a href="${pageContext.request.contextPath}/myprofile.do">마이페이지</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/myprofile.do">나의설정</a></li>
				<li><a href="${pageContext.request.contextPath}/mymatch.do">나의예약</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/rwrite_view.do">이용후기쓰기</a></li>
						<li>예약취소하기</li>
					</ul>
				</li>
				<li><a href="${pageContext.request.contextPath}/myhost.do">나의경기</a></li>
			</ul>
		</div>
		<div class="col-sm-3">
			<a href="${pageContext.request.contextPath}/info.do">고객센터</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/info.do">이용안내</a></li>
				<li><a href="${pageContext.request.contextPath}/qlist.do">문의하기</a></li>
				<li><a href="${pageContext.request.contextPath}/slist.do">건의하기</a></li>
				<li><a href="${pageContext.request.contextPath}/shopping_view.api">물건사기</a></li>
				<li><a href="${pageContext.request.contextPath}/anytalk.do">하고싶은말</a></li>
				<li><a href="${pageContext.request.contextPath}/festivaltalk.do" title="아무말대잔치">아무말대잔치</a></li>
			</ul>
		</div>
		<div class="col-sm-3">
			<a href="${pageContext.request.contextPath}">관리자메뉴</a>
			<ul>
				<li>경기장추가</li>
				<li>경기장수정</li>
				<li>안내내용 쓰기</li>
				<li>안내내용 삭제</li>
				<li>회원관리</li>
			</ul>
		</div>
	</div>
</div>
<div class="container">
<strong class="starcolor">3. DB 테이블 구성</strong>
<p><img src="images/db_1.png" alt="db1.png"/></p>
<p><img src="images/db_2.png" alt="db1.png"/></p>
<p><img src="images/db_3.png" alt="db1.png"/></p>
</div>
<div class="container">
<strong class="starcolor">4. 느낀 점</strong>
<p> 첫째, DB설계의 중요성을 깨닫았습니다. DB설계를 하고, insert, select 문을 테스트해본 결과를 토대로 개발을 진행해 나갔는데, 정말 편했습니다. 테스트를 거치지 않고 개발을 시작했다면 시간이 더욱 오래걸리고 헤맸을 것이라 생각되었습니다.</p>
<p> 둘째, 첫 웹프로젝트였지만, 생각보다 훨씬 재밌고 이것 저것 시도하고 싶은 기능들이 많았습니다. 어떤 기능을 구현할지 생각하고, 수 차례 시도하고, 수정하고, 에러사항을 찾아내면서
배운 것도 많았고, 성공적으로 구현해냈을 때에 큰 성취감을 느꼈습니다.</p>
<p> 셋째, 정리의 중요성을 다시 한 번 알게되었습니다. 어디서 어떤 클래스, 어떤 메소드를 만들었고, 경로처리가 어떻게 되는지 알아 보기 쉽게 정리한 것이 큰 힘이 되었고, frontcontroller의 중요성을 직접 깨닫았습니다.</p>
<p> 마지막으로, 아직 배워야할 것이 많고 배우고 싶은 것이 알면 알 수록 더 많아지고 있어서 앞으로 꾸준히 성장해나가고 싶은 마음가짐이 생겼습니다.</p>
</div>
</div>
<%@ include file="../inc/footer.jsp"%>
 