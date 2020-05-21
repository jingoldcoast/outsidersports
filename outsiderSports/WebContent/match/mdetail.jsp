<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>
<div class="container myfirst"><!-- 1 -->
<h3 class="myhidden">개설된 경기 상세보기</h3>
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

<div class="form-group mdetail">
<strong class="mtitle">${dto.mtitle}</strong>
<c:choose>
	<c:when test="${dto.msex eq 'm'}"><p style="color:#0080ff">남성 경기</p></c:when>
	<c:when test="${dto.msex eq 'f'}"><p style="color:#dc143c">여성 경기</p></c:when>
	<c:when test="${dto.msex eq 'c'}"><p style="color:#6a0dad">혼성 경기</p></c:when>
</c:choose>
<ul>
	<li>종목: ${ground.gcategory}</li>
	<li>경기날짜: ${dto.mdate.substring(0, 10)} / ${dto.mday}요일 / ${dto.mhour}시</li>
	<li>경기비용: <strong class="starcolor"><fmt:formatNumber value="${dto.mprice}" pattern="#,###"/></strong>원</li>
	<c:choose>
		<c:when test="${dto.mduration eq '60'}"><li>경기진행시간: 1시간</li></c:when>
		<c:when test="${dto.mduration eq '90'}"><li>경기진행시간: 1시간 반</li></c:when>
		<c:when test="${dto.mduration eq '120'}"><li>경기진행시간: 2시간</li></c:when>
	</c:choose>
	<li>최대인원: ${dto.mtotal}명</li>
	<c:choose>
		<c:when test="${ground.gside eq '3'}"><li>팀별 추천인원: 3 vs 3</li></c:when>
		<c:when test="${ground.gside eq '4'}"><li>팀별 추천인원: 4 vs 4</li></c:when>
		<c:when test="${ground.gside eq '5'}"><li>팀별 추천인원: 5 vs 5</li></c:when>
		<c:when test="${ground.gside eq '9'}"><li>팀별 추천인원: 9 vs 9</li></c:when>
		<c:when test="${ground.gside eq '10'}"><li>팀별 추천인원: 10 vs 10</li></c:when>
		<c:when test="${ground.gside eq '11'}"><li>팀별 추천인원: 11 vs 11</li></c:when>
	</c:choose>
</ul>
<input type="hidden" id="mcreatedate" value="${dto.mcreatedate}"/>
<input type="hidden" id="gno" value="${dto.gno}"/>
<input type="hidden" id="mno" value="${dto.mno}"/>
<input type="hidden" id="address" value="${ground.gaddr1}"/>
<div class="text-right linktodetail">
	<a href="${pageContext.request.contextPath}/gdetail.do?gno=${dto.gno}" class="btn btnc" title="경기장 상세보기">경기장 상세보러가기</a>
</div>
</div><!-- 매치 상세내용1 -->
<div class="form-group mdetail">
<p><strong class="starcolor">${dto.cnt}</strong>자리 남았습니다!</p>
<span>참가자: </span>
<c:forEach var="list" items="${list}">
<span class="glyphicon glyphicon-user">${list}</span>
</c:forEach>
</div>
 <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f5393725e755b8f460f6860f4facbc7&libraries=services"></script>
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
    coords = null; marker = null; infowindow = null;
}
 mapContainer = null; map = null; geocoder = null; 
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
		<td><img src="conv/air.png" alt="air.png"  <c:if test="${ground.air=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="냉난방"/></td>
		<td><img src="conv/ball.png" alt="ball.png" <c:if test="${ground.ball=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="공대여"/></td>
		<td><img src="conv/light.png" alt="light.png" <c:if test="${ground.light=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="조명"/></td>
		<td><img src="conv/parking.png" alt="parking.png" <c:if test="${ground.parking=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="주차시설"/></td>
		<td><img src="conv/shoes.png" alt="shoes.png" <c:if test="${ground.shoes=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="신발대여"/></td>
		<td><img src="conv/shower.png" alt="shower.png" <c:if test="${ground.shower=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="샤워장"/></td>
		<td><img src="conv/vest.png" alt="vest.png" <c:if test="${ground.vest=='n'}">style="-webkit-filter: grayscale(100%)"</c:if>title="팀조끼대여"/></td>
	</tr>
	<tr>
		<c:choose><c:when test="${ground.air=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.ball=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.light=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.parking=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.shoes=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.shower=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
		<c:choose><c:when test="${ground.vest=='n'}"><td>없음</td></c:when><c:otherwise><td>있음</td></c:otherwise></c:choose>
	</tr>
	<tr>
		<c:choose><c:when test="${ground.io=='y'}"><td colspan="7">실내 경기장</td></c:when><c:otherwise><td colspan="7">실외 경기장</td></c:otherwise></c:choose>
	</tr>
</tbody>
</table>
</div>

<div class="form-group">
<c:choose>
<c:when test="${sessionScope.oid ne null}">
	<a href="${pageContext.request.contextPath}/match_reserve.do?mno=${dto.mno}&amp;oid=${sessionScope.oid}&amp;mprice=${dto.mprice}" class="form-control btn btnc" title="예약하기">예약하기</a>
</c:when>
<c:when test="${dto.mtotal<dto.cnt}">
	<input type="button" value="예약 마감되었습니다." class="form-control btn btny" title="예약마감"/>
</c:when>
<c:otherwise>
	<input type="button" value="로그인 후 예약 가능합니다" class="form-control btn btny" title="로그인후예약가능"/>
</c:otherwise>
</c:choose>
</div>


<div class="form-group">
<strong>매니저 상세보기</strong>
<ul>
	<li>진행 매니저: ${dto.mhost}</li>
	<li>매니저 총평점: <strong class="starcolor"><fmt:formatNumber value="${dto.hstar}" pattern=".00"/></strong>점</li>
</ul>
<table class="table table-hover">
<caption>매니저 후기</caption>
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
				<c:when test="${rlist.hstar==5}"><td class="starcolor">★★★★★</td></c:when>
				<c:when test="${rlist.hstar==4}"><td class="starcolor">★★★★☆</td></c:when>
				<c:when test="${rlist.hstar==3}"><td class="starcolor">★★★☆☆</td></c:when>
				<c:when test="${rlist.hstar==2}"><td class="starcolor">★★☆☆☆</td></c:when>
				<c:when test="${rlist.hstar==1}"><td class="starcolor">★☆☆☆☆</td></c:when>
			</c:choose>
			<td>${rlist.hcomment}</td>
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


</div><!-- 1 -->
<%@ include file="../inc/footer.jsp"%>