package com.cndatacom.common.utils;


/**
 * 测试申请状态 常量
 * @author He Peng
 *
 */
public class ApplyStatusConstant {
	/** 待测 */
	public static final int TO_TEST = 0;
	/** 测试中 */
	public static final int TESTING = 1;
	/** 已提交（测试完毕） */
	public static final int COMMITTED = 2;
	/** 已审核 */
	public static final int AUDITED = 3;
	/**已确认*/
	public static final int CONFIRM = 4;
	/**不通过  未使用**/
	public static final int NOTPASS = -1;
}
