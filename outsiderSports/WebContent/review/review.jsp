<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<script>	
	jQuery(document).ready(function(){
		jQuery("#writeform").submit(function(){
			if(!(jQuery("input:radio[name='hstar']").is(":checked"))){ alert("평점을 남겨주세요!"); jQuery(":radio[id='h1']").attr("checked","checked"); return false;}
			if(jQuery("#hcomment").val()==""){ alert("한 줄평을 입력해주세요!\n여러분의 소중한 의견 감사드립니다♥"); jQuery("#hcomment").focus(); return false;}
			if(!(jQuery("input:radio[name='gstar']").is(":checked"))){ alert("평점을 선택 선택해주세요!"); jQuery(":radio[id='g1']").attr("checked","checked"); return false;}
			if(jQuery("#gcomment").val()==""){ alert("한 줄평을 입력해주세요!\n여러분의 소중한 의견 감사드립니다♥"); jQuery("#gcomment").focus(); return false;}
		});
	});
</script>
<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">이용후기 쓰기</h3>
<form action="${pageContext.request.contextPath}/rwrite.do" method="post"  id="writeform" enctype="multipart/form-data">
   <fieldset>
		   <legend>이용후기쓰기</legend>
		   <strong>(*) 은 필수입력사항입니다.</strong>
		   <input type="hidden" name="gno" value="${param.gno}"/>
			<input type="hidden" name="mno" value="${param.mno}"/>
			<div class="form-group">
			  <label for="hid">경기 진행 매니저</label>
			  <input type="text"   name="hid"   id="hid"   class="form-control" value="${param.mhost}" readonly="readonly"> 
			</div>																		
			<div class="form-group">
			  <label for="h1" >(*)경기 진행 내용이 어땠나요?</label><br/>
			  <input type="radio" name="hstar" id="h1" value="5"/><label for="h1" class="starcolor">★★★★★</label>
			  <input type="radio" name="hstar" id="h2" value="4"/><label for="h2" class="starcolor">★★★★☆</label>
			  <input type="radio" name="hstar" id="h3" value="3"/><label for="h3" class="starcolor">★★★☆☆</label>
			  <input type="radio" name="hstar" id="h4" value="2"/><label for="h4" class="starcolor">★★☆☆☆</label>
			  <input type="radio" name="hstar" id="h5" value="1"/><label for="h5" class="starcolor">★☆☆☆☆</label>
			</div>	
			<div class="form-group">
			  <label for="hcomment">(*)매니저님에 대한 한 줄평을 남겨주세요!</label>
			  <input type="text" name="hcomment"  id="hcomment"  class="form-control"/>
			</div>	
			<div class="form-group">
			  <label for="gname">진행한 경기장</label>
			  <input type="text"   name="gname"   id="gname"   class="form-control" value="${param.gname}" readonly="readonly"> 
			</div>
			<div class="form-group">
			  <label for="g1" >(*)경기장은 어땠나요?</label><br/>
			  <input type="radio" name="gstar" id="g1" value="5"/><label for="g1" class="starcolor">★★★★★</label>
			  <input type="radio" name="gstar" id="g2" value="4"/><label for="g2" class="starcolor">★★★★☆</label>
			  <input type="radio" name="gstar" id="g3" value="3"/><label for="g3" class="starcolor">★★★☆☆</label>
			  <input type="radio" name="gstar" id="g4" value="2"/><label for="g4" class="starcolor">★★☆☆☆</label>
			  <input type="radio" name="gstar" id="g5" value="1"/><label for="g5" class="starcolor">★☆☆☆☆</label>
			</div>	
			<div class="form-group">
			  <label for="gcomment">(*)경기장에 대한 한 줄평을 남겨주세요!</label>
			  <input type="text" name="gcomment"  id="gcomment"  class="form-control" />
			</div>			
			<div class="form-group">
			  <label for="rfile">후기 사진업로드 (최대 5MB)</label>
			  <input type="file" class="form-control" name="rfile" id="rfile"/>
	 	 	</div>			
			<div class="form-group  text-center">
				<input type="submit"   value="후기 남기기"  class="btn btnc" title="후기 남기기"/>  
				<input type="reset"    value="취소"  class="btn btn-default" title="취소"/>  
			</div>
	</fieldset>


</form>
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>