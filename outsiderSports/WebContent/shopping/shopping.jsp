<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/inc/header.jsp"%>
 <script>
 $(function(){
	 $.ajax({
			url:"${pageContext.request.contextPath}/navershopping.api", 
			type:"get",
			dataType:"json", 
			data: { "query" : "농구화"},
			success:function(data){
			for(var i =0; i < data.items.length; i++){
				var colsm4 = $("<div class='col-sm-2'>").html("<img style='height: 165px; max-width: 165px;' src='"+data.items[i].image+"' alt='"+data.items[i].mallName+"'/>");					
				var colsm6 = $("<div class='col-sm-8 shopdesc'>").html("<ul><li>상품명: "+data.items[i].title+"</li><li>최저가: "+data.items[i].lprice+"</li><li>판매처: "+data.items[i].mallName+"</li></ul>");
				var colsm2 = $("<div class='col-sm-2'>").html("<a href='"+data.items[i].link+"'>상품 사러가기</a>");
				var panelbody = $("<div class='panel-body'>").append(colsm4).append(colsm6).append(colsm2);
		  		var panel = $("<div class='panel'>").append(panelbody);
		  		$(".panel-group").append(panel);
		  		colsm4 = null; colsm6 = null; colsm2 = null; panelbody = null; panel = null;
			}
		},
		error:function(xhr, textStatus, errorThrown){ $(".panel-group").html(xhr.status);}
	});
	 $("#search").click(function(){
		 if($("#product").val()==""){
			 alert("상품명을 입력해주세요!");
			 $("#product").focus();
			 return false;
		}
		$(".panel-group").empty();
		 $.ajax({
				url:"${pageContext.request.contextPath}/navershopping.api", 
				type:"get",
				dataType:"json", 
				data: { "query" : $("#product").val()},
				success:function(data){
				for(var i =0; i < data.items.length; i++){
					var colsm4 = $("<div class='col-sm-2'>").html("<img style='height: 165px; max-width: 165px;' src='"+data.items[i].image+"' alt='"+data.items[i].mallName+"'/>");					
					var colsm6 = $("<div class='col-sm-8 shopdesc'>").html("<ul><li>상품명: "+data.items[i].title+"</li><li>최저가: "+data.items[i].lprice+"</li><li>판매처: "+data.items[i].mallName+"</li></ul>");
					var colsm2 = $("<div class='col-sm-2'>").html("<a href='"+data.items[i].link+"'>상품 사러가기</a>");
					var panelbody = $("<div class='panel-body'>").append(colsm4).append(colsm6).append(colsm2);
			  		var panel = $("<div class='panel'>").append(panelbody);
			  		$(".panel-group").append(panel);
			  		colsm4 = null; colsm6 = null; colsm2 = null; panelbody = null; panel = null;
				}
			},
			error:function(xhr, textStatus, errorThrown){ $(".panel-group").html(xhr.status);}
		});
	});
});
 </script>
<div class="container myfirst customer">
<div class="row">
	<div class="col-sm-2 menu">
		<%@ include file="../menu/customermenu.jsp" %>
	</div>
	<div class="col-sm-10" >
		<h3>물건사기</h3>
		<p>검색어를 입력하시면 네이버쇼핑을 즐길 수 있습니다!</p>
		<div class="row">
			<div class="col-sm-11 shopping"><label for="product">사고싶은 물건</label><input type="text" name="product" id="product" class="form-control" placeholder="찾고 싶은 상품명을 입력해주세요 ex) 풋살화"/></div>
			<div class="col-sm-1"><input type="button" class="btn btnc mtitlebtn" value="검색" title="검색" id="search"/></div>
		</div>
		<div class="panel-group"></div>
	</div>
</div>


</div>
<%@ include file="/inc/footer.jsp"%>