<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
  <c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
<div class="omemberlist" ><!-- 1 -->
		<h3>OutsiderSports 회원 전체</h3>
			<table class="table table-hover">
				<caption>가입된 회원 정보</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">아이디</th>
						<th scope="col">이름</th>
						<th scope="col">회원등급</th>
						<th scope="col">경기참여횟수</th>
						<th scope="col">포인트사용량</th>
						<th scope="col">잔여포인트</th>
						<th scope="col">경기개설횟수</th>
						<th scope="col">평균평점</th>
						<th scope="col">생년월일</th>
						<th scope="col">성별</th>
						<th scope="col">연락처</th>
						<th scope="col">가입날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${list.oid}</td>
							<td>${list.oname}</td>
							<td>${list.ograde}</td>
							<td>${list.matchcnt} 회</td>
							<td><fmt:formatNumber value="${list.usedpoint}" pattern="#,###"/>점</td>
							<td><fmt:formatNumber value="${list.opoint}" pattern="#,###"/>점</td>
							<td>${list.matchAsHost} 회</td>
							<td><fmt:formatNumber value="${list.hstar}" pattern=".00"/></td>
							<td>${list.obirth.substring(0, 10)}</td>
							<td>${list.osex}</td>
							<td>${list.ocontact}</td>
							<td>${list.odate.substring(0,10)}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="13" style="text-align:center">
							<ul class="pagination">
								<c:if test="${paging.pre_bottom>=paging.bottomlist}">
									<li><a href="${pageContext.request.contextPath}/olist.do?page=${(paging.pre_bottom-2)*paging.onepagelimit}">이전</a></li>
								</c:if>
								<c:forEach var="i" begin="${paging.pre_bottom}" end="${paging.next_bottom}" step="${1}" varStatus="status">
									<c:choose>
										<c:when test="${paging.current_bottom==i}">
										<li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/olist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
										</c:when>
										<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/olist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
										</c:otherwise>
									</c:choose>	
								</c:forEach>
								<c:if test="${paging.next_bottom<paging.pageAll}">
									<li><a href="${pageContext.request.contextPath}/olist.do?page=${paging.next_bottom*paging.onepagelimit}">다음</a></li>
								</c:if>
							</ul>
						</td>
					</tr>
				</tfoot>
			</table>
</div>
<%@ include file="/inc/footer.jsp"%>