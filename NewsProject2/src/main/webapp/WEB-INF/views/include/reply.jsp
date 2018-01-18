<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	var bnum = ${boardVO.bnum}; //게시물의 번호
	var replyPage = 1; 

	var writer = "${uname}";
	var authority = "${authorities}";
	//csrf 설정
	var csrfToken = $("meta[name='_csrf']").attr("content"); 
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content"); 

	//수정
	function modifyReply(rnum){
	    var reply = $(".replyLi[data-rnum="+rnum+"]");//li
	    $(".replyInsDiv").hide();
	    reply.find(".replyModDiv").toggle(); //다른쪽에 열린 수정 삭제 div 숨기기
	    reply.find('#replyModText').val(reply.find("p em").text());
	    reply.find("#replyModRnum").val(reply.attr("data-rnum"));

	    //후처리
	    reply.find("#replyModBtnSubmit").on("click", function(){
	        var rnum = reply.find("#replyModRnum").val();
	        var replytext = reply.find('#replyModText').val();
	        $.ajax({
	            type : "put",
	            url : './replies/update/' + rnum,
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
	                    getPage("./replies/com/"+bnum+"/"+replyPage);
	                }
	            },
	            error : function(request, status, error){
	                console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
	            }
	        });
	    });             
	}
	//삭제
	function deleteReply(rnum){
	    var reply = $(".replyLi[data-rnum="+rnum+"]");//li
	    //console.log(event.target.tagName+"테스트"+ event.eventPhase);
	    $(".replyModDiv").hide(); //다른쪽에 열린 수정 삭제 div 숨기기
	    $.ajax({
	        type:"delete",
	        url:"./replies/delete/" + rnum,
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
	                getPage("./replies/com/"+bnum+"/"+replyPage);
	            }
	        },
	        error : function(request, status, error){
	            console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
	        }
	    });
	}

	function addReReply(rnum){
	    var reply = $(".replyLi[data-rnum="+rnum+"]");//li
	    $(".replyModDiv").hide();
	    reply.find(".replyInsDiv").toggle();
	    
	    var replyer = $("#newReplyUsernick").val();
	    var depth = reply.find("#replyInsDep").val(); //this.depth 부모꺼
	    var idx = reply.find("#replyInsIdx").val(); //this.idx
	    reply.find("#replyInsBtnSubmit").on("click", function(){
	       $.ajax({
	        type : "post",
	        url : "./replies/addReReply",
	        beforeSend : function(xhr){
	          xhr.setRequestHeader(csrfHeader, csrfToken);  
	        },
	        headers : {
	            "Content-Type": "application/json",
	        },
	        dataType : "text",
	        data : JSON.stringify({bnum:bnum, replyer:replyer, replytext:reply.find("#replyInsText").val(), depth:depth, idx:idx }),
	        success : function(result){
	            console.log("결과 : " + result);
	            if(result == "success"){
	                //alert("댓글이 등록 되었습니다.");
	                replyPage = 1;
	                getPage("./replies/com/"+bnum+"/"+replyPage);
	                replytext="";
	            }
	        },
	        error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
	            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	       });
	    });
	}


	        //parameter : ./replies/com/"+bnum+"/"+replyPage 페이지 번호
	function getPage(pageInfo){
	    $.getJSON(pageInfo, function(data){//목록데이터 처리
	        var html = "";
	        html += '<li class="replyLi">'
	                html+='<span class="bg-green"> 댓글 수'
	                    html+='<small id="replycntSmall">[${boardVO.replycnt}] </small>'
	                html+='</span>'
	        html+='</li>'
	        $.each(data.list, function(key, value){
	           
	            html+='<li class="replyLi" data-rnum="'+this.rnum+'" style="margin-left:'+this.depthCnt*20+'px">'
	                html+='<div class="clearfix">'
	                        html+='<p class="pull-right">'+timeClock(this.regdate)+'</p>'
	                    	html+='<p>'
	                    	if(this.depthCnt > 0){
	          		          html += '<i class="fa fa-reply-all fa-1 fa-rotate-180" aria-hidden="true"></i>'
	          	           	}
	                    	html+='<번호 : '+this.rnum+'></p><h4 class="pull-left">'+this.replyer+'</h4>'
	                        html+='<input type="hidden" id="replyDepth" value="'+this.depth+'">'
	                        html+='<input type="hidden" id="replyIdx" value="'+this.idx+'">'
	                       if(this.replytext != "이미 삭제된 댓글입니다."){
		                        html+='<p class="pull-right">'
		        	                if(writer == this.replyer || authority == "[admin]"){
		        		                html+='<a class="btn btn-primary btn-xs replyModBtn" href="javascript:modifyReply('+this.rnum+');">수정</a>'
		        		                html+='<a class="btn btn-danger btn-xs replyDelBtn" href="javascript:deleteReply('+this.rnum+');">삭제</a>'
		        	                }
		                        	if(writer !=''){
		                        	    html+='<a class="btn bg-green btn-xs rereplies" href="javascript:addReReply('+this.rnum+')">댓글</a>'
		                        	}
		                        html+='</p>'
	                       }
	                html+='</div>'
	                html+='<p>'
	                    html+='<em>'+this.replytext+'</em>'
	                html+='</p>'
	                if(writer != ""){
	                    html+='<div class="col-md-12 form-group replyModDiv">'
	                        html+='<input type="hidden" value="" id="replyModRnum">'
	                        html+='<label class="sr-only">댓글내용</label>'
	                        html+='<textarea class="form-control" id="replyModText" placeholder="Comment"></textarea>'
	                        html+='<button class="btn btn-primary btn-xs pull-right" id="replyModBtnSubmit">수정</button>'
	                    html+='</div>'
	                    html+='<div class="col-md-12 form-group replyInsDiv">'
	                        html+='<input type="hidden" id="replyInsDep" value="'+this.depth+'">'
	                        html+='<input type="hidden" id="replyInsIdx" value="'+this.idx+'">'
	                        html+='<input type="text" class="form-control" id="replyInsReplyer" value="'+writer+'" readOnly>'
	                        html+='<label class="sr-only">댓글내용</label>'
	                        html+='<textarea class="form-control" id="replyInsText" placeholder="Comment"></textarea>'
	                        html+='<button class="btn btn-primary btn-xs pull-right" id="replyInsBtnSubmit">확인</button>'
	                    html+='</div>'
	               }
	            html+='</li>';
	            html+='<hr/>'
	        });
	       $("#comments").html(html);
	       $(".replyModDiv").hide(); //수정 삭제 숨기기
	       $(".replyInsDiv").hide();
	       printPaging(data.pageMaker,$(".pagination"));
	       $("#replycntSmall").html("[" + data.pageMaker.totalDataCount + "]");
	        
	    }); 
	    function timeClock(timeValue){
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
	    }
	    
	}//getPage함수 종료


$(document).ready(function(){
    $("#replyAddBtn").on("click", function(){
        replyerObj = $("#newReplyUsernick");
        replytextObj = $("#newReplyText");
        var replyer =  replyerObj.val();
        var replytext = replytextObj.val();
        
        $.ajax({
            type : "post",
            url : "./replies/add",
            beforeSend : function(xhr){
              xhr.setRequestHeader(csrfHeader, csrfToken);  
            },
            headers : {
                "Content-Type": "application/json",
            },
            dataType : "text",
            data : JSON.stringify({bnum:bnum, replyer:replyer, replytext:replytext}),
            success : function(result){
                console.log("결과 : " + result);
                if(result == "success"){
                    //alert("댓글이 등록 되었습니다.");
                    replyPage = 1;
                    getPage("./replies/com/"+bnum+"/"+replyPage);
                    replytextObj.val("");
                }
            },
            error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }); 
        
    //목록        
    if($("#comments li").size()>1){
        return;
    }
    getPage("./replies/com/"+ bnum + "/1");
    
    //페이징 버튼 이벤트 처리
    $(".pagination").on("click", "li a", function(evnet){
        event.preventDefault();
        replyPage = $(this).attr("href");
        $("#comments").html("");
        getPage("./replies/com/"+bnum+"/"+replyPage);
    });

	
});
</script>