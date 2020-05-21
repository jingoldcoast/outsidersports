<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<script>	
	jQuery(document).ready(function(){
		jQuery("#editform").submit(function(){
			if(jQuery("#spass").val()==""){
				alert("비밀번호를 입력하세요");
				$("#spass").focus();
				return false;
			}
			if(jQuery("#stitle").val()==""){
				alert("제목을 입력하세요");
				$("#stitle").focus();
				return false;
			}
			if(jQuery("#scontent").val()==""){
				alert("내용을 입력하세요");
				$("#scontent").focus();
				return false;
			}
			var spass = '<c:out value="${dto.spass}"/>';
			
			if(jQuery("#spass").val() != spass){
				alert("기존에 설정하신 비밀번호와 일치하지 않습니다.\n비밀번호를 다시 입력해주세요.");
				jQuery("#spass").focus();
				spass= null;
				return false;
			}
			spass = null;
			
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
			if(confirm("수정 진행하시겠습니까?")==false){
				return false;
			}
			
		});
	});
</script>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}'); </script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); history.go(-1);</script></c:when>
</c:choose>
<div class="container myfirst">
	<h3 class="myhidden">건의하기 글수정 </h3>
		<form action="${pageContext.request.contextPath}/sedit.do?sno=${dto.sno}&amp;page=${page}" method="post"  id="editform" enctype="multipart/form-data">
		   <fieldset>
		   <legend>건의글 수정하기</legend>
		   <input type="hidden" name="sno" value="${dto.sno}"/>
		   <input type="hidden" name="sfilebefore" value="${dto.sfile}"/>
			<div class="form-group">
			  <label for="sname"  >이름</label>
			  <input type="text"   name="sname"   id="sname"   class="form-control"   value="${dto.sname}"  disabled> 
			</div>			
			<div class="form-group">
			  <label for="spass" >비밀번호</label>
			  <input type="password"   name="spass"   id="spass"   class="form-control" > 
			</div>																
			<div class="form-group">
			  <label for="stitle"  >제목</label>
			  <c:choose>
			  	<c:when test="${dto.sstep eq ((dto.sgroup)*1000)}"><input type="text"   name="stitle"   id="stitle"   class="form-control"  value="${dto.stitle}"></c:when>
			 	<c:otherwise><input type="text"   name="stitle"   id="stitle"   class="form-control"  value="${dto.stitle}" disabled="disabled"></c:otherwise>
			  </c:choose>
			</div>	
			<div class="form-group">
			  <label for="scontent"  >내용</label>
			  <textarea name="scontent"  id="scontent"  cols="60"  rows="10"   class="form-control" >${dto.scontent}</textarea>
			</div>			
			<div class="form-group">
			  <label for="sfile">사진업로드 (최대 5MB)</label>
			  <input type="file" class="form-control" name="sfile" id="sfile" title="사진업로드" />
	 	 	</div>
			<div class="form-group  text-center">
				<input type="submit"   value="수정하기"   class="btn btnc" title="수정하기"/>  
				<a href="${pageContext.request.contextPath}/slist.do?page=${page}"  class="btn btny" title="목록보기">목록보기</a> 
			</div>
		 </fieldset>		
		</form> 	
</div>


<%@ include file="../inc/footer.jsp"%>