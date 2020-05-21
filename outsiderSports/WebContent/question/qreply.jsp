<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
 <script>	
	jQuery(document).ready(function(){
		jQuery("#qwriteform").submit(function(){
			if(jQuery("#qreplytitle").val()==""){
				alert("답변제목을 입력하세요");
				jQuery("#qtitle").focus();
				return false;
			}
			if(jQuery("#qreplyname").val()==""){
				alert("작성자를 입력하세요");
				jQuery("#qname").focus();
				return false;
			}
			if(jQuery("#qreplypass").val()==""){
				alert("비밀번호를 입력하세요");
				jQuery("#qpass").focus();
				return false;
			}
			
			if(jQuery("#qreplycontent").val()==""){
				alert("답변 내용을 입력하세요");
				jQuery("#qcontent").focus();
				return false;
			}
			var reg = /^<(\/)?([a-zA-Z0-9]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>$/;
			var target= $("#qreplycontent").val();
			if(reg.test(target)){
				alert("내용입력 실패! \n html 태그는 삽입할 수 없습니다!");
				$("#qreplycontent").focus();
				reg = null; target = null;
				return false;
			}
			reg = null; target = null;
		});
	});
</script>
 
<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">답변하기</h3>
 <div class="form-group"><!-- 2 -->
<form action="${pageContext.request.contextPath}/qreplywrite.do?qno=${param.qno}&amp;page=${page}" method="post" id="qwriteform">
 <table class="table table-hover">
<caption>답변하기</caption>
 <tbody>
 	<tr>
 		<th scope="row"><label for="qreplytitle">답변제목</label></th>
 		<td colspan="3"><input type="text" id="qreplytitle" name="qreplytitle" title="답변제목 입력" class="form-control"/></td>
 	</tr>
  	<tr>
 		<th scope="row"><label for="qreplyname">작성자</label></th>
 		<td>
			<input type="text" id="qreplyname" name="qreplyname" title="작성자 입력" class="form-control"/>
		</td>
 		<th scope="row"><label for="qreplypass">비밀번호</label></th>
 		<td>
			<input type="password" id="qreplypass" name="qreplypass" title="비밀번호 입력" class="form-control"/>
		</td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qreplycontent">답변내용</label></th>
 		<td colspan="3"><textarea name="qreplycontent"  id="qreplycontent"  cols="60"  rows="10"  title="답변내용 입력" class="form-control" ></textarea></td>
 	</tr>
 </tbody>
 </table>

	<div class="form-group  text-center">
		<input type="submit"   value="답변하기"  class="btn btnc" title="답변하기"/>  
		<input type="reset"    value="취소"  class="btn btn-default" title="취소"/>  
	</div>
</form>
</div><!-- 2 -->
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>