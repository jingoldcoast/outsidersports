<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 

<ul class="sidemenu">
<li><a href="${pageContext.request.contextPath}/gwrite_view.do">경기장 추가</a></li>
<li><a href="${pageContext.request.contextPath}/gedit_select.do">경기장 수정</a></li>
<li><a href="${pageContext.request.contextPath}/nwrite_view.do">안내내용 쓰기</a></li>
<li><a href="${pageContext.request.contextPath}/ndelete_view.do">안내내용 삭제</a></li>
<li><a href="${pageContext.request.contextPath}/olist.do">회원관리</a></li>
</ul>