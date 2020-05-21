<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
  <script>	
	jQuery(document).ready(function(){
		jQuery("#addform").submit(function(){
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
			if(jQuery("#gfile1").val()==""){ alert("대표이미지를 업로드해주세요!"); jQuery("#gfile1").focus(); return false;}
			var reg = /^<(\/)?([a-zA-Z0-9]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>$/;
			var target= $("#gdesc").val();
			if(reg.test(target)){
				alert("내용입력 실패! \n html 태그는 삽입할 수 없습니다!");
				$("#gdesc").focus();
				reg = null; target = null;
				return false;
			}
			reg = null; target = null;
		});
	});
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script>
$(function(){
	$("#zipcodesearch").click(function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	//console.log(data);
				$("#zipcode").val(data.zonecode);
				$("#gregion").val(data.sido);
				$("#address1").val(data.address);
	       }
	   }).open();
	});		
});  
</script>
<div class="container myfirst">
	<h3 class="myhidden">경기장입력</h3>
	<form action="${pageContext.request.contextPath}/gwrite.do" method="post" id="addform" enctype="multipart/form-data">
		<fieldset>
			<legend>경기장 정보입력</legend>
			<strong>(*) 은 필수입력사항입니다.</strong>
			<div class="form-group">
					<label for="gcategory">카테고리</label> 
					<select name="gcategory" id="gcategory" title="카테고리 선택" class="form-control">
					<option value="">선택</option>
					<option value="01">축구</option>
					<option value="02">농구</option>
					</select>
			</div>
			<div class="form-group">
				<label for="gname">경기장이름(*)</label> 
				<input type="text" name="gname" id="gname" class="form-control" placeholder="경기장이름" title="경기장이름"/>
			</div>
			<div class="form-group">
				<label for="address2">경기장주소(*)</label>
				<div class="row">
					<div class="col-sm-2">
						<input type="text" id="zipcode" name="zipcode" class="form-control" placeholder="우편번호" readonly="readonly" title="우편번호"/>
					</div>
					<div class="col-sm-10">
						<input type="button" id="zipcodesearch" class="btn btnc" value="우편번호 검색" title="우편번호 검색"/>
					</div>
				</div>
				<div class="row">
					<input type="hidden" id="gregion" name="gregion" value=""/>
					<div class="col-sm-7">
						<input type="text" id="address1" name="address1" readonly="readonly" class="form-control" placeholder="주소(자동입력)" title="주소(자동입력)"/>
					</div>
					<div class="col-sm-5">
						<input type="text" id="address2" name="address2" class="form-control" placeholder="상세주소" title="상세주소"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="gside">각팀 추천인원(*)</label> 
				 <select name="gside" id="gside" title="추천인원선택" class="form-control">
                 <option value="">선택</option>
                 <option value="3">3 vs 3</option>
                 <option value="4">4 vs 4</option>
                 <option value="5">5 vs 5</option>
                 <option value="9">9 vs 9</option>
                 <option value="10">10 vs 10</option>
                 <option value="11">11 vs 11</option>
           	 	 </select>
			</div>
			<div class="form-group">
			<table class="table table-hover">
			<caption>편의시설(*)</caption>
			  	<colgroup>
   					 <col style="background-color:transparent">
   					 <col>
  				</colgroup>
			<tbody>
				<tr>
					<th scope="row">주차시설</th>
					<td>
						<input type="radio" name="parking" id="parkingy" value="y"/><label for="parkingy">있음</label>
						<input type="radio" name="parking" id="parkingn" value="n"/><label for="parkingn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">조명</th>
					<td>
						<input type="radio" name="light" id="lighty" value="y"/><label for="lighty">있음</label>
						<input type="radio" name="light" id="lightn" value="n"/><label for="lightn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">실내/실외</th>
					<td>
						<input type="radio" name="io" id="in" value="y"/><label for="in">실내</label>
						<input type="radio" name="io" id="out" value="n"/><label for="out">실외</label>
					</td>
				</tr>
				<tr>
					<th scope="row">샤워실</th>
					<td>
						<input type="radio" name="shower" id="showery" value="y"/><label for="showery">있음</label>
						<input type="radio" name="shower" id="showern" value="n"/><label for="showern">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">냉난방</th>
					<td>
						<input type="radio" name="air" id="airy" value="y"/><label for="airy">있음</label>
						<input type="radio" name="air" id="airn" value="n"/><label for="airn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">공대여</th>
					<td>
						<input type="radio" name="ball" id="bally" value="y"/><label for="bally">있음</label>
						<input type="radio" name="ball" id="balln" value="n"/><label for="balln">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">팀조끼대여</th>
					<td>
						<input type="radio" name="vest" id="vesty" value="y"/><label for="vesty">있음</label>
						<input type="radio" name="vest" id="vestn" value="n"/><label for="vestn">없음</label>
					</td>
				</tr>
				<tr>
					<th scope="row">신발대여</th>
					<td>
						<input type="radio" name="shoes" id="shoesy" value="y"/><label for="shoesy">있음</label>
						<input type="radio" name="shoes" id="shoesn" value="n"/><label for="shoesn">없음</label>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div class="form-group">
			  <label for="gfile1">사진1(*) (최대 5MB)</label>
			  <p>대표이미지로 사용되오며, 필수 업로드 항목입니다.</p>
			  <input type="file" class="form-control" name="gfile1" id="gfile1" title="사진1"/>
			  <label for="gfile2">사진2 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile2" id="gfile2" title="사진2"/>
			  <label for="gfile3">사진3 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile3" id="gfile3" title="사진3"/>
			  <label for="gfile4">사진4 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile4" id="gfile4" title="사진4"/>
			  <label for="gfile5">사진5 (최대 5MB)</label>
			  <input type="file" class="form-control" name="gfile5" id="gfile5" title="사진5"/>
	 	 	</div>
	 	 	<div class="form-group">
			  <label for="gdesc">경기장 특이사항</label>
			  <textarea name="gdesc"  id="gdesc"  cols="60" rows="10" class="form-control" title="경기장 특이사항"></textarea>
			</div>	
			<div class="form-group">
				<input type="submit" class="btn btnc form-control" value="등록하기" title="등록하기"/>
			</div>
			<div class="form-group">
				<input type="reset" value="취소"  class="btn btn-default form-control" title="취소" /> 
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="/inc/footer.jsp"%>
