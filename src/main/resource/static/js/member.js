$(function(){
	
	
	
	$(".userbody").empty();
	$(".page").find("div").empty();
	$.ajax({
		url:"/listmember",
		type:"POST",
		data:{'page':1,'count':10},
		dataType:'JSON',
        async:false,
		success:function(data){
			
			layui.use('laypage', function(){
				  var laypage = layui.laypage;
				  
				  //执行一个laypage实例
				//完整功能
				  laypage.render({
				    elem: 'page'
				    ,count: data.records
				   ,limit:5
				    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
				    ,jump: function(obj){
				      console.log(obj);
				      loadUserList(obj.curr,obj.limit);
				    }
				  });
				});		
		}
	})
	
	
	
	/**
	 * 加载用户列表
	 */
	var cout=5;
	var total=0;
	//loadUserList(1,cout);
	function loadUserList(page,count){
		
		$(".userbody").empty();
		$(".page").find("div").empty();
		$.ajax({
			url:"/listmember",
			type:"POST",
			data:{'page':page,'count':count},
			dataType:'JSON',
	        async:false,
			success:function(data){
				$(".usernumber").html(data.records);
				$.each(data.rows,function(idx,obj){
					var url = "stuid="+obj.stuid+"&name="+obj.name+"&sex="+obj.sex+"&college="+obj.college+"&phone="+obj.phone+"&type="+obj.type+"&state="+obj.state+"&pass="+obj.password;
					var sex = obj.sex==1?"男":"女";
					var type = obj.type==1?'普通用户':'管理员';
					var a = obj.state==1?'<a onclick="member_stop(this,'+obj.stuid+')" href="javascript:;"  title="停用"><i class="layui-icon">&#xe601;</i></a>':'<a onclick="member_stop(this,'+obj.stuid+')" href="javascript:;"  title="启用"><i class="layui-icon">&#xe62f;</i></a>'
					var state =obj.state==1?'<span class="layui-btn layui-btn-normal layui-btn-mini">启用</span>':'<span class="layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled">停用</span>';
					$(".userbody").append('<tr><td><div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id="2"><i class="layui-icon">&#xe605;</i></div></td><td>' +obj.stuid+'</td><td>'+obj.name+' </td><td>'+sex+'</td><td>'+obj.college+'</td><td>'+obj.phone+'</td><td>'+type+'</td><td>'+obj.password+'</td><td class="td-status">'+state+'</td><td class="td-manage">'+a+'<a title="编辑"  onclick="x_admin_show(\'编辑\',\'./member-edit.html?'+url+'\',600,600)" href="javascript:;"><i class="layui-icon">&#xe642;</i></a><a onclick="x_admin_show(\'修改密码\',\'member-password.html\',600,400)" title="修改密码" href="javascript:;"><i class="layui-icon">&#xe631;</i></a><a title="删除" onclick="member_del(this,'+obj.stuid+')" href="javascript:;"><i class="layui-icon">&#xe640;</i></a></td></tr>')
					
					
					/*$(".userbody").append('<tr><td><div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='+idx+'><i class="layui-icon">&#xe605;</i></div></td><td>'+obj.stuid+'</td><td>'+obj.name+'</td><td>'+obj.sex==1?"男":"女"+'</td><td>'+obj.college+'</td><td>'+obj.phone+'</td><td>'+obj.type==1?"普通用户":"管理员"+'</td><td>'+obj.password+'</td><td class="td-status"><span class="layui-btn layui-btn-normal layui-btn-mini">'+obj.state==1?"正常":"禁用"+
					 '</span></td>'+
					 '<td class="td-manage"><a onclick="member_stop(this,"10001")" href="javascript:;"  title="启用"><i class="layui-icon">&#xe601;</i>'+
					'</a><a title="编辑"  onclick="x_admin_show("编辑","member-edit.html",600,400)" href="javascript:;">'+
					'<i class="layui-icon">&#xe642;</i></a><a onclick="x_admin_show("修改密码","member-password.html",600,400)" title="修改密码" href="javascript:;">'+
					'<i class="layui-icon">&#xe631;</i></a><a title="删除" onclick="member_del(this,"要删除的id")" href="javascript:;"><i class="layui-icon">&#xe640;</i></a></td></tr>');*/
				});
			/*	var text=' <a class="prev" href="#">&lt;&lt;</a>';
				total = data.total;
				for(var i=1;i<=data.total;++i){
					if(i==data.page)
						text+=' <span class="current" href="#">'+i+'</span>';
					else
						text+=' <a class="num" href="#">'+i+'</a>'
				}
				
				$(".page").find("div").append(text+' <a class="next" href="#">&gt;&gt;</a>')*/
				
			}
		})
	}
	
/*	$(".page").find("div").on("click",".num",function(){
		loadUserList($(this).html(),cout);
	})
	$(".page").find("div").on("click",".prev",function(){
		var cur = $(".page").find("div .current").html();
		if(cur>1)
			loadUserList(cur-1,cout);
	})
	$(".page").find("div").on("click",".next",function(){
		var cur = $(".page").find("div .current").html();
		if(cur<total)
			loadUserList(parseInt(cur)+1,cout);
	})*/
})