package com.cndatacom.common.utils;

//存放hql或者sql语句的常量类
public class Constants_shql {
	
	/**
	 * 测试次数区
	 */
	//查询在测试申请中非重复出现的测试次数
	public static final String hql_getNoRepeatCount= "from TestCountType as cou where cou not in (" +
			"select app.testCountType from TestApply as app " +
			"where app.testTerminalModelSoft.id=? and app.testTerminalModel.id=?)";
	
	
	//查询最新测试申请
	public static final String hql_getLatestApp = "from TestApply as app where app.auditStatus!=? and app.auditStatus!=? order by app.createdtime desc";
	//根据ApplyStatusConstant的状态值获取测试申请
	public static final String hql_getAppByStatus = "from TestApply as app where app.status=? order by app.createdtime desc";
	
	//根据终端查询未审核的测试报告
	public static final String hql_getReportByModelId = "from TestApply app where app.auditStatus!=? and app.testTerminalModel.id=? order by app.createdtime desc";
	//获取已经审核确认的测试报告
	public static final String hql_getReportAuByModelId = "from TestApply app where app.comfirmStatus=? and app.testTerminalModel.id=? order by app.createdtime desc";
	
	//未审核测试申请的软件版本
	public static final String hql_getSoftByModelId = "select distinct app.testTerminalModelSoft from TestApply app where app.auditStatus!=? and app.testTerminalModel.id=? order by app.testTerminalModelSoft.softwareVersion asc";
	//审核确认的软件版本
	public static final String hql_getAuSoftByModelId = "select distinct app.testTerminalModelSoft from TestApply app where app.comfirmStatus=? and app.testTerminalModel.id=? order by app.testTerminalModelSoft.softwareVersion asc";
	
	//根据类型id查询测试申请
	public static final String hql_getAppListByTypeId = "from TestApply as app  where app.testTerminalModel.testTerminalType.id=? order by app.createdtime desc";
	//判断测试申请中是否存在非默认的规范文档
	public static final String hql_chargeIsBeDeSpecBySpecId = "from TestApply as app where app.testSpecification.id=? and app.testSpecification.isDefault=?";
	//根据modelId和softId查询测试申请
	public static final String  hql_getAppByModeIdAndSoftId = "from TestApply app where app.testTerminalModel.id=? and app.testTerminalModelSoft.id=?";
	
	 //门户动态显示厂家菜单的厂家集合，只出现在测试集中出现的厂家
	public static final String hql_getManufacByShowedInApp = "select distinct app.testTerminalModel.testManufacturers from TestApply as app where app.auditStatus!=? and app.auditStatus!=?";
}
