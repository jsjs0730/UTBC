<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
    <title>:: Unreal Training Broad Casting :: </title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Weather Icons -->
    <link href="/resources/dist/css/weather-icons.min.css" rel="stylesheet" type="text/css" />
  
    <!-- Font Awesome Icons -->
    <link href="/resources/font-awesome/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
      <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
     <!-- Handlebars 4.0.11 -->
    <script src="/resources/plugins/handlebars/handlebars-v4.0.11.js"></script>
<script>
$(document).ready(
	function() {
		$('.section').on("click",
			function() {
				var location = $(this).attr("data");
				$(this).attr("href", "/board/listPage?bname="+location);						
			});
	});
</script>
 <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">UTBC</a>
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
      <li><a href="/">Home</a></li>
      <li><a href="" data="newsflash" class="section">속보</a>
      <li><a href="" data="politics" class="section">정치</a>
      <li><a href="" data="economy" class="section">경제</a>
      <li><a href="" data="society" class="section">사회</a>     
      <li><a href="" data="community" class="section">커뮤니티</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="isAnonymous()">
      	<li><a href="/member/terms"><span class="glyphicon glyphicon-user"></span>회원가입</a></li>
      	<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
      	<li>
      	<form id="logout" action="/logout" method="post" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="javascript:document.getElementById('logout').submit()"><span class="glyphicon glyphicon-log-in"></span>로그아웃</a>
		</c:if>
      	</li>
      </sec:authorize>
    </ul>
    <!-- <form class="navbar-form navbar-right">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form> -->
    </div>
  </div>
</nav>
	<c:if test="${!empty cri.bname}">
	   <section class="content-header">
	     <ol class="breadcrumb">
	       <li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
	       <li><a href="/board/listPage?bname=${cri.bname}">${cri.bname }</a></li>
	       <li class="active">${boardVO.title }</li>
	     </ol>
	   </section>
	</c:if>
	<c:if test="${empty cri.bname}">
		<section class="content-header" style="margin-top:30px">
		
		</section>
	</c:if>
