package com.cndatacom.rbac.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import com.cndatacom.common.utils.Constants;
import com.cndatacom.common.web.struts2.Struts2Utils;
import com.cndatacom.rbac.log.annotation.ModuleName;
import com.cndatacom.rbac.pojo.SysLog;
import com.cndatacom.rbac.system.service.ISysLogService;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��־��¼Aspect
 * 
 * @author yab
 */
@Aspect
@Component
public class LoggingAspect {

	@Resource
	private ISysLogService sysLogService;

	/**
	 * ���������,ƥ��Service��save��ͷ�ķ���
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.save*(..))")
	private void serviceStartWithSaveMethod() {
	}

	/**
	 * ���������,ƥ��Service��update��ͷ�ķ���
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.update*(..))")
	private void serviceStartWithUpdateMethod() {
	}

	/**
	 * ���������,ƥ��Service��delete��ͷ�ķ���
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.delete*(..))")
	private void serviceStartWithDeleteMethod() {
	}
	
	/**
	 * ���������,ƥ��ʹ��@ModuleNameע��ķ���
	 * 
	 * @param moduleName
	 */
	@SuppressWarnings("unused")
	@Pointcut("@annotation(moduleName)")
	private void annotationMethod(ModuleName moduleName) {
	}

	/**
	 * ���������,�ų���־��¼����ģ���Service�㷽��
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom.rbac.system.service.impl.SysLogServiceImpl.save(..))")
	private void logServiceMethod() {
	}

	/**
	 * Service����ע��Around֪ͨ
	 * 
	 * @param joinPoint
	 */
	@Around("annotationMethod(moduleName)")
	public Object annotationMethodServiceLog(ProceedingJoinPoint joinPoint,ModuleName moduleName)
			throws Throwable {
		return this.saveAroundLog(joinPoint,Constants.BUSINESS_LOG_TYPE, moduleName.value());
	}
	
	/**
	 * Service��������֪ͨ
	 * 
	 * @param joinPoint
	 */
	@Around("(serviceStartWithSaveMethod() || serviceStartWithUpdateMethod() || serviceStartWithDeleteMethod()) && !logServiceMethod()")
	public Object aroundServiceLog(ProceedingJoinPoint joinPoint)
			throws Throwable {
		return saveAroundLog(joinPoint,Constants.BUSINESS_LOG_TYPE,"Service�㷽��");
	}

	private Object saveAroundLog(ProceedingJoinPoint joinPoint,Long logType,String moduleName) throws Throwable {
		//������־����
		SysLog sysLog = new SysLog();
		sysLog.setCreatedtime(new Date());
		if(null != ActionContext.getContext()){
			sysLog.setClientIp(Struts2Utils.getRequest().getRemoteAddr());
		}else{
			sysLog.setClientIp("δ��¼");
		}
		sysLog.setLogType(logType);
		sysLog.setModuleName(moduleName);
		
		SecurityContext context = null;
		if(null != ActionContext.getContext()) {
			context = (SecurityContext)Struts2Utils.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		}
		if(null != context && null != context.getAuthentication()){
			sysLog.setOperAccount(context.getAuthentication().getName());
		}else{
			sysLog.setOperAccount("δ��¼");
		}
		
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		Method method = methodSignature.getMethod();

		Class<?>[] parameterTypes = method.getParameterTypes();

		Object returnValue = null; // Ŀ�귽������ֵ
		String returnMessage = null;

		StringBuffer parameter = new StringBuffer();
		parameter.append("���÷�����");
		parameter.append(joinPoint.getTarget().getClass().getName())
				.append(".").append(joinPoint.getSignature().getName()).append(
						"(");
		for (int i = 0; i < parameterTypes.length; i++) { // ��¼Ŀ�귽���β��б�
			parameter.append(parameterTypes[i].getName()).append(",");
		}
		parameter.deleteCharAt(parameter.length() - 1);
		parameter.append(")");
		parameter.append("<br>���ݲ���:");

		// ��ȡĿ�귽���������ֵ
		Object[] args = joinPoint.getArgs();

		if (args != null && args.length != 0) {
			parameter.append("(");
			for (int i = 0; i < args.length; i++) {
				parameter.append(args[i] == null ? "null" : args[i]).append(",");
			}
			parameter.deleteCharAt(parameter.length() - 1);
			parameter.append(")");
		} else {
			parameter.append("none");
		}
		String parameterStr = parameter.toString();

		try {
			returnValue = joinPoint.proceed(); // ִ��Ŀ�귽��
			
			sysLog.setOperStatus("�����ɹ�");
		} catch (Throwable e) {
			returnMessage = "<br>�쳣����:" + e.getClass().getName() + "<br>�쳣��Ϣ��" + e.getMessage();
			
			sysLog.setOperStatus("����ʧ��");

			throw e;
		} finally {
			if (null != returnMessage) {
				if (parameterStr.length() + returnMessage.length() > 300) {
					parameterStr = parameterStr.substring(0, (300 - returnMessage
						.length()))
						+ "..." + returnMessage;
				} else {
					parameterStr += returnMessage;
				}
			} else {
				if (parameterStr.length() > 300) {
					parameterStr = parameterStr.substring(0,300) + "..." ;
				}
			}
			
			parameterStr += "<br>����ֵ��" + (returnValue == null ? "void" :returnValue);
			//������־����ϸ��Ϣ
			sysLog.setOperDetail(parameterStr);
			
			//������־��¼
			sysLogService.save(sysLog);
		}

		return returnValue;
	}
}
