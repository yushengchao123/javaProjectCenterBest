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