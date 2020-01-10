$(function(){
	$(".personal").click(function(){
		if($.cookie("username")){
			window.location.href="/personal?stuid="+$.cookie("stuid");
		}else{
			if(confirm("您尚未登录，是否前往登录？")){
    			location.href='/alogin';
    		}
		}
	});
	
	 if($.cookie("username")){
	    	$('#d_login').remove();
	    	$('#register').hide();
	    	$('#welcome').css("display","block");
	    	$('#out').show();
			$('#s_name').text($.cookie("username"));
		}
	    
	    $('#logout').click(function(){
	    	$.removeCookie('username');
			window.location.href="/alogin	";
			
		});
	    
	    
	   //获取未读消息
	    if($.cookie("username")){
	    	$.ajax({
	    		url:"/getNotice",
	    		data:{
	    			type:0,
	    			stuid:$.cookie("stuid"),
	    		},
	    		type:"POST",
	    		success:function(data){
	    			console.log(data);
	    			var sum=0;
	    			var text="";
	    			$.each(data,function(i,item){
	    				if(item.anonymous==false){
	    					text+='<a href="/my-message" class="list-group-item d-flex justify-content-between align-items-center list-group-item-light">'+item.content+'<span class="badge badge-danger badge-pill">1</span></a>'
	    					sum++;
	    				}
	    				else{
	    					text+='<a href="/my-message" class="list-group-item d-flex justify-content-between align-items-center list-group-item-dark">'+item.content+'</a>'
	    				}
	    				
	    				
	    				
	    			});
	    			if(sum!=0)
	    				$("#navbardrop").append('<span class="badge badge-danger">'+sum+'</span>');
	    			$(".notice").empty();
	    			$(".notice").html(text);
	    		}
	    	})
	    }
	   
})