$(function(){
	$('#user').text($.cookie("username"));
	$('#layout').click(function(){
		$.removeCookie('username');
	})
})