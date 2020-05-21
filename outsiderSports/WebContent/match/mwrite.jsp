<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
 <%@ include file="/inc/header.jsp"%>
<script>	
	jQuery(document).ready(function(){
		$("#mprice").keyup(function(event){
            if (!(event.keyCode >=37 && event.keyCode<=40)) {
                var inputVal = $(this).val();
                $(this).val(inputVal.replace(/[^0-9]/gi,''));
           }
       });
		jQuery("#addform").submit(function(){
			if(jQuery("#gno").val()==""){ alert("경기장을 선택해주세요!"); jQuery("#gno").focus(); return false;}
			if(jQuery("#mtitle").val()==""){ alert("경기이름은 필수입니다!\n 경기장을 선택하시면 자동완성됩니다!"); jQuery("#gno").focus(); return false;}
			if(jQuery("#mdate").val()==""){ alert("경기날짜를 입력해주세요!"); jQuery("#mdate").focus(); return false;}
			if(jQuery("#mhour").val()==""){ alert("경기시간을 입력해주세요!"); jQuery("#mhour").focus(); return false;}
			if(jQuery("#mduration").val()==""){ alert("경기진행시간을 선택해주세요!"); jQuery("#mduration").focus(); return false;}
			if(jQuery("#mprice").val()==""){ alert("참여비용을 입력해주세요!"); jQuery("#mprice").focus(); return false;}
			if(jQuery("#mtotal").val()==""){ alert("최대인원을 설정해주세요!"); jQuery("#mtotal").focus(); return false;}
			if(!(jQuery("input:radio[name='msex']").is(":checked"))){ alert("참여자의 성별을 선택 선택해주세요!"); jQuery(":radio[id='s3']").attr("checked","checked"); return false;}
		});
		$("#gno").change(function(){
			if($("#gno").val()==""){
				$("#mtitle").val("");
				return false;
			}
			$.ajax({
				url: "${pageContext.request.contextPath}/selectgdetail.do",
				type: "get",
				dataType:"json",
				data: { "gno" : $("#gno").val()},
				success:function(data){
					var setmtitle = data[0].gregion + " / " + data[0].gname + " (" + data[0].gside + ")";
					$("#mtitle").val(setmtitle);
				},
				error:function(xhr, textStatus, errorThrown){
					$("#mtitle").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
				}
			});
		});
		$("#mtotal").change(function(){
			$(".mtotalresult").html($("#mtotal").val() + "명");
		});
	});
</script>

<div class="container myfirst">
	<h3 class="myhidden">경기개설</h3>
	<form action="${pageContext.request.contextPath}/mwrite.do" method="post" id="addform">
		<fieldset>
			<legend>개설할 경기정보 입력</legend>
			<strong>(*) 은 필수입력사항입니다.</strong>
			<div class="form-group">
				 <label for="gno">경기가 진행될 경기장(*)</label> 
				 <select name="gno" id="gno" title="경기장 선택" class="form-control">
                 <option value="">선택</option>
                 <c:forEach var="list" items="${list}" varStatus="status">
                 	<option value="${list.gno}">${list.gname}</option>
                 </c:forEach>
           	 	 </select>
			</div>
			<div class="form-group">
				<label for="mtitle">경기이름(*)</label>
				<input type="text" readonly="readonly" id="mtitle" name="mtitle" value="" placeholder="경기장선택시 자동완성" class="form-control" title="경기이름"/>
			</div>
			<div class="form-group">
				<label for="mhost">개설자(*)</label>
				<input type="text" readonly="readonly" id="mhost" name="mhost" value="${sessionScope.oid}" class="form-control" title="개설자"/>
			</div>
			<div class="form-group">
				<label for="mdate">경기날짜(*)</label>
				<input type="date" id="mdate" name="mdate" class="form-control" min="${today}" title="경기날짜"/>
			</div>
			<div class="form-group">
				<label for="mhour">경기시간(*)</label>
				<input type="time" id="mhour" name="mhour" class="form-control" title="경기시간"/>
				<p>경기는 정시에만 진행합니다. 분단위는 처리되지 않습니다!</p>
			</div>
			<div class="form-group">
				<label for="mduration">경기진행시간(*)</label>
				 <select name="mduration" id="mduration" title="경기진행시간 선택" class="form-control">
                 	<option value="">선택</option>
                 	<option value="60">1시간</option>
                 	<option value="90">1시간 반</option>
                 	<option value="120">2시간</option>
                 </select>
			</div>
			<div class="form-group">
				<label for="mprice">경기장 참여비용(*)</label>
				<input type="text" id="mprice" name="mprice" class="form-control" placeholder="1인당 참여비용 입력 (ex. 10000)" title="경기장 참여비용"/>
			</div>
			<div class="form-group">
				<label for="mtotal">참여자 최대 인원 및 성별(*)</label>
				<div class="row">
				<div class="mtotalresult col-sm-2">좌우 드래그>></div>
				<div class="col-sm-10">
				<input type="range" min="6" max="50" id="mtotal" name="mtotal" class="form-control"/>
				</div>
				</div>
				<input type="radio" name="msex" id="s1" value="m"/><label for="s1">남자</label>
				<input type="radio" name="msex" id="s2" value="f"/><label for="s2">여자</label>
				<input type="radio" name="msex" id="s3" value="c"/><label for="s3">남녀모두</label>
			</div>
			<div class="form-group">
			<c:choose>
				<c:when test="${sessionScope.oid eq null}"><input type="button" class="btn btny form-control" value="로그인 후 개설가능합니다" title="로그인 후 개설가능합니다"/></c:when>
				<c:otherwise><input type="submit" class="btn btnc form-control" value="등록하기" title="등록하기"/></c:otherwise>
			</c:choose>
			</div>
			<div class="form-group">
				<input type="reset" value="취소" class="btn btn-default form-control" title="취소" /> 
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="/inc/footer.jsp"%>