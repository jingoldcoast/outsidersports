<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="../inc/header.jsp"%>
 <div class="container myfirst">
	<h3 class="myhidden">답글하기 </h3>
		<form action="${pageContext.request.contextPath}/sreply.do?sno=${dto.sno}&amp;page=${page}" method="post"  id="replyform" enctype="multipart/form-data">
		   <fieldset>
		   <legend>답글하기</legend>
		   <input type="hidden" name="sno" value="${dto.sno}"/>
		   <input type="hidden" name="sgroup" value="${dto.sgroup}"/>
		   <input type="hidden" name="sstep" value="${dto.sstep}"/>
		   <input type="hidden" name="sindent" value="${dto.sindent}"/>
		   <input type="hidden" name="sfile" value="${dto.sfile}"/>
			<div class="form-group">
			  <label for="sname"  >이름</label>
			   <c:choose>
			  	<c:when test="${sessionScope.oid eq null}"> <input type="text"   name="sname"   id="sname"   class="form-control" />  </c:when>
			  	<c:otherwise> <input type="text"   name="sname"   id="sname"   class="form-control"  value="${sessionScope.oid}" readonly="readonly"/> </c:otherwise>
			  </c:choose>
			</div>			
			<div class="form-group">
			  <label for="spass"  >비밀번호</label>
			  <input type="password"   name="spass"   id="spass"   class="form-control" > 
			  <span>(*) 수정, 삭제시 필수</span>
			</div>																
			<div class="form-group">
			  <label for="stitle"  >제목</label>
			  <input type="text"   name="stitle"   id="stitle"   class="form-control"  value="${dto.stitle}" readonly> 
			  <span>답글은 제목을 수정할 수 없습니다</span>
			</div>	
			<div class="form-group">
			  <label for="scontent"  >내용</label>
			  <textarea name="scontent"  id="scontent"  cols="60"  rows="10"   class="form-control" >${dto.scontent}</textarea>
			</div>		
			<div class="form-group">
			  <label for="sfile">사진업로드 (최대 5MB)</label>
			  <input type="file" class="form-control" name="sfile" id="sfile"/>
	 	 	</div>			
			<div class="form-group  text-center">
				<input type="submit"   value="답글하기" title="답글하기"  class="btn btnc"/>  
				<a href="${pageContext.request.contextPath}/slist.do?page=${page}"  class="btn btny"  title="목록보기" >목록보기</a> 
			</div>
		 </fieldset>		
		</form> 	
</div>
<script>	
	jQuery(document).ready(function(){
		jQuery("#replyform").submit(function(){
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
		});
	});
</script>
 
<%@ include file="../inc/footer.jsp"%>
