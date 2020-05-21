<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
  <%@ include file="/inc/header.jsp"%>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}'); </script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); history.go(-1);</script></c:when>
</c:choose>
<div class="container myfirst suggest">
	<h3 class="myhidden">건의하기 상세보기</h3> 					
	<div class="panel" >
	  <div  class="panel-body"> 
	  		<span class="glyphicon glyphicon-eye-open">  조회수</span>
	  		<p>${dto.shit}</p>
	</div>	
	</div>	
	<div class="panel"  >
	  <div  class="panel-body">
	  		<span class="glyphicon glyphicon-user">  이름</span> 
			<p>${dto.sname}</p>
		</div>
	</div>				
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-pencil">  제목</span> 
	     <p>${dto.stitle}</p>
	  </div>	
	</div>
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-pencil">  내용</span>
	  	<p>${fn:replace(dto.scontent, crcn, br)}</p>

	  </div> 
	</div>	
		<div class="panel"  >
		  <div  class="panel-body">
		  	<span class="glyphicon glyphicon-picture">  사진</span>
		  	<p><a href="${pageContext.request.contextPath}/upload/${dto.sfile}"><img src="${pageContext.request.contextPath}/upload/${dto.sfile}" alt="${dto.sfile}"/></a></p>
		  	<p>${dto.sfile}</p>
		  </div> 
		</div>
	
	<div class="form-group text-center">
 		 <a href="${pageContext.request.contextPath}/sreply_view.do?sno=${dto.sno}&amp;page=${page}"  class="btn btnc"  title="답글하기">답글하기</a> 
		 <a href="${pageContext.request.contextPath}/sedit_view.do?sno=${dto.sno}&amp;page=${page}"  class="btn btnc" title="수정하기">수정하기</a> 
		 <a href="${pageContext.request.contextPath}/sdelete_view.do?sno=${dto.sno}"  class="btn btnc" title="삭제하기">삭제하기</a>    
		 <a href="${pageContext.request.contextPath}/slist.do?page=${page}"  class="btn btny" title="목록보기">목록보기</a> 
	</div>	
						 
</div>	

  
  <%@ include file="/inc/footer.jsp"%>