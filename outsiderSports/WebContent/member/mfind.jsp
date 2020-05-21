<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>

<div class="container myfirst">
		<h3 class="myhidden">나의 정보 찾기</h3>
		<form action="${pageContext.request.contextPath}/find.do" method="post" id="findform">
		<fieldset>
		<legend>나의 정보찾기</legend>
		<c:choose>
			<c:when test="${param.find == 'id'}">
			<div class="form-group">
			  <input type="hidden" name="find" value="id"/>
			  <label for="ocontact" >가입하신 연락처를 입력해주세요</label>
			  <input type="text" name="ocontact" id="ocontact" class="form-control" placeholder="연락처 입력" title="연락처 입력"> 
			</div>
			<div class="form-group">
				<input type="submit" value=" 아이디찾기"   class="btn btnc" title="아이디찾기"/>
				<input type="reset"  value="취소"  class="btn btn-default" title="취소"/>  
			</div>	
			</c:when>
			<c:when test="${param.find == 'pass'}">
			<div class="form-group">
			  <input type="hidden" name="find" value="pass"/>
			  <label for="ocontact" >가입하신 아이디를 입력해주세요</label>
			  <input type="text" name="oid" id="oid" class="form-control" placeholder="아이디 입력" title="아이디 입력"> 
			</div>
			<div class="form-group">
				<input type="submit" value="비밀번호찾기" class="btn btnc" title="비밀번호찾기"/>
				<input type="reset" value="취소"  class="btn btn-default" title="취소"/>  
			</div>	
			</c:when>
		</c:choose>
		</fieldset>
		</form>
</div>

<script>
	jQuery(document).ready(function(){
		jQuery("#findform").submit(function(){
			if(jQuery("#ocontact").val()==""){
				alert("연락처를 입력해주세요");
				jQuery("#ocontact").focus();
				return false;
			}
			if(jQuery("#oid").val()==""){
				alert("아이디를 입력해주세요");
				jQuery("#oid").focus();
				return false;
			}
		});
	});
</script>
<%@ include file="../inc/footer.jsp"%>