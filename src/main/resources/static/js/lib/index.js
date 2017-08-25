$(function(){
	initFileInput("file-Portrait1");
	console.log($("#loginIp").val());
});
function getName(name){
	$.ajax({
		url:"getAge",
		type:"POST",
		data:{"name" :name},
		dataType : "json",
		async: false,
		success : function(data){
			if(data.success){
				alert(data.person.age);
				console.log("ajax请求成功");
			}else{
				console.log("ajax请求失败");
			}
		},
		error : function(errorMsg) {
	         	alert("欧文被交易了！网络中断");   	
	     	}
	 });
}
//初始化fileinput控件（第一次初始化）
function initFileInput(ctrlName) {    
    var control = $('#' + ctrlName);    
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: "save?eventfile="+$("input[name='eventfile']").val()+"&file.fileName=1.jpg", //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
       /* uploadExtraData:{"id": 1, "fileName":'123.jpg'},*/
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        showCancel:true,
        showClose:true,
        dropZoneEnabled: true,
        browseClass: "btn btn-primary", //按钮样式
        //dropZoneEnabled: true,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        //maxFileCount: 10, //表示允许同时上传的最大文件个数
        /*enctype: 'multipart/form-data',*/
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

    }).on('filepreupload', function(event, data) {     //上传中
        var form = data.form, files = data.files, extra = data.extra,
        response = data.response, reader = data.reader;
        console.log(form);
    }).on("fileuploaded", function (event, data) {    //一个文件上传成功
        console.log('文件上传成功！'+data.id);

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！'+data.id);


    })
}
