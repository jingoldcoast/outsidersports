<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
 <script>	
	jQuery(document).ready(function(){
		var qemail2 = '<c:out value="${qemail2}"/>';
		jQuery("#qemail2").val(qemail2).attr("selected", "selected");
		var qcategory = '<c:out value="${qcategory}"/>';
		jQuery("#qcategory").val(qcategory).attr("selected", "selected");
		var qcase = '<c:out value="${qcase}"/>';
		jQuery("#qcase").val(qcase).attr("selected", "selected");
		
		jQuery("#qeditform").submit(function(){
			if(jQuery("#qtitle").val()==""){
				alert("문의제목을 입력하세요");
				jQuery("#qtitle").focus();
				return false;
			}
			if(jQuery("#qname").val()==""){
				alert("작성자를 입력하세요");
				jQuery("#qname").focus();
				return false;
			}
			if(jQuery("#qpass").val()==""){
				alert("비밀번호를 입력하세요");
				jQuery("#qpass").focus();
				return false;
			}
			if(jQuery("#qcontent").val()==""){
				alert("문의 내용을 입력하세요");
				jQuery("#qcontent").focus();
				return false;
			}
			if(jQuery("#qemail1").val()==""){
				alert("답변 받으실 email 계정을 입력하세요");
				jQuery("#qemail1").focus();
				return false;
			}
			if(jQuery("#qemail2").val()==""){
				alert("답변 받으실 email을 선택해주세요");
				jQuery("#qemail2").focus();
				return false;
			}
			var qpass = '<c:out value="${dto.qpass}"/>';
			
			if(jQuery("#qpass").val() != qpass){
				alert("기존에 설정하신 비밀번호와 일치하지 않습니다.\n비밀번호를 다시 입력해주세요.");
				jQuery("#qpass").focus();
				return false;
			}
			var reg = /^<(\/)?([a-zA-Z0-9]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>$/;
			var target= $("#qcontent").val();
			if(reg.test(target)){
				alert("내용입력 실패! \n html 태그는 삽입할 수 없습니다!");
				$("#qcontent").focus();
				return false;
			}
			if(confirm("문의내용을 수정하시겠습니까?")==false){
				qemail2 = null; qcategory = null; qcase = null; qpass = null; reg = null; target = null;
				return false;
			}
			qemail2 = null; qcategory = null; qcase = null; qpass = null; reg = null; target = null;
		});
	});
</script>
 
<div class="container myfirst"><!-- 1 -->
<h3>수정하기</h3>
 <div class="form-group"><!-- 2 -->
<form action="${pageContext.request.contextPath}/qedit.do" method="post" id="qeditform">
<input type="hidden" name="qno" value="${dto.qno}"/>
<strong>* 표시는 필수 입력 항목입니다.</strong>
 <table class="table table-hover">
<caption>수정하기</caption>
 <colgroup>
    <col style="background-color:#e7e7e7">
    <col>
    <col style="background-color:#e7e7e7">
     <col>
  </colgroup>
 <tbody>
 	<tr>
 		<th scope="row"><label for="qcategory">문의종목 *</label></th>
 		<td>
 		<select name="qcategory" id="qcategory" title="문의종목 선택" class="form-control">
			<option value="01">축구</option>
			<option value="02">농구</option>
		</select>
		</td>
 		<th scope="row"><label for="qcase">문의종류 *</label></th>
 		<td>
 		<select name="qcase" id="qcase" title="문의종류 선택" class="form-control">
			<option value="01">참여/예약</option>
			<option value="02">개설</option>
			<option value="03">결제</option>
			<option value="04">기타</option>
		</select>
		</td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qtitle">문의제목 *</label></th>
 		<td colspan="3"><input type="text" id="qtitle" name="qtitle" title="문의제목 입력" class="form-control" value="${dto.qtitle}"/></td>
 	</tr>
  	<tr>
 		<th scope="row"><label for="qname">작성자</label></th>
 		<td>
 			<input readonly type="text"  value="${dto.qname}" id="qname" name="qname" title="작성자" class="form-control"/>
		</td>
 		<th scope="row"><label for="qpass">비밀번호 *</label></th>
 		<td>
			<input type="password" id="qpass" name="qpass" title="비밀번호 입력" class="form-control"/>
		</td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qcontent">문의내용 *</label></th>
 		<td colspan="3"><textarea name="qcontent"  id="qcontent"  cols="60"  rows="10"  title="문의내용 입력" class="form-control" >${dto.qcontent}</textarea></td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qemail1">답변 받을 email *</label></th>
 		<td><input type="text" class="form-control" name="qemail1" id="qemail1" title="메일 계정 입력" value="${qemail1}"></td>
 		<td><strong>@</strong></td>
 		<td>
           <select id="qemail2" name="qemail2" title="메일 제공 사이트 선택" class="form-control" data-qemail2="사과" >
                 <option value="">선택</option>
                 <option value="naver.com">naver.com</option>
                 <option value="daum.net">daum.net</option>
                 <option value="hotmail.com">hotmail.com</option>
                 <option value="nate.com">nate.com</option>
                 <option value="yahoo.co.kr">yahoo.co.kr</option>
                 <option value="paran.com">paran.com</option>
                 <option value="empas.com">empas.com</option>
                 <option value="dreamwiz.com">dreamwiz.com</option>
                 <option value="freechal.com">freechal.com</option>
                 <option value="lycos.co.kr">lycos.co.kr</option>
                 <option value="korea.com">korea.com</option>
                 <option value="gmail.com">gmail.com</option>
                 <option value="hanmir.com">hanmir.com</option>
            </select>
 		</td>
 	</tr>
 </tbody>
 </table>

	<div class="form-group text-center">
		<input type="submit"   value="수정하기"  class="btn btnc" title="수정하기"/>  
		<a href="${pageContext.request.contextPath}/qlist.do?page=${page}" class="btn btny" title="목록보기">목록보기</a>
	</div>
</form>
</div><!-- 2 -->
</div><!-- 1 -->

<%@ include file="../inc/footer.jsp"%>