<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
<script>
$(function(){
	$("#ko").click(function(){
		if($("#acomment").val()==""){ alert("하고싶은 말을 적어주세요!"); $("#acomment").focus(); return false;}
		if($("#apass").val()==""){ alert("비밀번호를 적어주세요!\n삭제시 필요합니다!"); $("#apass").focus(); return false;}
		$.ajax({
			url:"${pageContext.request.contextPath}/notpapago.api", 
			type:"get",
			dataType:"text", 
			data: {"acomment" : $("#acomment").val(), "apass": $("#apass").val()},
			success:function(data){
				$("#anytalk").children().first().remove();
				$("#acomment").val("");
				$("#apass").val("");
				$("#anytalk").append($("<div class='panel-body anytalk'>").html(data));
			},
			error:function(xhr, textStatus, errorThrown){ $(".result").html(xhr.status);}
		});
	});	  
	$("#en").click(function(){
		if($("#acomment").val()==""){ alert("하고싶은 말을 적어주세요!"); $("#acomment").focus(); return false;}
		if($("#apass").val()==""){ alert("비밀번호를 적어주세요!\n삭제시 필요합니다!"); $("#apass").focus(); return false;}
		$.ajax({
			url:"${pageContext.request.contextPath}/papago.api", 
			type:"get",
			dataType:"json", 
			data: {"acomment": $("#acomment").val(), "apass": $("#apass").val()},
			success:function(data){
				$("#anytalk").children().first().remove();
				$("#acomment").val("");
				$("#apass").val("");
				$("#anytalk").append($("<div class='panel-body anytalk'>").html(data.message.result.translatedText));
			},
			error:function(xhr, textStatus, errorThrown){ $("#anytalk").html(xhr.status);}
		});
	});	   
});
</script>
<div class="container myfirst customer"><!-- 1 -->
<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/customermenu.jsp" %>
	</div>
	<div class="col-sm-10" >
		<h3>하고싶은말</h3>
		<p>최근 등록된 10개의 글만 보여집니다.</p>
		<p><strong class="starcolor">페이지 하단</strong>에서 하고싶은 말을 <strong class="starcolor">한글,</strong> 혹은 한글을 <strong class="starcolor">영어로 번역</strong>하여 등록할 수 있습니다!</p>
		<div class="panel" id="anytalk">
		<c:forEach var ="list" items="${list}">
			<div class="panel-body anytalk" id="a${list.ano}"> ${list.acomment} </div>
		</c:forEach>
		</div>
		<div class="panel">
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-9">
						<label for="acomment">하고싶은말 등록</label>
						<input type="text" name="acomment" id="acomment" class="form-control" placeholder="등록하고 싶은 말을 적어주세요 ex) 배고파요" title="하고싶은말 적기"/>
					</div>
					<div class="col-sm-3">
						<label for="apass">비밀번호</label>
						<input type="password" name="apass" id="apass" class="form-control" title="비밀번호"/>
					</div>
				</div>
				<div class="row papago">
					<div class="col-sm-6"><input type="button" class="btn btnc form-control" value="한글로 등록하기" title="한글로 등록하기" id="ko"/></div>
					<div class="col-sm-6"><input type="button" class="btn btny form-control" value="영어로 번역해서 등록하기" title="영어로 등록하기" id="en"/></div>
				</div>
			</div>
		</div>		
	</div>
</div>
</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>