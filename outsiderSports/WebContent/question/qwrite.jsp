<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
 <script>	
	jQuery(document).ready(function(){
		jQuery("#qwriteform").submit(function(){
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
			var reg = /^<(\/)?([a-zA-Z0-9]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>$/;
			var target= $("#qcontent").val();
			if(reg.test(target)){
				alert("내용입력 실패! \n html 태그는 삽입할 수 없습니다!");
				$("#qcontent").focus();
				reg = null; target = null;
				return false;
			}
			reg = null; target = null;
		});
	});
</script>
 
<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">문의하기</h3>
 <div class="form-group"><!-- 2 -->
<form action="${pageContext.request.contextPath}/qwrite.do" method="post" id="qwriteform">
<strong>* 표시는 필수 입력 항목입니다.</strong>
 <table class="table table-hover">
<caption>문의하기</caption>
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
 		<td colspan="3"><input type="text" id="qtitle" name="qtitle" title="문의제목 입력" class="form-control"/></td>
 	</tr>
  	<tr>
 		<th scope="row"><label for="qname">작성자 *</label></th>
 		<td>
 			<c:choose>
 				<c:when test="${sessionScope.oid eq null}"><input type="text" id="qname" name="qname" title="작성자 입력" class="form-control"/></c:when>
 				<c:otherwise><input type="text" id="qname" name="qname" value="${sessionScope.oid}" class="form-control" readonly/></c:otherwise>
 			</c:choose>
			
		</td>
 		<th scope="row"><label for="qpass">비밀번호 *</label></th>
 		<td>
			<input type="password" id="qpass" name="qpass" title="비밀번호 입력" class="form-control"/>
		</td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qcontent">문의내용 *</label></th>
 		<td colspan="3"><textarea name="qcontent"  id="qcontent"  cols="60"  rows="10"  title="문의내용 입력" class="form-control" ></textarea></td>
 	</tr>
 	<tr>
 		<th scope="row"><label for="qemail1">답변 받을 email *</label></th>
 		<td><input type="text" class="form-control" name="qemail1" id="qemail1" title="메일 계정 입력"></td>
 		<td><strong>@</strong></td>
 		<td>
           <select name="qemail2" id="qemail2" title="메일 제공 사이트 선택" class="form-control">
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

	<div class="form-group  text-center">
		<input type="submit"   value="문의하기"  class="btn btnc" title="문의하기"/>  
		<input type="reset"    value="취소"  class="btn btn-default" title="취소"/>  
	</div>
</form>
</div><!-- 2 -->
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>