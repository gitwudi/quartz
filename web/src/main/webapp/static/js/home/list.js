function pause(id) {
	$.ajax({
		type : 'POST',
		url : 'pause',
		data : {id:id},
		dataType : 'json',
		success : function(data) {
			if (data) {
				window.location.reload();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.readyState);
		}
	});
}

function resume(id) {
	$.ajax({
		type : 'POST',
		url : 'resume',
		data : {id:id},
		dataType : 'json',
		success : function(data) {
			if (data) {
				window.location.reload();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.readyState);
		}
	});
}