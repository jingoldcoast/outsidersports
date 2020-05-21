<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="../inc/header.jsp" %>
<script>
jQuery(document).ready(function(){
	jQuery("#editform").submit(function(){
		if(jQuery("#gno").val()==""){ alert("수정할 경기장을 선택해주세요!"); jQuery("#gno").focus(); return false;}
	});
});
</script>
<div class="container myfirst">
	<h3 class="myhidden">경기장  수정</h3>
		<form action="${pageContext.request.contextPath}/gedit_view.do" method="post"  id="editform">
		   <fieldset>
		   <legend>경기장 수정</legend>
			<div class="form-group">
			  <label for="gno" >수정할 경기장 선택</label>
			   <select name="gno" id="gno" title="수정할 경기장 선택" class="form-control">
                 <option value="">선택</option>
                 <c:forEach var="list" items="${list}" varStatus="status">
                 	<option value="${list.gno}">${list.gname}</option>
                 </c:forEach>
           	 	</select>
			</div>	
			<div class="form-group  text-center">
				<input type="submit"   value="수정하러가기"  class="btn btnc" title="수정하러가기"/>  
				<input type="reset"    value="취소"  class="btn btn-default" title="취소"/>  
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>
<%@ include file="../inc/footer.jsp" %>