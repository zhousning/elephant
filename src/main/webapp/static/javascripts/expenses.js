$(document).ready(function() {
	$("#expense-upload-btn").click(function(){
		$(this).attr("disabled","disabled");
		$("#circle").css('display','block'); 
		$("#upload-excel").submit();
	});
});