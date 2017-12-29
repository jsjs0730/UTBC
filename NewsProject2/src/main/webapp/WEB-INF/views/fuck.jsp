<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file_name///</title>
</head>
<body>
<div class="container-fluid">
  <div class="row content">
  	<div class="col-sm-12">
      <h3>이슈</h3>
		 <br/>
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1"></li>
		      <li data-target="#myCarousel" data-slide-to="2"></li>
		      <li data-target="#myCarousel" data-slide-to="3"></li>
		    </ol>
		
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner" role="listbox">
		      <div class="item active">
		        <img src="./img/01b4ccb22db966ed51a007a4e8d2011948d2b738.png" alt="Chania" width="460" height="345">
		        <div class="carousel-caption">
		          <h3>Chania</h3>
		          <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
		        </div>
		      </div>
		      <div class="item">
		        <img src="./img/02b2f807c7da391ba950925e382a3af42bd06f85.jpg" alt="Chania" width="460" height="345">
		        <div class="carousel-caption">
		          <h3>Chania</h3>
		          <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
		        </div>
		      </div>    
		      <div class="item">
		        <img src="./img/1d99b2cb26d145f302a70764f729c6cdf9824d63.jpg" alt="Flower" width="460" height="345">
		        <div class="carousel-caption">
		          <h3>Flowers</h3>
		          <p>Beautiful flowers in Kolymbari, Crete.</p>
		        </div>
		      </div>
		      <div class="item">
		        <img src="./img/1e0644a6d16879495700cd4a23f004134e097dfc.jpg" alt="Flower" width="460" height="345">
		        <div class="carousel-caption">
		          <h3>Flowers</h3>
		          <p>Beautiful flowers in Kolymbari, Crete.</p>
		        </div>
		      </div>  
		    </div>
		
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
	</div><!-- 슬라이더 종료 -->

     <div class="row">
		<div class="col-sm-4" >
			<h3>정치</h3>
			 <ul class="list-group">
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			</ul>
		</div>
		<div class="col-sm-4" >
			<h3>경제</h3>
			<ul class="list-group">
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			</ul>
		</div>
	<div class="col-sm-2 sidenav">
    	<h3>실시간 뉴스</h3>
	   	<ul class="list-group">
		    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
		    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
		    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
		    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
		    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
		</ul>
		
	</div>	
     </div>
     
     <div class="row">
       	<div class="col-sm-4">
			<h3>사회</h3>
			  <ul class="list-group">
			   <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			   <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			   <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			   <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			   <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			</ul>
		</div>	
		<div class="col-sm-4">
			<h3>커뮤니티</h3>
			   <ul class="list-group">
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			</ul>
 		</div>
	 	<div class="col-sm-2 sidenav">
	    	<h3>인기글</h3>
		   	<ul class="list-group">
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			    <li class="list-group-item"><a>First item</a> <span class="badge">+12</span></li>
			</ul>
			
		</div>		
     </div>
   	
  </div>
</div>

</body>
</html>