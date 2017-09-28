/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

///页面加载后绑定事件
$(document).ready(function() {

	//设置导航标题
		initNavTitle();

		//初始化filter控件
		initFilterControl();

		//初始化排序字段
		initSortFields();

		//显示上次操作结果提示信息
		showActionMessage();

		$("#btnAdvSearch").hide();

	});

/**
 * 设置导航标题
 */
function initNavTitle() {
	try {
		//获取导航标题
		if ($("#navTitle")) {
			$("#navTitle").html("当前位置:" + parent.getNavTitle());
		}
	} catch (e) {

	}
}

function doRefreshFrame(config) {
	var configParams = config.split("$");
	parent.frames[configParams[0]].location.href = configParams[1];
}

/**
 * 显示/隐藏 高级查询面板
 */
function switchAdvSearch() {
	$("#advSearchDiv").slideToggle(150, changeBtnStyle);
}

/**
 * 更新“高级搜索”提示图标样式
 */
function changeBtnStyle() {
	if ($("#btnAdvSearch").attr("className").indexOf("Down") > -1) {
		$("#btnAdvSearch").attr("className", "ZB_blueUp");
	} else {
		$("#btnAdvSearch").attr("className", "ZB_blueDown");
	}
}

/**
 * 单击表头，按字段排序
 */
function sortDataByField(sender) {
	var orderBy = $(sender).attr("sortField");
	$("[name='page.orderBy']").val(orderBy);
	if ($(sender).attr("class").indexOf("desc") > -1) {
		$("[name='page.order']").val("asc");
		$(sender).attr("class", "sort-asc");
	} else {
		$("[name='page.order']").val("desc");
		$(sender).attr("class", "sort-desc");
	}
	$("#listForm").submit();
}

/**
 * 初始化排序表头
 */
function initSortFields() {
	$("[class='sort-n']").each(function() {

		//绑定click事件
			$(this).bind("click", function() {
				sortDataByField(this);
			});
		});

	//设置当前排序字段的样式和值
	var orderBy = $("[name='page.orderBy']").val();
	var order = $("[name='page.order']").val();
	$("[id='sortField_" + orderBy + "']").attr("class", "sort-" + order);
}

/**
 * 初始化“过滤栏”控件
 */
function initFilterControl() {
	$("[class=filterField]").each(function() {
		//保存此控件值的hidden对象
			var hd = $("[id='hd_" + $(this).attr("name") + "']");
			if (null != hd) {
				//radio类型的,设置checked
				if ($(this).attr("type") == "radio") {
					if (hd.val() == $(this).val()) {
						$(this).attr("checked", "checked");
					} else {
						$(this).attr("checked", "");
					}
				} else {//其他类型，直接赋值
					$(this).val(hd.val());
				}
			}

			$(this).bind("change", function() {
				$("#listForm").submit();
			});
		});
}

//////////////////////////////////////////////////////分页脚本相关函数 start//////////////////////////////////////////////
/**
 *按用户输入的页码进行跳转
 */
function jumpPage() {
	try {
		var goPageNo = parseInt($("[id=txtPageNo]").val());
		var re = /^[1-9]\d*$/;//验证正整数
		if ("" == $("[id=txtPageNo]").val()) {
			showMessage("请输入要调转的页数。");
			return;
		}
		if (!re.test($("[id=txtPageNo]").val())) {
			showMessage("页码格式有误，只能输入正整数。");
			return;
		}
		//if(isNaN(goPageNo))    {
		//showMessage("页码格式有误，请重新输入。");
		//}
		//当前页不用跳转
		if (goPageNo == getCurrentPageNo()) {
			return;
		}
		if (goPageNo > 0 && goPageNo < (getMaxPageCount() + 1)) {
			doJumpPage((goPageNo - 1) * getPageSize());
		} else {
			showMessage("请输入正确的跳转页面,不能为负数或者大于当前总页数");
		}
	} catch (err) {
		showMessage("页码格式有误，请重新输入。");
	}
}

/**
 * 跳转到首页
 */
function jumpFirstPage() {
	if (getCurrentPageNo() > 1)
		doJumpPage(0);
}

/**
 * 跳转到尾页
 */
function jumpLastPage() {
	if (getCurrentPageNo() < getMaxPageCount())
		doJumpPage((getMaxPageCount() - 1) * getPageSize());
}

/**
 * 下一页
 */
function pageDown() {
	if (getCurrentPageNo() < getMaxPageCount())
		doJumpPage(getCurrentPageNo() * getPageSize());
}

/**
 * 上一页
 */
function pageUp() {
	if (getCurrentPageNo() > 1)
		doJumpPage((getCurrentPageNo() - 2) * getPageSize());
}

/**
 * 用户下拉框选择不同的“每页记录数”
 */
function changePageSize() {
	$("#limit").val(getPageSize());
	doJumpPage(0);
}

/**
 * 获取当前页码
 */
function getCurrentPageNo() {
	return parseInt($("[id=page.pageNo]").val());
}

/**
 * 获取最大页数
 */
function getMaxPageCount() {
	return parseInt($("[id=pageTotalPages]").val());
}

/**
 * 获取每页记录数
 */
function getPageSize() {
	return parseInt($("[id=page.pageSize]").val());
}

/**
 * 分页跳转函数
 */
function doJumpPage(start) {
	$("#start").val(start);
	$("#listForm").submit();
}
//////////////////////////////////////////////////////分页脚本相关函数 end//////////////////////////////////////////////

//////////////////////////////////////////////////////关键字搜索控件相关函数 start//////////////////////////////////////////////
/**
 * 下拉框初始化
 */
function initSimpleSearch(id) {
	//关键字下拉框
	var ddlSearch = $("[id=ddl_" + id + "]");
	//关键字输入框
	var txtKeyWord = $("[id=txtItemsValue]");
	//搜索按钮
	var btnSearch = $("[id=btnSearch_" + id + "]");
	//搜索输入框包裹器
	var searchInputWarp = $("[id=searchInputWarp]");

	ddlSearch.attr("class", "ZB_FormSelect");
	txtKeyWord.attr("class", "ZB_FormTextfield");
	btnSearch.attr("class", "ZB_Btn");

	ddlSearch.find("option").each(
			function() {
				var matchtype = $(this).attr("matchType");
				var datatype = $(this).attr("dataType");
				var fieldName = $(this).attr("fieldName");
				if (datatype.length > 0) {
					if (datatype != "All") {
						$(this).attr(
								"value",
								matchtype + datatype.substring(0, 1) + "_"
										+ fieldName);
					} else {
						var fuzzySearchFieldName = "LIKES_";
						var options = ddlSearch
								.find("option[dataType='String']");
						var optionsLength = options.length;
						var index = 0;
						//遍历所有String类型的
						options.each(function() {
							var fieldName = $(this).attr("fieldName");
							//外键关联字段不纳入模糊查询中
								if (fieldName.indexOf(":") > 0) {
									return;
								}
								fuzzySearchFieldName = fuzzySearchFieldName
										+ fieldName;
								index++;
								if (index < optionsLength) {
									fuzzySearchFieldName = fuzzySearchFieldName
											+ "_OR_";
								}
							});
						//赋值给"模糊查询"选项
						$(this).attr("value", fuzzySearchFieldName);
					}

				}
			});

	//绑定下拉框事件
	ddlSearch.bind("change", function() {
		var selectItem = $(this).find("option:selected");
		var selectDataType = selectItem.attr("dataType");
		if (searchInputWarp.attr("dataType") != selectDataType) {
			updateSearchInputWarp(selectDataType);
		}
	});

	//绑定搜索按钮事件
	btnSearch.bind("click", function() {
		//清除上次搜索的条件参数
			cleanPreSearchParams();
			$("[name=itemsValue]").val($("[id=txtItemsValue]").val());
			
			$("[name=checkItems]").val($("[id=ddl_" + id + "]").val());
			
			if($("[id=txtItemsValue]").val()==""){
				showMessage("请输入关键字");
				return;
			}

			//1.日期搜索日期类型时的预处理
			if (searchInputWarp.attr("dataType") == "Date") {
				//1.1开始日期的filter名字
				var beginDateFilterName = "filter_GED_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				//开始日期的值
				var beginDateFilterValue = $("#searchDateRegion_begin").val();
				//1.2结束日期的filter名字
				var endDateFilterName = "filter_LTD_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				//结束日期的值
				var endDateFilterValue = $("#searchDateRegion_end").val();
				if(beginDateFilterValue==""||endDateFilterValue==""){
					showMessage("请完整选择日期");
					return;
				} 
				if(beginDateFilterValue>endDateFilterValue){
					showMessage("起始日期不能大于结束日期");
					return;
				}
				if(beginDateFilterValue==endDateFilterValue){
					showMessage("起始日期不能等于结束日期");
					return;
				}

				//1.3动态创建两个filter字段控件
				$("#listForm").append(createHiddenField(beginDateFilterName,beginDateFilterValue)).append(createHiddenField(endDateFilterName, endDateFilterValue));

				//将当前的搜索时间段保存到hidden中，在post到后台时附带，在页面初始化时再重新读出赋值
				//格式：开始时间$结束时间
				$("#searchDateRegion").val(beginDateFilterValue + "$" + endDateFilterValue);
				//清空默认的关键字输入框值，避免附带其他查询条件
				$("[name=itemsValue]").val("");
			} else if (searchInputWarp.attr("dataType") == "Month") {
				//1.1开始日期的filter名字
				var beginDateFilterName = "filter_GES_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				//开始日期的值
				var beginDateFilterValue = $("#searchDateRegion_begin").val();
				//1.2结束日期的filter名字
				var endDateFilterName = "filter_LES_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				//结束日期的值
				var endDateFilterValue = $("#searchDateRegion_end").val();
				if(beginDateFilterValue==""||endDateFilterValue==""){
					showMessage("请完整选择月份");
					return;
				} 
				if(beginDateFilterValue>endDateFilterValue){
					showMessage("起始月份不能大于结束月份");
					return;
				}

				//1.3动态创建两个filter字段控件
				$("#listForm").append(createHiddenField(beginDateFilterName,beginDateFilterValue)).append(createHiddenField(endDateFilterName, endDateFilterValue));

				//将当前的搜索时间段保存到hidden中，在post到后台时附带，在页面初始化时再重新读出赋值
				//格式：开始时间$结束时间
				$("#searchDateRegion").val(beginDateFilterValue + "$" + endDateFilterValue);
				//清空默认的关键字输入框值，避免附带其他查询条件
				$("[name=itemsValue]").val("");
			} else if(searchInputWarp.attr("dataType") == "Int"){
				$("[name=itemsValue]").val($("[id=selectValue]").val().replace(/[^a-zA-Z0-9\u4E00-\u9FA5]*$/g,''));
			} else{
				//$("[name=itemsValue]").val($("[id=txtItemsValue]").val().replace(/[^\w|\u4e00-\u9fff]/g,''));
				$("[name=itemsValue]").val($("[id=txtItemsValue]").val().replace(/[^a-zA-Z0-9\u4E00-\u9FA5]*$/g,''));
			}
			doSimpleSearch();
		});

	try {
		//初始化赋值
		ddlSearch.val(document.getElementById("checkItems").value);
		var currentDataType = ddlSearch.find("option:selected").attr("dataType");
		updateSearchInputWarp(currentDataType);
		$("[id=txtItemsValue]").val(document.getElementById("itemsValue").value);

		//日期类型的，需要特殊赋值
		if ($("#searchDateRegion").val().length > 0&& currentDataType == "Date") {
			var dates = $("#searchDateRegion").val().split("$");
			//需要有 开始日期，结束日期
			if (dates.length > 1) {
				$("#searchDateRegion_begin").val(dates[0]);
				$("#searchDateRegion_end").val(dates[1]);

				//###########创建隐藏域字段#############################
				//开始日期的filter名字
				var beginDateFilterName = "filter_GED_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				preSearchHiddenField.push(beginDateFilterName);
				//开始日期的值
				var beginDateFilterValue = $("#searchDateRegion_begin").val();

				//结束日期的filter名字
				var endDateFilterName = "filter_LTD_"+ $("[id=ddl_" + id + "]").find("option:selected").attr("fieldName");
				preSearchHiddenField.push(endDateFilterName);
				//结束日期的值
				var endDateFilterValue = $("#searchDateRegion_end").val();

				//动态创建两个filter字段控件
				$("#listForm").append(createHiddenField(beginDateFilterName,beginDateFilterValue)).append(createHiddenField(endDateFilterName,endDateFilterValue));
			}
		}

	} catch (err) {
		alert(err);
	}

}
//上次搜索条件是创建的隐藏域
var preSearchHiddenField = new Array();

function cleanPreSearchParams() {
	for ( var i = 0; i < preSearchHiddenField.length; i++) {
		$("#" + preSearchHiddenField[i]).remove();
	}
}

/**
 *创建一个隐藏控件对象
 */
function createHiddenField(id, value) {
	//jquery方式
	//return $("<input />").attr("id", id).attr("name", id).attr("type", "hidden").val(value);
	//原生js方式
	return "<input type=\"hidden\" id=\"" + id + "\" name=\"" + id
			+ "\" value=\"" + value + "\" />";
}

/**
 * 更新关键字输入框方式（文本、日期范围...)
 */
function updateSearchInputWarp(dataType) {
	//搜索输入框包裹器
	var searchInputWarp = $("[id=searchInputWarp]");
	switch (dataType) {
	
	case "Date":
		searchInputWarp.html(getDateTypeSearchHtml());
		break;
	case "Month":
		searchInputWarp.html(getMonthTypeSearchHtml());
		break;
	case "Int":
		searchInputWarp.html(getSelectTypeSearchHtml());
		break;
	case "Long":
	case "All":
	case "String":
		searchInputWarp.html(getStringTypeSearchHtml());
		break;

	default:
		searchInputWarp.html(getAllTypeSearchHtml());
		break;
	}
	searchInputWarp.attr("dataType", dataType);
}

/**
 * 简单关键字搜索
 */
function doSimpleSearch() {
	//搜索前，先将分页参数重置
	$("#start").val(0);
	//$("#limit").val(10);
	$("#listForm").submit();
}

/**
 * 日期类型的关键字搜索方式（时间段方式）
 */
function getDateTypeSearchHtml() {
	var html = "<input type=\"text\" onclick=\"showWdatePicker(this,'yyyy-MM-dd')\" style=\"width:120px;height:15px;\" class=\"dateField\" id=\"searchDateRegion_begin\" name=\"searchDateRegion_begin\" />"
			+ "至"
			+ "<input type=\"text\" onclick=\"showWdatePicker(this,'yyyy-MM-dd')\" style=\"width:120px;height:15px;\" class=\"dateField\" id=\"searchDateRegion_end\" name=\"searchDateRegion_end\" />";
	return html;
}

/**
 * 月份类型的关键字搜索方式（月份区间方式）
 */
 function getMonthTypeSearchHtml() {
	var html = "<input type=\"text\" onclick=\"showWdatePicker(this,'yyyy-MM')\" style=\"width:120px;height:15px;\" class=\"dateField\" id=\"searchDateRegion_begin\" name=\"searchDateRegion_begin\" />"
			+ "至"
			+ "<input type=\"text\" onclick=\"showWdatePicker(this,'yyyy-MM')\" style=\"width:120px;height:15px;\" class=\"dateField\" id=\"searchDateRegion_end\" name=\"searchDateRegion_end\" />";
	return html;
}

/**
 * 文本、数值的关键字搜索方式（通用方式）
 */
function getStringTypeSearchHtml() {

	return "<input type=\"text\" value=\"\" name=\"txtItemsValue\" id=\"txtItemsValue\"  class=\"ZB_FormTextfield\" maxlength=\"50\"></input>";
}

/**
 * “全部”搜索方式
 */
function getAllTypeSearchHtml() {
	return "<input type=\"text\"  name=\"txtItemsValue\" id=\"txtItemsValue\" value=\"\"  class=\"ZB_FormTextfield_Disabled\" disabled=\"true\" ></input>";
}

function getCheckValue() {

	var foo = "";
	$("[name=checkItem]").each(function() {
		if ($(this).attr("checked")) {
			foo = foo + $(this).val() + ",";
		}
	});
	return foo;
}

function confirmDelete(url) {
	var ids = getCheckValue();
	//alert("ids="+ids);
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
	
	if (confirm("确定生成优惠券吗？")) {
		
		document.getElementById("ids").value = getCheckValue();
		document.listForm.action = url;
		document.listForm.submit();
	}
}


function confirmUpdate(url) {
	var ids = getCheckValue();
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
	if (confirm("确定修改所选记录吗？")) {
		//alert(url);
		document.getElementById("ids").value = getCheckValue();
		document.listForm.action = url;
		document.listForm.submit();
	}
}

function confirmExport(url) {
	var ids = getCheckValue();
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
 	if (ids.split(",").length > 2) {
		alert("只能选择一条记录");
		return;
	}
	if (confirm("确定导出所选报表吗？")) {
		document.getElementById("exportBalanceId").value = getExportBalanceId();
		document.listForm.action = url;
		document.listForm.submit();
	}
}

function getExportBalanceId() {
	var foo = "";
	$("[name=checkItem]").each(function() {
		if ($(this).attr("checked")) {
			foo = foo + $(this).val();
		}
	});
	return foo;
}

function confirmDeleteById(url, id) {
	if (confirm("确定删除当前记录吗？")) {
		document.getElementById("ids").value = id;
		document.listForm.action = url;
		document.listForm.submit();
	}
}

function updateStatusFun(url, statu) {
	var ids = getCheckValue();
	if (ids == "") {
		alert("请先选择记录");
		return;
	}
	$("#updateStatus").val(statu);
	document.getElementById("ids").value = getCheckValue();
	document.listForm.action = url;
	document.listForm.submit();

}

/**
	日期格式化函数
**/
Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
