<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	$(document).ready(function(){
		getVote("./vote/"+bnum);
	});   
	var bname =  $("#bname").val();
	var bnum = ${boardVO.bnum}; //게시물의 번호
	var voter = "${uname}";
	function voteBoard(selectBtn){
	    if(voter != ""){	        
	        if(selectBtn == 1){
				chk = "vck";
	        }else{
				chk = "hck";
	        }
	        
		    var ballot = {
				bname : bname,
				bnum : bnum,
				voter : voter,
				chk : chk
		    };
		    $.ajax({
		        type:"post",
		        url:"./vote/" + bnum,
		        beforeSend : function(xhr){
		          xhr.setRequestHeader(csrfHeader, csrfToken);  
		        },
		        headers:{
		            contentType :"application/x-www-form-urlencoded; charset=UTF-8",
		        },
		        dataType : "json",
		        data : ballot,
		        success:function(result){
		            if(result.code == "GOOD"){
		                getVote("./vote/"+bnum);
		            }else{
		                alert(result.message);
		            }
		        },
		        error : function(request, status, error){
		            console.log("code" + request.status+ "\n" + "message : " + request.responseText+"\n"+"error : "+error);
		        }
		    });
	    }else{
	        if(confirm("로그인을 하시겠습니까??")){
				$(location).attr("href", "/login");	            
	        }
	    }
	
	}
	function getVote(pageInfo){
	    $.getJSON(pageInfo, function(ballotData){
		    var data = "";
				$.each(ballotData.list, function(key, value){
				    data+='<a href="javascript:voteBoard(1);">'
				    data+='<b><span id="boardVotedLike">'+this.vlike+'</span></b>'
				    data+='<p>좋다</p>'
				    data+='</a>'
				    data+='<a href="javascript:voteBoard(0);">'
				    data+='<b><span id="boardVotedDislike">'+this.dislike+'</span></b>'
				    data+='<p>ㅄ</p>'
				    data+='</a>'
				});
	    	$(".boardVote").html(data);
	    });
	}


</script>
<style>
	.boardVote>a{border:2px solid #D35460;color:#D35460;display: inline-block;width:60px;height:60px;margin:0px 10px;text-decoration:none;border-radius:4px;}
	.boardVote>a>b{display: block; padding-top: 10px; font: bold 17px/1 Arial,sans-serif;}
	.boardVote>a>p{margin: 0;font-weight: 700;font-size: 10px;}
</style>

<div class="boardVote" style="margin:0px auto;text-align:center;">
</div>