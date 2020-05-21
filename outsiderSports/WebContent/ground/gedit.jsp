<%@page import="com.company.dto.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>

  <script>	
	jQuery(document).ready(function(){
		$("#zipcodesearch").click(function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	console.log(data);
					$("#zipcode").val(data.zonecode);
					$("#gregion").val(data.sido);
					$("#address1").val(data.address);
		       }
		   }).open();
		});		
		
		jQuery("#editform").submit(function(){
			if(jQuery("#gname").val()==""){ alert("경기장이름을 입력해주세요!"); jQuery("#gname").focus(); return false;}
			if(jQuery("#zipcode").val()==""){ alert("우편번호를 검색해주세요!"); jQuery("#zipcode").focus(); return false;}
			if(jQuery("#gside").val()==""){ alert("추천인원을 선택해주세요!"); jQuery("#gside").focus(); return false;}
			if(jQuery("#gcategory").val()==""){ alert("카테고리를 선택해주세요!"); jQuery("#gcategory").focus(); return false;}
			if(!(jQuery("input:radio[name='parking']").is(":checked"))){ alert("주차시설을 선택 선택해주세요!"); jQuery(":radio[id='parkingn']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='light']").is(":checked"))){ alert("조명을 선택 선택해주세요!"); jQuery(":radio[id='lightn']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='io']").is(":checked"))){ alert("실내/실외를 선택 선택해주세요!"); jQuery(":radio[id='in']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='shower']").is(":checked"))){ alert("샤워실을 선택 선택해주세요!"); jQuery(":radio[id='showern']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='air']").is(":checked"))){ alert("냉난방을 선택 선택해주세요!"); jQuery(":radio[id='airn']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='ball']").is(":checked"))){ alert("공대여를 선택 선택해주세요!"); jQuery(":radio[id='balln']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='vest']").is(":checked"))){ alert("팀조끼대여를 선택 선택해주세요!"); jQuery(":radio[id='vestn']").attr("checked","checked"); return false;}
			if(!(jQuery("input:radio[name='shoes']").is(":checked"))){ alert("신발대여를 선택 선택해주세요!"); jQuery(":radio[id='shoesn']").attr("checked","checked"); return false;}
		});
	});
</script>
<div class="container myfirst">
<%
GroundDTO gdto = new GroundDTO();
gdto = (GroundDTO)request.getAttribute("dto");
String gca = gdto.getGcategory(); 
String c1 = gdto.getParking();	
String c2 = gdto.getLight();
String c3 = gdto.getIo(); 
String c4 = gdto.getShower();  
String c5 = gdto.getAir();
String c6 = gdto.getBall(); 
String c7 = gdto.getVest();  
String c8 = gdto.getShoes(); 
String gside = gdto.getGside() + ""; 
%>
	<h3 class="myhidden">경기장 정보수정</h3>
	<form action="${pageContext.request.contextPath}/gedit.do" method="post" id="editform" enctype="multipart/form-data">
		<fieldset>
			<legend>경기장 정보수정</legend>
			<input type="hidden" name="gno" value="${dto.gno}"/>
			<div class="form-group">
					<label for="gcategory">카테고리</label> 
					<select name="gcategory" id="gcategory" title="카테고리 선택" class="form-control">
					<option value="">선택</option>
					<option value="01" <%if( gca.equals("축구")){%> selected <%}%>>축구</option>
					<option value="02" <%if( gca.equals("농구")){%> selected <%}%>>농구</option>
					</select>
			</div>
			<div class="form-group">
				<label for="gname">경기장이름</label> 
				<input type="text" name="gname" id="gname" class="form-control" placeholder="경기장이름" value="${dto.gname}" title="경기장이름"/>
			</div>
			<div class="form-group">
				<label for="address2">경기장주소</label>
				<div class="row">
					<div class="col-sm-2">
						<input type="text" id="zipcode" name="zipcode" class="form-control" placeholder="우편번호" readonly="readonly" value="${dto.gzipcode}" title="우편번호"/>
					</div>
					<div class="col-sm-10">
						<input type="button" id="zipcodesearch" class="btn btnc" value="우편번호 검색" title="우편번호 검색"/>
					</div>
				</div>
				<div class="row">
					<input type="hidden" id="gregion" name="gregion" value=""/>
					<div class="col-sm-7">
						<input type="text" id="address1" name="address1" readonly="readonly" class="form-control" placeholder="주소(자동입력)" value="${dto.gaddr1}" title="주소(자동입력)"/>
					</div>
					<div class="col-sm-5">
						<input type="text" id="address2" name="address2" class="form-control" placeholder="상세주소" value="${dto.gaddr2}" title="상세주소"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="gside">각팀 추천인원</label> 
				 <select name="gside" id="gside" title="추천인원선택" class="form-control">
                 <option value="">선택</option>
                 <option value="3"<%if( gside.equals("3")){%> selected <%}%>>3 vs 3</option>
                 <option value="4"<%if( gside.equals("4")){%> selected <%}%>>4 vs 4</option>
                 <option value="5"<%if( gside.equals("5")){%> selected <%}%>>5 vs 5</option>
                 <option value="9"<%if( gside.equals("9")){%> selected <%}%>>9 vs 9</option>
                 <option value="10"<%if( gside.equals("10")){%> selected <%}%>>10 vs 10</option>
                 <option value="11"<%if( gside.equals("11")){%> selected <%}%>>11 vs 11</option>
           	 	 </select>
			</div>
			<div class="form-group">
			<table class="table table-hover">
			<caption>편의시설</caption>
			  	<colgroup>
   					 <col style="background-color:transparent">
   					 <col>
  				</colgroup>
			<tbody>
				<tr>
					<th scope="row">주차시설</th>
					<td>
						<input type="radio" name="parking" id="parkingy" value="y" <%if(c1.equals("y")){%> checked <%}%>/><label for="parkingy">있음</label>
						<input type="radio" name="parking" id="parkingn" value="n"<%if(c1.equals("n")){%> checked <%}%>/><label for="parkingn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">조명</th>
					<td>
						<input type="radio" name="light" id="lighty" value="y"<%if(c2.equals("y")){%> checked <%}%>/><label for="lighty">있음</label>
						<input type="radio" name="light" id="lightn" value="n"<%if(c2.equals("n")){%> checked <%}%>/><label for="lightn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">실내/실외</th>
					<td>
						<input type="radio" name="io" id="in" value="y"<%if(c3.equals("y")){%> checked <%}%>/><label for="in">실내</label>
						<input type="radio" name="io" id="out" value="n"<%if(c3.equals("n")){%> checked <%}%>/><label for="out">실외</label>
					</td>
				</tr>
				<tr>
					<th scope="row">샤워실</th>
					<td>
						<input type="radio" name="shower" id="showery" value="y"<%if(c4.equals("y")){%> checked <%}%>/><label for="showery">있음</label>
						<input type="radio" name="shower" id="showern" value="n"<%if(c4.equals("n")){%> checked <%}%>/><label for="showern">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">냉난방</th>
					<td>
						<input type="radio" name="air" id="airy" value="y"<%if(c5.equals("y")){%> checked <%}%>/><label for="airy">있음</label>
						<input type="radio" name="air" id="airn" value="n"<%if(c5.equals("n")){%> checked <%}%>/><label for="airn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">공대여</th>
					<td>
						<input type="radio" name="ball" id="bally" value="y"<%if(c6.equals("y")){%> checked <%}%>/><label for="bally">있음</label>
						<input type="radio" name="ball" id="balln" value="n"<%if(c6.equals("n")){%> checked <%}%>/><label for="balln">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">팀조끼대여</th>
					<td>
						<input type="radio" name="vest" id="vesty" value="y"<%if(c7.equals("y")){%> checked <%}%>/><label for="vesty">있음</label>
						<input type="radio" name="vest" id="vestn" value="n"<%if(c7.equals("n")){%> checked <%}%>/><label for="vestn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">신발대여</th>
					<td>
						<input type="radio" name="shoes" id="shoesy" value="y"<%if(c8.equals("y")){%> checked <%}%>/><label for="shoesy">있음</label>
						<input type="radio" name="shoes" id="shoesn" value="n"<%if(c8.equals("n")){%> checked <%}%>/><label for="shoesn">없음</label>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div class="form-group">
			<input type="hidden" name="gfilebefore" value="${dto.gfile}"/>
			  <label for="gfile1">사진1 (최대 5MB) 대표이미지</label>
			  <p>사진 선택하지 않을 시, 기존 사진으로 대체됩니다.</p>
			  <input type="file" class="form-control" name="gfile1" id="gfile1" title="사진선택1"/>
			  <label for="gfile2">사진2 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile2" id="gfile2" title="사진선택2"/>
			  <label for="gfile3">사진3 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile3" id="gfile3" title="사진선택3"/>
			  <label for="gfile4">사진4 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile4" id="gfile4" title="사진선택4"/>
			  <label for="gfile5">사진5 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile5" id="gfile5" title="사진선택5"/>
	 	 	</div>
	 	 	<div class="form-group">
			  <label for="gdesc">경기장 특이사항</label>
			  <textarea name="gdesc"  id="gdesc"  cols="60"  rows="10"   class="form-control" title="경기장특이사항">${dto.gdesc}</textarea>
			</div>	
			<div class="form-group">
				<input type="submit" class="btn btnc form-control" value="등록하기" title="등록하기"/>
			</div>
			<div class="form-group">
				<input type="reset"    value="취소"  class="btn btn-default form-control" title="취소" /> 
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="/inc/footer.jsp"%>
