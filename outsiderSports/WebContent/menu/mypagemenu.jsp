<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>


<ul class="sidemenu">
<li><a href="${pageContext.request.contextPath}/myprofile.do" title="나의설정">나의설정</a></li>
<li><a href="${pageContext.request.contextPath}/mymatch.do" title="나의참여">나의참여</a></li>
<li><a href="${pageContext.request.contextPath}/myhost.do" title="나의경기">나의경기</a></li>
</ul>
