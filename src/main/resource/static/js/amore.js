var serverUrl = "http://10.1.79.22:8056/";

$(function(){
    var typeid=$('#m_id').text(); 
    var total=0;
    var pag =1;
    var cout = 9;
    loadVideo(pag,cout);
    function loadVideo(page,count){
    	$.ajax({
    		url:'/moreVideo',
            type:'POST',
            data:{'typeid':typeid,'page':page,'count':count},
            dataType:'JSON',
            async:false,
            success: function(data){
           	   
            	if(data.total==data.page){
            		$("#loadmore").html("没有更多了！");
            		$("#loadmore").attr("disabled","disabled");
            		
            	}
            	
            	
            	$.each(data.rows,function(idx,item){
            		$('#m_show').append('<div class="right-item col-sm-4 col-md-4 col-lg-4"><a href="/playvedio?id='+item.id+'" target="_blank"><img src="'+item.img+'" alt="vedio" class="person-img" /></a><div class="person-info" style="height: 80px; padding: 10px 0 0 10px; color: #000; font-size: 16px;position:relative;text-indent:2px;" align="left">'
    							+item.title
    							
    							+'<div  style="position:absolute;bottom:16px; color: #999; font-size: 16px;width:90%;text-indent:0px;"><span style="float:left;"><i class="iconfont icon-bofangliang"></i> '+item.playvolume+'</span>'
    							+'<span style="float:right;"><i class="iconfont icon-shijian"></i>'+item.createtime+'</span></div></div></div>');
    			})        	            	
            }      
               
        });
    }
	$("#loadmore").click(function(){
		loadVideo(++pag,cout);
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
    
    
     //所有数据的分页
  /* $('#pageLimit').bootstrapPaginator({    			
   			
		        currentPage: 1,//当前的请求页面。
		        totalPages: total,//一共多少页。
		        size:"normal",//
		        bootstrapMajorVersion: 3,
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
			    	$('#m_show').children().remove();				    				
				        $.ajax({				        	
				        	 url:'moreData',
				             type:'POST',
				             data:{'GuestType':typeid,'page':page,'count':10},
				             dataType:'JSON',
				             success:function (data) {
				            	 console.log(data);
				                for(var i=0;i<data.rows.length;i++){
				            	$('#m_show').append('<div class="right-item col-sm-3 col-md-3 col-lg-3 search-item">' +
			    	                    '<a href="/adetail?GuestId=' + data.rows[i].guestid + '" target="_blank"><img src="' + serverUrl + data.rows[i].guestimg + '"alt="嘉宾" class="person-img"/></a>' +
			    	                    '<div class="person-info" align="center">' + '<h4 >' + data.rows[i].guestname + '</h4><p>' + data.rows[i].address + '</p>' +
			    	                    '<p>' + data.rows[i].summary + '</p></div></div>');		                            
				                
				                }
				            }
				        })
			    	}
   })*/
});