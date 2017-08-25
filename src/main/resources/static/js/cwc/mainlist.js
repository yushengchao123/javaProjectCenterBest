$(function(){
	var request = new HRequest($("#loginIp").val(),$("#loginPort").val(),$("#loginHost").val(),$("#loginPath").val(),$("#loginTime").val());
	console.log(request);
});
function addCondition(){
	$("#dialog").modal({
		backdrop:false,
		keyboard:false,
		show:false
   });
	/*$('#dialog').on('show.bs.modal',
		    function() {
		$("#ssiframe").attr("src","showtime");
		        alert('嘿，我听说您喜欢模态框...');
		    });*/
	$("#dialog").modal("show");
}

function HRequest(ip,port,host,path,time){
	this.ip = ip;
	this.port = port;
	this.host = host;
	this.path = path;
	this.time = time;
}