/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function openDailog(title, url, width, height, ismodal, sender) {
	var win;
	var t_title = "新建窗口";
	var t_url = "#";
	var t_ismodal = true;
	var t_width = 500;
	var t_height = 400;
	if (title) {
		t_title = title;
	}
	if (url) {
		t_url = url;
	}
	if (ismodal != undefined) {
		t_ismodal = ismodal;
	}
	if (width) {
		t_width = width;
	}
	if (height) {
		t_height = height;
	}

	var t_href = "<iframe style='border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px";
	t_href = t_href
			+ "width: "
			+ t_width
			+ "px; height: "
			+ t_height
			+ "px; border-right-width: 0px'"
			+ "src="
			+ t_url
			+ " frameborder=\"0\" width=\"100%\" scrolling=\"auto\" height=\"100%\"></iframe>";

	if (!win) {
		win = new Ext.Window( {
			id : 'opDailog',
			title : t_title,
			layout : 'fit',
			width : t_width,
			height : t_height,
			//closeAction:'show',
			plain : true,
			modal : t_ismodal,
			items : [ {
				title : t_title,
				header : false,
				html : t_href,
				border : false
			} ]

		});
	}
	win.sender = sender;
	//alert(win.backUrl);

	win.show(this);
}

function showCalendar(owner) {
	MyCalendar.SetDate(owner);
}

function getCheckValue() {

	var checkItem = Ext.query("*[name=checkItem]");
	var foo = "";
	for ( var i = 0; i < checkItem.length; i++) {
		if (checkItem[i].checked) {

			foo = foo + Ext.get(checkItem[i]).getValue();
			if (i < checkItem.length)
				foo = foo + ",";
		}
	}

	return foo;
}

function confirmDelete(url) {
	var ids = getCheckValue();
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
	
	if (confirm("确定删除所选记录吗？")) {
		document.getElementById("ids").value = getCheckValue();
		document.listForm.action = url;
		document.listForm.submit();
	}
}
function confirmDeleteData(url) {
	var ids = getCheckValue();
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
	if (confirm("确定删除所选记录吗？")) {
		document.getElementById("ids").value = getCheckValue();
		document.listForm.action = url;
		document.listForm.submit();
		window.parent.document.location.reload();
	}
}
function switchCheckAll(sender) {
	var checkItem = Ext.query("*[name=checkItem]");

	for ( var i = 0; i < checkItem.length; i++) {
		checkItem[i].checked = sender.checked;
	}
}

function closeDailog() {
	Ext.getCmp('opDailog').close();
}

function submitData() {

	$.ajax( {
		type : 'post',
		url : $('#editForm').attr("action"),
		dataType : 'text',
		data : $('#editForm').serialize(),
		success : function(xml) {
			updateDataGrid();
			closeDailog();
		},
		error : function(xml, err) {
			alert(err);
		}
	});

}

function submitUpdateData() {

	$.ajax( {
		type : 'post',
		url : $('#editForm').attr("action"),
		dataType : 'text',
		data : $('#editForm').serialize(),
		success : function(xml) {
			updateData("menulist");
			closeDailog();
		},
		error : function(xml, err) {
			alert(err);
		}
	});

}

function updateDataGrid() {
	var backUrl = $(window.parent.document).find("#centerpageid").attr("src");
	$(window.parent.document).find("#centerpageid").attr("src", backUrl);
}

function updateData(id) {
	var sender = top.Ext.getCmp('opDailog').sender;
	sender.location.reload();
}

function jumpPage(m) {
	if (m == null) {
		document.forms["listForm"].submit();
		return;
	}
	var s = parseInt(document.getElementById("start").value);
	var limit = parseInt(document.getElementById("limit").value);
	var totalCount = parseInt(document.getElementById("page_totalCount").value);
	var start;
	if (m == 0) {
		start = (parseInt(document.getElementById("page_pageNo").value) - 1)
				* limit;
	} else {
		start = s + m * limit;
	}
	if (start >= 0 && start < totalCount) {
		document.getElementById("start").value = start;
		document.forms["listForm"].submit();
	} else {
		alert("请输入正确的跳转页面,不能为负数或者大于当前总页数");
	}
}

function jumpFirstPage() {
	document.getElementById("start").value = 0;
	document.forms["listForm"].submit();
}

function jumpLastPage() {

	var limit = parseInt(document.getElementById("limit").value);
	var totalCount = parseInt(document.getElementById("page_totalCount").value);
	if (totalCount % limit > 0) {
		document.getElementById("start").value = parseInt(totalCount / limit)
				* limit;
	} else {
		document.getElementById("start").value = (parseInt(totalCount / limit) - 1)
				* limit;
	}

	document.forms["listForm"].submit();
}