<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
<div class="container myfirst mypage"><!-- 1 -->
<div class="form-group">
<div class="col-sm-2">
<%@ include file="../menu/mypagemenu.jsp" %>
</div>
	<div class="col-sm-10">
			<h3>나의참여</h3>
			<p>${sessionScope.oid}님은 현재까지 <strong class="starcolor">총 ${cnt}번 경기</strong>에 참여하였습니다!</p>
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#menu0">축구</a></li>
				<li><a data-toggle="tab" href="#menu1">농구</a></li>
			</ul>

			<div class="tab-content">
				<div id="menu0" class="tab-pane fade in active">
					<table class="table table-striped">
					<caption>나의참여</caption>
					<thead>
						<tr>
							<th scope="col">경기 이름</th>
							<th scope="col">경기 날짜</th>
							<th scope="col">경기장 주소</th>
							<th scope="col">이용 후기</th>
							<th scope="col">예약 취소</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list}">
					<c:choose>
					<c:when test="${list.gcategory eq '축구'}">
						<tr>
							<td><a href="${pageContext.request.contextPath}/mdetail.do?mno=${list.mno}">${list.mtitle}</a></td>
							<td>${list.mdate.substring(0, 10)} / ${list.mhour} 시</td>
							<td>${list.gaddr1}</td>
							<td><a href="${pageContext.request.contextPath}/rwrite_view.do?mno=${list.mno}&amp;gno=${list.gno}&amp;mhost=${list.mhost}&amp;gname=${list.gname}" class="btn btnc">쓰기</a></td>
							<td><a href="${pageContext.request.contextPath}/mymatchdelete.do?mno=${list.mno}&amp;mprice=${list.mprice}" class="btn btnc">취소</a></td>
						</tr>
					</c:when>
					</c:choose>
					</c:forEach>
					</tbody>
					</table>
				</div>
				<div id="menu1" class="tab-pane fade">
					<table class="table table-striped">
					<caption>나의예약</caption>
					<thead>
						<tr>
							<th scope="col">경기 이름</th>
							<th scope="col">경기 날짜</th>
							<th scope="col">경기장 주소</th>
							<th scope="col">이용 후기</th>
							<th scope="col">예약 취소</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list}">
					<c:choose>
					<c:when test="${list.gcategory eq '농구'}">
						<tr>
							<td><a href="${pageContext.request.contextPath}/mdetail.do?mno=${list.mno}">${list.mtitle}</a></td>
							<td>${list.mdate.substring(0, 10)} / ${list.mhour} 시</td>
							<td>${list.gaddr1}</td>
							<td><a href="${pageContext.request.contextPath}/rwrite_view.do?mno=${list.mno}&amp;gno=${list.gno}&amp;mhost=${list.mhost}&amp;gname=${list.gname}" class="btn btnc">쓰기</a></td>
							<td><a href="${pageContext.request.contextPath}/mymatchdelete.do?mno=${list.mno}&amp;mprice=${list.mprice}" class="btn btnc">취소</a></td>
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