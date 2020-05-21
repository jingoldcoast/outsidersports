<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
  <script>	
	jQuery(document).ready(function(){
		alert("oustiderSport에 처음오셨네요!\n편리한 서비스제공을 위하여 아래 정보도 입력부탁드립니다!");
		jQuery("#joinForm").submit(function(){
			if(jQuery("#oname").val()==""){ alert("이름을 입력해주세요!"); jQuery("#oname").focus(); return false;}
			if(jQuery("#ocontact").val()==""){ alert("연락처를 입력해주세요!"); jQuery("#ocontact").focus(); return false;}
			if(jQuery("#obirth").val()==""){ alert("생년월일을 입력해주세요!"); jQuery("#obirth").focus(); return false;}
			if(!(jQuery("input:radio[name='osex']").is(":checked"))){ alert("성별을 선택 선택해주세요!"); jQuery(":radio[id='s1']").attr("checked","checked"); return false;}
			if($("#agree:checked").length==0){ alert("모든 약관에 동의해주세요!"); jQuery("#agree").focus(); jQuery(":checkbox[name='agree']").attr("checked","checked"); return false;}

		});
	});
</script>
<div class="container myfirst">
	<h3 class="myhidden">카카오톡 로그인 회원가입</h3>
	<form action="${pageContext.request.contextPath}/kakaojoin.do" method="post" id="joinForm">
		<fieldset>
			<legend>필수 입력 정보</legend>
			<div class="form-group">
				<label for="oid">아이디</label>
				<input type="text" name="oid" id="oid" class="form-control" value="${nickname}" readonly title="아이디"/>
			</div>
			<div class="form-group">
				<label for="oname">이름</label> 
				<input type="text" name="oname" id="oname" class="form-control" placeholder="이름(실명으로 입력해주세요)" title="이름"/>
			</div>
			<div class="form-group">
				<label for="ocontact">연락처</label> 
				<input type="text" name="ocontact" id="ocontact" class="form-control" placeholder="전화번호 입력" title="연락처"/>
			</div>
			<div class="form-group">
				<label for="obirth">생년월일</label>
				<div class="row">
					<div class="col-sm-5">
						<input type="date" name="obirth" id="obirth" class="form-control" title="생년월일"/>
					</div>
					<div class="col-sm-7">
						<input type="radio" name="osex" id="s1" value="s1"/><label for="s1">남자</label>
						<input type="radio" name="osex" id="s2" value="s2"/><label for="s2">여자</label>
					</div>
				</div>
			</div>
			<div class="form-group" >
				<div class="row">
						<div class="col-sm-7">
							<input type="checkbox" id="agree" name="agree" title="Company 서비스 약관 동의"/>
							<label for="agree">OUTSIDER SPORTS 서비스 이용 약관 및 개인 정보 수집 및 이용에 동의합니다.</label>		
						</div>
						<div class="col-sm-5" id="modals"><!--  -->
						<button type="button" class="btn btny" data-toggle="modal" data-target="#myModal">약관 확인하기</button>
						<!-- Modal -->
						<div id="myModal" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">OUTSIDER SPORTS 약관</h4>
									</div>
									<div class="modal-body">
										<strong>서비스이용약관</strong><br/>
										<textarea class="form-control" name="companyServiceAgree" rows="10" readonly><%@ include file="/agree/agree1.txt" %></textarea>
										<strong>개인정보처리방침</strong><br/>
										<textarea class="form-control" name="companyServiceAgree" rows="10" readonly><%@ include file="/agree/agree2.txt" %></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btnc" data-dismiss="modal" title="닫기">닫기</button>
									</div>
								</div>
							</div>
						</div><!-- 모달 메세지 div끝 -->
					</div><!--  -->
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btnc form-control" value="회원가입" title="회원가입"/>
			</div>
			<div class="form-group">
				<input type="reset"    value="취소"  class="btn btn-default form-control" title="취소" /> 
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="/inc/footer.jsp"%>