/**
 * Created by Administrator on 2018/3/10 0010.
 */
$(function(){
	var d_id=$('#d_id').text();
    $.ajax({
        url:"/infoData?GuestId="+d_id,
        type:"POST",
        dataType:"json",
        success: function(data){      		
        	    var time=Date.parse(new Date());
        	    var start=Date.parse(data.beginTime);    	   
        	    var end=Date.parse(data.endTime); 
        	    /*alert(data.beginTime)
        	    alert(data.endTime)*/
                $('#main').append('<div id="xian"><img id="d_photo" src="http://10.1.79.22:8056/'+data.guestImg+'" alt="个人相片">'+
                    '<div class="jieshao"> <p><b>姓名： </b>'+data.guestName+'</p>'+
                    '<p><b>类型：</b>'+data.guestType+'</p>'+
                    '<p id="d_tip"><b>主题：</b>拉网线的诀窍</p>'+
                    '<p id="d_date"><b>日期：</b>2018/06/01 19:00</p>'+                    
                    '<p><b>详情：</b>'+data.summary+'</p> </div> </div>'
                    );
                if(data.guestName=="xgg"){
        	    	$('#d_tip').show();
        	    	$('#d_date').show();
        	    	$('#order').removeClass('btn-danger');
        	    	$('#order').addClass('btn-info');
        	    	$('#order').text('预约');
        	    }                    
                }
			
        });
    $("#order").click(function () {
        $('#myModal').modal();
        });
    $('#btn_submit').click(function(){
    	if($('#order').text()=="预约"){
	    	alert("预约成功")
	    	$('#order').removeClass('btn-info');
	    	$('#order').addClass('btn-default');
	    	$('#order').text('已预约');
	    	$('#order').attr('disabled',"true")
    	}
    	else{
    		alert("PICK成功")
	    	$('#order').removeClass('btn-danger');
	    	$('#order').addClass('btn-default');
	    	$('#order').text('已 PICK');
	    	$('#order').attr('disabled',"true")
    	}
    })
    $("#send-btn").click(function(){
    	var message=$("#textarea").val();
    	$("#textarea").val('');
    	var date=new Date();
    	var t=date.toLocaleString();
    /*	alert(t);
    	alert(message)*/
    	setTimeout(function(){
    		$("#words-list").append(' <div class="words-item">'+
                '<h3>xgg</h3>&nbsp&nbsp;'+
                '<span>'+t+'</span>'+
                '<a class="pull-right" href="void:">回复</a>'+
                '<p>'+message+'</p>'+
            '</div>')},"5000");
    	alert("提交成功！")
    	
    })
  
});