<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>UTBC | 회원정보입력</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="/resources/font-awesome/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="/resources/plugins/jQueryUI/jquery-ui-1.10.3.min.js"></script>
    <script src="/resources/js/member.js"></script>
<style type="text/css">
.topbar{text-align:center;list-style-type:none;}
.bottomBtn{border:0px #fff none; text-align:center}
li>a>i{font-size:30px}
li>a{border:1px solid}
.imp1{color:#f00}

@media (min-width: 979px) {
	body {
		margin-top:100px;
	}
}

@media (max-width: 978px) {
	.container {
	 padding-top:100px;
	 padding:0;
	 margin:0;
	}
      
    body {
     padding-top:300px;
    }  
  	.navbar-fixed-top, .navbar-fixed-bottom, .navbar-static-top {
      margin-left: 0;
      margin-right: 0;
  	  margin-bottom:0;
  	}
  	
}
    
</style>
</head>
<body>
	<div class="container">
		<ul class="topbar nav navbar-fixed-top row">
			<li class="active" id="1"><a class="col-sm-4"> <i class="fa fa-gavel" aria-hidden="true"></i>
					<h4>이용약관 동의</h4>
			</a></li>
			<li class="deactive" id="2"><a class="col-sm-4"> <i class="fa fa-id-card-o" aria-hidden="true"></i>
					<h4>회원정보입력</h4>
			</a></li>
			<li class="deactive" id="3"><a class="col-sm-4"> <i class="fa fa-handshake-o" aria-hidden="true"></i>
					<h4>회원가입완료</h4>
			</a></li>
		</ul>
		<hr/>
		<div class="jumbotron">
			<h2>회원가입</h2>
			<hr/>
			<form name="memFrm" id="memFrm" method="POST" class="form-horizontal">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><span class="imp1">*</span>아이디</label>
					<div class="col-sm-10">
						<input type="text" name="uid" id="uid" placeholder="사용할 아이디를 입력하세요" class="form-control" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"><span class="imp1">*</span>비밀번호</label>
					<div class="col-sm-10">
						<input type="password" name="upw" id="upw" placeholder="비밀번호를 입력하세요" class="form-control" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><span class="imp1">*</span>재확인</label>
					<div class="col-sm-10">
						<input type="password" name="pwdChk" id="pwdChk" placeholder="비밀번호를 입력하세요" class="form-control" >
					</div>
				</div>
				<hr/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><span class="imp1">*</span>닉네임</label>
					<div class="col-sm-10">
						<input type="text" name="uname" id="uname" placeholder="닉네임을 입력하세요" class="form-control" >
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"><span class="imp1">*</span>이메일</label>
					<div class="col-sm-3">
						<input type="text" name="emailid" id="emailid" required placeholder="이메일아이디" class="form-control" />								
					</div>
					<div class="col-sm-4">
						<input type="text" name="domain2" id="domain2" value="" placeholder="이메일도메인"  />				
					</div>
					<div class="col-sm-3">
					<select name="domain" id="domain" class="form-control" >
						<option value="">선택하세요</option>
						<option value="nate.com">nate.com</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option value="not">직접입력</option>
					</select>										
					</div>
					<div class="col-sm-12">
						<input type="text" style="text-align:center" name="email" id="email" readonly class="form-control"/>
					</div>
				</div>				
				<br/>
				<div class="form-group">
					<label class="col-sm-2 control-label">생일</label>
					<div class="col-sm-10">
						<input type="date" name="birthday" id="birthday" min="1900-01-02" class="form-control" ><br>
						<br>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">성별</label>
					<div class="col-sm-10">
						<input type="radio" name="gender" id="gender1" value="M" checked />남자 <input type="radio" name="gender" id="gender2" value="G" />여자
					</div>
				</div>
				
				<hr/>
				<div class="form-group">
					<label class="col-sm-3 control-label">프로필에 표시될 사진을 업로드 해주세요.</label>
					<div class="col-sm-5" style="border:1px solid #000">
						<input type="file" name="profile_picture" id="profile_picture" style="width:100%">				
					</div>
					<div class="col-sm-3">
						<img class="img-rounded" style="float:left" width="150" height="150" src="/resources/dist/img/propic.jpg">
						<input type="hidden" name="filesrc" id="filesrc">
					</div>
									
				</div>
		
				<br/>
				<div class="form-group">
					<label class="col-sm-3 control-label" >프로필에 표시될 내용을 입력하세요.</label>
					<div class="col-sm-12">
						<textArea class="form-control" rows="5" name="profile_content" id="profile_content" ></textArea>
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-2 control-label">원하는 권한</label>
					<div class="col-sm-12">
						<input type="radio" name="auth" id="auth1" value="normal" checked />일반 <input type="radio" name="auth" id="auth2" value="report" />기자 <input type="radio" name="auth" id="auth3" value="admin" />관리자
					</div>
				</div>
				
				<div class="bottomBtn">
					<input type="submit" class="btn btn-info btn-lg btnSubmit" value="전송"></input>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-info btn-lg" value="다시쓰기"></input>
				</div>
		
		</form>
		
	</div>
</div>
</body>
</body>
</html>