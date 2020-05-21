<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
  <script>	
	jQuery(document).ready(function(){
		jQuery("#editpassform").submit(function(){
			if(jQuery("#opass").val()==""){
				alert("현재 비밀번호를 입력해주세요!");
				jQuery("#opass").focus();
				return false;
			}
			if(jQuery("#newopass1").val()==""){
				alert("새 비밀번호를 입력해주세요!");
				jQuery("#newopass1").focus();
				return false;
			}
			if(jQuery("#newopass2").val()==""){
				alert("새 비밀번호 확인을 입력해주세요!");
				jQuery("#newopass2").focus();
				return false;
			}
			if(jQuery("#newopass1").val() != jQuery("#newopass2").val()){
				alert("비밀번호가 일치하지 않습니다!");
				jQuery("#newopass1").focus();
				return false;
			}
			if(confirm("비밀번호 수정을 진행하시겠습니까?")==false){
				return false;
			}
		});
	});
</script>
 
 <c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result == 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
 
<div class="container myfirst">
<h3 class="myhidden">비밀번호 변경</h3>
	<form action="${pageContext.request.contextPath}/mypassedit.do" method="post" id="editpassform">
		<fieldset>
			<legend>비밀번호 변경</legend>
			<div class="form-group">
				<strong>아이디</strong>
				<input type="text" name="oid" value="${param.oid}" readonly class="form-control"/>
			</div>
			<div class="form-group">
				<label for="opass">현재 비밀번호</label> 
				<input type="password" name="opass" id="opass" class="form-control" placeholder="현재 비밀번호"/>
			</div>
			<div class="form-group">
				<label for="newopass1">새 비밀번호</label> 
				<input type="password" name="newopass1" id="newopass1" class="form-control" placeholder="새 비밀번호"/>
			</div>
			<div class="form-group">
				<label for="newopass2">새 비밀번호 확인</label> 
				<input type="password" name="newopass2" id="newopass2" class="form-control" placeholder="새 비밀번호 확인"/>
			</div>
			<div class="form-group  text-center">
				<input type="submit" class="btn btnc" value="비밀번호 수정하기" title="비밀번호 수정하기"/>
				<input type="reset"    value="취소"  class="btn btn-default" title="취소"/> 
			</div>
		</fieldset>
	</form>

</div>
<%@ include file="/inc/footer.jsp"%>