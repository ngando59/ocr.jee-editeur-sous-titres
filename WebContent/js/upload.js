$().ready(function() {
	$('#myfile').change(function() {
		$("#file-return").html($('#myfile').val());
	});
	$("#input-file-trigger").click(function(){
		$("#input-file").focus();
	});
});