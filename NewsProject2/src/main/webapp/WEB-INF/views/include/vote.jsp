<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="boardVote" style="margin:0px auto;text-align:center;">
</div>

<script>
	
	var bname =  $("#bname").val();
	var bnum = ${boardVO.bnum}; //게시물의 번호
	var writer = "${uname}";
	function getVote(pageInfo){
	    $.getJSON(pageInfo, function(ballotData){
		    var data = "";
				$.each(ballotData.list, function(key, value){
				    data+='<a class="" href="javascript:vote(like)">'
				    data+='<b><span id="boardVotedLike">0</span></b>'
				    data+='<p>좋아요</p>'
				    data+='</a>'
				    data+='<a class="" href="javascript:vote(dislike)">'
				    data+='<b><span id="boardVotedDislike">0</span></b>'
				    data+='<p>박규</p>'
				    data+='</a>'
				});
	    	$(".boardVote").html(data);
	    });
	}
	function vote(selectBtn){
	    if(writer != ""){
	        if(selectBtn == "like"){
				chk = "vck";
	        }else{
				chk = "hck"
	        }
	        
		    var ballot = {
				bname : bname,
				bnum : bnum,
				writer : writer,
				chk : chk
		    };
		    
		    $.ajax({
		        type:"post",
		        url:"./vote/" + bnum,
		        beforeSend : function(xhr){
		          xhr.setRequestHeader(csrfHeader, csrfToken);  
		        },
		        headers:{
		            "Content-Type" : "application/json",
		        },
		        dataType : "json",
		        success:function(result){
		            if(result.code == "OK"){
		                getVote("./vote/"+bnum);
		            }else{
		                alert(result.message);
		            }
		        },
		        error : function(request, status, error){
		            console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
		        }
		    });
	    }
	}
	$(document).ready(function(){
		getVote("./vote/"+bnum);
	});    
</script>
<style>
	.boardVote>a{border:2px solid #D35460;color:#D35460;display: inline-block;width:60px;height:60px;margin:0px 10px;text-decoration:none;border-radius:4px;}
	.boardVote>a>b{display: block; padding-top: 10px; font: bold 17px/1 Arial,sans-serif;}
	.boardVote>a>p{margin: 0;font-weight: 700;font-size: 10px;}
</style>