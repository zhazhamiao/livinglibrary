$(function(){
	var d_id=$('#d_id').text();
	
	/**
	 * 加载主要信息
	 */
    $.ajax({
        url:"/infoData?GuestId="+d_id,
        type:"POST",
        dataType:"json",
        success: function(data){ 
        	var state;
        	 var time=Date.parse(new Date());
     	    var start=Date.parse(data.begintime);    	   
     	    var end=Date.parse(data.endtime); 
        	if(data.isshow==0 && start>time){
        		state = "预约";
        	}else{
        		state="回顾";
        	}
        	   
        	    
        	    console.log(start);	    
                $('#detail').append('<div id="left-img" class="col-sm-4 col-md-4 col-lg-4"><img id="person-img" src="http://10.1.79.22:8056/'+data.guestimg+'" alt="个人相片"></div>'+
                    '<div id="person-info" class="col-sm-8 col-md-8 col-lg-8" align="left"><div id="info-con" class="center"><h2 style="margin-left:30px;color:#999">'+data.guestname+'  '+data.address+'</h2><br /><br />'+
                    '<p style="font-size:20px;color:#333;text-indent:40px;">'+data.summary+'</p>'+
                    '<div id="order-btn1"><input type="button" style="display:none" class="btn btn-info" id="shu-btn" value="书单" data-toggle="modal"/>'+
                    '<input type="button" class="btn btn-info" id="order-btn" value="'+state+'"/></div></div>'
                );
                if(end>start){
        	    	$('#d_tip').show();
        	    	$('#d_date').show();
        	    	$('#info-con').removeClass("center");
        	    	$('#order-btn').addClass("btn-info");
        	    	$('#order-btn').removeAttr("disabled");
        	    }
                if($.cookie("username")){
                	var statu;
                	$.ajax({
                		url:"/isYuyue",
                		type:"POST",
                		data:{
                			guestid:d_id,
                			stuid:$.cookie("stuid")
                		},
                		success:function(data){
                			if(start>time){
                				if(data==1){//已预约
                    				$("#order-btn").val("已预约");
                    				$("#btn_submit").hide();
                    				$("#btn_cancel").show();
                    				statu="<b>已预约(请勿重复预约！)</b>";
                    				$("#status").css({"color":"red"});
                    			}else if(data==0){
                    				statu="<b>未预约</b>";
                    				$("#status").css({"color":"blue"});
                    			}else{
                    				$("#order-btn").val("预约已取消");
                    				$("#btn_submit").attr("disabled",true);
                    				statu="<b>已取消预约（不可再预约！）</b>";
                    				$("#status").css({"color":"red"});
                    			}
                    			$("#status").html("状态："+statu);
                			}
                			
                		}
                	})
                	
                	$("#stuid").html("学号：<b>"+$.cookie("stuid")+"</b>");
                	$("#name").html("姓名：<b>"+$.cookie("username")+"</b>");
                	
                	$("#guestName").html("讲座名：<b>"+$("#info-con").find("h2").html()+"</b>");
                	$("#yuyuetime").html("预约时间：<b>"+data.begintime+"</b>");
                	$("#riot").html("状态：<b>"+data.ordernum+"/"+data.allnum+"</b>")
                	
                	$('#order-btn').attr("data-target","#myModal");            	
                }
                
         }
			
    });
    
    $("#btn_submit").click(function(){
    	if($("#stuid").html().indexOf("*")>=0){
    		alert("出错了！！！");
    	}else{
    		$.ajax({
    			url:"yuYue",
    			type:"POST",
    			data:{
    				guestid:d_id,
        			stuid:$.cookie("stuid")
    			},
    			success:function(data){
    				if(data==1){//预约成功
    				    	  
    					layer.msg('预约成功');
    				    	
    					setTimeout(function(){
    						location.reload()
    					},"3000");
    					
    				}else{//预约失败
  				    	layer.msg('预约失败'); 
    					setTimeout(function(){
    						location.reload()
    					},"3000");
    				}
    			}
    		})
    	}
    });
    
    /**
     * 取消预约
     */
    $("#btn_cancel").click(function(){
    	
    	
    	layer.open({
    		  content: '确定要取消预约吗？'
    		  ,btn: ['确定',  '取消']
    		  ,yes: function(index, layero){
    		    //按钮【按钮一】的回调
    			  $.ajax({
    		    		url:"/cancelYuyue",
    		    		type:"POST",
    		    		data:{
    		    			guestid:d_id,
    		    			stuid:$.cookie("stuid")
    		    		},
    		    		success:function(data){
    		    			if(data==1){
    		    				layer.msg("取消预约成功，不可再预约该场讲座！");
    		    				setTimeout(function(){
    	    						location.reload()
    	    					},"3000");
    		    			}
    		    			
    		    		}
    		    	});
    		  }
    		  ,btn2: function(index, layero){
    		    //按钮【按钮二】的回调
    		    
    		    //return false 开启该代码可禁止点击该按钮关闭
    		  }
    		  ,cancel: function(){ 
    		    //右上角关闭回调
    		    
    		    //return false 开启该代码可禁止点击该按钮关闭
    		  }
    		});
    	
    	
    	
    	
    })
    
    $('body').on('click','#order-btn',function(){
    	
    	if($('#order-btn').val()==="回顾"){
    		//跳转到回顾页面
    		location.href='/pastview?guestid='+d_id;
    		return;
    	}else{
    		if(!$.cookie("username")){
        		if(confirm("您尚未登录，是否前往登录？")){
        			location.href='/alogin';
        		}
        	}else{
        		$('#myModal').modal("show");
        	}
    	}
    	
    	
	});
    
    //加载评论
    loadcomment();
   
    /**
     * 提交评论信息
     */
    $("#send-btn").click(function(){
    	
    	if(!$.cookie("username")){
    		if(confirm("您尚未登录，是否前往登录？")){
    			location.href='/alogin';
    		}
    	}else{
    	
	    	if($("#textarea").val()==""){
			    	  
			    layer.msg('请输入内容!!!');
	    	}else{
	    		$.ajax({
	    			url:"/addcomment",
	    			type:"POST",
	    			data:{
	    				guestid:d_id,
	    				stuid:$.cookie("stuid"),
	    				content:$("#textarea").val(),
	    				isrely:0
	    			},
	    			success:function(data){
	    				if(data==1){
	    					layer.msg("评论成功,请等待管理员审核通过！");
	    					$("#textarea").val("");
	    					setTimeout(function(){
	    						loadcomment();
	    					},"3000");
	    				}else{
	    					layer.msg("评论失败");
	    					$("#textarea").val("");
	    				}
	    			}
	    		})
	    	}
    	}
    })
    
    if($.cookie("username")){
    	$('#d_login').remove();
    	$('#register').hide();
    	$('#welcome').css("display","block");
    	$('#out').show();
		$('#s_name').text($.cookie("username"));
	}
    
    $('#logout').click(function(){
    	$.removeCookie('username');
		window.location.href="/aindex";
		
	});
   
    function loadcomment(){
   	 /**
        * 加载评论信息
        */
   	$("#words-list").empty();
       $.ajax({
       	url:"/getcomment",
       	type:"POST",
       	data:{
       		guestid:d_id
       	},
       	success:function(data){
       		//显示评论信息
       		$.each(data,function(index,obj){
       			
       			$("#words-list").append('<div class="words-item"><h5>'+
       					obj.name+'</h5>&nbsp; <span>'+
       					obj.addtime+'</span>&nbsp;&nbsp;<a class="" href="void:">回复</a><p>'+
       					obj.content+'</p></div>');
       		})
       	}
       });
   }
});

