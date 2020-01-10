$(function(){
	
	var listhtml="";
	var total=1;
	var id=0;
	var name=""
	//点击打开选择嘉宾的对话框
	
	$("#name").click(function(){
		/*alert("电子记录")*/
		$("#myModalLabel").text("选择嘉宾姓名");
	    $('#c_name').modal();
	});
	//获取数据及对其进行分页
	$("#name").click(function(){
	    $.ajax({				        	
	        url:'/getList',	
	        type:'POST',
	        data:{'page':1,'count':10},
	        dataType:'JSON',
	        async:false,
	        success:function (data){
	        	 total=data.total;
	        	 for(var i=0;i<data.rows.length;i++){		
		            	listhtml+='<tr class="row">'
		            		 +'<td class="col-lg-2">'+data.rows[i].guestid+'</td>'
		                    +'<td class="col-lg-4">'+data.rows[i].guestname+'</td>'		                    		                   		 
		                    +'<td class="col-lg-6"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'		                    
		                +'</tr>';
		            }
		        	$('#Userlist').append(listhtml);
	        }
	    }),
	    //分页插件
	    $('#pageLimit').bootstrapPaginator({    			
			
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
		    	$('#Userlist').children().remove();	
		    	listhtml="";
			        $.ajax({				        	
			        	 url:'/getList',
				            type:'POST',
				            data:{'page':page,'count':10},
				            dataType:'JSON',
			            success:function (data) {			               
			            	for(var i=0;i<data.rows.length;i++){		
			            		listhtml+='<tr class="row">'
			            			 +'<td class="col-lg-2">'+data.rows[i].guestid+'</td>'
				                    +'<td class="col-lg-4">'+data.rows[i].guestname+'</td>'		                    		                   		 
				                    +'<td class="col-lg-6"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'		                    
				                +'</tr>';
				            }
				        	$('#Userlist').append(listhtml);			                			            
			            }
			        })
		    }
	    });
    })

    //搜索嘉宾
	 $("#search").click(function(){
		 var s_total=1;
    	var name=$("#tab_search").val();
    	$('#Userlist').children().remove();
    	listhtml="";
    	if(name!=null){
    	    	$('#example').children().remove();
    	    	$('#example').append('<ul id="s_pageLimit"></ul>');
    	    	
    	    	listhtml='';

    	    	 $.ajax({				        	
    		            url:'/searchGuest',
    		            type:'POST',
    		            data:{'name':name,'page':1,'count':10},
    		            dataType:'JSON',
    		            async:false,
    		            success:function (data) {
    		            	 s_total=data.total;
//    		            	alert(s_total)
    		            	
    		            	for(var i=0;i<data.rows.length;i++){		
    		            		listhtml+='<tr class="row">'
    		            			 +'<td class="col-lg-2">'+data.rows[i].guestid+'</td>'
				                    +'<td class="col-lg-4">'+data.rows[i].guestname+'</td>'		                    		                   		 
				                    +'<td class="col-lg-6"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'		                    
				                +'</tr>';
    			            }
    			        	$('#Userlist').append(listhtml);
    		            }
    		     });
    	    	 //按搜索所获得的数据的分页
    	    	    $('#s_pageLimit').bootstrapPaginator({    			
    	    			
    	    	        currentPage: 1,//当前的请求页面。
    	    	        totalPages: s_total,//一共多少页。
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
    	    		    	$('#Userlist').children().remove();	
    	    		    	listhtml="";
    	    			        $.ajax({				        	
    	    			        		url:'/searchGuest',
    	    				            type:'POST',
    	    				            data:{'name':name,'page':1,'count':10},
    	    				            dataType:'JSON',
    	    			            success:function (data) {
    	    			               
    	    			            	for(var i=0;i<data.rows.length;i++){		
    	    			            		listhtml+='<tr class="row">'
    	    				                    +'<td class="col-lg-2">'+data.rows[i].guestid+'</td>'
    	    				                    +'<td class="col-lg-4">'+data.rows[i].guestname+'</td>'		                    		                   		 
    	    				                    +'<td class="col-lg-5"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'		                    
    	    				                +'</tr>';
    	    				            }
    	    				        	$('#Userlist').append(listhtml);
    	    			                
    	    			            
    	    			            }
    	    			        })
    	    		    }
    	    	    });
    	}
    });
	
	//选中了表单中的嘉宾
	$('#Userlist').on('click','tr',function(e){
		$(this).css("background-color","#4169E1");
		id=$(this).find("td").eq(0).text();
		name=$(this).find("td").eq(1).text();
	})
	$('#Userlist').on('dblclick','tr',function(e){
		$('#c_name').modal('hide');
		$('#name').val(name);
	})
	$('#c_btn').click(function(){
		$('#c_name').modal('hide');
		$('#name').val(name);
	})
	//提交所设置的数据
	$('#submit').click(function(){
		var formData = new FormData();
		formData.append('guestName',$('#g_name').val());
		formData.append('theme',$('#theme').val());
		formData.append('start',$('#start').val());
		formData.append('end',$('#end').val());
		formData.append('num',$('#num').val());
		formData.append('intro',$('#intro').val());
		$.ajax({
    		url:'/addInvitation',
    		type:'POST',  		
    		data:formData,
    		contentType:false,
    		processData:false,
	    	success:function(data){
	    		alert("添加数据成功！"); //添加数据成功
	    		 window.location.href="/order";
	    		
	    	},
	    	error:function(data){
	    		alert("添加数据失败！");
	    	}
    	})
	})
})