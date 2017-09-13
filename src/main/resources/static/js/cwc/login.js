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
							self.location = data.url;
						}else{
							$("#dd").removeClass("hidden");
						}
					}				
				});
			}