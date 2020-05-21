<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">경기장 상세보기</h3>
 <div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
  <c:forEach items="${img}" var="img" varStatus="status">
  <c:choose>
  	<c:when test="${status.first}">
  		 <li data-target="#myCarousel" data-slide-to="${status.index}" class="active"></li>
  	</c:when>
  	<c:otherwise>
  		 <li data-target="#myCarousel" data-slide-to="${status.index}"></li>
  	</c:otherwise>
  </c:choose>
  </c:forEach>
  </ol>
  <!-- Wrapper for slides -->
  <div class="carousel-inner">
  <c:forEach items="${img}" var="img" varStatus="status">
  <c:choose>
  	<c:when test="${status.first}">
  		 <div class="item active">
  		 	<p><img src="${pageContext.request.contextPath}/upload/${img}" alt="${img}" style="width:100%; max-height: 350px; min-height: 350px;" title="${img}"/></p>
      		<%-- <p><img src="/upload/${img}" alt="${img}" style="width:100%; max-height: 350px; min-height: 350px;" title="이미지"></p> --%>
    	</div>
  	</c:when>
  	<c:otherwise>
  		 <div class="item">
      		<p><img src="${pageContext.request.contextPath}/upload/${img}" alt="${img}" style="width:100%; max-height: 350px; min-height: 350px;" title="${img}"/></p>
      		<%-- <p><img src="/upload/${img}" alt="${img}" style="width:100%; max-height: 350px; min-height: 350px;" title="이미지"></p> --%>
    	</div>
  	</c:otherwise>
  </c:choose>
  </c:forEach>
  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div><!-- end carousel -->
<script>
$(function(){
	$("#glikebtn").click(function(){
		 $.ajax({
				url: "${pageContext.request.contextPath}/glike.do",
				  type: "get",
				  dataType: "json",
				  data: {"gno" : $("#glikebtn").attr("name")},
				  success:function(data){
					  $("#glike").html(data.glike);
				 },
				  error:function(xhr, textStatus, errorThrown){
					  $("#glike").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
				 }
			});
	});
});
</script>
<div class="form-group mdetail">
<strong class="mtitle">${dto.gname}</strong>
<ul>
	<li>종목: ${dto.gcategory}</li>
	<c:choose>
		<c:when test="${dto.gside eq '3'}"><li>팀별 추천인원: 3 vs 3</li></c:when>
		<c:when test="${dto.gside eq '4'}"><li>팀별 추천인원: 4 vs 4</li></c:when>
		<c:when test="${dto.gside eq '5'}"><li>팀별 추천인원: 5 vs 5</li></c:when>
		<c:when test="${dto.gside eq '9'}"><li>팀별 추천인원: 9 vs 9</li></c:when>
		<c:when test="${dto.gside eq '10'}"><li>팀별 추천인원: 10 vs 10</li></c:when>
		<c:when test="${dto.gside eq '11'}"><li>팀별 추천인원: 11 vs 11</li></c:when>
	</c:choose>
	<li>주소: (${dto.gzipcode}) ${dto.gaddr1} ${dto.gaddr2}</li>
	<li>등록일: ${dto.gdate.substring(0, 10)}</li>
</ul>
<div class="text-right linktodetail">
	<strong class="starcolor" id="glike">${dto.glike}</strong><span>명이 이 경기장을 좋아합니다!</span>
	<input type="button" value="저도 좋아요!" id="glikebtn" class="btn btnc" name="${dto.gno}" title="저도 좋아요!"/>
</div>
</div>

 <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f5393725e755b8f460f6860f4facbc7&libraries=services"></script>
<input type="hidden" id="address" value="${dto.gaddr1}"/><!-- 지도에 표시할 주소 -->
<div id="map" style="width:100%;height:350px;"></div> <!-- 지도 표시할 곳 -->

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  
//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 
//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();
//주소로 좌표를 검색합니다
geocoder.addressSearch($("#address").val(), function(result, status) {
// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {
    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
   });
    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("#address").val()+'</div>'
   });
    infowindow.open(map, marker);
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
 map = null; geocoder = null;
});    
</script>
<div class="form-group text-center">
<table class="table table-hover">
<caption>편의시설 상세정보</caption>
<thead>
	<tr>
		<th scope="col" class="text-center">냉난방</th>
		<th scope="col" class="text-center">공대여</th>
		<th scope="col" class="text-center">조명</th>
		<th scope="col" class="text-center">주차시설</th>
		<th scope="col" class="text-center">신발대여</th>
		<th scope="col" class="text-center">샤워장</th>
		<th scope="col" class="text-center">팀조끼대여</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td><img src="conv/air.png" alt="air.png"  <c:if test="${dto.air=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/ball.png" alt="ball.png" <c:if test="${dto.ball=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/light.png" alt="light.png" <c:if test="${dto.light=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/parking.png" alt="parking.png" <c:if test="${dto.parking=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/shoes.png" alt="shoes.png" <c:if test="${dto.shoes=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/shower.png" alt="shower.png" <c:if test="${dto.shower=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
		<td><img src="conv/vest.png" alt="vest.png" <c:if test="${dto.vest=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>/></td>
	</tr>
	<tr>
		<c:choose><c:when test="${dto.air=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.ball=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.light=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.parking=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.shoes=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.shower=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${dto.vest=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
	</tr>
	<tr>
		<c:choose><c:when test="${dto.io=='y'}"><td colspan="7">실내 경기장</td></c:when><c:otherwise><td colspan="7">실외 경기장</td></c:otherwise></c:choose>
	</tr>
</tbody>
</table>
</div>
<div class="form-group">
<strong>경기장 특이사항</strong>
<p>${fn:replace(dto.gdesc, crcn, br)}</p>
</div>
<c:choose>
<c:when test="${sessionScope.oid ne null}">
<div class="form-group">
	<a href="${pageContext.request.contextPath}/qlist.do" class="form-control btn btnc" title="대관 문의하기">대관 문의하기</a>
</div>
</c:when>
<c:otherwise>
<div class="form-group">
	<input type="button" value="로그인 후  대관문의 가능합니다" class="form-control btn btny" title="로그인 후 대관문의 가능합니다"/>
</div>
</c:otherwise>
</c:choose>


<div class="form-group">
<p>경기장 총평점: <strong style="color:#ed1a3a"><fmt:formatNumber value="${dto.gstar}" pattern=".00"/></strong>점</p>
<table class="table table-hover">
<caption>경기장 후기</caption>
<thead>
	<tr>
		<th scope="col">번호</th>
		<th scope="col">평점</th>
		<th scope="col">한줄평</th>
		<th scope="col">글쓴이</th>
		<th scope="col">글쓴날</th>
	</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${rlist ne null}">
	<c:forEach var="rlist" items="${rlist}" varStatus="status">
		<tr>
			<td>${no-status.index}</td>
			<c:choose>
				<c:when test="${rlist.gstar==5}"><td class="starcolor">★★★★★</td></c:when>
				<c:when test="${rlist.gstar==4}"><td class="starcolor">★★★★☆</td></c:when>
				<c:when test="${rlist.gstar==3}"><td class="starcolor">★★★☆☆</td></c:when>
				<c:when test="${rlist.gstar==2}"><td class="starcolor">★★☆☆☆</td></c:when>
				<c:when test="${rlist.gstar==1}"><td class="starcolor">★☆☆☆☆</td></c:when>
			</c:choose>
			<td>${rlist.gcomment}</td>
			<td>${rlist.rwriter}</td>
			<td>${rlist.rdate.substring(0, 16)}</td>
		</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
		<tr class="text-center">
			<td colspan="5">작성된 후기글이 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>
	
</tbody>
</table>
</div>

</div>
<%@ include file="../inc/footer.jsp"%>