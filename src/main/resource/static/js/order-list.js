$(function(){
	var cout = 10;
	var total=0
	loadGuest(1,cout);
	//通过表格展现数据
	function loadGuest(page,count){
		$(".guestlist").empty();
		$(".page").find("div").empty();
		$.ajax({				        	
	        url:'/getList',	
	        type:'POST',
	        data:{'page':page,'count':count},
	        dataType:'JSON',
	        async:false,
	        success:function (data){
	        	 console.log(data);
	        	 $(".total").html(data.records);
	        	 var temp;
	        	 var url;
	        	 $.each(data.rows,function(idx,obj){
	        		 
	        		 url="guestid="+obj.guestid+"&guestname="+obj.guestname+"&address="+obj.address+"&guesttype="+obj.guesttype+"&guestimg="+obj.guestimg+"&summary="+obj.summary.replace(/[\r\n]/g,"")+"&isshow="+obj.isshow+"&begintime="+obj.begintime+"&endtime="+obj.endtime+"&allnum="+obj.allnum;
	        		// console.log(url);
	        		 temp=obj.isshow==0?obj.ordernum+"/"+obj.allnum:"已取消";
	        		 $(".guestlist").append('<tr><td><div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+idx+'><i class="layui-icon">&#xe605;</i></div></td><td>'+
						obj.guestname+'</td><td>'+
						obj.address+'</td><td><img class="a_img" src="http://10.1.79.22:8056/'+
						obj.guestimg+'"></td><td>'+
						obj.summary+'</td><td class="status">'+
						temp+'</td><td>'+
						obj.begintime+'</td><td class="td-manage">'+
			            '<a title="编辑"  onclick="x_admin_show(\'编辑\',\'order-view.html?'+url+'\')" href="javascript:;">'+
			            '<i class="layui-icon">&#xe63c;</i></a>'+
			            '<a title="取消" onclick="order_del(this,'+obj.guestid+')" href="javascript:;">'+
			            '<i class="layui-icon">&#xe640;</i></a></td></tr>')	
	        	 });
	        	 
	        		var text=' <a class="prev" href="#">&lt;&lt;</a>';
					total = data.total;
					for(var i=1;i<=data.total;++i){
						if(i==data.page)
							text+=' <span class="current" href="#">'+i+'</span>';
						else
							text+=' <a class="num" href="#">'+i+'</a>'
					}
					
					$(".page").find("div").append(text+' <a class="next" href="#">&gt;&gt;</a>')
	        }
	    });
	}
	$(".page").find("div").on("click",".num",function(){
		loadGuest($(this).html(),cout);
	})
	$(".page").find("div").on("click",".prev",function(){
		var cur = $(".page").find("div .current").html();
		if(cur>1)
			loadGuest(cur-1,cout);
	})
	$(".page").find("div").on("click",".next",function(){
		var cur = $(".page").find("div .current").html();
		if(cur<total)
			loadGuest(parseInt(cur)+1,cout);
	})
   
})
