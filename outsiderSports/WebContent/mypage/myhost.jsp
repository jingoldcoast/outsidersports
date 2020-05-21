<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>

<div class="container myfirst mypage"><!-- 1 -->
<div class="form-group">
<div class="col-sm-2">
<%@ include file="../menu/mypagemenu.jsp" %>
</div>
	<div class="col-sm-10">
			<h3>나의경기</h3>
			<p>${sessionScope.oid}님은 현재까지 <strong class="starcolor">총 ${cnt}번 경기</strong>를 개설하였습니다!</p>
			<p>현재 '내가 받은 평점'은 <strong class="starcolor">${myhstar} </strong>입니다! </p>
			<p>앞으로도 매니저로서 좋은 활동 부탁드려요 : )  고맙습니다!</p>
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#menu0">축구</a></li>
				<li><a data-toggle="tab" href="#menu1">농구</a></li>
			</ul>

			<div class="tab-content">
				<div id="menu0" class="tab-pane fade in active">
					<table class="table table-striped">
					<caption>나의 경기</caption>
					<thead>
						<tr>
							<th scope="col">개설한 날짜</th>
							<th scope="col">경기 이름</th>
							<th scope="col">경기 날짜</th>
							<th scope="col">참여 비용</th>
							<th scope="col">신청자 / 총 참여가능인원</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list}">
					<c:choose>
					<c:when test="${list.gcategory eq '축구'}">
						<tr>
							<td>${list.mcreatedate.substring(0, 10)}</td>
							<td><a href="${pageContext.request.contextPath}/mdetail.do?mno=${list.mno}">${list.mtitle}</a></td>
							<td>${list.mdate.substring(0, 10)} / ${list.mhour} 시</td>
							<td><fmt:formatNumber value="${list.mprice}" pattern="#,###"/>원</td>
							<td>${list.mtotal - list.cnt} / ${list.mtotal}</td>
						</tr>
					</c:when>
					</c:choose>
					</c:forEach>
					</tbody>
					</table>
				</div>
				<div id="menu1" class="tab-pane fade">
					<table class="table table-striped">
					<caption>나의 경기</caption>
					<thead>
						<tr>
							<th scope="col">개설한 날짜</th>
							<th scope="col">경기 이름</th>
							<th scope="col">경기진행 날짜</th>
							<th scope="col">참여 비용</th>
							<th scope="col">신청자 / 총 참여가능인원</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list}">
					<c:choose>
					<c:when test="${list.gcategory eq '농구'}">
						<tr>
							<td>${list.mcreatedate.substring(0, 10)}</td>
							<td><a href="${pageContext.request.contextPath}/mdetail.do?mno=${list.mno}">${list.mtitle}</a></td>
							<td>${list.mdate.substring(0, 10)} / ${list.mhour} 시</td>
							<td><fmt:formatNumber value="${list.mprice}" pattern="#,###"/>원</td>
							<td>${list.mtotal - list.cnt} / ${list.mtotal}</td>
						</tr>
					</c:when>
					</c:choose>
					</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
	</div>
</div>
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>