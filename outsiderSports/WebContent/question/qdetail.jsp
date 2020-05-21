<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>

<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}'); </script></c:when>
	<c:when test="${requestScope.result == 0}"><script>alert('비밀번호를 확인해주세요!'); </script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); history.go(-1);</script></c:when>
</c:choose>

<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">상세보기</h3>
 <div class="form-group"><!-- 2 -->
 <table class="table table-hover">
<caption>문의글</caption>
 <colgroup>
    <col style="background-color:#e7e7e7">
    <col>
    <col style="background-color:#e7e7e7">
  </colgroup>
 <tbody>
 	<tr>
 		<th scope="row">문의종목</th>
 		<td>${dto.qcategory}</td>
 		<th scope="row">문의종류</th>
 		<td>${dto.qcase}</td>
 	</tr>
 	<tr>
 		<th scope="row">문의일</th>
 		<td>${dto.qdate.substring(0,10)}</td>
 		<th scope="row">답변일</th>
 		<td>${dto.qreplydate.substring(0,10)}</td>
 	</tr>
 	<tr>
 		<th scope="row">조회수</th>
 		<td><span class="badge">${dto.qhit}</span></td>
 		<th scope="row">처리상태</th>
 		<td>${dto.qstatus}</td>
 	</tr>
 	<tr>
 		<th scope="row">문의제목</th>
 		<td colspan="3">${dto.qtitle}</td>
 	</tr>
  	<tr>
 		<th scope="row">작성자</th>
 		<td colspan="3">${dto.qname}</td>
 	</tr>
 	<tr>
 		<th scope="row">문의내용</th>
 		<td colspan="3">${dto.qcontent}</td>
 	</tr>
 	<tr>
 		<th scope="row">답변 받을 email</th>
 		<td colspan="3">${dto.qemail}</td>
 	</tr>
 </tbody>
 </table>
 
 	<div class="form-group  text-center">
	<c:if test="${dto.qreplydate eq null}"><a href="${pageContext.request.contextPath}/qreply_view.do?qno=${dto.qno}&amp;page=${page}"  class="btn btnc" >답변하기</a></c:if>
		<a href="${pageContext.request.contextPath}/qedit_view.do?qno=${dto.qno}&amp;page=${page}"  class="btn btnc" >수정하기</a>
		<a href="${pageContext.request.contextPath}/delete_view.do?qno=${dto.qno}&amp;page=${page}"  class="btn btnc" >삭제하기</a>
		<a href="${pageContext.request.contextPath}/qlist.do?page=${page}" class="btn btny">목록보기</a>
	</div>
 
 
 <!-- 답변내용이 있다면 -->
 <c:if test="${dto.qreplydate ne null}">
 <div class="form-group">
  <table class="table table-hover">
 <caption>답변글</caption>
 <tbody>
  	<tr>
 		<th scope="row">제목</th>
 		<td>${dto.qreplytitle}</td>
 	</tr>
  	<tr>
 		<th scope="row">작성자</th>
 		<td>${dto.qreplyname}</td>
 	</tr>
  	<tr>
 		<th scope="row">답변내용</th>
 		<td>${dto.qreplycontent}</td>
 	</tr>
 
 </tbody>
  </table>
 </div>
 </c:if>
  <!-- 답변내용이 있다면 끝 -->
</div><!-- 2 -->
</div><!-- 1 -->

<%@ include file="../inc/footer.jsp"%>