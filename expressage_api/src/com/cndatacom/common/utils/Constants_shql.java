package com.cndatacom.common.utils;

//���hql����sql���ĳ�����
public class Constants_shql {
	
	/**
	 * ���Դ�����
	 */
	//��ѯ�ڲ��������з��ظ����ֵĲ��Դ���
	public static final String hql_getNoRepeatCount= "from TestCountType as cou where cou not in (" +
			"select app.testCountType from TestApply as app " +
			"where app.testTerminalModelSoft.id=? and app.testTerminalModel.id=?)";
	
	
	//��ѯ���²�������
	public static final String hql_getLatestApp = "from TestApply as app where app.auditStatus!=? and app.auditStatus!=? order by app.createdtime desc";
	//����ApplyStatusConstant��״ֵ̬��ȡ��������
	public static final String hql_getAppByStatus = "from TestApply as app where app.status=? order by app.createdtime desc";
	
	//�����ն˲�ѯδ��˵Ĳ��Ա���
	public static final String hql_getReportByModelId = "from TestApply app where app.auditStatus!=? and app.testTerminalModel.id=? order by app.createdtime desc";
	//��ȡ�Ѿ����ȷ�ϵĲ��Ա���
	public static final String hql_getReportAuByModelId = "from TestApply app where app.comfirmStatus=? and app.testTerminalModel.id=? order by app.createdtime desc";
	
	//δ��˲������������汾
	public static final String hql_getSoftByModelId = "select distinct app.testTerminalModelSoft from TestApply app where app.auditStatus!=? and app.testTerminalModel.id=? order by app.testTerminalModelSoft.softwareVersion asc";
	//���ȷ�ϵ�����汾
	public static final String hql_getAuSoftByModelId = "select distinct app.testTerminalModelSoft from TestApply app where app.comfirmStatus=? and app.testTerminalModel.id=? order by app.testTerminalModelSoft.softwareVersion asc";
	
	//��������id��ѯ��������
	public static final String hql_getAppListByTypeId = "from TestApply as app  where app.testTerminalModel.testTerminalType.id=? order by app.createdtime desc";
	//�жϲ����������Ƿ���ڷ�Ĭ�ϵĹ淶�ĵ�
	public static final String hql_chargeIsBeDeSpecBySpecId = "from TestApply as app where app.testSpecification.id=? and app.testSpecification.isDefault=?";
	//����modelId��softId��ѯ��������
	public static final String  hql_getAppByModeIdAndSoftId = "from TestApply app where app.testTerminalModel.id=? and app.testTerminalModelSoft.id=?";
	
	 //�Ż���̬��ʾ���Ҳ˵��ĳ��Ҽ��ϣ�ֻ�����ڲ��Լ��г��ֵĳ���
	public static final String hql_getManufacByShowedInApp = "select distinct app.testTerminalModel.testManufacturers from TestApply as app where app.auditStatus!=? and app.auditStatus!=?";
}
