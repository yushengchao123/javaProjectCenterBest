$(function(){
				
				$("#go").click(function(){
					$("#cc").addClass("hidden");
					$("#dd").addClass("hidden");
					loginsuccess();
				});
			});
			
			function loginsuccess(){
				var username=$("#username").val();
				var password=$("#password").val();
				$.ajax({
					url: 'login',
					async : false,
					type : "post",
					data : {"loginId":username,
						    "password":password},
					success : function(data){
						if(data.success){
							$("#cc").removeClass("hidden");
							self.location = "/taobao/index";
						}else{
							$("#dd").removeClass("hidden");
						}
					}				
				});
				/*if(username=="903" ){
					$("#cc").removeClass("hidden");
					self.location='/taobao/index'; 
			
				}else{
					$("#dd").removeClass("hidden");
					
				}*/
			}