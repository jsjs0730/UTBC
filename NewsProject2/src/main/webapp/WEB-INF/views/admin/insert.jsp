<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ include file="../include/header.jsp" %>
<style>
.fileDrop {
  width: 95%;
  height: 100px;
  border: 2px dashed #22d952;
  margin: auto;  
}

.fileDropDis{
    margin: 40px auto;
    width: 150px;
    height: 15px;
    padding: 0px;
}
.popup{position:absolute;}
.back{position:absolute;background-color: gray; width : 380px; height: 280px;opacity: 0.5;overflow: hidden; z-index:1101;}
.front{z-index: 1110; opacity: 1; border:1px; margin:0px auto;}
.show{position:absolute; width:350px; height: 250px;padding:0px;margin:15px;}
#popup_front>img{position:absolute;}
.popupDis{position:absolute;padding:0px;width:380px;height:15px;line-height:15px;text-align:center;background-color: #5fa26a;color: #fff;}
</style>
<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		$('.canclebtn').on("click", function(){
			self.location = "/board/listPage?bname=${cri.bname}&page=${cri.page}&perPageNum=${cri.perPageNum}";
		});
	});
</script>
<form id='registerForm' role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">bname</label>
			<select name="bname">
				<option value="politics">정치</option>
				<option value="society">사회</option>				
				<option value="economy">경제</option>
				<option value="newsflash">속보</option>
				<option value="community">커뮤니티</option>
			</select>	
		</div>
		<div class="form-group">
			<label for="title">Title</label>
			<input type="text" name="title" class="form-control" placeholder="Enter 제목">	
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea rows="30" class="form-control" name="content" id="content" placeholder="내용을 적어라" ></textarea>	
		</div>
		<div class="form-group">
			<label for="usernick">닉네임</label>
			<input type="text" name="usernick" class="form-control" placeholder="작성자를 적어라">	
		</div>		
		<div class="form-group">
			<label for="exampleInputEmail1">파일 업로드</label>
			<div class="fileDrop">
				<div class="fileDropDis">여기에 사진 드래그</div>
			</div>	
			<label for="mobileFile">Upload</label>
			<input type="file" name="mobileFile" id="mobileFile" multiple />
			<!-- <output id="list"></output> -->
			
		</div>		
		
	</div><!-- .box-body -->
	<div class="box-footer">
		<div>
			<hr>
		</div>

		<ul class="mailbox-attachments clearfix uploadedList"></ul>
		<div class="popup back" style="display:none;"></div>
        <div id="popup_front" class="popup front" style="display:none;">
        	<img src="" alt="" id="popup_img" />
        	<div class="popupDis">더블클릭하면 본문에 추가합니다.</div>
        </div>
        
		<button type="submit" class="btn btn-primary">확인</button>
		<button type="submit" class="btn btn-warning canclebtn">취소</button>	
	</div>
</form>
<script type="text/javascript" src="/resources/plugins/smarteditor2/js/service/HuskyEZCreator.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a class="btn btn-danger btn-xs pull-left ineditbtn"><i class="fa fa-arrow-up"></i>	
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
  </div>
</li>                
</script>    

<script>
$.fn.setCenter = function(){
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
	//console.log("window.height: "+$(window).height() + "\n/ this.outerHeight :"+ $(this).outerHeight()+"\n/ window.scrollTop :"+$(window).scrollTop());
    return this;
}

var template = Handlebars.compile($("#template").html());

$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});

var files;
function uploadControl(files){
	var formData = new FormData();
	for(var i=0; i<files.length; i++){
		formData.append("files", files[i]);    
	}
	
	$.ajax({
		  url: '/uploadAjax',
		  data: formData,
		  dataType:'json',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(arr){
		      alert(arr);
		      console.log(arr);
		      for(var i=0; i<arr.length; i++){
		         var data = arr[i];
		         var str = "";
		    	 var fileInfo = getFileInfo(data);
				 var html = template(fileInfo);
				 $(".uploadedList").append(html);
			}
		}
	});	
}
//모바일로 
$("#mobileFile").on("change", function(event){
    files = event.target.files; // FileList object
    console.log("files[0]: " +files[0]);
	uploadControl(files);
  /*   // files is a FileList of File objects. List some properties.
    var output = [];
    for (var i = 0, f; f = files[i]; i++) {
      output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ', f.size, ' bytes, last modified: ', f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a','</li>');
    }
   $('#list').html('<ul>' + output.join('') + '</ul>'); */
});
//PC
$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	files = event.originalEvent.dataTransfer.files;
	console.log("files[0]: " +files[0]);
	//var file = files[0];
	uploadControl(files);
});
//삭제처리
$(".uploadedList").on("click", ".delbtn", function(event){
	event.preventDefault();
	if(confirm("삭제하시겠습니까???") == true){
		var that = $(this);
	    $.ajax({
			url:"/deleteFile",
			type:"post",
			data: {fileName:$(this).attr("href")},
			dataType:"text",
			beforeSend:function(){
			    var inputArea = $("#contentIFrm").contents().find("#se2_iframe").contents().find("body.se2_inputarea");
				//에디터 상에서 삭제처리		    
				if(inputArea.has('<img src="'+ that.closest("li").find(".mailbox-attachment-name").attr("href") +'">')){
					$("#contentIFrm").contents().find("#se2_iframe").contents().find("body.se2_inputarea").find("img[src='"+that.closest("li").find(".mailbox-attachment-name").attr("href")+"']").each(function(){
					    $(this).remove();
					});
			 	}
		 	},
		 	//DB에서 삭제처리
			success:function(result){
				if(result == 'deleted'){
					that.closest("li").remove();
				}
			}
	});
	}else{
	    console.log("삭제취소");
	}
});


//이미지 파일 이벤트 처리-에디터에 주입
$(".uploadedList").on("dblclick", "img", function(event){
    var inputArea = $("#contentIFrm").contents().
	find("#se2_iframe").contents().
	 find("body.se2_inputarea");
    inputArea.append("<p><img src='"+ $(this).closest("li").find(".mailbox-attachment-name").attr("href") +"'></p><br/>" );
    inputArea.find("img").css({
      	 "max-width" :"100%",
      	 "max-height":"100%"
      	});
    console.log($(this).attr("src"));
});
$(".uploadedList").on("click", ".ineditbtn", function(event){
    var inputArea = $("#contentIFrm").contents().
	find("#se2_iframe").contents().
	 find("body.se2_inputarea");
    inputArea.append("<p><img src='"+ $(this).closest("li").find(".mailbox-attachment-name").attr("href") +"'></p><br/>" );
   	inputArea.find("img").css({
   	 "max-width" :"100%",
   	 "max-height":"100%"
   	});
    console.log($(this).attr("src"));
});


//이미지 파일 이벤트 처리 - 미리보기 열기
$(".uploadedList").on("mouseenter", "img", function(event){
    var fileLink = $(this).closest("li").find(".mailbox-attachment-name").attr("href");
    if(checkImageType(fileLink)){
        event.preventDefault();
        var imgTag = $("#popup_img");
        imgTag.attr("src", fileLink);
        //console.log(imgTag.attr("src"));
        
        $(".popup").show();
        $(".popup").setCenter();
        imgTag.addClass("show");
    }
});
//이미지 파일 이벤트 처리 - 미리보기 닫기
$(".uploadedList").on("mouseout", "img", function(){
    $(".popup").hide();
});


var obj = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: obj,
    elPlaceHolder : "content",
    sSkinURI:"/resources/plugins/smarteditor2/SmartEditor2Skin.html",
    htParams : {
        // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
        bUseToolbar : true,            
        // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
        bUseVerticalResizer : true,    
        // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
        bUseModeChanger : true,
    }
});

$("#registerForm").submit(function(event){
	event.preventDefault();
	obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	var that = $(this);
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);
	that.get(0).submit();
});
</script>

<%@ include file="../include/footer.jsp" %>