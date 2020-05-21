<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../inc/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result == 0}"><script>alert('비밀번호를 확인해주세요'); </script></c:when>
</c:choose>

<div class="container myfirst">
		<h3>문의 삭제</h3>
		<form action="${pageContext.request.contextPath}/qdelete.do" method="post"   id="deleteform"> 
		<input type="hidden" name="qno" value="${param.qno}"/>
			<div class="form-group">
			  <label for="qpass"  >비밀번호</label>
			  <input type="password"   name="qpass"   id="qpass"   class="form-control" > 
			  <strong>삭제 시 비밀번호 확인이 필요합니다.</strong>
			</div>
			<div class="form-group">
				<input type="submit" value=" 삭제하기 "   class="btn btnc" title="삭제하기"/>
				<input type="reset"  value="취소"  class="btn btn-default" title="취소"/>  
			</div>	
		</form>
</div>

<script>
	jQuery(document).ready(function(){
		jQuery("#deleteform").submit(function(){
			if(jQuery("#qpass").val()==""){
				alert("비밀번호을 입력해주세요");
				jQuery("#qpass").focus();
				return false;
			}
		});
	});
</script>
<%@ include file="../inc/footer.jsp" %>