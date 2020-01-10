var serverUrl = "http://10.1.79.22:8056/";

$(document).ready(function() {
	var list = new Array();
	$.ajax({
		type:"get",
		url:"/getCarousel",
		async:true,
		success:function(data){
			list = data;
			//console.log(list);
			
			var tagItem = "";
			var bigImgItem = "";
			var listItem = "";
			for(var i = 0; i < list.length; i++) {
				var temp = i+1;
				tagItem += '<li data-target="#carousel-box" data-slide-to="' + i + '"></li>';
				bigImgItem += '<div class="carousel-item">';
				bigImgItem += '<img src="' + serverUrl + list[i].url + '" class="carousel-img">';
				bigImgItem += '<div class="carousel-caption">';
				bigImgItem += '<h3>第' + temp + '张图片描述标题</h3>';
				bigImgItem += '<p>描述文字!</p>';
				bigImgItem += '</div></div>';
			}

			//加载大图
			$("#img-box").append(bigImgItem);
			$("#img-box").find(".carousel-item").eq(0).addClass("active");
			//加载指示符
			$("#play-tag").append(tagItem);
			$("#play-tag").find("li").eq(0).addClass("active");

			//$("#demo").carousel(0);
			//console.log(list);
			
		}
	});
	
	
});

$(function(){
	var a=0;
	var b=0;
	var c=0;
	var total=0;	//总页数
    $.ajax({
        url:"getGuest",
        type:"POST",
        dataType:"json",
        success: function(data){
        	$.ajax({
        		url:"getGuestType",
        		type:"POST",
        		dataType:"json",
        		success: function(t_data){
        			for(var i=0;i<data.length;i++){ 
        				
        				switch(data[i].guesttype){
	        				case 1:
	        					b=b+1;
	        					if(b<=4){
	        						$('#early').append('<div class="right-item col-sm-3 col-md-3 col-lg-3">' +
	                	                    '<a href="/info?GuestId='+data[i].guestid+'" target="_blank"><img originalSrc="' + serverUrl + data[i].guestimg+'"alt="嘉宾" class="person-img"/></a>'+
	                	                    '<div class="person-info" align="center">' + '<h4 >'+data[i].guestname+'</h4><p>'+data[i].address+'</p>'+
	                	                    '<p>'+data[i].summary+'</p></div> </div>');
	    
	        					}
	        					else{
	        						$('#early').append("");
	        					}
	        					
	        					break;
	        				case 2:
	        					a++;
	        					if(a<=4){
	        						$('#mates').append('<div class="right-item col-sm-3 col-md-3 col-lg-3">' +
	                	                    '<a href="/info?GuestId='+data[i].guestid+'" target="_blank"><img originalSrc="' + serverUrl + data[i].guestimg+'"alt="嘉宾" class="person-img"/></a>'+
	                	                    '<div class="person-info" align="center">' + '<h4 >'+data[i].guestname+'</h4><p>'+data[i].address+'</p>'+
	                	                    '<p>'+data[i].summary+'</p></div> </div>');
	        					}
	        					else{
	        						$('#mates').append("");
	        					}
	        					break;
	        				case 3:
	        					c++;
	        					if(c<=4){
	        						$('#social').append('<div class="right-item col-sm-3 col-md-3 col-lg-3">' +
	                	                    '<a href="/info?GuestId='+data[i].guestid+'" target="_blank"><img originalSrc="' + serverUrl + data[i].guestimg+'"alt="嘉宾" class="person-img"/></a>'+
	                	                    '<div class="person-info" align="center">' + '<h4 >'+data[i].guestname+'</h4><p>'+data[i].address+'</p>'+
	                	                    '<p>'+data[i].summary+'</p></div> </div>');
	        					}
	        					else{
	        						$('#social').append("");
	        					}
	        					break;
	        					
        				}
        				if(a==b==c==3){
        					break;
        				}
        	            
        	          }
        		}
        	});           
        }
    });
    
    //延迟加载	
    setTimeout(function(){
    	$(".showp img,#s_xian img").delayLoading({
    		defaultImg: "images/bg.png", // 预加载前显示的图片    
    		errorImg: "", // 读取图片错误时替换图片(默认：与defaultImg一样)    
    		imgSrcAttr: "originalSrc", //记录图片路径的属性(默认：originalSrc，页面img的src属性也要替换为originalSrc)    
    		beforehand: 0, // 预先提前多少像素加载图片(默认：0)    
    		event: "scroll", // 触发加载图片事件(默认：scroll)    
    		duration: "2000", // 三种预定淡出(入)速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认:"normal"    
    		//container: window, // 对象加载的位置容器(默认：window)    
    		//success: function(imgObj) {}, // 加载图片成功后的回调函数(默认：不执行任何操作)    
    		//error: function(imgObj) {} // 加载图片失败后的回调函数(默认：不执行任何操作)    
    	});
    },"500");
    
    if($.cookie("username")){
    	$('#d_login').hide();
    	$('#h_login').show();
		$('#s_name').text($.cookie("username"));
	}
    
    
    $('#logout').click(function(){
    	$.ajax({
			url:"/logout",
			type:"GET",
			success:function(data){
				window.location.href="/index";
				$.removeCookie('username');
				$('#d_login').show();
		    	$('#h_login').hide();
			},
			error:function(data){
				console.log(data);
			}
		})
		
	});
    
    $("#s_input").keydown(function (e) {
        if (e.keyCode == 13) {
        	$('#s_btn').click();
        }
    });
    
    $('#s_btn').click(function(){  
    	var name=$('#s_input').val();
    	$("#result").show();
    	$('#s_xian').children().remove();
    	if(name!=""){   		
    		$('#main').remove();
    		$('#s_nav').children().remove();
    		$('#s_nav').append('<nav aria-label="breadcrumb"><ol class="breadcrumb"><li class="breadcrumb-item"><a href="http://www.nchu.edu.cn/">南昌航空大学</a></li>'+
				                        '<li class="breadcrumb-item"><a href="http://lib.nchu.edu.cn/">图书馆</a></li>'+
				                        '<li class="breadcrumb-item"><a href="">真人图书馆</a></li>'+
				                        '<li class="breadcrumb-item active">搜索结果</li></ol></nav>')
    		 $.ajax({
    			   url:'searchGuest',
		            type:'POST',
		            data:{'name':name,'page':1,'count':8},
		            dataType:'JSON',
		            async:false,
    		        success: function(data){
    		        	total=data.total;
    		            for(var i=0;i<data.rows.length;i++){
    		            	$('#s_xian').append('<div class="right-item col-sm-3 col-md-3 col-lg-3 search-item">' +
            	                    '<a href="/info?GuestId='+data.rows[i].guestid+'" target="_blank"><img src="' + serverUrl + data.rows[i].guestimg+'"alt="嘉宾" class="person-img"/></a>'+
            	                    '<div class="person-info" align="center">' + '<h4 >'+data.rows[i].guestname+'</h4><p>'+data.rows[i].address+'</p>'+
            	                    '<p>'+data.rows[i].summary+'</p></div> </div>');	  
    		                    }
    		            }
    		 });
    	}
    	 //搜索的分页
        $('#pageLimit').bootstrapPaginator({    			
    		
            currentPage: 1,//当前的请求页面。
            totalPages: total,//一共多少页。
            size:"normal",//
            bootstrapMajorVersion: 4,
            alignment:"right",
            numberOfPages:5,
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first": return "首页";
                    case "prev": return "上一页";
                    case "next": return "下一页";
                    case "last": return "末页";
                    case "page": return page;
                }
            },
    	    onPageClicked: function (event, originalEvent, type, page){//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
    	    	$('#s_xian').children().remove();
    		        $.ajax({				        	
    		        	 url:'searchGuest',
    			            type:'POST',
    			            data:{'name':name,'page':page,'count':8},
    			            dataType:'JSON',
    		            success:function (data) {    		            	
    		                for(var i=0;i<data.rows.length;i++){
    		            	$('#s_xian').append('<div class="right-item col-sm-3 col-md-3 col-lg-3 search-item">' +
            	                    '<a href="/info?GuestId='+data.rows[i].guestid+'" target="_blank"><img src="' + serverUrl + data.rows[i].guestimg+'"alt="嘉宾" class="person-img"/></a>'+
            	                    '<div class="person-info" align="center">' + '<h4 >'+data.rows[i].guestname+'</h4><p>'+data.rows[i].address+'</p>'+
            	                    '<p>'+data.rows[i].summary+'</p></div> </div>');		                            
    		                
    		                }
    		            }
    			        
    		        });
    	    }
        });
        
    });
    
});



