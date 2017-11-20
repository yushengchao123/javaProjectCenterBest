$(function(){
	
	
/*$("#btn_register").bind("click",function(){
	 var rform = $('#registerForm').serialize();
	 var submitData=decodeURIComponent(rform,true);
	

	console.log(submitData);
	$.ajax({
	    url:'',
	    data:submitData,
	    cache:false,//false是不缓存，true为缓存
	    async:true,//true为异步，false为同步
	    beforeSend:function(){
	        //请求前
	    },
	    success:function(result){
	        //请求成功时
	    },
	    complete:function(){
	        //请求结束时
	    },
	    error:function(){
	        //请求失败时
	    }
	})
	
});*/
$("#getCode").bind("click",function(){
	var codeEmail=$("#inputEmail").val();
	console.log(codeEmail);
	var cce={'mail':'291883553@qq.com'};
	$.ajax({
	    url:'../cwc/login/sendVerificationCode',
	    data:cce,
	    async:true,//true为异步，false为同步
	    type:"post",
	    beforeSend:function(){
	        //请求前
	    },
	    success:function(result){
	        //请求成功时
	    	console.log(result);
	    },
	    complete:function(){
	        //请求结束时
	    },
	    error:function(){
	        //请求失败时
	    }
	})
	
	
	
});



})

