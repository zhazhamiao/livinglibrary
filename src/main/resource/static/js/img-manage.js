var repImgUrl = "";

$(document).ready(function() {
	var list = new Array();
	var preUrl = "http://10.1.79.22:8056/";
	$.ajax({
		type:"get",
		url:"/getCarousel",
		async:true,
		success:function(data){
			list = data;
			//console.log(list);
			
			var tagItem = "";
			var bigImgItem = "";
			var listItem = "";
			for(var i = 0; i < list.length; i++) {
				tagItem += '<li data-target="#demo" data-slide-to="' + i + '"></li>';
				listItem += '<li><img src="' + preUrl + list[i].url + '" class="small-img" /></li>';
				bigImgItem += '<div class="carousel-item">';
				bigImgItem += '<img src="' + preUrl + list[i].url + '" class="big-img" id="' + list[i].id + '">';
				bigImgItem += '<div class="carousel-caption">';
				bigImgItem += '<input type="button" class="btn-info btn rep-btn"  value="更换该图片" />';
				bigImgItem += '<input type="button" class="btn-danger btn del-btn" value="删除该图片" />';
				bigImgItem += '</div></div>';
			}

			//加载大图
			$("#bigBox").append(bigImgItem);
			$("#bigBox").find(".carousel-item").eq(0).addClass("active");
			//加载略缩图
			$("#listBox").append(listItem);
			//加载指示符
			$("#playTag").append(tagItem);
			$("#playTag").find("li").eq(0).addClass("active");

			//$("#demo").carousel(0);

			$("#demo").carousel('pause');
			//console.log(list);
			
		}
	});
	
	//略缩图点击事件
	$("body").on("click","#listBox li",function(){
    	var imgNum = $(this).index();
		$("#demo").carousel(imgNum);
		$("#demo").carousel('pause');
	});
	
	//删除图片
	$("body").on("click",".del-btn",function() {
		var that = $(this);
		var delImgUrl = that.closest(".carousel-caption").siblings("img").attr("src").slice(27);
		//var delImgId = that.closest(".carousel-caption").siblings("img").attr("id").slice(28);
		var check = confirm("确定删除该图片？");
		console.log(delImgUrl);
		if(check) {
			$.ajax({
				type: "POST",
				url: "/delCarousel",
				data: {
					url: delImgUrl
				},
				success: function(data) {
					location.reload();
				},
				error: function() {
					alert("请求失败！");
				}
			});
		}
	});
	
	//点击更换图片
	$("body").on("click",".rep-btn",function() {
		repImgUrl = $(this).closest(".carousel-caption").siblings("img").attr("src").slice(28);
		$("#rep-file").click();
	});
	//点击添加图片
	$("#add-btn").on("click",function() {
		$("#add-file").click();
	});

});

//添加图片
function addImg(obj) {
	var formData = new FormData();
	var imgFile = obj.files[0];
	//console.log(imgFile.name);
	
	formData.append("Img",imgFile);
	$.ajax({
		type:"post",
		url:"/addCarousel",
		data:formData,
		async:false,
		processData:false,
		contentType:false,
		success:function(data){
			location.reload();
		}
	});

}

//替换图片
function repImg(obj){
	var formData = new FormData();
	var imgFile = obj.files[0];
	//console.log(imgFile.name);
	
	formData.append("Img",imgFile);
	formData.append("url",repImgUrl);
	$.ajax({
		type:"post",
		url:"/alterCarouselByUrl",
		data:formData,
		async:false,
		processData:false,
		contentType:false,
		success:function(data){
				location.reload();
		}
	});
}
