<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp" %>
<script>
jQuery(document).ready(function(){
	jQuery("#writeform").submit(function(){
		if(jQuery("#ncategory").val()==""){
			alert('카테고리를 선택해주세요!');
			jQuery("#ncategory").focus();
			return false;
		}
		if(jQuery("#ntitle").val()==""){
			alert("제목을 입력해주세요!");
			jQuery("#ntitle").focus();
			return false;
		}
		if(jQuery("#ncontent").val()==""){
			alert('내용을 입력해주세요!');
			jQuery("#ncontent").focus();
			return false;
		}
	});
});
</script>

<div class="container myfirst">
	<h3 class="myhidden">안내 내용쓰기 </h3>
		<form action="${pageContext.request.contextPath}/nwrite.do" method="post"  id="writeform">
		   <fieldset>
		   <legend>안내 내용쓰기</legend>
		   <strong>(*) 은 필수입력사항입니다.</strong>
			<div class="form-group">
			  <label for="ncategory" >카테고리(*)</label>
			  <select name="ncategory" id="ncategory" title="카테고리 선택" class="form-control">
                 <option value="">선택</option>
                 <option value="n1">공지사항</option>
                 <option value="n6">서비스</option>
                 <option value="n2">경기진행</option>
                 <option value="n3">경기취소</option>
                 <option value="n5">경기예약</option>
                 <option value="n4">결제</option>
           	  </select>
			</div>																		
			<div class="form-group">
			  <label for="ntitle" >제목(*)</label>
			  <input type="text" name="ntitle" id="ntitle" class="form-control" title="제목"> 
			</div>	
			<div class="form-group">
			  <label for="ncontent">내용(*)</label>
			  <textarea name="ncontent"  id="ncontent"  cols="60"  rows="10"   class="form-control" title="내용"></textarea>
			</div>	
			<div class="form-group text-center">
				<input type="submit"  value="내용쓰기" class="btn btnc" title="내용쓰기"/>  
				<input type="reset" value="취소" class="btn btn-default" title="취소"/>  
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>
<%@ include file="../inc/footer.jsp" %>