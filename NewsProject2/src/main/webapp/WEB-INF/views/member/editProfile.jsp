<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UTBC | 회원정보변경</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="/resources/font-awesome/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="/resources/bootstrap/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
      <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
 
	<script src="/resources/plugins/iCheck/icheck.min.js"></script>
<sec:authentication property="principal" var="principal"/>
<script>
	  $(function () {
	    $('input').iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass: 'iradio_square-blue',
	      increaseArea: '20%' // optional
	    });
	  });

$(document).ready(function(){
    var csrfToken = "${_csrf.token}" 
    var csrfHeader = "${_csrf.headerName}" 
    var userName = "${principal.uname}"
    $("#upw").val("");//시작하자마자 지워버리기
	//닉네임
	$("#uname").blur(function(){
		var reg = /^[ㄱ-ㅎ가-힣a-zA-z0-9]{2,6}$/;
		var msg1 = "닉네임은 영어숫자한글2~6글자까지";
		var flag = $("#uname");
		$.ajax({
	       url:"/member/chkUser",
	       beforeSend : function(xhr){
	           xhr.setRequestHeader(csrfHeader, csrfToken);  
	       },
	       type:"post",
	       dataType:"text",
	       data:$("#uname"),
	       success:function(data){
	           if(data>0 && userName != flag.val()){
	              $("#memFrm>div.form-group:eq(1)").removeClass(" has-success has-feedback");
	              $("#memFrm>div.form-group:eq(1)").addClass(" has-error has-feedback");
	              $(".imp1").html("<span class='imp1'>이미 사용중인 닉네임 입니다.</span>");
	              flag.focus();
	              console.log(data);
	           }else{
	              $(".imp1").text("");
	              $("#memFrm>div.form-group:eq(1)").removeClass(" has-error has-feedback");
	              checkInput(reg, flag, msg1);
	              bkInput(flag);    
	           }
	       }
	     });
	});
  //프로필사진
	$("#profile_picture").on("change", function(event){
	    var formData = new FormData();
	    var files = event.target.files;
	    var file = files[0];
	    formData.append('file' ,file);
	    console.log("member_profile_picture : " + file);
	    $.ajax({
	       url:"/uploadProfile",
	       beforeSend : function(xhr){
               xhr.setRequestHeader(csrfHeader, csrfToken);  
           },
	       data:formData,
	       dataType:"text",
	       processData: false,
           contentType: false,
           type:'POST',
           success:function(data){
               imgsrc="/displayFile?location=profile&fileName="+data;
               $(".img-rounded").attr("src", imgsrc);
               $("#filesrc").val(imgsrc);
           }
	    });
	});  
    function checkInput(reg, flag, msg1){
    	//체크해서 아무것도 없거나 같지 않으면
    	if(!reg.test(flag.val())){
    	    flag.next(".imp1").text(msg1);
    		flag.parent().addClass(" has-error has-feedback");	
    	}else{
    		flag.parent().removeClass(" has-error has-feedback");
    		flag.parent().addClass(" has-success has-feedback");
    		flag.next(".imp1").text("");
    	}
    }
    //복귀
    function bkInput(flag){
        flag.focus(function(){
    		flag.parent().removeClass(" has-error has-feedback");
    		flag.next(".imp1").remove();
    	});
    }
    //유효성검사
	$("#memFrm").submit(function(event){
	    event.preventDefault();
	    if($("#memFrm>div").hasClass("has-error") || $("#email").val() == "" || $("#upw").val() == ""||  $("#uname").val() == ""){
	    	alert("틀린 정보가 있으니 다시 확인하시오")				
			return false;
		}else{
			$(this).get(0).submit();
			return true;
		}			
	});
});
	</script>
  <link rel="stylesheet" href="/resources/plugins/iCheck/square/blue.css">

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">

<div class="login-box">
  <div class="login-logo">
    <a href="/"><b>UTBC</b></a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
  
    <form action="/member/editProfile" method="post" id="memFrm" name="memFrm">
      <div class="form-group has-feedback">
		<label class="col-sm-12 control-label">프로필에 표시될 사진을 업로드 해주세요.</label>
		<img class="img-rounded" style="float:left" width="40" height="40" src="${principal.filesrc }">
		<input type="file" name="profile_picture" id="profile_picture" style="width:100%" value="${principal.profile_picture }">				
		<input type="hidden" name="filesrc" id="filesrc" value="${principal.filesrc }">
      </div>
      <input type="hidden" name="uid" id="uid" value="${principal.uid }">
      <div class="form-group has-feedback">
        <input type="text" id="uname" name="uname" class="form-control" value="${principal.uname }" placeholder="uname">
        <strong class='imp1'></strong>
      </div>
      <div class="form-group has-feedback">
      	<label class="col-sm-12 control-label" >비밀번호</label>
        <input type="password" id="upw" name="upw" class="form-control" value="${principal.upw }" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <label class="col-sm-12 control-label" >이메일</label>
        <input type="text" id="email" name="email" class="form-control" value="${principal.email }" placeholder="Password">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <label class="col-sm-12 control-label" >프로필에 표시될 내용을 입력하세요.</label>
		<textArea class="form-control" rows="5" name="profile_content" id="profile_content" >${principal.profile_content }</textArea>
      </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="row">
        <div class="col-xs-4">
          <button type="submit" id="loginbtn" class="btn btn-primary btn-block btn-flat" style="text-align:center">저장</button>
        </div>
      </div>
    </form>
   </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
</body>
</html>