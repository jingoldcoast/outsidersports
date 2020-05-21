<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp" %>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result >= 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"> <script>alert('처리 실패\n관리자에게 문의해주세요'); </script> </c:when>
</c:choose>
<script>	
	jQuery(document).ready(function(){
		jQuery("#editform").submit(function(){
			if(jQuery("#oname").val()==""){
				alert("이름을 입력해주세요!");
				jQuery("#oname").focus();
				return false;
			}
			if(jQuery("#ocontact").val()==""){
				alert("연락처를 입력해주세요!");
				jQuery("#ocontact").focus();
				return false;
			}
			if(confirm("정보 수정을 진행하시겠습니까?")==false){
				return false;
			}
		});
		jQuery("#chargeform").submit(function(){
			if(jQuery("#spoint").val()==""){
				alert("충전할 포인트를 입력하세요");
				jQuery("#spoint").focus();
				return false;
			}
		});
	});
</script>

<div class="container myfirst mypage"><!-- 1 -->
<div class="form-group">
	<div class="col-sm-2">
		<%@ include file="../menu/mypagemenu.jsp" %>
	</div>
	<div class="col-sm-10">
		<h3>나의설정</h3>
		<form action="${pageContext.request.contextPath}/myinfoedit.do" method="post" id="editform">
		<input type="hidden" name="oid" value="${dto.oid}"/>
		<table class="table table-striped">
		<caption>회원정보 설정</caption>
		<tbody>
			<tr>
				<th scope="row">아이디</th>
				<td>${dto.oid}</td>
			</tr>		
			<tr>
				<th scope="row">이름</th>
				<td><input type="text" name="oname" id="oname" value="${dto.oname}" class="form-control"/></td>
			</tr>		
			<tr>
				<th scope="row">비밀번호</th>
				<td>
					<a href="${pageContext.request.contextPath}/mypassedit_view.do?oid=${dto.oid}" class="btn btnc">비밀번호 수정하기</a>
				</td>
			</tr>		
			<tr>
				<th scope="row">생년월일</th>
				<td>${dto.obirth.substring(0,10)}</td>
			</tr>			
			<tr>
				<th scope="row">연락처</th>
				<td><input type="text" name="ocontact" id="ocontact" value="${dto.ocontact}" class="form-control"/></td>
			</tr>		
			<tr>
				<th scope="row">보유 포인트</th>
				<td><fmt:formatNumber value="${dto.opoint}" pattern="#,###"/>점 <button type="button" class="btn btnc" data-toggle="modal" data-target="#myModal">충전하기</button></td>
			</tr>		
			<tr>
				<th scope="row">회원등급</th>
				<td>${dto.ograde}</td>
			</tr>		
		</tbody>
		</table>
		<div class="form-group  text-center">
			<input type="submit"   value="수정하기"  class="btn btnc" title="수정하기"/>  
		</div>
		</form>
	</div>
</div>
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
		<form action="${pageContext.request.contextPath}/spoint_charge.do" method="post" id="chargeform">
		<input type="hidden" name="oid" value="${dto.oid}"/>
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">포인트 충전하기</h4>
				</div>
				<div class="modal-body">
					<label for="opoint">충전할 포인트</label>
					<input type="text" name="opoint" id="opoint" placeholder="충전할 포인트를 입력하세요(숫자만)" class="form-control"/>
				</div>
				<div class="modal-footer">
					<input type="submit"   value="충전하기"  class="btn btnc" title="충전하기"/>  
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</form>
		</div>
	</div>
	<!-- 모달 메세지 div끝 -->
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp" %>