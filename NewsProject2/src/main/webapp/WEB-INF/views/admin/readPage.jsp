<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.popup{position:absolute;}
	.back{background-color: gray; opacity: 0.5;overflow: hidden; z-index:1101;}
	.front{z-index: 1110; opacity: 1; border:1px; margin:auto;}
	.show{position:relative; max-width:1200px; max-height: 800px; overflow: auto;}
</style>
<%@ include file="../include/header.jsp" %>
<form role="form" method="post" action="modifyPage" class="frm1">
	<input type="hidden" name="bname" value="${cri.bname }">
	<input type="hidden" name="bnum" value="${boardVO.bnum }">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
	<input type="hidden" name="searchTarget" value="${cri.searchTarget}">
	<input type="hidden" name="searchKeyword" value="${cri.searchKeyword}">
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



<script id="template" type="text/x-handlebars-template">
<li class="replyLi">
	<span class="bg-green"> 댓글 수
	 <small id="replycntSmall">[${boardVO.replycnt}] </small>
	</span>
</li>
	{{#each .}}

<li class="replyLi" data-rnum={{rnum}}>
	<div class="clearfix">
		<h3><댓글번호 : {{grp}}></h3><h4 class="pull-left">{{replyer}}</h4>
		<p class="pull-right">{{prettifyDate regdate}}</p>
     	<input type="hidden" id="replyGrp" value="{{grp}}">
      	<input type="hidden" class="replyLvl" id="replyLvl" value="{{lvl}}">
	</div>
	<p>
		<em>{{replytext}}</em>
	</p>
	<button class="btn btn-primary btn-xs replyModBtn">수정</button>
	<a class="btn btn-danger btn-xs replyDelBtn">삭제</a>	
	<a class="btn bg-green btn-xs rereplies">댓글</a>
	<hr/>
	<div class="col-md-12 form-group replyModDiv">
		<input type="hidden" value="" id="replyModRnum">
		<label class="sr-only">댓글내용</label>
		<textarea class="form-control" id="replyModText" placeholder="Comment"></textarea>
		<button class="btn btn-primary btn-xs" id="replyModBtnSubmit">수정</button>
	</div>
	<div class="col-md-12 form-group replyInsDiv">
     	<input type="hidden" id="replyInsGrp">
      	<input type="hidden" id="replyInsLvl">
		<input type="text" class="form-control" id="replyInsReplyer" placeholder="Name">
		<label class="sr-only">댓글내용</label>
		<textarea class="form-control" id="replyInsText" placeholder="Comment"></textarea>
		<button class="btn btn-primary btn-xs" id="replyInsBtnSubmit">확인</button>
	</div>
</li>
	{{/each}}
</script>
<script>
	$(document).ready(function(){
		Handlebars.registerHelper("prettifyDate", function(timeValue){
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			var hour = dateObj.getHours();
			var min = dateObj.getMinutes();
			
			if(month < 10){
				month = "0" + month;
			}
			
			if(date < 10){
				date = "0" + date;
			}
			if(hour < 10){
				hour = "0" + hour;
			}
			
			var sec = dateObj.getSeconds();
			return year + "/" + month + "/" + date + "\t"+ hour + ":"+ min +":"+sec;
		});
		
		var printData = function(replyArr, target, templateObject){
			var template = Handlebars.compile(templateObject.html());
			var html = template(replyArr);
			$(".replyLi").remove();//like a flicker
			target.append(html);
		};
		
		var bnum = ${boardVO.bnum}; //게시물의 번호
		var replyPage = 1; //수정이나 삭제 작업 이후 사용자가 보던 댓글의 페이지번호를 담아둔다.
		
		//페이징 처리를 위한 함수 parameter : 페이지 번호
		function getPage(pageInfo){
			$.getJSON(pageInfo, function(data){//목록데이터 처리
				printData(data.list, $("#comments"), $("#template"));
				  var indent = $(".replyLvl");
				  $.each(indent, function(index, item) {
				   var num = $(this).val();
				    $(this).parent().parent().css({
				     "margin-left" : num*20+"px"
				    });
				  });
				//수정
				$(".replyModBtn").on("click", function(){
					var reply = $(this).parent();//li
					
					$(".replyModDiv").hide(); //다른쪽에 열린 수정 삭제 div 숨기기
					$(".replyInsDiv").hide();
					reply.find(".replyModDiv").show();			 
					reply.find('#replyModText').val(reply.find("p em").text());
					reply.find("#replyModRnum").val(reply.attr("data-rnum"));
				
					//후처리
					reply.find("#replyModBtnSubmit").on("click", function(){
						var rnum = reply.find("#replyModRnum").val();
						var replytext = reply.find('#replyModText').val();
						$.ajax({
							type : "put",
							url : './replies/' + rnum,
							headers : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "PUT"
							},
							data : JSON.stringify({replytext : replytext}),
							dataType : "text",
							success : function(result){
								//console.log("수정" + result);
								if(result == "success"){
									//alert("수정을 성공했음");
									getPage("./replies/"+bnum+"/"+replyPage);
								}
							},
							error : function(request, status, error){
								console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
							}
						});
					});				
				});
				
				//삭제
				$(".replyDelBtn").on("click", function(){
					var reply = $(this).parent();//li
					console.log(event.target.tagName+"테스트"+ event.eventPhase);
					$(".replyModDiv").hide(); //다른쪽에 열린 수정 삭제 div 숨기기
					var rnum = reply.attr("data-rnum");
					$.ajax({
						type:"delete",
						url:"./replies/" + rnum,
						headers:{
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "DELETE"
						},
						dataType : "text",
						success:function(result){
							console.log("삭제결과 : " + result);

							if(result == "success"){
								//alert("삭제됨");
								getPage("./replies/"+bnum+"/"+replyPage);
							}
						},
						error : function(request, status, error){
							console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
						}
					});
				});
				
				//대댓글
				$(".rereplies").on("click", function(){
					var reply = $(this).parent();
					$(".replyInsDiv").hide();
					$(".replyModDiv").hide(); //다른쪽에 열린  div 숨기기
					reply.find(".replyInsDiv").show();
					reply.find("#replyInsBtnSubmit").on("click", function(){
						var replyInsReplyer = reply.find('#replyInsReplyer').val();
						var replyInsText = reply.find('#replyInsText').val();
						var replyInsGrp = reply.find("#replyGrp").val();
						var replyInsLvl = reply.find("#replyLvl").val();
						console.log("text : "+ replyInsText + "\n" + "grp : " + replyInsGrp + "\n" +  "lvl : " + replyInsLvl);
						$.ajax({
							type : "post",
							url : './replies/re',
							headers : {
								"Content-Type": "application/json",
								"X-HTTP-Method-Override" : "POST"
							},
							data : JSON.stringify({
								bnum : bnum,
								grp : replyInsGrp,
								lvl : replyInsLvl,
								replytext : replyInsText,
								replyer : replyInsReplyer
							}),
							dataType : "text",
							success : function(result){
								if(result == "success"){
									replyPage = 1;
									getPage("./replies/"+bnum+"/"+replyPage);
								}
							},
							error : function(request, status, error){
								console.log("bnum : "+bnum + "\n"+"text : "+ replyInsText + "\n" + "grp : " + replyInsGrp + "\n" +  "lvl : " + replyInsLvl);
								console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
							}
						});
					});	
				});
				printPaging(data.pageMaker,$(".pagination"));
				$(".replyModDiv").hide(); //수정 삭제 숨기기
				$(".replyInsDiv").hide();
				$("#replycntSmall").html("[" + data.pageMaker.totalDataCount + "]");
			});//getPage함수 종료
		}
		
		var printPaging = function(pageMaker, target){//페이징 버튼 처리
			var str = "";
			if(pageMaker.prev){
				str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";			
			}
			for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
				var strClass= pageMaker.cri.page == i ? 'class=active' : '';
				str += "<li " + strClass + "><a href='"+ i + "'>"+ i +"</a></li>";			
			}		
			if(pageMaker.next){
				str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
			}
			target.html(str);
		};
	
		//목록		
		if($("#comments li").size()>1){
			return;
		}
		getPage("./replies/"+ bnum + "/1");

		//페이징 버튼 이벤트 처리
		$(".pagination").on("click", "li a", function(evnet){
			event.preventDefault();
			replyPage = $(this).attr("href");
			getPage("./replies/"+bnum+"/"+replyPage);
		});
		
		//삽입
		$("#replyAddBtn").on("click", function(){
			replyerObj = $("#newReplyUsernick");
			replytextObj = $("#newReplyText");
			var replyer =  replyerObj.val();
			var replytext = replytextObj.val();
			//alert("bnum : "+ bnum+ ", replyPage : "+ replyPage);
			$.ajax({
				type : "post",
				url : "./replies/add",
				headers : {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				data : JSON.stringify({bnum:bnum, replyer:replyer, replytext:replytext}),
				success : function(result){
					console.log("결과 : " + result);
					if(result == "success"){
						//alert("댓글이 등록 되었습니다.");
						replyPage = 1;
						getPage("./replies/"+bnum+"/"+replyPage);
						replyerObj.val("");
						replytextObj.val("");
					}
				},
				error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		});
		
		//첨부파일 처리
		var template = Handlebars.compile($("#templateAttach").html());
		
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
		        <hr/>
		        <ul class="mailbox-attachments clearfix uploadedList"></ul>
		        <div class="popup back" style="display:none;"></div>
		        <div id="popup_front" class="popup front" style="display:none;"><img src="" alt="" id="popup_img" /></div>
		        
			<ul class="pager">
				<li><button type="submit" class="btn btn-primary goListBtn">&larr;목록</button></li>
				<li><button type="submit" class="btn btn-warning modifyBtn">수정</button></li>	
				<li><button type="submit" class="btn btn-danger removeBtn">삭제</button></li>
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
				  <div class="col-md-6 form-group">
				    <label class="sr-only" for="newReplyUsernick">닉네임</label>
				    <input type="text" class="form-control" id="newReplyUsernick" placeholder="Name">
				  </div>
				  <div class="col-md-12 form-group">
				    <label class="sr-only" for="newReplyText">댓글내용</label>
				    <textarea class="form-control" id="newReplyText" placeholder="Comment"></textarea>
				  </div>
				  <div class="col-md-12 form-group text-right">
				  	<button type="submit" class="btn btn-primary" id="replyAddBtn">등록</button>
				  </div>								
			</div>
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