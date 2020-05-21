<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
<script>
$(function(){
	$(document).on("click", ":button", function(){
		if($(this).attr("id") == "add"){
			if( $("#fcontent").val()==""){ alert("아무말을 적어주세요!"); $("#fcontent").focus(); return false; }
			if( $("#fpass").val()==""){ alert("비밀번호를 입력해주세요!\n수정 삭제시 필요합니다!"); $("#fpass").focus(); return false; }
			$.ajax({
				url:"${pageContext.request.contextPath}/addfestivaltalk.do", 
				type:"get",
				dataType:"text", 
				data: {"fcontent" : $("#fcontent").val(), "fpass": $("#fpass").val()},
				success:function(data){
					//$(".festivaltalk > .panel.panel-success").empty();
					var colsm8 = $("<div class='col-sm-8'>").html("<input type='text' readonly='readonly' class='form-control' title='아무말' value='"+$("#fcontent").val()+"' id='fc"+data+"'/>");
					var colsm2 = $("<div class='col-sm-2'>").html("<input type='password' readonly='readonly' class='form-control' title='비밀번호' placeholder='비밀번호' id='fp"+data+"'/>");
					var colsm11 = $("<div class='col-sm-1'>").html("<input type='button' class='btn btnc' title='수정하기' value='수정' id='e"+data+"' name='"+data+"'/>");
					var colsm12 = $("<div class='col-sm-1'>").html("<input type='button' class='btn btnc' title='삭제하기' value='삭제' id='d"+data+"' name='"+data+"'/>");
					var row = $("<div class='panel-body' id='b"+data+"'>").append(colsm8).append(colsm2).append(colsm11).append(colsm12);
					$(".festivaltalk > .panel.panel-success").append(row);
					$("#fcontent").val("");
					$("#fpass").val("");
					colsm8 = null; colsm2 = null; colsm11 = null; colsm12 = null; row = null;
				},
				error:function(xhr, textStatus, errorThrown){ $(".festivaltalk").html(xhr.status);}
			});
		}else{
			  var fno = $(this).attr("name");
			  var id = $(this).attr("id");
			  if(  $("#fc" + fno).attr("readonly") == "readonly"  ){
				  $("#fc" + fno).attr("readonly", false);
				  $("#fp" + fno).attr("readonly", false);
			  }else{
				  if($("#fc" + fno).val()==""){alert("아무말을 적어주세요!");  $("#fc" + fno).val().focus(); return false;}
				  $.ajax({
						url:"${pageContext.request.contextPath}/festivaltalkeditordelete.do", 
						type:"get",
						dataType:"text", 
						data: {"fcontent" : $("#fc" + fno).val(), "fpass": $("#fp" + fno).val(), "fno" : fno, "what" : id},
						success:function(data){
							if(data=="success"){
								$("#fp" + fno).val("");
								if(id.charAt(0)=="e"){
									$("#fc" + fno).attr("readonly", true);
									$("#fp" + fno).attr("readonly", true);
									alert("수정에 성공했습니다!");
									fno = null; id = null;
								}else{
									$("#b"+fno).remove();
									alert("삭제에 성공했습니다!");
									fno = null; id = null;
								}
								
							}else{
								alert("비밀번호를 확인해주세요!");
								$("#fp" + fno).focus();
								fno = null; id = null;
							}
						},
						error:function(xhr, textStatus, errorThrown){ $(".festivaltalk").html(xhr.status);}
					});
			  }
		}
	});
});
</script>
<div class="container myfirst customer"><!-- 1 -->
<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/customermenu.jsp" %>
	</div>
	<div class="col-sm-10" >
		<h3>아무말대잔치</h3>
		<p>아무말대잔치는 매달 진행하는 <strong class="starcolor">이벤트</strong>입니다.</p>
		<p>매월 1일, 전 월에 등록된 아무말 중, 가장 재치있는 말들을 <strong class="starcolor">5개 선정</strong>합니다!<p>
		<p>아무말 무엇이든 등록해주세요! <strong class="starcolor">페이지 하단</strong>으로 가시면 등록란이 있습니다!</p>
		<div class="festivaltalk">
			<div class="panel panel-success">
			<h4>${date}월에 등록된 아무말들:</h4>
			 	<c:forEach var="list" items="${list}">
			 		<div class="panel-body" id="b${list.fno}">
			 			<div class="col-sm-8"><input type="text" readonly="readonly" class="form-control" title="아무말" value="${list.fcontent}" id="fc${list.fno}"/></div>
			 			<div class="col-sm-2"><input type="password" readonly="readonly" class="form-control" title="비밀번호" placeholder="비밀번호" id="fp${list.fno}"/></div>
			 			<div class="col-sm-1"><input type="button" class="btn btnc" title="수정하기" value="수정" id="e${list.fno}" name="${list.fno}"/></div>
			 			<div class="col-sm-1"><input type="button" class="btn btnc" title="삭제하기" value="삭제" id="d${list.fno}" name="${list.fno}"/></div>
			 		</div>
			 	</c:forEach>
			</div>
		</div>
		
		<div class="row festivaltalk">
			<h4>아무말 등록하기</h4>
			<div class="col-sm-8"><input type="text" class="form-control" title="아무말적기" placeholder="아무말을 적어주세요" id="fcontent"/></div>
			<div class="col-sm-2"><input type="password" class="form-control" title="비밀번호" placeholder="비밀번호" id="fpass"/></div>
			<div class="col-sm-2"><input type="button" class="form-control btn btny" title="등록하기" value="등록" id="add"/></div>					
		</div>
	</div>
</div>
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>