<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp" %>
<script>
jQuery(document).ready(function(){
	jQuery("#writeform").submit(function(){
		if(jQuery("#sname").val()==""){
			alert('이름을 입력해주세요!');
			jQuery("#sname").focus();
			return false;
		}
		if(jQuery("#spass").val()==""){
			alert("비밀번호를 입력해주세요!");
			jQuery("#spass").focus();
			return false;
		}
		if(jQuery("#stitle").val()==""){
			alert('제목을 입력해주세요!');
			jQuery("#stitle").focus();
			return false;
		}
		if(jQuery("#scontent").val()==""){
			alert("건의 내용을 입력해주세요!'");
			jQuery("#scontent").focus();
			return false;
		}
		var reg = /^<(\/)?([a-zA-Z0-9]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>$/;
		var target= $("#scontent").val();
		if(reg.test(target)){
			alert("내용입력 실패! \n html 태그는 삽입할 수 없습니다!");
			$("#scontent").focus();
			reg = null;
			target = null;
			return false;
		}
		reg = null;
		target = null;
		if(confirm("건의 진행하시겠습니까?")==false){
			return false;
		}
	});
});
</script>

<div class="container myfirst">
	<h3 class="myhidden">건의하기 글쓰기</h3>
		<form action="${pageContext.request.contextPath}/swrite.do" method="post"  id="writeform" enctype="multipart/form-data">
		   <fieldset>
		   <legend>건의내용쓰기</legend>
			<div class="form-group">
			  <label for="sname"  >이름</label>
			  <c:choose>
			  	<c:when test="${sessionScope.oid eq null}"><input type="text"   name="sname"   id="sname"   class="form-control" > </c:when>
			  	<c:otherwise><input type="text"   name="sname"   id="sname"   class="form-control" value="${sessionScope.oid}" readonly="readonly"></c:otherwise>
			  </c:choose>
			</div>			
			<div class="form-group">
			  <label for="spass"  >비밀번호</label>
			  <input type="password"   name="spass"   id="spass"   class="form-control" > 
			  <span>(*) 수정, 삭제시 필수</span>
			</div>																
			<div class="form-group">
			  <label for="stitle"  >제목</label>
			  <input type="text"   name="stitle"   id="stitle"   class="form-control" > 
			</div>	
			<div class="form-group">
			  <label for="scontent">건의 내용</label>
			  <textarea name="scontent"  id="scontent"  cols="60"  rows="10"   class="form-control" ></textarea>
			</div>	
			<div class="form-group">
			  <label for="sfile">사진업로드 (최대 5MB)</label>
			  <input type="file" class="form-control" name="sfile" id="sfile"/>
	 	 	</div>			
			<div class="form-group  text-center">
				<input type="submit"   value="건의하기"  class="btn btnc" title="건의하기"/>  
				<input type="reset"    value="취소"  class="btn btn-default" title="취소"/>  
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>
<%@ include file="../inc/footer.jsp" %>