$(function(){
	//点击xgg展现相应的权限
	var name=""
	$('#xgg').click(function(){
		$(this).css("background-color","#4169E1").siblings().css("background","");
		$('#tab').children().remove();
		$('#tab').append(            
	                   '<tr><td>管理数据</td></tr>'+
	                   '<tr><td>设置近期嘉宾</td></tr><tr><td>管理首页轮播</td></tr>'+
	                   '<tr><td>热度设置</td></tr><tr><td>留言审核</td></tr>'+
	                   '<tr><td>论坛管理</td></tr>');
	});
	//获取单前选择的权限
	$("#tab").on('click','tr',function(){
		$(this).addClass('del')
		$(this).css("background-color","#1E90FF").siblings().css("background","");
		
	});
	//删除选中的权限
	$('#out').click(function(){
		$("tr[class=del]").remove();
	});
	
	$("#tab1").on('click','tr',function(){
		name=$(this).find("td").eq(0).text();
		$(this).css("background-color","#1E90F9").siblings().css("background","");
		
	});
	//增加选中的权限
	$('#in').click(function(){
		$("#tab").append('<tr><td>'+name+'</td></tr>')
	})
	
})
	