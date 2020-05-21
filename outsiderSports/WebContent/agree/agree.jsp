<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<div class="container myfirst">
<h3 class="myhidden">개인정보처리방침</h3>
<strong>서비스이용약관</strong><br/>
<textarea class="form-control" name="companyServiceAgree" rows="50" readonly><%@ include file="/agree/agree1.txt" %></textarea>
<strong>개인정보처리방침</strong><br/>
<textarea class="form-control" name="companyServiceAgree" rows="20" readonly><%@ include file="/agree/agree2.txt" %></textarea>
</div><!-- 큰 div -->
<%@ include file="../inc/footer.jsp"%>