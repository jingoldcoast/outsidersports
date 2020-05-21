<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
  <c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}'); </script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
<div class="container myfirst" ><!-- 1 -->

<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/adminmenu.jsp" %>
	</div>
	<div class="col-sm-10" >
		<h3>관리자메뉴</h3>
		<p>관리자 페이지입니다. 원하는 메뉴를 선택하세요</p>
	</div>
</div>

</div>
<%@ include file="/inc/footer.jsp"%>