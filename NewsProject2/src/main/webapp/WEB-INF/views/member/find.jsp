<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
body{background-image: url("/resources/img/world-map-png-35423.png");background-size: cover;background-position: right;}
button{float:right;}
.content{height:700px;margin:0px auto;}
.nav-tabs{ background-color:#161616;}
.tab-content{
    background-color:#303136;
    color:#fff;
    padding:5px
}

</style>
${code }
${message }
<script>
	$(function(){
	   var code = "${result.code}";
	   var message = "${result.message}";
	   var alert = "";
	 	  	alert += '<div class="alert alert-'+code+'">'
	  		alert += '<strong>'+code+'!</strong> '+message
			alert += '</div>'
		if(code != ""){
		    $(".nav.nav-tabs").before(alert);
		}
	   
	});
</script>
<div class="content" >
<div class="col-sm-4 col-sm-offset-4">
	 <div class="login-logo">
	    <a href="/"><b>UTBC</b></a>
	 </div> 
	<!-- Custom Tabs -->
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#tab_1" data-toggle="tab"
			aria-expanded="true">아이디 찾기
			</a>
		</li>
		<li class="">
			<a href="#tab_2" data-toggle="tab"
			aria-expanded="false">비밀번호 찾기
			</a>
		</li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_1">
			<form role="form" method="post" action="./find/id">
				<div class="box-body">
					<input class="form-control" name="email" type="text" placeholder="이메일을 입력하세요">
				</div>
				<div class="box-body">
					<button type="submit" class="btn btn-primary">전송</button>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<!-- /.tab-pane -->
		<div class="tab-pane" id="tab_2">
			<form role="form" method="post" action="./find/password">
				<div class="box-body">
					<input class="form-control" type="text" name="uid" placeholder="아이디를 입력하세요">
				</div>
				<div class="box-body">
					<input class="form-control" type="text" name="email" placeholder="이메일을 입력하세요">
				</div>
				<div class="box-body">
					<button type="submit" class="btn btn-primary">전송</button>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		<!-- /.tab-pane -->
	</div>
	<!-- /.tab-content -->
</div>
</div>
<%@ include file="../include/footer.jsp"%>

