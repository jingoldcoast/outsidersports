<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp" %>
<script>
jQuery(document).ready(function(){
	jQuery("#deleteform").submit(function(){
		if(jQuery("#ntitle").val()==""){
			alert('삭제할 제목을 입력해주세요!');
			jQuery("#ntitle").focus();
			return false;
		}
		if(confirm("삭제를 진행하시겠습니까?")==false){
			return false;
		}
	});
});
</script>

<div class="container myfirst">
	<h3 class="myhidden">안내 내용 삭제하기 </h3>
		<form action="${pageContext.request.contextPath}/ndelete.do" method="post"  id="deleteform">
		   <fieldset>
		   <legend>안내 내용삭제</legend>
		   <strong>(*) 은 필수입력사항입니다.</strong>
			<div class="form-group">
			  <label for="ntitle" >삭제할 안내내용 제목(*)</label>
			  <input type="text" name="ntitle" id="ntitle" class="form-control" title="삭제할제목"> 
			</div>	
			<div class="form-group  text-center">
				<input type="submit" value="삭제하기" class="btn btnc" title="삭제하기"/>  
				<input type="reset"  value="취소" class="btn btn-default" title="취소"/>  
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>
<%@ include file="../inc/footer.jsp" %>