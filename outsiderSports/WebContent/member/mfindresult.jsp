<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>

<div class="container myfirst">
<h3>나의 정보</h3>
<div class="panel" >
	  	<div  class="panel-body"> 
		  		<c:choose>
				<c:when test="${param.find =='id'}">
					<span class="glyphicon glyphicon-search"> 아이디</span>
				</c:when>
				<c:when test="${param.find =='pass'}">
					<span class="glyphicon glyphicon-search"> 비밀번호</span>
				</c:when>
				</c:choose>
		  		<c:choose>
				<c:when test="${result==''}">
					<p>입력하신 정보와 일치하는 정보가 없습니다.<br/>회원가입 여부를 확인해주세요!</p>
				</c:when>
				<c:otherwise><p>${result}</p></c:otherwise>
				</c:choose>
		</div>	
</div>
</div>
<%@ include file="../inc/footer.jsp"%>