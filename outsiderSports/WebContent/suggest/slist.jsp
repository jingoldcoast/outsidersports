<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
 <c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
<div class="container myfirst customer"><!-- 1 -->

<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/customermenu.jsp" %>
	</div>
	<div class="col-sm-10" >
			<h3 class="myhidden">건의하기 전체</h3>
			<table class="table table-striped">
				<caption>건의하기</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">글제목</th>
						<th scope="col">글쓴이</th>
						<th scope="col">글쓴날</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list}" varStatus="status">
						<tr>
							<td>${paging.pageTotal-status.index-paging.page}</td>
							<td><a
								href="${pageContext.request.contextPath}/sdetail.do?sno=${list.sno}&amp;page=${page}">${list.stitle}</a></td>
							<td>${list.sname}</td>
							<td>${list.sdate.substring(0,10)}</td>
							<td><span class='badge'>${list.shit}</span></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center">
							<ul class="pagination">
								<c:if test="${paging.pre_bottom>=paging.bottomlist}">
									<li><a href="${pageContext.request.contextPath}/slist.do?page=${(paging.pre_bottom-2)*paging.onepagelimit}">이전</a></li>
								</c:if>
								<c:forEach var="i" begin="${paging.pre_bottom}" end="${paging.next_bottom}" step="${1}" varStatus="status">
									<c:choose>
										<c:when test="${paging.current_bottom==i}">
										<li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/slist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
										</c:when>
										<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/slist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
										</c:otherwise>
									</c:choose>	
								</c:forEach>
								<c:if test="${paging.next_bottom<paging.pageAll}">
									<li><a href="${pageContext.request.contextPath}/slist.do?page=${paging.next_bottom*paging.onepagelimit}">다음</a></li>
								</c:if>
							</ul>
						</td>
					</tr>
				</tfoot>
			</table>
			<p class="text-right">
				<a href="${pageContext.request.contextPath}/swrite_view.do" title="건의하기" class="btn btnc">건의하기</a>
			</p>
		</div>
</div>
</div>
<%@ include file="/inc/footer.jsp"%>