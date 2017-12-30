<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	.popup{position:absolute;}
	.back{background-color: gray; opacity: 0.5;overflow: hidden; z-index:1101;}
	.front{z-index: 1110; opacity: 1; border:1px; margin:auto;}
	.show{position:relative; max-width:1200px; max-height: 800px; overflow: auto;}
</style>
<form role="form" method="post" action="modifyPage" class="frm1">
	<input type="hidden" name="bname" value="${cri.bname }">
	<input type="hidden" name="bnum" value="${boardVO.bnum }">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
	<input type="hidden" name="searchTarget" value="${cri.searchTarget}">
	<input type="hidden" name="searchKeyword" value="${cri.searchKeyword}">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<script>
	$(document).ready(function(){
		var formObj = $("form[class='frm1']");
		console.log(formObj);
		
		$(".goListBtn").on("click", function(){
			formObj.attr("method", "get");
			formObj.attr("action", "/board/listPage");
			formObj.submit();
		});
		$(".removeBtn").on("click", function(){
		   var replyCnt = $("#replycntSmall").html().replace(/[^0-9]/g, "");
		   /*  if(replyCnt>0){
		        alert("댓글이 달린 게시물을 삭제할 수 없다");
		        return;		        
		    } */
		    var arr = [];
		    $(".uploadedList li").each(function(index){
		        arr.push($(this).attr("data-src"));
		    });
		    if(arr.length > 0){
		        $.post("/deleteAllFiles", {files:arr}, function(){
		            
		        });
		    }
			formObj.attr("action", "/board/removePage");
			formObj.submit();			
		});
		$(".modifyBtn").on("click", function(){
			formObj.attr("action", "/board/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();			
		});
	});
</script>

<script>
	$(document).ready(function(){
		
		//첨부파일 처리
		var template = Handlebars.compile($("#templateAttach").html());
		var bnum = ${boardVO.bnum}; //게시물의 번호
		$.getJSON("/board/getAttach/" + bnum, function(list){
		    $(list).each(function(){
		        var fileInfo = getFileInfo(this);
		        var html = template(fileInfo);
		        $(".uploadedList").append(html);
		    });
		});
		//이미지 파일 이벤트 처리-확대처리
		$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		    var fileLink = $(this).attr('href');
		    if(checkImageType(fileLink)){
		        event.preventDefault();
		        var imgTag = $("#popup_img");
		        imgTag.attr("src", fileLink);
		        console.log(imgTag.attr("src"));
		        
		        $(".popup").show("slow");
		        imgTag.addClass("show");
		    }
		});
		//이미지 파일 이벤트 처리-축소처리
		$("#popup_img").on("click", function(){
		    $(".popup").hide("slow");
		});
	});
</script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src="{{fullName}}">
	<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachement" /></span>
	<div class="mailbox-attachment-info">
		<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</div>
</li>
</script>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<h1><a href="/board/readPage?bnum=${boardVO.bnum}">${boardVO.title }</a></h1>
		        <div class="row">
		          	<div class="col-sm-4 col-md-4">
		          		<i class="fa fa-barcode" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.bnum }</span>
			        </div>				          	
		          	<div class="col-sm-8 col-md-8">
		          		<i class="fa fa-user" aria-hidden="true"></i>&nbsp; <a style="color:#888">${boardVO.usernick }</a> <span style="color:#bbb">&nbsp; |  &nbsp; </span>
		          		<i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.like }</span><span style="color:#bbb">&nbsp; |  &nbsp; </span>
						<i class="fa fa-eye" aria-hidden="true"></i>&nbsp; <span class="itm">${boardVO.viewcnt }</span><span style="color:#bbb">&nbsp; |  &nbsp; </span>
		          		<span class="glyphicon glyphicon-pencil"></span> <a href="singlepost.html#comments">${boardVO.replycnt }</a>			          		
		          		&nbsp;&nbsp;<span class="glyphicon glyphicon-time"></span> <fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
		        	</div>
				</div>
		        <hr/>
		       
		        <p class="lead">
		          ${boardVO.content }
				</p>
				
		        <div class="row">
		        <%@ include file="../include/vote.jsp" %>
		        </div>
		        <hr/>
		        <ul class="mailbox-attachments clearfix uploadedList"></ul>
		        <div class="popup back" style="display:none;"></div>
		        <div id="popup_front" class="popup front" style="display:none;"><img src="" alt="" id="popup_img" /></div>
		        
			<ul class="pager">
				<li><button type="submit" class="btn btn-primary goListBtn">&larr;목록</button></li>
				<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.uname" var="uname"/>
				<sec:authentication property="principal.authorities" var="authorities"/>
					<c:if test="${boardVO.usernick == uname or authorities == '[admin]'}">
						<li><button type="submit" class="btn btn-warning modifyBtn">수정</button></li>	
						<li><button type="submit" class="btn btn-danger removeBtn">삭제</button></li>
					</c:if>	
				</sec:authorize>
			</ul>
			
			<hr />
			<ul id="comments" class="comments" style="list-style-type:none;maring:0;padding:0">
				
			</ul>	
			<!--  Paging -->
			<div class="text-center">
				<ul id="pagination" class="pagination pagination-sm no-margin">
				</ul>
			</div>
			<!-- Comment form -->
				<div class="well" style="height:220px">
					<h4>너도 한 마디</h4>				
					<sec:authorize access="isAuthenticated()">
					  <div class="col-md-6 form-group">
					    <label class="sr-only" for="newReplyUsernick">닉네임</label>
					    <input type="text" class="form-control" id="newReplyUsernick" value="${uname }" placeholder="Name" readOnly>
					  </div>
					  <div class="col-md-12 form-group">
					    <label class="sr-only" for="newReplyText">댓글내용</label>
					    <textarea class="form-control" id="newReplyText" placeholder="Comment"></textarea>
					  </div>
					  <div class="col-md-12 form-group text-right">
					  	<button type="submit" class="btn btn-primary" id="replyAddBtn">등록</button>
					  </div>								
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<p>댓글작성은 <a href="/login">로그인</a>을 해야 합니다.</p>
					</sec:authorize>
				</div>
			<%@ include file="../include/reply.jsp" %>
		</div>
		<div class="col-md-4">
			<div class="well text-center">
				<p class="lead">
					Don't want to miss updates? Please click the below button!
				</p>
				<button class="btn btn-primary btn-lg">Subscribe to my feed</button>
			</div>
			<!-- Latest Posts -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Latest Posts</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="singlepost.html">1. Aries Sun Sign March 21 - April 19</a></li>
					<li class="list-group-item"><a href="singlepost.html">2. Taurus Sun Sign April 20 - May 20</a></li>
					<li class="list-group-item"><a href="singlepost.html">3. Gemini Sun Sign May 21 - June 21</a></li>
					<li class="list-group-item"><a href="singlepost.html">4. Cancer Sun Sign June 22 - July 22</a></li>
					<li class="list-group-item"><a href="singlepost.html">5. Leo Sun Sign July 23 - August 22 </a></li>
				</ul>
			</div>
			<!-- Categories -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Categories</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">Signs</a></li>
					<li class="list-group-item"><a href="#">Elements</a></li>
					<li class="list-group-item"><a href="#">Planets</a></li>
					<li class="list-group-item"><a href="#">Cusps</a></li>
					<li class="list-group-item"><a href="#">Compatibility</a></li>
				</ul>
			</div>
			<!-- Tags -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Tags</h4>
				</div>
				<div class="panel-body">
					<ul class="list-inline">
						<li><a href="#">Aries</a></li>
						<li><a href="#">Fire</a></li>
						<li><a href="#">Mars</a></li>
						<li><a href="#">Taurus</a></li>
						<li><a href="#">Earth</a></li>
						<li><a href="#">Moon</a></li>
						<li><a href="#">Gemini</a></li>
						<li><a href="#">Air</a></li>
						<li><a href="#">Mercury</a></li>
						<li><a href="#">Cancer</a></li>
					</ul>
				</div>
			</div>
			<!-- Recent Comments -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Recent Comments</h4>
				</div>
				<ul class="list-group">
					<li class="list-group-item"><a href="#">I don't believe in astrology but still your writing style is really great! - <em>john</em></a></li>
					<li class="list-group-item"><a href="#">Wow.. what you said is really true! I'm an aries though - <em>Anto</em></a></li>
					<li class="list-group-item"><a href="#">Does capricorn and aries is compatibile? - <em>Sarah</em></a></li>
					<li class="list-group-item"><a href="#">I'm a cancer woman been in love with Leo. Will this work? - <em>Mary</em></a></li>
				</ul>
			</div>

		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp" %>