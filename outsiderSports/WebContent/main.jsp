<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/inc/header.jsp"%>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요'); </script></c:when>
</c:choose>
 <script>
 $(function(){
	 $("#mtitleform").submit(function(){
		 if($("#mtitlesearch").val()==""){
			 alert("찾으실 경기장이름을 입력해주세요!");
			 $("#mtitlesearch").focus();
			 return false;
		}
	});
	 $("#dont").change(function(){
		 if($("#dont").is(":checked")){
				$.ajax({
					url:"${pageContext.request.contextPath}/eventdont.do", 
					type:"get",
					dataType:"text", 
					success:function(){
						$(".container.pop-up").remove();
					},
					error:function(xhr, textStatus, errorThrown){
						$(".container").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
					}
				});
		}
	});
});
 </script>
<!-- 이벤트 사진 시작 -->
<div class="row myfirst">
<div id="myCarousel3" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel3" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel3" data-slide-to="1"></li>
    <li data-target="#myCarousel3" data-slide-to="2"></li>
    <li data-target="#myCarousel3" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <p><a href="${pageContext.request.contextPath}/info.do"><img src="images/mm1.png" alt="mm1png" title="공지사항" style="width: 100%; max-height: 338px"/></a></p>
    </div>

    <div class="item">
      <p><a href="${pageContext.request.contextPath}/shopping_view.api"><img src="images/mm2.png" alt="mm2.png" title="물건사기" style="width: 100%; max-height: 338px"/></a></p>
    </div>

    <div class="item">
      <p><a href="${pageContext.request.contextPath}/anytalk.do"><img src="images/mm3.png" alt="mm3.png" title="하고싶은말" style="width: 100%; max-height: 338px"/></a></p>
    </div>
    
    <div class="item">
      <p><a href="${pageContext.request.contextPath}/festivaltalk.do"><img src="images/mm4.png" alt="mm4.png" title="아무말대잔치" style="width: 100%; max-height: 338px"/></a></p>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel3" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel3" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<!-- 이벤트 사진 끝 -->
<!-- 팝업 시작 -->
<c:if test="${dont eq null}">
<div class="container pop-up">
<div class="col-sm-10">
<h3>안내말씀</h3>
<p>OutsiderSports는 상업적 목적이 아닌 개인포트폴리오 용도로 제작하였습니다. 홈페이지의 일부내용 및 이미지 등은 출처가 따로 있음을 밝히며 그 어떠한 수익창출 목적으로도 사용하지 않았습니다. 혹시 문제가 되는 부분이 있다면 바로 시정하겠습니다.<br/>그럼 좋은 하루되세요!^^</p>
<p>참고사이트: 플랩풋볼, strangersoccer, 아이엠그라운드</p>
<p>연락처: jingoldcoast@gmail.com </p>
</div>
<div class="col-sm-2">
<img src="images/SmileyFace.jpg" alt="smile" style="width:190px"/>
</div>
<div class="text-right">
	<input type="checkbox" name="dont"	id="dont" value="dont" title="오늘하루동안보지않기"/><label for="dont">오늘하루동안 보지않기</label>
</div>
</div>
</c:if>
<!-- 팝업 끝 -->
<div class="container">
<form action="${pageContext.request.contextPath}/mlist.do" method="post" id="mtitleform" class="mtitlemain">
<fieldset>
<legend>개설된 경기검색</legend>
<div class="row">
	<div class="col-sm-11"><label for="mtitlesearch">개설경기 이름</label><input type="text" name="mtitlesearch" id="mtitlesearch" class="form-control" placeholder="찾으실 경기이름을 적어주세요 ex) 생활체육광장"/></div>
	<div class="col-sm-1"><input type="submit" class="btn btnc mtitlebtn" value="검색" title="검색"/></div>
</div>
</fieldset>
</form>
</div><!-- end 첫번째 container -->
<div class="container mlistfilter">
<form action="${pageContext.request.contextPath}/mlist.do?stype=filter" method="post" class="mainfilter">
<fieldset>
<legend>개설경기 필터검색</legend>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><label for="gcategory">카테고리</label> 
		<select name="gcategory" id="gcategory" title="카테고리 선택" class="form-control">
		<option value="">전체</option>
		<option value="01">축구</option>
		<option value="02">농구</option>
		</select>
	</div>
	<div class="form-group">
	<div class="row">
		<div class="col-sm-4"><span class="glyphicon glyphicon-ok starcolor"></span><label for="mdate1">경기검색날짜 부터</label><input type="date" id="mdate1" name="mdate1" class="form-control" min="${today}" title="날짜시작"/></div>
		<div class="col-sm-4"><label for="mdate2">까지</label><input type="date" id="mdate2" name="mdate2" class="form-control" title="날짜종료"/></div>
		<div class="col-sm-4"><span class="glyphicon glyphicon-ok starcolor"></span><label for="gstar">경기장평점 (이상, 5점만점)</label><input type="number" id="gstar" name="gstar" min="1" max="5" class="form-control" title="경기장평점"/></div>
	</div>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>지역:</span>
		<input type="checkbox" name="gregion" value="01" id="gregion01" checked="checked"/><label for="gregion01">서울</label>
		<input type="checkbox" name="gregion" value="02" id="gregion02"/><label for="gregion02">경기</label>
		<input type="checkbox" name="gregion" value="03" id="gregion03"/><label for="gregion03">인천</label>
		<input type="checkbox" name="gregion" value="04" id="gregion04"/><label for="gregion04">부산</label>
		<input type="checkbox" name="gregion" value="05" id="gregion05"/><label for="gregion05">대구</label>
		<input type="checkbox" name="gregion" value="06" id="gregion06"/><label for="gregion06">광주</label>
		<input type="checkbox" name="gregion" value="07" id="gregion07"/><label for="gregion07">대전</label>
		<input type="checkbox" name="gregion" value="08" id="gregion08"/><label for="gregion08">제주</label>
		<input type="checkbox" name="gregion" value="09" id="gregion09"/><label for="gregion09">울산</label>
		<input type="checkbox" name="gregion" value="10" id="gregion10"/><label for="gregion10">강원</label>
		<input type="checkbox" name="gregion" value="11" id="gregion11"/><label for="gregion11">충북</label>
		<input type="checkbox" name="gregion" value="12" id="gregion12"/><label for="gregion12">충남</label>
		<input type="checkbox" name="gregion" value="13" id="gregion13"/><label for="gregion13">전북</label>
		<input type="checkbox" name="gregion" value="14" id="gregion14"/><label for="gregion14">전남</label>
		<input type="checkbox" name="gregion" value="15" id="gregion15"/><label for="gregion15">경북</label>
		<input type="checkbox" name="gregion" value="16" id="gregion16"/><label for="gregion16">경남</label>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>요일:</span>
		<input type="checkbox" name="mday" value="0" id="day0" checked="checked"/><label for="day0">일요일</label>
		<input type="checkbox" name="mday" value="1" id="day1"/><label for="day1">월요일</label>
		<input type="checkbox" name="mday" value="2" id="day2"/><label for="day2">화요일</label>
		<input type="checkbox" name="mday" value="3" id="day3"/><label for="day3">수요일</label>
		<input type="checkbox" name="mday" value="4" id="day4"/><label for="day4">목요일</label>
		<input type="checkbox" name="mday" value="5" id="day5"/><label for="day5">금요일</label>
		<input type="checkbox" name="mday" value="6" id="day6"/><label for="day6">토요일</label>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>경기진행시간:</span>
		<input type="checkbox" name="mduration" value="0" id="mduration0"/><label for="mduration0">1시간</label>
		<input type="checkbox" name="mduration" value="1" id="mduration1" checked="checked"/><label for="mduration1">1시간 반</label>
		<input type="checkbox" name="mduration" value="2" id="mduration2"/><label for="mduration2">2시간</label>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>성별:</span>
		<input type="checkbox" name="msex" value="m" id="m" checked="checked"/><label for="m">남성경기</label>
		<input type="checkbox" name="msex" value="f" id="f"/><label for="f">여성경기</label>
		<input type="checkbox" name="msex" value="c" id="c"/><label for="c">혼성경기</label>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>편의시설(모두포함):</span>
		<input type="checkbox" name="conv" value="parking" id="parking" checked="checked"/><label for="parking">주차장</label>
		<input type="checkbox" name="conv" value="light" id="light"/><label for="light">조명</label>
		<input type="checkbox" name="conv" value="in" id="in"/><label for="in">실내</label>
		<input type="checkbox" name="conv" value="out" id="out"/><label for="out">실외</label>
		<input type="checkbox" name="conv" value="shower" id="shower"/><label for="shower">샤워장</label>
		<input type="checkbox" name="conv" value="air" id="air"/><label for="air">냉난방</label>
		<input type="checkbox" name="conv" value="ball" id="ball"/><label for="ball">공대여</label>
		<input type="checkbox" name="conv" value="vest" id="vest"/><label for="vest">팀조끼대여</label>
		<input type="checkbox" name="conv" value="shoes" id="shoes"/><label for="shoes">신발대여</label>
	</div>
	<div class="form-group">
		<span class="glyphicon glyphicon-ok starcolor"></span><span>각팀추천인원:</span>
		<input type="checkbox" name="gside" value="3" id="gside3"/><label for="gside3">3 vs 3</label>
		<input type="checkbox" name="gside" value="4" id="gside4"/><label for="gside4">4 vs 4</label>
		<input type="checkbox" name="gside" value="5" id="gside5"/><label for="gside5">5 vs 5</label>
		<input type="checkbox" name="gside" value="9" id="gside9" checked="checked"/><label for="gside9">9 vs 9</label>
		<input type="checkbox" name="gside" value="10" id="gside10"/><label for="gside10">10 vs 10</label>
		<input type="checkbox" name="gside" value="11" id="gside11"/><label for="gside11">11 vs 11</label>
	</div>
	<div class="form-group">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-3"><input type="submit" value="필터검색" class="form-control btn btnc" title="필터검색"/></div>
		<div class="col-sm-3"><input type="reset"    value="취소"  class="form-control btn btn-default" title="취소"/>  </div>
		<div class="col-sm-3"></div>
	</div>
	
	</div>
</fieldset>
</form>
</div>
<!--새로 개설된 경기 시작 -->
<div class="container main">
<h4  class="maindesc">새로 개설된 경기</h4>
<div id="myCarousel0" class="carousel slide" data-ride="carousel" data-interval="false">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel0" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel0" data-slide-to="1"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
    <c:forEach begin="0" end="3" var="bg" items="${newmatch}" varStatus="status">
    		<div class="col-sm-3 text-center">
    		<div class="thumbnail">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/mdetail.do?mno=${bg.mno}"><img style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.thumbnail}" alt="${bg.thumbnail}" class="img-thumbnail"/></a>
					<c:choose>
						<c:when test="${bg.msex eq 'm'}"><p><span class="main_m">■ </span>남성 경기</p></c:when>
						<c:when test="${bg.msex eq 'f'}"><p><span class="main_f">■ </span>여성 경기</p></c:when>
						<c:when test="${bg.msex eq 'c'}"><p><span class="main_mix">■ </span>혼성 경기</p></c:when>
					</c:choose>
				<p class="main_title">${bg.mtitle}</p>
				<p class="main_date">진행날짜: ${bg.mdate.substring(0, 10)} / ${bg.mhour} 시</p>
				<p>참여비용: <fmt:formatNumber value="${bg.mprice}" pattern="#,###"/>원</p>
    		</div>
    		</div>
    </c:forEach>
    </div>
    <div class="item">
      <c:forEach begin="4" end="7" var="bg" items="${newmatch}">
    		<div class="col-sm-3 text-center">
    		<div class="thumbnail">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/mdetail.do?mno=${bg.mno}"><img style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.thumbnail}" alt="${bg.thumbnail}" class="img-thumbnail"/></a>
    				<c:choose>
						<c:when test="${bg.msex eq 'm'}"><p><span class="main_m">■ </span>남성 경기</p></c:when>
						<c:when test="${bg.msex eq 'f'}"><p><span class="main_f">■ </span>여성 경기</p></c:when>
						<c:when test="${bg.msex eq 'c'}"><p><span class="main_mix">■ </span>혼성 경기</p></c:when>
					</c:choose>
				<p class="main_title">${bg.mtitle}</p>
				<p class="main_date">진행날짜: ${bg.mdate.substring(0, 10)} / ${bg.mhour} 시</p>
				<p>참여비용: <fmt:formatNumber value="${bg.mprice}" pattern="#,###"/>원</p>
    		</div>
    		</div>
  	  </c:forEach>
    </div>
  </div>
  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel0" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel0" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<!-- 새로개설된 경기 끝 -->
<!-- Best 경기장 시작 -->
<div class="maingray">
<div class="container main">
<h4  class="maindesc">BEST 경기장</h4>
<div class="row text-center">
<div class="col-sm-6">
<p><img src="images/mycrown.png" alt="mycrown.png" title="1위아이콘"></p>
<div class="thumbnail gray">
<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bestground[0].gno}"><img style="width: 100%; height: 347px" src="${pageContext.request.contextPath}/upload/${bestground[0].gfile}" alt="${bestground[0].gfile}" class="img-thumbnail"/></a>
<p>${bestground[0].gname}</p>
			<c:choose>
				<c:when test="${bestground[0].gstar>4}"><p class="starcolor">★★★★★</p></c:when>
				<c:when test="${bestground[0].gstar>3}"><p class="starcolor">★★★★☆</p></c:when>
				<c:when test="${bestground[0].gstar>2}"><p class="starcolor">★★★☆☆</p></c:when>
				<c:when test="${bestground[0].gstar>1}"><p class="starcolor">★★☆☆☆</p></c:when>
				<c:when test="${bestground[0].gstar<=1}"><p class="starcolor">★☆☆☆☆</p></c:when>
			</c:choose>
</div>
</div>
<div class="col-sm-6">
	<div class="row best">
	<c:forEach begin="1" end="2" var="bg" items="${bestground}" varStatus="status">
		<div class="col-sm-6">
		<div class="thumbnail gray">
			<p><strong>${status.index +1}위</strong></p>
			<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bg.gno}"><img style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.gfile}" alt="${bg.gfile}" class="img-thumbnail"/></a>
			<p>${bg.gname}</p>
			<c:choose>
	    				<c:when test="${bg.gstar>4}"><p class="starcolor">★★★★★</p></c:when>
	    				<c:when test="${bg.gstar>3}"><p class="starcolor">★★★★☆</p></c:when>
	    				<c:when test="${bg.gstar>2}"><p class="starcolor">★★★☆☆</p></c:when>
	    				<c:when test="${bg.gstar>1}"><p class="starcolor">★★☆☆☆</p></c:when>
	    				<c:when test="${bg.gstar<=1}"><p class="starcolor">★☆☆☆☆</p></c:when>
	    	</c:choose>
		</div>
		</div>
	</c:forEach>
	</div>
	<div class="row text-right">
		<p>#지금까지 #이런경기장은 #없었다</p>
		<p>#이것은 프로경기장인가 #동네경기장인가?</p>
		<p>#outsidersports에서 만나볼 수 있는 #경기장입니다</p>
	</div>
</div>
</div>
</div>
</div>
<!-- Best 경기장 끝 -->
<!-- 인기 경기장 시작 -->
<div class="container main">
<h4 class="maindesc">인기 경기장</h4>
<div id="myCarousel5" class="carousel slide" data-ride="carousel" data-interval="false">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel5" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel5" data-slide-to="1"></li>
    <li data-target="#myCarousel5" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner text-center">
    <div class="item active">
    <c:forEach begin="0" end="3" var="bg" items="${popularground}" varStatus="status">
    		<div class="col-sm-3">
    		<div class="thumbnail">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bg.gno}"><img class="img-circle" style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.gfile}" alt="${bg.gfile}" class="img-thumbnail"/></a>
    			<p><strong>${status.index +1}위</strong></p>
    			<p>${bg.gname}</p>
    			<p><strong class="starcolor">${bg.glike}</strong>명이 좋아합니다</p>
    		</div>
    		</div>
    </c:forEach>
    </div>
    <div class="item">
      <c:forEach begin="4" end="7" var="bg" items="${popularground}" varStatus="status">
    		<div class="col-sm-3">
    		<div class="thumbnail">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bg.gno}"><img class="img-circle" style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.gfile}" alt="${bg.gfile}" class="img-thumbnail"/></a>
    			<p><strong>${status.index +1}위</strong></p>
    			<p>${bg.gname}</p>
    			<p><strong class="starcolor">${bg.glike}</strong>명이 좋아합니다</p>
    		</div>
    		</div>
  	  </c:forEach>
    </div>
    <div class="item">
      <c:forEach begin="8" end="11" var="bg" items="${popularground}" varStatus="status">
    		<div class="col-sm-3">
    		<div class="thumbnail">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bg.gno}"><img class="img-circle" style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.gfile}" alt="${bg.gfile}" class="img-thumbnail"/></a>
    			<p><strong>${status.index +1}위</strong></p>
    			<p>${bg.gname}</p>
    			<p><strong class="starcolor">${bg.glike}</strong>명이 좋아합니다</p>
    		</div>
    		</div>
  	  </c:forEach>
    </div>
  </div>
  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel5" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel5" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>
<!-- 인기 경기장 끝 -->
<!-- 신규 경기장 시작 -->
<div class="maingray">
<div class="container main">
<h4 class="maindesc">신규 경기장</h4>
    <c:forEach begin="0" end="2" var="bg" items="${newground}" varStatus="status">
    		<div class="col-sm-4 text-center">
    		<div class="thumbnail gray">
    		<%-- <p><img src="${pageContext.request.contextPath}/upload/${list.gfile.split('/')[0]}" alt="${list.gfile.split('/')[0]}" style="width: 100%; height: 170px;"/></p> --%>
		  		<a href="${pageContext.request.contextPath}/gdetail.do?gno=${bg.gno}"><img style="width: 100%; height: 170px;" src="${pageContext.request.contextPath}/upload/${bg.gfile}" alt="${bg.gfile}" class="img-thumbnail"/></a>
    			<p>${bg.gname}</p>
    			<p>${bg.gaddr1}  ${bg.gaddr2}</p>
				<p>등록일: ${bg.gdate.substring(0, 10)}</p>
    		</div>
    		</div>
    </c:forEach>
</div>
 </div>
<!-- 신규 경기장 끝 -->
<div class="container news">
<div class="col-sm-6">
<h4 class="maindesc">최신 스포츠뉴스 TOP10</h4>
<ul>
	<li></li>
</ul>
</div>
<div class="col-sm-6">
<h4 class="maindesc">2010년대 대한민국 스포츠 TOP10</h4>
<iframe width="480" height="280" src="https://www.youtube.com/embed/1SCFg7xinqM" ></iframe>
</div>
</div>
 <script>
  $(function(){
		  $.ajax({
				url: "${pageContext.request.contextPath}/navernews.do",
				  type: "get",
				  dataType: "json",
				  success:function(data){
					  $(".container.news > .col-sm-6 > ul").empty();
						for(var i = 0; i < data.items.length; i++){
							 $(".container.news > .col-sm-6 > ul").append($("<li>").html("<a href='"+data.items[i].originallink+"'>" + data.items[i].title + "</a>"));
					  	}
				 },
				  error:function(xhr, textStatus, errorThrown){
					  $("#soccer").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
				 }
			});
	 });
  </script>
<%@ include file="/inc/footer.jsp"%>