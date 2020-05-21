<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>OutsiderSports</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="css/kimheejin101.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="http://jingoldcoast.cafe24.com/outsidersports/favicon/myfavicon.ico">
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="60">
<div>
<h1 class="myhidden">h1: Outsider Sports(상호명)</h1>
	<nav class="navbar navbar-default navbar-fixed-top">
	<h2 class="myhidden">h2: 주메뉴</h2>
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/madeLogo.png" alt="LOGO" title="로고"></a>
	    </div>
	    <div class="collapse navbar-collapse myfont" id="myNavbar">
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${pageContext.request.contextPath}/mlist.do" title="경기참여">경기참여</a></li>
		        <li><a href="${pageContext.request.contextPath}/mwrite_view.do" title="경기개설">경기개설</a></li>
		        <li><a href="${pageContext.request.contextPath}/glist.do" title="경기장보기">경기장보기</a></li>
		        <c:choose>
		        	<c:when test="${sessionScope.oid eq null }">
		        		<li><a href="${pageContext.request.contextPath}/join_view.do" title="회원가입">회원가입</a></li>
		        		<li><a href="${pageContext.request.contextPath}/login_view.do" title="로그인">로그인</a></li>
		        	</c:when>
		        	<c:otherwise>
		        		<li><a href="${pageContext.request.contextPath}/myprofile.do" title="마이페이지">${sessionScope.oid }</a></li>
		        		<c:choose>
		        			<c:when test="${sessionScope.header eq null }"><li><a href="${pageContext.request.contextPath}/logout.do" title="로그아웃">로그아웃</a></li></c:when>
		        			<c:otherwise><li><a href="${pageContext.request.contextPath}/kakao.logout" title="로그아웃">로그아웃</a></li></c:otherwise>
		        		</c:choose>
		        	</c:otherwise>
		        </c:choose>
		        <li><a href="${pageContext.request.contextPath}/info.do" title="고객센터">고객센터</a></li>
		        <c:if test="${sessionScope.oid eq 'admin'}">
		         <li><a href="${pageContext.request.contextPath}/admin.do" title="관리자메뉴">관리자메뉴</a></li>
		        </c:if>
		      </ul>
	    </div>
	  </div>
	</nav>
</div>