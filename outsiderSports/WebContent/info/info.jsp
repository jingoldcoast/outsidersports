<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/inc/header.jsp"%>
<script>
$(function(){
	//setting("n1");
	$("#n1").click(function(){ setting("n1");});
	$("#n2").click(function(){ setting("n2");});
	$("#n3").click(function(){ setting("n3");});
	$("#n4").click(function(){ setting("n4");});
	$("#n5").click(function(){ setting("n5");});
	$("#n6").click(function(){ setting("n6");});
	
	function setting(ncategory){
		$.ajax({
			url:"${pageContext.request.contextPath}/ncategory.do", 
			type:"get",
			dataType:"text", 
			data: { "ncategory" : ncategory},
			success:function(data){
				$("#accordion").html(data);
			},
			error:function(xhr, textStatus, errorThrown){
				$("#accordion").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
			}
		});
	}
	setting();
});		
</script>
 <c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result == 0}"><script>alert('입력하신 제목에 해당하는 내용이 없습니다!');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
<div class="container myfirst customer"><!-- 1 -->
<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/customermenu.jsp" %>
	</div>
	<div class="col-sm-10" >
	<h3 class="myhidden">이용안내</h3>
		<div class="row">
			<div class="col-sm-2"><p id="n1"><img style="width:41px; cursor: pointer" src="images/n1.png" alt="n1.png" title="공지사항"/>공지사항</p></div>
			<div class="col-sm-2"><p id="n6"><img style="width:41px; cursor: pointer" src="images/n6.png" alt="n6.png" title="서비스"/>서비스</p></div>
			<div class="col-sm-2"><p id="n2"><img style="width:41px; cursor: pointer" src="images/n2.png" alt="n2.png" title="경기진행"/>경기진행</p></div>
			<div class="col-sm-2"><p id="n3"><img style="width:41px; cursor: pointer" src="images/n3.png" alt="n3.png" title="경기취소"/>경기취소</p></div>
			<div class="col-sm-2"><p id="n5"><img style="width:41px; cursor: pointer" src="images/n5.png" alt="n5.png" title="경기예약"/>경기예약</p></div>
			<div class="col-sm-2"><p id="n4"><img style="width:41px; cursor: pointer" src="images/n4.png" alt="n4.png" title="결제"/>결제</p></div>
		</div>
		<div class="form-group"><!-- 아코디언 + 글쓰기버튼 -->
			<div class="panel-group" id="accordion"><!-- 여기에 넣으면 되고 -->
				<c:forEach var="list" items="${list}"	varStatus="status">
					  <div class="panel panel-default">
						    <div class="panel-heading-info">
						      <h4 class="panel-title">
						        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${status.index}" title="제목">${list.ntitle}</a>
						      </h4>
						    </div>
						    <div id="collapse${status.index}" class="panel-collapse collapse <c:if test="${status.first}">in</c:if>">
						      <div class="panel-body">${list.ncontent}</div>
						    </div>
					</div>
				</c:forEach>		
			</div><!--  -->
		</div>
	</div>
</div>

</div>
<%@ include file="/inc/footer.jsp"%>