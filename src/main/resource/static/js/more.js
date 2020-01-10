/**
 * Created by Administrator on 2018/3/10 0010.
 */
$(function(){
     var typeid=$('#m_id').text(); 
     var total=0;
	$.ajax({
		 url:'moreData',
         type:'POST',
         data:{'GuestType':typeid,'page':1,'count':9},
         dataType:'JSON',
         async:false,
        success: function(data){
        	$.ajax({
                url:"/getGuestType",
                type:"POST",
                dataType:"json",
                success: function(t_data){
                	var type=t_data[typeid-1].typename;
                	$('#m_class').text(type);
                    $('h1').text(type);
                }
                });
        
               for(var i=0;i<data.rows.length;i++){
            	   total=data.total;
	               
	            	$('#m_show').append('<div class="col-sm-4 col-md-4 col-xs-4"> <div class="thumbnail"> ' +
	                        '<a href="/info?GuestId='+data.rows[i].guestid+'" target="_blank"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"alt="嘉宾"/></a>'+
	                        '<h4 >'+data.rows[i].guestname+'</h4><p>'+data.rows[i].address+'</p>'+
	                        '<p>'+data.rows[i].summary+'</p></div></div>');	                            
	                
               }
          }
               
            
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
			    	$('#m_show').children().remove();				    				
				        $.ajax({				        	
				        	 url:'moreData',
				             type:'POST',
				             data:{'GuestType':typeid,'page':page,'count':9},
				             dataType:'JSON',
				            success:function (data) {
				                for(var i=0;i<data.rows.length;i++){
				            	$('#m_show').append('<div class="col-sm-4 col-md-4 col-xs-4"> <div class="thumbnail"> ' +
				                        '<a href="/info?GuestId='+data.rows[i].guestid+'" target="_blank"><img class="a_img" src="http://10.1.79.22:8056/'+data.rows[i].guestimg+'"alt="嘉宾"/></a>'+
				                        '<h4 >'+data.rows[i].guestname+'</h4><p>'+data.rows[i].address+'</p>'+
				                        '<p>'+data.rows[i].summary+'</p></div></div>');		                            
				                
				                }
				            }
				        })
			    	}
    })
});