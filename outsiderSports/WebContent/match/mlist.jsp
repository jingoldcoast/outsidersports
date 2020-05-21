<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
  <script>
  $(function(){
	  $(":input").on("click", function(event){
		  var gcategorys=[]; 
		  $('input[name="gcategory"]:checkbox:checked').each(function(){gcategorys.push($(this).val());}); 
		  var gcategory = gcategorys.join(','); 
		  
		  var gregions=[]; 
		  $('input[name="gregion"]:checkbox:checked').each(function(){gregions.push($(this).val());}); 	
		  var gregion = gregions.join(','); 				
		  
		  var mdays=[]; 
		  $('input[name="mday"]:checkbox:checked').each(function(){mdays.push($(this).val());}); 
		  var mday = mdays.join(','); 
		  
		  var mhours=[]; 
		  $('input[name="mhour"]:checkbox:checked').each(function(){mhours.push($(this).val());}); 
		  var mhour = mhours.join(','); 
		  
		  var mdurations=[]; 
		  $('input[name="mduration"]:checkbox:checked').each(function(){mdurations.push($(this).val());}); 
		  var mduration = mdurations.join(','); 
		  
		  var msexs=[]; 
		  $('input[name="msex"]:checkbox:checked').each(function(){msexs.push($(this).val());}); 
		  var msex = msexs.join(','); 
		  
		  var gstars=[]; 
		  $('input[name="gstar"]:checkbox:checked').each(function(){gstars.push($(this).val());}); 
		  var gstar = gstars.join(','); 
		  
		  var convs=[]; 
		  $('input[name="conv"]:checkbox:checked').each(function(){convs.push($(this).val());}); 
		  var conv = convs.join(','); 
		  
		  var gsides=[]; 
		  $('input[name="gside"]:checkbox:checked').each(function(){gsides.push($(this).val());}); 
		  var gside = gsides.join(','); 
		  
		  var mprices=[]; 
		  $('input[name="mprice"]:checkbox:checked').each(function(){mprices.push($(this).val());}); 
		  var mprice = mprices.join(','); 
		  
		  var find = "";
		  if($("#find").val() != null){
			  find = $("#find").val();
		 }

		  $.ajax({
				url: "${pageContext.request.contextPath}/mlist_filter.do",
				  type: "get",
				  dataType: "json",
				  data: {"gcategory" : gcategory, "gregion": gregion,  "mday" : mday, "mhour": mhour, "mduration" : mduration, "msex" : msex, "gstar" : gstar, "conv" : conv, "gside" : gside, "mprice": mprice, "find" : find},
				  success:function(data){
					  $(".pagination").hide();
					  $(".col-sm-10").empty();
						for(var i = 0; i < data.length; i++){
							var msex = "";
							if(data[i].msex=="m"){ msex = "<p><span class='main_m'>■ </span>남성 경기</p>";}
							else if(data[i].msex=="f"){ msex = "<p><span class='main_f'>■ </span>여성 경기</p>";}
							else if(data[i].msex=="c"){ msex = "<p><span class='main_mix'>■ </span>혼성 경기</p>";}
							
					  		var colsm31 = $("<div class='col-sm-4'>").html("<p><img style='width: 100%; height: 170px;' src='${pageContext.request.contextPath}/upload/"+data[i].thumbnail+"' alt='"+data[i].thumbnail+"'/></p>");
					  		var colsm6 = $("<div class='col-sm-5 mlistview'>").html(
					  			"<a href='${pageContext.request.contextPath}/mdetail.do?mno="+data[i].mno+"' title='상세보기'>"+data[i].mtitle+"</a>"+	
					  			 msex +
					  			"<p>최대인원: "+data[i].mtotal+"명</p>"+
					  			"<p>참여비용: <span class='starcolor'>"+data[i].mprice+"</span>원</p>"+
					  			"<p>진행날짜: "+data[i].mdate+" / "+data[i].mday+"요일 / "+data[i].mhour+" 시</p>"
					  		);
					  		var colsm32 = $("<div class='col-sm-3 mlistview'>").html(
					  			"<p><span class='glyphicon glyphicon-star starcolor'></span>경기장평점: "+data[i].gstar+" / 5</p>"+
					  			"<p><span class='glyphicon glyphicon-ok-sign starcolor'></span>남은자리: "+data[i].cnt+" </p>"
					  		);
							var panelbody = $("<div class='panel-body'>").append(colsm31).append(colsm6).append(colsm32);
					  		var panel = $("<div class='panel'>").append(panelbody);
					  		$(".col-sm-10").append(panel);
					  		colsm31 = null; colsm6 = null; colsm32 = null; panelbody = null; panel=null;
					  	}
						
				 },
				  error:function(xhr, textStatus, errorThrown){
					  $(".error").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
				 }
			});
		  
		  gcategorys = null; gcategory = null; gregions = null; gregion = null; mdays = null; mday = null; mhours = null; mhour = null; 
		  mdurations = null; mduration = null; msexs = null; msex = null; gstars = null; gstar = null; convs = null; conv = null; 
		  gsides = null; gside = null; mprices = null; mprice = null; find = null;
		  
	 });
 });
  </script>
<div class="container myfirst"><!-- 1 -->

	<h3>개설된 경기 전체</h3>
		<div class="form-group text-right find">
					<a href="${pageContext.request.contextPath}/mlist.do?find=gstar"><strong <c:if test="${find eq 'gstar'}">style="color:#ed1a3a;"</c:if>>경기장 평점순</strong></a> 
					<a href="${pageContext.request.contextPath}/mlist.do?find=mdate"><strong <c:if test="${find eq 'mdate'}">style="color:#ed1a3a;"</c:if>>경기진행 임박순</strong></a> 
					<a href="${pageContext.request.contextPath}/mlist.do?find=mcreatedate"><strong <c:if test="${find eq 'mcreatedate'}">style="color:#ed1a3a;"</c:if>>최신업로드순</strong></a> 
					<a href="${pageContext.request.contextPath}/mlist.do?find=mprice1"><strong <c:if test="${find eq 'mprice1'}">style="color:#ed1a3a;"</c:if>>가격 높은순</strong></a> 
					<a href="${pageContext.request.contextPath}/mlist.do?find=mprice2"><strong <c:if test="${find eq 'mprice2'}">style="color:#ed1a3a;"</c:if>>가격 낮은순</strong></a> 
					<a href="${pageContext.request.contextPath}/mlist.do?find=mtitle"><strong <c:if test="${find eq 'mtitle'}">style="color:#ed1a3a;"</c:if>>가나다순</strong></a> 
		</div>
<div class="col-sm-2 sidesearch">
 
<div class="row side">
<div class="error"></div>
<h4>스포츠 종목</h4>
<ul class="side">
	<li><input type="checkbox" name="gcategory" value="01" id="01"/><label for="01">축구</label></li>
	<li><input type="checkbox" name="gcategory" value="02" id="02"/><label for="02">농구</label></li>
</ul>
</div>
<div class="row side">
<h4>지역</h4>
<ul class="side">
	<li class="gregion"><input type="checkbox" name="gregion" value="01" id="gregion01"/><label for="gregion01">서울</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="02" id="gregion02"/><label for="gregion02">경기</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="03" id="gregion03"/><label for="gregion03">인천</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="04" id="gregion04"/><label for="gregion04">부산</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="05" id="gregion05"/><label for="gregion05">대구</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="06" id="gregion06"/><label for="gregion06">광주</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="07" id="gregion07"/><label for="gregion07">대전</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="08" id="gregion08"/><label for="gregion08">제주</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="09" id="gregion09"/><label for="gregion09">울산</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="10" id="gregion10"/><label for="gregion10">강원</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="11" id="gregion11"/><label for="gregion11">충북</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="12" id="gregion12"/><label for="gregion12">충남</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="13" id="gregion13"/><label for="gregion13">전북</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="14" id="gregion14"/><label for="gregion14">전남</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="15" id="gregion15"/><label for="gregion15">경북</label></li>
	<li class="gregion"><input type="checkbox" name="gregion" value="16" id="gregion16"/><label for="gregion16">경남</label></li>
</ul>
</div>
<div class="row side">
<h4>요일</h4>
<ul class="side">
	<li><input type="checkbox" name="mday" value="0" id="day0"/><label for="day0">일요일</label></li>
	<li><input type="checkbox" name="mday" value="1" id="day1"/><label for="day1">월요일</label></li>
	<li><input type="checkbox" name="mday" value="2" id="day2"/><label for="day2">화요일</label></li>
	<li><input type="checkbox" name="mday" value="3" id="day3"/><label for="day3">수요일</label></li>
	<li><input type="checkbox" name="mday" value="4" id="day4"/><label for="day4">목요일</label></li>
	<li><input type="checkbox" name="mday" value="5" id="day5"/><label for="day5">금요일</label></li>
	<li><input type="checkbox" name="mday" value="6" id="day6"/><label for="day6">토요일</label></li>
</ul>
</div>
<div class="row side">
<h4>참여 비용</h4>
<ul class="side">
	<li><input type="checkbox" name="mprice" value="4999" id="mprice0"/><label for="mprice0">0~4,999원</label></li>
	<li><input type="checkbox" name="mprice" value="9999" id="mprice1"/><label for="mprice1">5,000~9,999원</label></li>
	<li><input type="checkbox" name="mprice" value="14999" id="mprice2"/><label for="mprice2">10,000~14,999원</label></li>
	<li><input type="checkbox" name="mprice" value="19999" id="mprice3"/><label for="mprice3">15,000~19,999원</label></li>
	<li><input type="checkbox" name="mprice" value="20000" id="mprice4"/><label for="mprice4">20,000원 이상</label></li>
</ul>
</div>
<div class="row side">
<h4>경기 진행시간</h4>
<ul class="side">
	<li><input type="checkbox" name="mduration" value="0" id="mduration0"/><label for="mduration0">1시간</label></li>
	<li><input type="checkbox" name="mduration" value="1" id="mduration1"/><label for="mduration1">1시간 반</label></li>
	<li><input type="checkbox" name="mduration" value="2" id="mduration2"/><label for="mduration2">2시간</label></li>
</ul>
</div>
<div class="row side">
<h4>경기 성별</h4>
<ul class="side">
	<li><input type="checkbox" name="msex" value="m" id="m"/><label for="m">남성</label></li>
	<li><input type="checkbox" name="msex" value="f" id="f"/><label for="f">여성</label></li>
	<li><input type="checkbox" name="msex" value="c" id="c"/><label for="c">혼성</label></li>
</ul>
</div>
<div class="row side starcolor">
<h4>경기장 평점(이하)</h4>
<ul class="side">
	<li><input type="checkbox" name="gstar" value="5" id="gstar5"/><label for="gstar5">★★★★★</label></li>
	<li><input type="checkbox" name="gstar" value="4" id="gstar4"/><label for="gstar4">★★★★☆</label></li>
	<li><input type="checkbox" name="gstar" value="3" id="gstar3"/><label for="gstar3">★★★☆☆</label></li>
	<li><input type="checkbox" name="gstar" value="2" id="gstar2"/><label for="gstar2">★★☆☆☆</label></li>
	<li><input type="checkbox" name="gstar" value="1" id="gstar1"/><label for="gstar1">★☆☆☆☆</label></li>
</ul>
</div>
<div class="row side">
<h4>편의시설(모두 포함)</h4>
<ul class="side">
	<li><input type="checkbox" name="conv" value="parking" id="parking"/><label for="parking">주차장</label></li>
	<li><input type="checkbox" name="conv" value="light" id="light"/><label for="light">조명</label></li>
	<li><input type="checkbox" name="conv" value="in" id="in"/><label for="in">실내</label></li>
	<li><input type="checkbox" name="conv" value="out" id="out"/><label for="out">실외</label></li>
	<li><input type="checkbox" name="conv" value="shower" id="shower"/><label for="shower">샤워장</label></li>
	<li><input type="checkbox" name="conv" value="air" id="air"/><label for="air">냉난방</label></li>
	<li><input type="checkbox" name="conv" value="ball" id="ball"/><label for="ball">공대여</label></li>
	<li><input type="checkbox" name="conv" value="vest" id="vest"/><label for="vest">팀조끼대여</label></li>
	<li><input type="checkbox" name="conv" value="shoes" id="shoes"/><label for="shoes">신발대여</label></li>
</ul>
</div>
<div class="row side">
<h4>각팀 추천인원</h4>
<ul class="sidelast">
	<li><input type="checkbox" name="gside" value="3" id="gside3"/><label for="gside3">3 vs 3</label></li>
	<li><input type="checkbox" name="gside" value="4" id="gside4"/><label for="gside4">4 vs 4</label></li>
	<li><input type="checkbox" name="gside" value="5" id="gside5"/><label for="gside5">5 vs 5</label></li>
	<li><input type="checkbox" name="gside" value="9" id="gside9"/><label for="gside9">9 vs 9</label></li>
	<li><input type="checkbox" name="gside" value="10" id="gside10"/><label for="gside10">10 vs 10</label></li>
	<li><input type="checkbox" name="gside" value="11" id="gside11"/><label for="gside11">11 vs 11</label></li>
</ul>
</div>
<input type="hidden" value="${find}" name="find" id="find"/>
</div>
<!-- 상세페이지 시작 -->
		<div class="col-sm-10">
		<c:if test="${list.size() ==0}"><strong style="font-size: 18px;">입력하신 조건에 맞는 검색결과가 없습니다.</strong></c:if>
			<c:forEach var="list" items="${list}" varStatus="status">
				<div class="panel" >
					<div class="panel-body">
							<div class="col-sm-4">
								<p><img src="${pageContext.request.contextPath}/upload/${list.thumbnail}" alt="${list.thumbnail}" style="width: 100%; height: 170px;"/></p>
					  			<%-- <p><img style="width: 100%; height: 170px;" src="/upload/${list.thumbnail}" alt="${list.thumbnail}"/></p>  --%>
							</div>
							<div class="col-sm-5 mlistview">
								<a href="${pageContext.request.contextPath}/mdetail.do?mno=${list.mno}&amp;page=${page}">${list.mtitle}</a>
								<c:choose>
									<c:when test="${list.msex eq 'm'}"><p><span class="main_m">■ </span>남성 경기</p></c:when>
									<c:when test="${list.msex eq 'f'}"><p><span class="main_f">■ </span>여성 경기</p></c:when>
									<c:when test="${list.msex eq 'c'}"><p><span class="main_mix">■ </span>혼성 경기</p></c:when>
								</c:choose>
								<p>최대인원: ${list.mtotal}명</p>
								<p>참여비용: <span class="starcolor"><fmt:formatNumber value="${list.mprice}" pattern="#,###"/></span>원</p>
								<p>진행날짜: ${list.mdate.substring(0, 10)} / ${list.mday}요일 / ${list.mhour} 시</p>
							</div>
							<div class="col-sm-3 mlistview">
								<p><span class="glyphicon glyphicon-star starcolor"></span>경기장평점: <fmt:formatNumber value="${list.gstar}" pattern=".00"/> / 5</p>
								<p><span class="glyphicon glyphicon-ok-sign starcolor"></span>남은자리: ${list.cnt}</p>
							</div>
					</div>
				</div>
		</c:forEach>
		<c:if test="${paging ne null}">
			<div class="form-group" style="text-align: center">
				<ul class="pagination">
					<c:if test="${paging.pre_bottom>=paging.bottomlist}">
						<c:choose>
							<c:when test="${find eq null}">
								<li><a href="${pageContext.request.contextPath}/mlist.do?page=${(paging.pre_bottom-2)*paging.onepagelimit}">이전</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/mlist.do?find=${find}&amp;page=${(paging.pre_bottom-2)*paging.onepagelimit}">이전</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:forEach var="i" begin="${paging.pre_bottom}" end="${paging.next_bottom}" step="${1}" varStatus="status">
						<c:choose>
							<c:when test="${paging.current_bottom==i}">
								<c:choose>
									<c:when test="${find eq null}">
										<li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/mlist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li class="active"><a style="background-color: #ed1a3a; border-color: #ed1a3a;" href="${pageContext.request.contextPath}/mlist.do?find=${find}&amp;page=${(i-1)*paging.onepagelimit}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${find eq null}">
										<li><a href="${pageContext.request.contextPath}/mlist.do?page=${(i-1)*paging.onepagelimit}">${i}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/mlist.do?find=${find}&amp;page=${(i-1)*paging.onepagelimit}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
					<c:if test="${paging.next_bottom<paging.pageAll}">
						<c:choose>
							<c:when test="${find eq null}">
								<li><a href="${pageContext.request.contextPath}/mlist.do?page=${paging.next_bottom*paging.onepagelimit}">다음</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/mlist.do?${find}&amp;page=${paging.next_bottom*paging.onepagelimit}">다음</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</ul>
			</div>
			</c:if>
		</div><!-- 상세페이지 끝 -->

</div>
<%@ include file="/inc/footer.jsp"%>