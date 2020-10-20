package com.revature.rms.employee.aspects;

import com.revature.rms.core.aspects.CoreLoggingAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect extends CoreLoggingAspect {

	@Override
	@Pointcut("within(com.revature..*)")
	public void logAll() {}
}
