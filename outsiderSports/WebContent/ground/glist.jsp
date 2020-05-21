<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
 <script>
 $(function(){
	 $("#gnameform").submit(function(){
		 if($("#gnamesearch").val()==""){
			 alert("찾으실 경기장이름을 입력해주세요!");
			 $("#gnamesearch").focus();
			 return false;
		}
	});
});
 </script>
<div class="container myfirst"><!-- 1 -->

	<h3>경기장 전체</h3>
	
		<form action="${pageContext.request.contextPath}/glist.do" method="post" id="gnameform">
		<div class="row">
			<div class="col-sm-10"><input type="text" name="gnamesearch" id="gnamesearch" class="form-control" placeholder="찾으실 경기장이름을 적어주세요 ex) 힐링 풋살장" title="찾을 경기장이름 입력"/></div>
			<div class="col-sm-2"><input type="submit" class="btn btnc form-control" value="검색하기" title="검색하기"/></div>
		</div>
		</form>
	
		<div class="form-group text-right find">
					<a href="${pageContext.request.contextPath}/glist.do?find=gstar"><strong <c:if test="${find eq 'find=gstar'}">style="color:#ed1a3a;"</c:if>>경기장 평점순</strong></a> 
					<a href="${pageContext.request.contextPath}/glist.do?find=glike"><strong <c:if test="${find eq 'find=glike'}">style="color:#ed1a3a;"</c:if>>경기장 좋아요순</strong></a> 
					<a href="${pageContext.request.contextPath}/glist.do?find=gdate"><strong <c:if test="${find eq 'find=gdate'}">style="color:#ed1a3a;"</c:if>>최신업로드순</strong></a> 
					<a href="${pageContext.request.contextPath}/glist.do?find=gname"><strong <c:if test="${find eq 'find=gname'}">style="color:#ed1a3a;"</c:if>>가나다순</strong></a> 
		</div>
	<c:if test="${list.size() ==0}"><strong style="font-size: 18px;">입력하신 조건에 맞는 검색결과가 없습니다.</strong></c:if>
	<c:forEach var="list" items="${list}" varStatus="status">
	<div class="panel" >
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-4">
					<p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width:100%; height:170px;" title="${list.gfile.split('/')[0]}"/></p>
		  			<%-- <p><img style="width: 100%; height: 170px;" src="/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" title="이미지"/></p> --%>
				</div>
				<div class="col-sm-5">
					<p><a href="${pageContext.request.contextPath}/gdetail.do?gno=${list.gno}&amp;page=${page}" title="상세보기">${list.gname}</a></p>
					<p>종목: ${list.gcategory}</p>
					<p>주소: (${list.gzipcode}) ${list.gaddr1} ${list.gaddr2}</p>
					<p>등록일: ${list.gdate.substring(0, 10)}</p>
				</div>
				<div class="col-sm-3">
					<p class="gbold"><span class="glyphicon glyphicon-star starcolor"></span>경기장평점: <fmt:formatNumber value="${list.gstar}" pattern=".00"/> / 5</p>
					<p class="gbold"><span class="glyphicon glyphicon-thumbs-up starcolor"></span>좋아요: ${list.glike} </p>
				</div>
			</div>
		</div>
	</div>
	</c:forEach>
	<c:if test="${paging ne null}"><!-- 이름검색으로 했을 때는 페이징 처리 안하기 위해서 -->
	<div class="form-group" style="text-align: center">
		<ul class="pagination">
			<c:if test="${paging.pre_bottom>=paging.bottomlist}">
			<c:choose>
				<c:when test="${find eq null}">
					<li><a href="${pageContext.request.contextPath}/glist.do?page=${(paging.pre_bottom-2)*paging.onepagelimit}" title="이전">이전</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/glist.do?${find}&amp;page=${(paging.pre_bottom-2)*paging.onepagelimit}" title="이전">이전</a></li>
				</c:otherwise>
			</c:choose>
							
			</c:if>
			<c:forEach var="i" begin="${paging.pre_bottom}" end="${paging.next_bottom}" step="${1}" varStatus="status">
				<c:choose>
					<c:when test="${paging.current_bottom==i}">
					<c:choose>
						<c:when test="${find eq null}"><li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/glist.do?page=${(i-1)*paging.onepagelimit}" title="${i}">${i}</a></li></c:when>
						<c:otherwise><li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/glist.do?${find}&amp;page=${(i-1)*paging.onepagelimit}" title="${i}">${i}</a></li></c:otherwise>
					</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${find eq null}"><li><a href="${pageContext.request.contextPath}/glist.do?page=${(i-1)*paging.onepagelimit}" title="${i}">${i}</a></li></c:when>
							<c:otherwise><li><a href="${pageContext.request.contextPath}/glist.do?${find}&amp;page=${(i-1)*paging.onepagelimit}" title="${i}">${i}</a></li></c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			<c:if test="${paging.next_bottom<paging.pageAll}">
				<c:choose>
					<c:when test="${find eq null}"><li><a href="${pageContext.request.contextPath}/glist.do?page=${paging.next_bottom*paging.onepagelimit}" title="다음">다음</a></li></c:when>
					<c:otherwise><li><a href="${pageContext.request.contextPath}/glist.do?${find}&amp;page=${paging.next_bottom*paging.onepagelimit}" title="다음">다음</a></li></c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</div>
	</c:if>		

</div>
<%@ include file="/inc/footer.jsp"%>