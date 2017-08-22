$(function(){
				
				$("#go").click(function(){
					$("#cc").addClass("hidden");
					$("#dd").addClass("hidden");
					loginsuccess()
				})
			})
			
			function loginsuccess(){
				var username=$("#username").val();
				var password=$("#password").val();
				if(username=="903" ){
					$("#cc").removeClass("hidden");
					self.location='/taobao/index'; 
			
				}else{
					$("#dd").removeClass("hidden");
					
				}
			}