<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UTBC | 로그인</title>
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
	<script>
	  $(function () {
	    $('input').iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass: 'iradio_square-blue',
	      increaseArea: '20%' // optional
	    });
	    $('#loginbtn').on("click", function(event){
	        if($("#uid").val() == ""){
	            event.preventDefault();
	            alert("아이디를 입력해라");
	            $("#uid").focus();
	        }else if($("#upw").val() == ""){
	            event.preventDefault();
	            alert("비밀번호를 입력해라");
	            $("#upw").focus();
	        }else{
	            $("#loginfrm").attr("action" , "<c:url value='/login'/>");
	            $("#loginfrm").submit();
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
  <sec:authentication property="principal"/>
    <form action="<c:url value='/member/editProfile'/>" method="post" id="loginfrm" name="loginfrm">
      <div class="form-group has-feedback">
        <input type="text" id="uid" name="uid" class="form-control" value="${principal.uid }" placeholder="id">
        <span class="glyphicons glyphicons-keys form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" id="upw" name="upw" class="form-control" value="${principal.upw }" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" id="email" name="email" class="form-control" value="${principal.email }" placeholder="Password">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" id="email" name="email" class="form-control" value="${principal.profile_content }" placeholder="Password">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="row">
        <div class="col-xs-4">
          <button type="submit" id="loginbtn" class="btn btn-primary btn-block btn-flat">로그인</button>
        </div>
      </div>
    </form>
    <a href="#">비밀번호찾기</a><br>
    <a href="#" class="text-center">회원가입</a>
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
</body>
</html>