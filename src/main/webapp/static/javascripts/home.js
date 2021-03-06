$(document).ready(function(){
	initChart();
	$(".submit-btn").click(function(){
		var url = $(this).attr("data-url") + "/export_xls";
		var departmentId = $("select[name='departmentId']").val();
		var start =  $("input[name='start']").val();
		var end =  $("input[name='end']").val();
		if (start == "" || end == "") {
			alert("请选择时间");
			return;
		}
		url += "?departmentId=" + departmentId + "&start="+ start + "&end=" +end;
		window.location.href = url;
	});
	$("#randomForm").submit(function() {
		var start =  $("input[name='start']").val();
		var end =  $("input[name='end']").val();
		if (start == "" || end == "") {
			alert("请选择时间");
			return false;
		}
		$.post("statistics/random_selector", $(this).serialize(),
			function (result) {
				if ($.isEmptyObject(result)) {
					$("#question-ctn").html("without result");
					$("#random-submit").attr("disabled", "disabled");
				} else {
					var exacct = result["exacct"];
					var department = result["department"];
					$.each(result, function(key, value){
						var obj = key;
						var statics = value;
						if (obj == "exacct" || obj == "department") {
							return;
						}
						var canvas = document.getElementById(obj).getContext('2d');
						var labels = new Array();
						var datas = new Array();
						for (var i=0; i< statics.length; i++) {
							labels[i] = statics[i].label;
							datas[i] = statics[i].sum;
						}
						var basicOptions = options(obj, exacct, department);
						var type = basicOptions["type"];
						var label = basicOptions["label"];
						var bckcolor = basicOptions["bckcolor"];
						var borderColor = basicOptions["borderColor"];
						
						var chartOptions = {
					        scales: {
					            yAxes: [{
					                ticks: {
					                    beginAtZero:true
					                }
					            }]
					        }
					    }
						
						var dataOption = {
							    type: type,
							    data: {
							    	labels: labels,
							    	datasets: [{
									    label: label,
									    data: datas,
									    backgroundColor: bckcolor,
									    borderColor: borderColor,
									    borderWidth: 1
							    	}]
							    },
							    options: chartOptions
						}			
						new Chart(canvas, dataOption);
					});
				}	
			},
			"json"
		);
		return false;
	});
});

function initChart() {
	$(".chart-ctn").each(function(){
		var tag = $(this).attr("data-tag");
		var obj = tag, exacct = "", department="";
		var canvas = document.getElementById(obj).getContext('2d');
		var basicOptions = options(obj, exacct, department);
		var type = basicOptions["type"];
		var label = basicOptions["label"];
		var labels = [], datas = [];
		
		var dataOption = {
			    type: type,
			    data: {
			    	labels: labels,
			    	datasets: [{
					    label: label,
					    data: datas
			    	}]
			    },
		}			
		new Chart(canvas, dataOption);
	});
}


function options(obj, exacct, department) {
	switch (obj) {
	case "depSumCostPerMonth":
		type = "line";
		label = "部门总费用";
		bckcolor = 'rgba(255, 99, 132, 0.2)';
		borderColor = 'rgba(255,99,132,1)';
		break;
	case "depExacctCostPerMonth":
		type = "line";
		label = "部门" + exacct + "费用";
		bckcolor = 'rgba(54, 162, 235, 0.2)';
		borderColor = 'rgba(54, 162, 235, 1)';
		break;
	case "allDepExacctCostPerMonth":
		type = "line";
		label = "企业" + exacct + "费用";
		bckcolor = 'rgba(255, 206, 86, 0.2)';
		borderColor = 'rgba(255, 206, 86, 1)';
		break;
	case "allCostPerMonth":
		type = "line";
		label = "企业总费用";
		bckcolor = 'rgba(75, 192, 192, 0.2)';
		borderColor = 'rgba(75, 192, 192, 1)';
		break;
	case "allCostPerDepByMonth":
		type = "bar";
		label = "每个部门总费用";
		bckcolor = 'rgba(153, 102, 255, 0.2)';
		borderColor = 'rgba(153, 102, 255, 1)';
	break;
		case "allCostPerDepByMonthAndExacct":
		type = "bar";
		label = "每个部门" + exacct + "费用";
		bckcolor = 'rgba(255, 159, 64, 0.2)';
		borderColor = 'rgba(255, 159, 64, 1)';
		break;
	default:
		type = "bar";
		label = "";
		bckcolor = 'rgba(255, 159, 64, 0.2)';
		borderColor = 'rgba(255, 159, 64, 1)';
		break;
	}
	var result = {
			"type": type,
			"label": label,
			"bckcolor": bckcolor,
			"borderColor": borderColor
	}
	return result;
}