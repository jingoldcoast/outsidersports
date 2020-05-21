<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/inc/header.jsp"%>
 <%request.setCharacterEncoding("UTF-8"); %>
  <script>	
	jQuery(document).ready(function(){
		var check = -1; //중복여부 확인했는지 체크할라고
		jQuery("#joinform").submit(function(){
			if(jQuery("#oid").val()==""){ alert("아이디를 입력해주세요!"); jQuery("#oid").focus(); return false;}
			if(jQuery("#oname").val()==""){ alert("이름을 입력해주세요!"); jQuery("#oname").focus(); return false;}
			if(jQuery("#ocontact").val()==""){ alert("연락처를 입력해주세요!"); jQuery("#ocontact").focus(); return false;}
			if(jQuery("#opass1").val()==""){ alert("비밀번호를 입력해주세요!"); jQuery("#opass1").focus(); return false;}
			if(jQuery("#opass2").val()==""){ alert("비밀번호 확인을 입력해주세요!"); jQuery("#opass2").focus(); return false;}
			if(jQuery("#obirth").val()==""){ alert("생년월일을 입력해주세요!"); jQuery("#obirth").focus(); return false;}
			if(!(jQuery("input:radio[name='osex']").is(":checked"))){ alert("성별을 선택 선택해주세요!"); jQuery(":radio[id='s1']").attr("checked","checked"); return false;}
			if($("#agree:checked").length==0){ alert("모든 약관에 동의해주세요!"); jQuery("#agree").focus(); jQuery(":checkbox[name='agree']").attr("checked","checked"); return false;}
			if(jQuery("#opass1").val() != jQuery("#opass2").val()){ 
				alert("비밀번호가 일치하지 않습니다!"); 
				jQuery("#opass1").focus(); 
				jQuery("#opass1").val(""); jQuery("#opass2").val("");  
				return false; 
			}
			if(check != 0){
				alert('아이디 중복검사를 완료해주세요!');
				$("#oid").focus();
				return false;
			}else{
				check = null;
			}
			var reg = /^010(?:\d{3,4})\d{4}$/;
			var target = $("#ocontact").val();
			if(!reg.test(target)){
				alert('연락처 입력형식 오류입니다.\n01023567777의 형태로 숫자만 입력해주세요!');
				jQuery("#ocontact").focus();
				return false;
			}
			reg = null; target = null;
		});
		 $("#oid").keyup(function(event){
              if (!(event.keyCode >=37 && event.keyCode<=40)) {
                  var inputVal = $(this).val();
                  $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
                  inputVal = null;
             }
         });
		jQuery("#oid").blur(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/oidcheck.do",
				  type: "post",
				  dataType: "text",
				  data: { "oid" : $("#oid").val()},
				  success:function(data){
					if(data==0){
						 $(".result").html("<span style='color:green'>멋진 아이디입니다!</span>");
						 check=0;
					}
					else if (data==1){
						 $(".result").html("<span style='color:red'>이미 사용중인 아이디입니다. 다시 시도해주세요!</span>");
						 check=1;
					}
					else{
						 $(".result").html("<span style='color:red'>사용불가입니다. 관리자에게 문의해주세요</span>");
						 check=-1;
					}
					
				 },
				  error:function(xhr, textStatus, errorThrown){
					  $(".result").html(textStatus + "(HTTP-)" + xhr.status + "/" + errorThrown);
				 }
			});
		
		});
	});
</script>
<c:choose>
	<c:when test="${requestScope.result eq null}"></c:when>
	<c:otherwise><script>alert('${what}');</script></c:otherwise>
</c:choose>
<div class="container myfirst join">
	<h3 class="myhidden">회원가입</h3>
	<strong class="starcolor">${what}</strong>
	<form action="${pageContext.request.contextPath}/join.do" method="post" id="joinform">
		<fieldset>
			<legend>회원가입 정보</legend>
			<strong>(*) 은 필수입력사항입니다.</strong>
			<div class="form-group">
				<label for="oid">아이디(*)</label>
				<div class="row">
					<div class="col-sm-6">
						<input type="text" name="oid" id="oid" class="form-control" placeholder="아이디(30자 내로, 한글불가)" maxlength="30" title="아이디입력"/>
					</div>
					<div class="result col-sm-6">
						※주의: 아이디는 중복되게 가입할 수 없습니다.
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="oname">이름(*)</label> 
				<input type="text" name="oname" id="oname" class="form-control" placeholder="이름(실명으로 입력해주세요)" title="이름입력"/>
			</div>
			<div class="form-group">
				<label for="ocontact">연락처(*)</label> 
				<input type="text" name="ocontact" id="ocontact" class="form-control" placeholder="전화번호 입력" title="전화번호 입력"/>
			</div>
			<div class="form-group">
				<label for="opass1">비밀번호(*)</label> 
				<input type="password" name="opass1" id="opass1" class="form-control" placeholder="비밀번호" title="비밀번호입력"/>
			</div>
			<div class="form-group">
				<label for="opass2">비밀번호 확인(*)</label> 
				<input type="password" name="opass2" id="opass2" class="form-control" placeholder="비밀번호 확인" title="비밀번호 확인 입력"/>
			</div>
			<div class="form-group">
				<label for="obirth">생년월일(*)</label>
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
						<button type="button" class="btn btny" data-toggle="modal" data-target="#myModal" title="약관 확인하기">약관 확인하기</button>
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
										<textarea class="form-control" name="companyServiceAgree" rows="10" readonly title="서비스이용약관"><%@ include file="/agree/agree1.txt" %></textarea>
										<strong>개인정보처리방침</strong><br/>
										<textarea class="form-control" name="companyServiceAgree" rows="10" readonly title="개인정보처리방침"><%@ include file="/agree/agree2.txt" %></textarea>
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
			  <script src="https://www.google.com/recaptcha/api.js"></script>
			  <script type="text/javascript">
			      var onloadCallback = function() {
			        grecaptcha.render('html_element', {
			          'sitekey' : '6Ldw0N8UAAAAAIAlbKX-LtHjDvjkkdjZvTMVuksY'
			        });
			      };
			  </script>
   			 <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"> </script>
			<div class="form-group"><!-- 로봇이아닙니다 -->
				<div class="g-recaptcha" data-sitekey="6Ldw0N8UAAAAAIAlbKX-LtHjDvjkkdjZvTMVuksY"></div>
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