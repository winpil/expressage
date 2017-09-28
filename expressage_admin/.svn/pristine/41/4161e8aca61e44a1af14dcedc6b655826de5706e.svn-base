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
 * 日志记录Aspect
 * 
 * @author yab
 */
@Aspect
@Component
public class LoggingAspect {

	@Resource
	private ISysLogService sysLogService;

	/**
	 * 定义切入点,匹配Service层save开头的方法
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.save*(..))")
	private void serviceStartWithSaveMethod() {
	}

	/**
	 * 定义切入点,匹配Service层update开头的方法
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.update*(..))")
	private void serviceStartWithUpdateMethod() {
	}

	/**
	 * 定义切入点,匹配Service层delete开头的方法
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom..service..*.delete*(..))")
	private void serviceStartWithDeleteMethod() {
	}
	
	/**
	 * 定义切入点,匹配使用@ModuleName注解的方法
	 * 
	 * @param moduleName
	 */
	@SuppressWarnings("unused")
	@Pointcut("@annotation(moduleName)")
	private void annotationMethod(ModuleName moduleName) {
	}

	/**
	 * 定义切入点,排除日志记录功能模块的Service层方法
	 */
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.cndatacom.rbac.system.service.impl.SysLogServiceImpl.save(..))")
	private void logServiceMethod() {
	}

	/**
	 * Service方法注解Around通知
	 * 
	 * @param joinPoint
	 */
	@Around("annotationMethod(moduleName)")
	public Object annotationMethodServiceLog(ProceedingJoinPoint joinPoint,ModuleName moduleName)
			throws Throwable {
		return this.saveAroundLog(joinPoint,Constants.BUSINESS_LOG_TYPE, moduleName.value());
	}
	
	/**
	 * Service方法环绕通知
	 * 
	 * @param joinPoint
	 */
	@Around("(serviceStartWithSaveMethod() || serviceStartWithUpdateMethod() || serviceStartWithDeleteMethod()) && !logServiceMethod()")
	public Object aroundServiceLog(ProceedingJoinPoint joinPoint)
			throws Throwable {
		return saveAroundLog(joinPoint,Constants.BUSINESS_LOG_TYPE,"Service层方法");
	}

	private Object saveAroundLog(ProceedingJoinPoint joinPoint,Long logType,String moduleName) throws Throwable {
		//构造日志对象
		SysLog sysLog = new SysLog();
		sysLog.setCreatedtime(new Date());
		if(null != ActionContext.getContext()){
			sysLog.setClientIp(Struts2Utils.getRequest().getRemoteAddr());
		}else{
			sysLog.setClientIp("未登录");
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
			sysLog.setOperAccount("未登录");
		}
		
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		Method method = methodSignature.getMethod();

		Class<?>[] parameterTypes = method.getParameterTypes();

		Object returnValue = null; // 目标方法返回值
		String returnMessage = null;

		StringBuffer parameter = new StringBuffer();
		parameter.append("调用方法：");
		parameter.append(joinPoint.getTarget().getClass().getName())
				.append(".").append(joinPoint.getSignature().getName()).append(
						"(");
		for (int i = 0; i < parameterTypes.length; i++) { // 记录目标方法形参列表
			parameter.append(parameterTypes[i].getName()).append(",");
		}
		parameter.deleteCharAt(parameter.length() - 1);
		parameter.append(")");
		parameter.append("<br>传递参数:");

		// 获取目标方法传入参数值
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
			returnValue = joinPoint.proceed(); // 执行目标方法
			
			sysLog.setOperStatus("操作成功");
		} catch (Throwable e) {
			returnMessage = "<br>异常类型:" + e.getClass().getName() + "<br>异常消息：" + e.getMessage();
			
			sysLog.setOperStatus("操作失败");

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
			
			parameterStr += "<br>返回值：" + (returnValue == null ? "void" :returnValue);
			//设置日志的详细信息
			sysLog.setOperDetail(parameterStr);
			
			//保存日志记录
			sysLogService.save(sysLog);
		}

		return returnValue;
	}
}
