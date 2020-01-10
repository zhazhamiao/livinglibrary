/**
 * Created by Administrator on 2018/3/10 0010.
 */
$(function(){
	
	var guesttype=0;	//选择的类别
	var g_id=0;		//点击行的ID
	var total=1;    //判断总数据共有多少页
	var img1 = new Image();
	var Choose=0;	//判断是点击了增加还是修改打开模态框
	var listhtml=''; //定义表格的内容		
	var typeArray=new Array(); //定义类型的数组
	var s_total=0;
	var t_total=0;
	//请求获取所有的类型
	$.ajax({
		url:'/getGuestType',
        type:'POST',
        dataType:'JSON',
        async:false,
        success:function(data){
        	for(var i=0;i<data.length;i++){
        		typeArray[i]=data[i].typename;
        	}
        }
	})
	
	//通过表格展现数据
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
	                    +'<td class="col-lg-1">'+(i+1)+'</td>'
	                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
	                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
	                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
	                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
	                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
	                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
	                    +'<td class="col-lg-2">'
	                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
	                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
	                    +'</td>'
	                +'</tr>';
	            }
	        	$('#Userlist').append(listhtml);
        }
    });
   
    
    //选择类别展示相应的数据
    $('#sel').change(function(){
    	$('#Userlist').children().remove();
    	$('#example').children().remove();
    	$('#example').append('<ul id="t_pageLimit"></ul>');
    	
    	listhtml='';
    	 guesttype=$(this).val();
    	 $.ajax({				        	
	            url:'/moreData',
	            type:'POST',
	            data:{'GuestType':guesttype,'page':1,'count':10},
	            dataType:'JSON',
	            async:false,
	            success:function (data) {
	            	 t_total=data.total;
	            	/*alert(total)*/
	            	
	            	for(var i=0;i<data.rows.length;i++){		
		            	listhtml+='<tr class="row">'
		                    +'<td class="col-lg-1">'+(i+1)+'</td>'
		                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
		                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
		                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
		                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
		                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
		                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
		                    +'<td class="col-lg-2">'
		                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
		                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
		                    +'</td>'
		                +'</tr>';
		            }
		        	$('#Userlist').append(listhtml);
	            }
	     });
    	 //按类别所获得的数据的分页
    	    $('#t_pageLimit').bootstrapPaginator({    			
    			
    	        currentPage: 1,//当前的请求页面。
    	        totalPages: t_total,//一共多少页。
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
    			        	 url:'/moreData',
    				            type:'POST',
    				            data:{'GuestType':guesttype,'page':1,'count':10},
    				            dataType:'JSON',
    			            success:function (data) {
    			               
    			            	for(var i=0;i<data.rows.length;i++){		
    				            	listhtml+='<tr class="row">'
    				                    +'<td class="col-lg-1">'+((page-1)*10+i+1)+'</td>'
    				                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
    				                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
    				                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
    				                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
    				                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
    				                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
    				                    +'<td class="col-lg-2">'
    				                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
    				                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
    				                    +'</td>'
    				                +'</tr>';
    				            }
    				        	$('#Userlist').append(listhtml);
    			                
    			            
    			            }
    			        })
    		    }
    	    });
    })
    
	//修改数据，打开模态框并传值
	 $('#Userlist').on('click','.modify',function(e){
		$("#myModalLabel").text("修改个人信息");
	    $('#myModal').modal();
	    Choose=1;
	    g_id=e.currentTarget.dataset.id
	    
	    //在弹窗中显示各个输入框的值
	    var g_name=$(this).parent().parent().find("td").eq(1).text();
	    $('#g_name').val(g_name);
	    var g_birthday=$(this).parent().parent().find("td").eq(2).text();
	    $('#g_birdata').val(g_birthday);
	    var g_class=$(this).parent().parent().find("td").eq(3).text();
	    var f_class=0;
	    for(var i=0;i<typeArray.length;i++){
	    	if(typeArray[i]==g_class){
	    		f_class=i+1;
	    	}
	    }
	    $('#g_class').val(f_class);
	    var g_work=$(this).parent().parent().find("td").eq(4).text();
	    $('#g_work').val(g_work);
	    var imge=$(this).parent().parent().find("td").eq(5).children("img").attr("src");
        img1.src=imge;
        $('#imgPreview').append(img1); 
	    var g_detial=$(this).parent().parent().find("td").eq(6).text();
	    $('#g_detial').val(g_detial);
		
	 });
	 
	//删除数据
	 $('#Userlist').on('click','.del',function(e){	
		 var conf = confirm('确定删除此条数据吗？？？');
	         if (conf) {
			  $.ajax({
				 url:'/delGuest',
				 type:'POST',
				 data:{
					id:e.currentTarget.dataset.id, 
				 },
				 success:function(data){
					 alert("数据删除成功!");
					 window.location.href="/add";
				 },
				 error:function(data){
					 alert("数据删除失败!");
				 }
			  });
       }
	 });
	 
		//添加数据时打开添加框
	    $("#a_add").click(function () {
	    	Choose=0;
	        $("#myModalLabel").text("增加个人信息");
	        $('#myModal').modal();
	    });
	    
	    //模态框关闭时清空所有数据   
	    $('#myModal').on('hidden.bs.modal',function(){   
	    	$('#imgPreview').children().remove();
	    	$('#addform')[0].reset();
	    })
	    	
	 
	 //增加和修改数据
	$("#btn_submit").click(function(){  //添加数据，只做了图片的上传，还需添加其他信息	
		
		//表单验证：对表单中的各输入项进行精准的验证，并进行适当的提示
		
		if($('#addform input').val()==null||$('#addform input').val()==""){
				$('#addform input').each(function(){
					var text=$(this).parent().prev().text();
					if($(this).val()==""){
						
						var atr=text+'不能为空！请输入'+text;
						alert(atr);
						/*return false;*/
					}
				})
		}
		
		
		else{
		var img = $('#g_photo')[0].files[0];	
		var formData = new FormData();
		formData.append('guestName',$('#g_name').val());
		formData.append('birthday',$('#g_birdata').val());
		formData.append('GuestType',$('#g_class').val());
		formData.append('address',$('#g_work').val());
		formData.append('guestImg',img);
		formData.append('summary',$('#g_detial').val());
		formData.append('isShow',$('#isShow input[name="is_show"]:checked').val());
		
		//判断是通过添加的按钮还是修改的按钮提交的数据  0为添加，1为修改
		if(Choose==0){
				$.ajax({
		    		url:'/addGuest',
		    		type:'POST',  		
		    		data:formData,
		    		contentType:false,
		    		processData:false,
			    	success:function(data){
			    		alert("添加数据成功！"); //添加数据成功
			    		 window.location.href="/add";
			    		
			    	},
			    	error:function(data){
			    		alert("添加数据失败！");
			    	}
		    	})
		}
		else{
//			if(!$("input").change()){
//				formData.set('guestImg',"undefined");
//			}
//			formData.set('guestImg',img1);
/*			alert(formData.get('guestImg'));*/
			formData.append('guestid',g_id);
				$.ajax({
		    		url:'/alterGuest',
		    		type:'POST',	    		
		    		data:formData,
		    		contentType:false,
		    		processData:false,
			    	success:function(data){
			    		alert("修改数据成功！"); 
			    		 window.location.href="/add";
			    		
			    	},
			    	error:function(data){
			    		alert("修改数据失败！");
			    	}
		    	})
		}
		}
    });
	

    	
    //搜索数据
    $("#search").click(function(){
    	var name=$("#tab_search").val();
    	$('#Userlist').children().remove();
    	$('h2').text("搜索结果")
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
    			                    +'<td class="col-lg-1">'+(i+1)+'</td>'
    			                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
    			                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
    			                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
    			                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
    			                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
    			                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
    			                    +'<td class="col-lg-2">'
    			                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
    			                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
    			                    +'</td>'
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
    	    				                    +'<td class="col-lg-1">'+((page-1)*10+i+1)+'</td>'
    	    				                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
    	    				                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
    	    				                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
    	    				                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
    	    				                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
    	    				                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
    	    				                    +'<td class="col-lg-2">'
    	    				                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
    	    				                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
    	    				                    +'</td>'
    	    				                +'</tr>';
    	    				            }
    	    				        	$('#Userlist').append(listhtml);
    	    			                
    	    			            
    	    			            }
    	    			        })
    	    		    }
    	    	    });
    	}
    });
    
    //添加图片的预览效果
    $('#g_photo').change(function () { 
			if (typeof (FileReader) != "undefined") { 
				var dvPreview = $('#imgPreview'); 
				dvPreview.html(""); 
				var regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/; 
				$($(this)[0].files).each(function () { 
					    var file = $(this); 
					    if (regex.test(file[0].name.toLowerCase())) { 
					        var reader = new FileReader(); 
					        reader.readAsDataURL(file[0]); 
					        reader.onload = function (e) { 
					            var img = new Image(); 
					            img.src=e.target.result;
					            dvPreview.append(img); 
					        } 				        					       
					    } 
					    else { 
					        alert(file[0].name + "is not a valid image file"); 
					        dvPreview.html(""); 
					        return false; 
					    } 
					}); 
					
					} 
			 else { 
			    	alert("This browser does not support HTML5 FileReader"); 
			} 

}); 

   $('#test').click(function(){
	  $('#pageLimit').bootstrapPaginator("show",3);
    })
    //所有数据的分页
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
			    	/*console.log(event);
			    	console.log(originalEvent);*/
			    	/*alert(page);
			    	alert(type*/
			    	listhtml="";
				        $.ajax({				        	
				            url:'/getList',
				            type:'POST',
				            data:{'page':page,'count':10},
				            dataType:'JSON',
				            success:function (data) {
				               
				            	for(var i=0;i<data.rows.length;i++){		
					            	listhtml+='<tr class="row">'
					                    +'<td class="col-lg-1">'+((page-1)*10+i+1)+'</td>'
					                    +'<td class="col-lg-1">'+data.rows[i].guestname+'</td>'
					                    +'<td class="col-lg-2">'+data.rows[i].birthday+'</td>'
					                    +'<td class="col-lg-2">'+typeArray[data.rows[i].guesttype-1]+'</td>'
					                    +'<td class="col-lg-1">'+data.rows[i].address+'</td>'
					                    +'<td class="col-lg-1"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"></td>'
					                    +'<td class="col-lg-2">'+data.rows[i].summary+'</td>'
					                    +'<td class="col-lg-2">'
					                        +'<button class="btn btn-info modify" data-id="'+data.rows[i].guestid+'">修改</button>'
					                        +'<button class="btn btn-danger del" data-id="'+data.rows[i].guestid+'">删除</button>'
					                    +'</td>'
					                +'</tr>';
					            }
					        	$('#Userlist').append(listhtml);
				                
				            
				            }
				        })
			    }
	});
    
    
   
    
});