$(document).ready(function() {
	$("#btn-add-explain").click(function() {
		var html = "<div class='form-group explain-content'><div class=' col-sm-2'><input type='text' class='form-control' name='explainNames' value=''/></div><div class=' col-sm-9'><input type='text' class='form-control' name='explainDescriptions'  value=''/></div><div class='col-sm-1'><button type='button' class='btn btn-danger btn-delete-explain'onclick='deleteExplain(this)'>删除</button></div></div>";
		$("#explain-ctn").append(html);
	});
});

function deleteExplain(that) {
	$(that).parentsUntil("#explain-ctn").remove();
}