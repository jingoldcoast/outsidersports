<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../inc/header.jsp"%>

<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:when test="${requestScope.result > 0}"><script>alert('${what}');</script></c:when>
	<c:when test="${requestScope.result == 0}"><script>alert('로그인 정보가 일치하지 않습니다.\n다시 로그인 시도해주세요!');</script></c:when>
	<c:when test="${requestScope.result < 0}"><script>alert('처리 실패\n관리자에게 문의해주세요');</script></c:when>
</c:choose>
 <script>	
	jQuery(document).ready(function(){
		jQuery("#loginform").submit(function(){
			if(jQuery("#oid").val()==""){
				alert("아이디를 입력해주세요!");
				jQuery("#oid").focus();
				return false;
			}
			if(jQuery("#opass").val()==""){
				alert("비밀번호를 입력해주세요!");
				jQuery("#opass").focus();
				return false;
			}
		});
	});
</script>
<div class="container myfirst login">
<h3 class="myhidden">로그인</h3>
<div class="col-sm-6">
<form action="${pageContext.request.contextPath}/login.do" method="post" id="loginform">
<fieldset>
<legend>로그인</legend>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-9">
				<div class="row form-group">
					<div class="col-sm-3"><label for="oid">아이디</label></div>
					<div class="col-sm-9">
						<c:choose>
								<c:when test="${remember eq null}"><input type="text" name="oid" id="oid" class="form-control" /></c:when>					
								<c:otherwise><input type="text" name="oid" id="oid" class="form-control" value="${dto.oid}" title="아이디"/></c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3"><label for="opass">비밀번호</label></div>
					<div class="col-sm-9"><input type="password" name="opass" id="opass" class="form-control" title="비밀번호"/></div>
				</div>
				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-9">
						<c:choose>
								<c:when test="${remember eq null}"><input type="checkbox" name="remember" id="remember"/></c:when>					
								<c:otherwise><input type="checkbox" name="remember" id="remember" checked/></c:otherwise>
						</c:choose>
						<label for="remember">아이디 저장하기</label>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<input type="submit" class="btn btnc loginbtn" value="LOGIN" title="로그인"/>
			</div>
		</div>
		<div class="row text-center findinfo">
					<a href="${pageContext.request.contextPath}/find_view.do?find=id" title="아이디 찾기"><strong>아이디 찾기</strong></a> 
					<a href="${pageContext.request.contextPath}/find_view.do?find=pass" title="비밀번호 찾기"><strong>비밀번호 찾기</strong></a> 
		</div>
		<div class="form-group text-center">
		<div class="col-sm-6">
		<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=a681026afa8a8a4d931d39ba35758e28&redirect_uri=http://localhost:8080/outsiderSports/kakao.login&response_type=code"> -->
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=a681026afa8a8a4d931d39ba35758e28&redirect_uri=http://jingoldcoast.cafe24.com/outsidersports/kakao.login&response_type=code">
		<img style="width: 212px; height: 47px;" src="images/kakaoLogin.png" alt="카카오톡로그인" title="카카오톡로그인">
		</a>
		</div>
		<div class="col-sm-6">
		<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=7n0_FK3nbZQcD6HgH7MM&state=네아로&redirect_uri=http://jingoldcoast.cafe24.com/outsidersports/naver.login"> 
		<img style="width: 212px; height: 47px;" src="images/naverlogin.PNG" alt="네이버로그인" title="네이버로그인">
		</a>
		</div>
		
		</div>
	</div>
</fieldset>
</form>
</div>

<div class="col-sm-6 loginad">
	<a href="https://www.youtube.com/watch?v=U0Cc77wR3cU" title="시연동영상보기(새창열림)"><img src="ad/mine2.png" alt="mine2.png"/></a>
</div>
</div><!-- 큰 div -->

<%@ include file="../inc/footer.jsp"%>