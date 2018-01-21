/**
 * 
 */
//terms.html
$(function(){		
	$("i").css("font-size", "30px");
	if($("li").hasClass("active")){
		$("li.active").children("a").children("i").removeClass();
		$("li.active").children("a").addClass("bg-success");
		$("li.active").children("a").children("i").addClass("fa fa-check-square");
		$("li.active").children("a").children("i").css("color","#16ec08");		
	}
	$("li.deactive").children("a").addClass("bg-warning");
	$(".btn-warning").on("click", function(){
		$(location).attr("href","/member/signup")
	})
});

//signup.html
//무결성검사
$(function(){
    var csrfToken = $("meta[name='_csrf']").attr("content"); 
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content"); 
	//이메일
	$("#domain").change(
	function checkEmail(){
		var emailid = $("#emailid");//아이디
		var domain = $("#domain");//선택기		
		var email = "";
		var reg = /^\w{3,20}[@][a-zA-Z0-9]{2,10}[.][a-z]{2,10}([.][a-z]{2,3})?/;//처음부터 끝까지 한글이 들어있는가
		$("#domain option:selected").each(function (){
			if($("#domain").val()=="not" || $("#domain").val()==""){
				domain = ""
				$("#domain2").attr("disabled",false);			
				email = emailid.val() + "@" + $("#domain2").val();
			}else{
				domain = $("#domain2").val($("#domain option:selected").text())
				email = emailid.val() + "@" + domain.val();
			}			
		});
		if(email == "잘못된 이메일이다." || !reg.test(email) || String(email).indexOf("@")==0 || String(email).length-1 == String(email).indexOf("@")){
			$("#email").val("잘못된 이메일이다.");
			$("#email").effect("shake");
			$("#email").parent().parent().removeClass(" has-success has-feedback");
			$("#email").parent().parent().addClass(" has-error has-feedback");					
		}else{
			$("#email").val(email);
			$("#email").parent().parent().removeClass(" has-error has-feedback");
			$("#email").parent().parent().addClass(" has-success has-feedback");
		}		
	});
	
	//아이디
	$("#uid").blur(function(){
        var reg = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,8}$/;//첫번째문자 영문, 나머지4~8 영문,숫자포함
        var msg1 = "4~8글자 영어숫자조합!!"
        var msg2 = "3~8글자 영어숫자조합으로 만드시오.";
        var flag = $("#uid");
        $.ajax({
           url:"/member/chkUser",
           beforeSend : function(xhr){
               xhr.setRequestHeader(csrfHeader, csrfToken);  
           },
           type:"post",
           dataType:"text",
           data:$("#uid"),
           success:function(data){
               if(data>0){
                  $("#memFrm>div.form-group:eq(0)").removeClass(" has-success has-feedback");
                  $("#memFrm>div.form-group:eq(0)").addClass(" has-error has-feedback");
                  $("#memFrm>div.form-group:eq(0)>label").html("<span class='imp1'>*</span>아이디 - 이미 사용중인 아이디 입니다.");
                  console.log(data);
               }else{
                   $("#memFrm>div.form-group:eq(0)").removeClass(" has-error has-feedback");
                   $("#memFrm>div.form-group:eq(0)>label").html("<span class='imp1'>*</span>아이디");
                   checkInput(reg, flag, msg1);
                   bkInput(flag, msg2);    
               }
           }
        });
    });
		
	//비번
		$("#upw").blur(function(){
			var reg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&%*^+=-])(?=.*[0-9]).{6,13}$/;
		    //var reg = /[a-z]/g
			//전방탐색 하위까지 영문자, 특문, 숫자 {8,13}자리 끝까지 돌아서 무조건 한개 들어가야함
			var msg1 = "6~13글자 영어대문자숫자특문조합!! 무조건 한개씩이라도 들어가야함";
			var msg2 = "6~13글자 영어대문자숫자특문조합으로 만드시오.";
			var flag = $("#upw");
			checkInput(reg, flag, msg1);
			bkInput(flag, msg2);
		});

	//비번 재확인
		$("#pwdChk").blur(function(){	
			//체크해서 아무것도 없거나 같지 않으면
				if($(this).val() == "" || $("#upw").val() != $(this).val()){
					$(this).val("")
					$("#pwdChk").attr("placeholder", "다시시도!")
					$(this).parent().parent().effect("shake");
					$(this).parent().parent().addClass(" has-error has-feedback");
				}else{
					$(this).parent().parent().removeClass(" has-error has-feedback");
					$(this).parent().parent().addClass(" has-success has-feedback");
				}
			$("#pwdChk").focus(function(){
				$("#pwdChk").attr("placeholder", "비밀번호를 확인하세요")
				$(this).parent().parent().removeClass(" has-error has-feedback");		
			});
		});
			
	/*//이름
		$("#userName").blur(function(){	
			var reg = /^[가-힣]{2,6}$/;
			var msg1 = "자기 이름을 모르는건가??? 한글2~6글자까지";
			var msg2 = "이름을 입력해라";
			var flag = $("#userName");			
			checkInput(reg, flag, msg1);
			bkInput(flag, msg2);
		});*/
		
	//닉네임
		$("#uname").blur(function(){
			var reg = /^[ㄱ-ㅎ가-힣a-zA-z0-9]{2,6}$/;
			var msg1 = "닉네임은 영어숫자한글2~6글자까지";
			var msg2 = "닉네임을 적어라";
			var flag = $("#uname");
    		$.ajax({
               url:"/member/chkUser",
               beforeSend : function(xhr){
                   xhr.setRequestHeader(csrfHeader, csrfToken);  
               },
               type:"post",
               dataType:"text",
               data:$("#uname"),
               success:function(data){
                   if(data>0){
                      $("#memFrm>div.form-group:eq(3)").removeClass(" has-success has-feedback");
                      $("#memFrm>div.form-group:eq(3)").addClass(" has-error has-feedback");
                      $("#memFrm>div.form-group:eq(3)>label").html("<span class='imp1'>*</span>닉네임 - 이미 사용중인 닉네임 입니다.");
                      console.log(data);
                   }else{
                       $("#memFrm>div.form-group:eq(3)").removeClass(" has-error has-feedback");
                       $("#memFrm>div.form-group:eq(3)>label").html("<span class='imp1'>*</span>닉네임");
                       checkInput(reg, flag, msg1);
                       bkInput(flag, msg2);    
                   }
               }
             });
		});
				
		
		function checkInput(reg, flag, msg1){
		    flag.after("<strong class='imp1'></strong>");
			//체크해서 아무것도 없거나 같지 않으면
			if(!reg.test(flag.val())){
			    flag.next("strong").text(msg1);
				flag.parent().parent().effect("shake");
				flag.parent().parent().addClass(" has-error has-feedback");	
			}else{
				flag.parent().parent().removeClass(" has-error has-feedback");
				flag.parent().parent().addClass(" has-success has-feedback");
				flag.next("strong").text();
			}
		}
		//복귀
		function bkInput(flag, msg2){
		    //flag.after("<strong class='imp1'></strong>");
			flag.focus(function(){
				flag.parent().parent().removeClass(" has-error has-feedback");
				flag.next("strong").remove();
			});
		}	
		
		//프로필사진
		$("#profile_picture").on("change", function(event){
		    var formData = new FormData();
		    var files = event.target.files;
		    var file = files[0];
		    formData.append('file' ,file);
		    console.log("member_profile_picture : " + file);
		    $.ajax({
		       url:"/uploadProfile",
		       beforeSend : function(xhr){
	               xhr.setRequestHeader(csrfHeader, csrfToken);  
	           },
		       data:formData,
		       dataType:"text",
		       processData: false,
	           contentType: false,
	           type:'POST',
	           success:function(data){
	               imgsrc="/displayFile?location=profile&fileName="+data;
	               $(".img-rounded").attr("src", imgsrc);
	               $("#filesrc").val(imgsrc);
	           }
		    });
		});
		$("#memFrm").submit(function(event){
		    event.preventDefault();
			if($("#memFrm>div").hasClass("has-error") || $("#email").val() == "" || $("#uid").val() == ""|| $("#upw").val() == ""|| $("#pwdChk").val() == ""|| $("#uname").val() == ""){	
				alert("틀린 정보가 있으니 다시 확인하시오")				
				return false;
			}else{
				$(this).get(0).submit();
				return true;
			}			
		});

	
});
