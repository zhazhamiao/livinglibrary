$(function(){
	var sex = $(".sexT").html();
	if(sex=="true"){
		$(".sexI").attr("title","男");
	}else{
		$(".sexI").attr("title","女");
	}
	
	$.ajax({
		url:"getUserInfo",
		data:{
			stuid:$.cookie("stuid"),
		},
		success:function(data){
			$(".profile-user-name").html(data.name);
			if(data.sex==true){
				$(".sex-ico").addClass("male-ico");
				
			}else{
				$(".sex-ico").addClass("female-ico");
			}
			$(".profile-city").html('<i class="ico-pcity"></i>'+data.stuid);
			$(".profile-work").html('<i class="ico-pwork"></i>'+data.phone);
			$(".profile-edu").html('<i class="ico-pedu"></i>'+data.college);
			
		}
	})
})