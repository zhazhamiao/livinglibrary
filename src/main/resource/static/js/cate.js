$(function(){
	 /**
    **加载评论 参数0--加载未审核评论，-1--加载所有评论
    **/
	var cout = 100;
	var total=0;
	loadcomment(0,1,cout);
	var recommand = ['<span class="x-red">消极</span>','<span style="color:#CDBE70">中性</span>','<span style="color:#7D26CD">积极</span>','<span>无</span>']; //AI推荐。0-消极，1-中性，2-积极，3-未识别成功
    function loadcomment(isshow,page,count){
    	 $(".x-cate").empty();
        $(".page").find("div").empty();
    	 $.ajax({
    	    	url:"/loadcomment",
    	    	type:"POST",
    	    	data:{
    	    		isshow:isshow,
    	    		page:page,
    	    		count:count,
    	    	},
    	    	success:function(data){
    	    		$(".static").html(data.records);
    	    		$.each(data.rows,function(idx,obj){
    	    			$(".x-cate").append('<tr cate-id='+idx+'><td><div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+idx+'><i class="layui-icon">&#xe605;</i></div></td><td>'+
    	    					idx+'</td>=<td>'+
    	    					obj.gname+'</td><td>'+
    			             	obj.name+'</td><td><b>'+
    			             	obj.content+'</b></td><td>'+
    			             	obj.keyword+'</td><td>'+
    			             	recommand[obj.airecommend]+'</td><td class="td-manage">'+
    			              	'<button class="layui-btn layui-btn layui-btn-xs"  onclick="comment_show(this,'+obj.id+')" ><i class="layui-icon">&#xe642;</i>展示</button>'+
    			              	'<button class="layui-btn layui-btn-warm layui-btn-xs"  onclick="comment_hide(this,'+obj.id+')" ><i class="layui-icon">&#xe642;</i>隐藏</button>'+
    			              	'<button class="layui-btn-danger layui-btn layui-btn-xs"  onclick="comment_del(this,'+obj.id+')" href="javascript:;" ><i class="layui-icon">&#xe640;</i>删除</button>'+
    			            	'</td></tr>');
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
    	    	},
    	    	error:function(data){
    	    		layer.msg("连接超时");
    	    	}
    	    });
    	 $(".page").find("div").on("click",".num",function(){
	     	  loadcomment(-1,$(this).html(),cout);
	 	})
	 	$(".page").find("div").on("click",".prev",function(){
	 		var cur = $(".page").find("div .current").html();
	 		if(cur>1)
	 			loadcomment(-1,cur-1,cout);
	 	})
	 	$(".page").find("div").on("click",".next",function(){
	 		var cur = $(".page").find("div .current").html();
	 		if(cur<total)
	 			loadcomment(-1,parseInt(cur)+1,cout);
	 	})
    }
   
    
    
})