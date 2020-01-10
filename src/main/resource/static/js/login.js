
function SelectChange(){
	switch($('#l_select').find('option:selected').val()){
	case "1":
		$('#l_xuehao').text("学号:");
		$('#num').attr("placeholder","请输入学号");
		break;
	case "2":
		$('#l_xuehao').text("工号:");
		$('#num').attr("placeholder","请输入工号");
		break;
	case "3":
		$('#l_xuehao').text("账号:");
		$('#num').attr('placeholder',"请输入账号");
		break;
	case "4":
		$('#l_xuehao').text("账号:");
		$('#num').attr("placeholder","请输入账号");
		break;
	}
	}
	
$(function(){
	//登陆信息提交与检测
	$("#b_login").click(function(){
		var num=$('#num').val();
		var password=$('#password').val();
		var typeid=$('#l_select').val();
		
		if(num!="" && password!=""){
			$("#b_login").text(" 登陆中...");
			var formdata=new FormData();
			formdata.append('stuid',num);
			formdata.append('pwd',password);
			formdata.append('type',typeid);
			
			 $.ajax({
				 url:'login',
				 type:'POST',
				 data:formdata,	
				 contentType:false,
		    	 processData:false,
				 success:function(data){
					 
//					 alert(data);
					 if(data==="\"error\""){
							alert("账号或密码错误！请重新输入！")
							setTimeout(function(){
								$('#b_login').html("登陆");
								$('#num').html("");
								$('#password').html("");
							},1000);
					 }
					 else{	
						 var reg = /\"/g;
						 	$.cookie("username",data.replace(reg,""));
							$('#b_login').html('正在跳转');
							setTimeout(function(){
//								alert(typeid);
								switch(typeid){
								case '1':
									 window.location.href="/index";
									 break;
								case '2':
									window.location.href="/index";
									break;
								case '3':
									window.location.href="/manager/";
									break;
								case '4':	
									window.location.href="/manager/";
								}
							},1000)
							
					}
				
				 },
				 error:function(XHR){
					    alert("无法连接到网络!");
						/*setTimeout(function(){							
							$('#b_login').html("登 录");
						},2000);*/
					}
			  });
		}
		else{
			alert("账号或密码不得为空!");
		}
	})
})