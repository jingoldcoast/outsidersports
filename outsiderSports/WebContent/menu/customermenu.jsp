<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 

<ul class="sidemenu">
<li><a href="${pageContext.request.contextPath}/info.do" title="이용안내">이용안내</a></li>
<li><a href="${pageContext.request.contextPath}/qlist.do" title="문의하기">문의하기</a></li>
<li><a href="${pageContext.request.contextPath}/slist.do" title="건의하기">건의하기</a></li>
<li><a href="${pageContext.request.contextPath}/shopping_view.api" title="물건사기">물건사기</a></li>
<li><a href="${pageContext.request.contextPath}/anytalk.do" title="하고싶은말">하고싶은말</a></li>
<li><a href="${pageContext.request.contextPath}/festivaltalk.do" title="아무말대잔치">아무말대잔치</a></li>
</ul>