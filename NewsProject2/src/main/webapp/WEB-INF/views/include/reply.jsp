<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

var data = '<li class="replyLi">'
			data+='<span class="bg-green"> 댓글 수'
				data+='<small id="replycntSmall">[${boardVO.replycnt}] </small>'
			data+='</span>'
	data+='</li>'
data+='<li class="replyLi" data-rnum={{rnum}}>'
	data+='<div class="clearfix">'
		data+='<h3><댓글번호 : {{grp}}></h3><h4 class="pull-left">{{replyer}}</h4>'
			data+='<p class="pull-right">{{prettifyDate regdate}}</p>'
			data+='<input type="hidden" id="replyGrp" value="{{grp}}">'
			data+='<input type="hidden" class="replyLvl" id="replyLvl" value="{{lvl}}">'
	data+='</div>'
	data+='<p>'
		data+='<em>{{replytext}}</em>'
	data+='</p>'
	data+='<button class="btn btn-primary btn-xs replyModBtn">수정</button>'
	data+='<a class="btn btn-danger btn-xs replyDelBtn">삭제</a>'
	data+='<a class="btn bg-green btn-xs rereplies">댓글</a>'
	data+='<hr/>'
	data+='<div class="col-md-12 form-group replyModDiv">'
		data+='<input type="hidden" value="" id="replyModRnum">'
		data+='<label class="sr-only">댓글내용</label>'
		data+='<textarea class="form-control" id="replyModText" placeholder="Comment"></textarea>'
		data+='<button class="btn btn-primary btn-xs" id="replyModBtnSubmit">수정</button>'
	data+='</div>'
	data+='<div class="col-md-12 form-group replyInsDiv">'
		data+='<input type="hidden" id="replyInsGrp">'
		data+='<input type="hidden" id="replyInsLvl">'
		data+='<input type="text" class="form-control" id="replyInsReplyer" placeholder="Name">'
		data+='<label class="sr-only">댓글내용</label>'
		data+='<textarea class="form-control" id="replyInsText" placeholder="Comment"></textarea>'
		data+='<button class="btn btn-primary btn-xs" id="replyInsBtnSubmit">확인</button>'
	data+='</div>'
data+='</li>'

	$(document).ready(function(){
		function timeClock(){
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
		};
		
		
		var bnum = ${boardVO.bnum}; //게시물의 번호
		var replyPage = 1; //수정이나 삭제 작업 이후 사용자가 보던 댓글의 페이지번호를 담아둔다.
		
		//페이징 처리를 위한 함수 parameter : 페이지 번호
		function getPage(pageInfo){
			$.getJSON(pageInfo, function(data){//목록데이터 처리
				  var indent = $(".replyLvl");
				  $.each(indent, function(index, item) {
				   var num = $(this).val();
				    $(this).parent().parent().css({
				     "margin-left" : num*20+"px"
				    });
				  });
				
				//csrf 설정
				var csrfToken = $("meta[name='_csrf']").attr("content"); 
				var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
				var csrfHeader = $("meta[name='_csrf_header']").attr("content"); 
					
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
						beforeSend : function(xhr){
						  xhr.setRequestHeader(csrfHeader, csrfToken);  
						},
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
								replytextObj.val("");
							}
						},
						error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
							console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
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
							beforeSend : function(xhr){
							  xhr.setRequestHeader(csrfHeader, csrfToken);  
							},
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
						beforeSend : function(xhr){
						  xhr.setRequestHeader(csrfHeader, csrfToken);  
						},
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
							beforeSend : function(xhr){
							  xhr.setRequestHeader(csrfHeader, csrfToken);  
							},
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
	});
		
</script>