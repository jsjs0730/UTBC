<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>


<style>
.user-block>img {
    width: 40px;
    height: 40px;
    float: left;
}
.profile-user-img {
    margin: 0 auto;
    width: 100px;
    padding: 3px;
    border: 3px solid #dcdcdc;
}
.widget-user .widget-user-image {
    position: absolute;
    top: 65px;
    left: 50%;
    margin-left: -45px;
}
.widget-user .widget-user-header {
    padding: 20px;
    height: 120px;
    border-top-right-radius: 3px;
    border-top-left-radius: 3px;
}
.widget-user .widget-user-image>img {
    width: 90px;
    height: auto;
    border: 3px solid #fff;
}
.widget-user .box-footer {
    padding-top: 30px;
}
.bg-black {
    background-color: #111 !important;
}
@media (min-width: 979px) {
	
	.carousel-inner > .item > img {height:500px ;width: auto;margin: auto;}
	.carousel-inner > .item > a > img {width: 70%;margin: auto;}
	.container-fluid{
		margin-left:50px;
	}
}

@media (max-width: 978px) {
	
  	.carousel-inner > .item > img {height:300px;width:auto;margin:auto;}
  	.row.content {height: auto;} 
  	.container-fluid{
		margin-left:15px;
		margin-right:15px
	}
}
</style>
<!-- Main Content -->
<section class="content">
	<div class="row">
		<div class="col-md-9"><!-- left-->
			<div class="row" style="margin-bottom:30px"><!-- slider -->
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
			      <li data-target="#myCarousel" data-slide-to="3"></li>
			      <li data-target="#myCarousel" data-slide-to="4"></li>
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner" role="listbox">
			      	<c:forEach var="sbox" items="${slide }" >
				     	 <div class="item" data="${sbox.bnum }">
					      <img src="/resources/img/0d07da65.gif" alt="${sbox.bnum }" width="460" height="345">
					       <div class="carousel-caption">
					            <a href="/board/readPage?bnum=${sbox.bnum }"><h2 style="color:#2ce9ef">${sbox.title }</h2></a>
					        </div>
				      	</div>
			         </c:forEach>
			    </div>
			    <script>
					$(function(){
					    var imgbnum = new Array();
					    $(".carousel-inner>.item").each(function(){
					        imgbnum.push($(this).attr("data"));
					    	var bnum = imgbnum.pop();
					        //홈게시글 첨부파일 불러오기
					  	  	$.getJSON("/board/getAttach/"+bnum  , function(list){
					  	        //console.log("list : " + list);
					  	        //console.log("imgbnum.pop : " + bnum);
							    $(list).each(function(i, e){
							        if(i==0){//마지막 파일만(pop은 마지막부터 가져온다.)
									    var fileInfo = getFileInfo(e);
									    console.log(fileInfo);
							        	$("img[alt="+bnum+"]").attr("src", fileInfo.getLink);	 
							        }
							    });
							});
					    });
					    $(".carousel-inner>.item:eq(0)").addClass("active");
					    
					});
				</script>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
			</div><!-- slider end -->
			
			<div class="row">
			<c:forEach items="${list }" var="boardVO">
				<div class="col-md-4">
					<div class="box box-widget box-solid box-default" data="${boardVO.bnum }">
			            <div class="box-header with-border">
			              <div class="user-block">
			                <img class="img-circle boardWriter" src="${boardVO.filesrc }" alt="User Image">
			                <span class="username"><a href="#">${boardVO.usernick }</a></span>
			                <span class="description"><a href="/board/readPage?bnum=${boardVO.bnum}" style="color:#000">${boardVO.title }</a></span>
			              </div>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body">
			              <img class="img-responsive pad" id="phoo" data="${boardVO.bnum }" src="/resources/dist/img/photo2.png" alt="Photo">
			              <p>
							<c:choose>
								<c:when test="${fn:length(boardVO.content)>50 }">
									<p>${fn:escapeXml(fn:substring(boardVO.content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "), 0, 30))}...&lt;생략&gt;</p>
								</c:when>
								<c:otherwise>
									<p>${fn:escapeXml(boardVO.content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "))  }</p>
								</c:otherwise>
							</c:choose>   
						</p>
			              <span class="pull-right text-muted">${boardVO.vlike } likes - ${boardVO.dislike } dislikes - ${boardVO.viewcnt } comments</span>
			            </div>
			            <!-- /.box-body -->
					</div>
				</div>
			</c:forEach>
				<script type="text/javascript" src="/resources/js/upload.js"></script>
				<script>
					$(function(){
					    var imgbnum = new Array();
					    $(".col-md-4>.box-widget").each(function(){
					        imgbnum.push($(this).attr("data"));
					    	var bnum = imgbnum.pop();
					        //홈게시글 첨부파일 불러오기
					  	  	$.getJSON("/board/getAttach/"+bnum  , function(list){
					  	        //console.log("list : " + list);
					  	        //console.log("imgbnum.pop : " + bnum);
							    $(list).each(function(i, e){
							        if(i==0){//마지막 파일만(pop은 마지막부터 가져온다.)
									    var fileInfo = getFileInfo(e);
									    console.log(fileInfo);
							        	$("img[data="+bnum+"]").attr("src", fileInfo.getLink);	 
							        }
							    });
							});
					    });
					    var boardWriter =  $(".boardWriter").attr("src"); 
	            	    if(boardWriter == '' || boardWriter == null){
	            	        $(".boardWriter").attr("src", "/resources/dist/img/user1-128x128.jpg");       	   
	            	    }
					});
				</script>
			</div><!-- .row 1 -->
			
		</div><!-- .left -->
		<div class="row"><!-- right -->
			<div class="col-md-2">
				<div class="row">
				<sec:authorize access="isAnonymous()">
					<div class="box box-solid box-info col-md-12">					
			            <div class="box-header with-border">
			              <h3 class="box-title">로그인</h3>
			              <c:if test="${not empty securityexceptionmsg }">
			            	  <h5 class="box-content">${securityexceptionmsg}</h5>
			              </c:if>
						 
			            </div>
	           			 <!-- /.box-header -->
			            <!-- form start -->
				            <form class="form-horizontal" action="<c:url value='/login'/>" method="post" id="loginfrm" name="loginfrm">
				            <input type="hidden" name="loginRedirect" value="${loginRedirect }" />
				              <div class="box-body">
				                <div class="form-group">
				                  <label for="uid" class="col-sm-2 control-label">ID</label>
				                  <div class="col-sm-10">
				                    <input type="text" class="form-control" id="uid" name="uid" placeholder="아이디를 입력">
				                  </div>
				                </div>
				                <div class="form-group">
				                  <label for="upw" class="col-sm-2 control-label">Password</label>
				                  <div class="col-sm-10">
				                    <input type="password" class="form-control" id="upw" name="upw" placeholder="비밀번호">
				                  </div>
				                </div>
				                <div class="form-group">
				                  <div class="col-sm-offset-2 col-sm-10">
				                    <div class="checkbox">
				                      <label>
				                        <input type="checkbox"> Remember me
				                      </label>
				                    </div>
				                  </div>
				                </div>
				              </div>
				              <!-- /.box-body -->
				              <div class="box-footer">
				                <button type="submit" class="btn btn-info pull-right" id="loginbtn">확인</button>
				                <button type="submit" class="btn btn-default">비밀번호찾기</button>
				              </div>
				              <!-- /.box-footer -->
				               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				            </form>
	          		</div>	          		
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<script>
		            	$(function(){
		            	    var login_userimage =  $(".login_userimage").attr("src"); 
		            	    if(login_userimage == '' || login_userimage == null){
		            	        $(".login_userimage").attr("src", "/resources/dist/img/propic.jpg");       	   
		            	    }
		            	});
					</script>
					<div class="box box-solid box-primary">
			            <div class="box-body box-profile">
			              <img class="profile-user-img img-responsive img-circle login_userimage" src='<sec:authentication property="principal.filesrc"/>' alt="User profile picture">
			              <h3 class="profile-username text-center"><sec:authentication property="principal.uname"/>님 환영합니다.</h3>
			              <p class="text-muted text-center">권한 : <sec:authentication property="principal.authorities"/></p>
			
			              <ul class="list-group list-group-unbordered">
			                <li class="list-group-item">
			                  <b>내가 쓴 글</b> <a class="pull-right">1,322</a>
			                </li>
			                <li class="list-group-item">
			                  <b>내가 쓴 댓글</b> <a class="pull-right">543</a>
			                </li>
			                <li class="list-group-item">
			                  <b>포인트</b> <a class="pull-right">13,287</a>
			                </li>
			              </ul>
							<!-- /.col -->
							<form id="logout" action="/logout" method="post" >
								<input type="hidden" name="loginRedirect" value="${loginRedirect }" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
							<c:if test="${pageContext.request.userPrincipal.name != null}">
								<a class="btn btn-default" href="javascript:document.getElementById('logout').submit()">로그아웃</a>
							</c:if>
			            </div>
			            <!-- /.box-body -->
		         	</div>
			    </sec:authorize>
				</div>
				<script>
				  $(function () {
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
				<div class="row" style="margin-top:50px">
				<div class="small-box bg-aqua col-md-12">
		            <div class="inner">
		              <h3>맑음</h3>
		
		              <p>오늘날씨</p>
		            </div>
		            <div class="icon">
		              <i class="fa fa-sun-o"></i>
		            </div>
		            <a href="#" class="small-box-footer">
		              	전국날씨 <i class="fa fa-arrow-circle-right"></i>
		            </a>
		          </div>
				</div>
				<div class="row" style="margin-top:50px">
					<div class="box box-widget box-solid box-default col-md-12">
		               <div class="box-header">
			              <h3 class="box-title">인기 기사</h3>
			            </div>
			            <!-- /.box-header -->
			            <div class="box-body no-padding">
			              <table class="table table-condensed">
			                <tbody>
				                <tr>
				                  <th style="width: 40px">순위</th>
				                  <th style="text-align:center">제목</th>
				                  <th style="width: 40px">공감</th>
				                </tr>
				                <c:forEach var="favorite" items="${favorite }" varStatus="status">
					                <tr class="fav">
					                  <td style="text-align:center"><span class="badge  bg-blue">${status.index +1}</span></td>
					                  <td><a href="/board/readPage?bnum=${favorite.bnum }">${favorite.title }</a></td>
					                  <td>${favorite.vlike }</td>
					                </tr>
				                </c:forEach>
			              	</tbody>
			              </table>
			            </div>
				            <!-- /.box-body -->
						</div>
				</div>
			</div>
		</div><!-- .right -->
	</div>
</section>
<script>
    $(".badge:eq(0)").removeClass("bg-blue").addClass("bg-red");
    $(".badge:eq(1)").removeClass("bg-blue").addClass("bg-red");
    $(document).ready(function(){
	    var ticker = function() {
	        timer = setTimeout(function() {
	          $(".table-condensed>tbody>tr:eq(1)").animate({ marginTop: "-20px" }, 400, function() {
	            $(this).appendTo(".table-condensed>tbody");
	          });
	          ticker();
	        }, 2000);
    	};
    	ticker();
    });
</script>
<%@ include file="include/footer.jsp" %>
