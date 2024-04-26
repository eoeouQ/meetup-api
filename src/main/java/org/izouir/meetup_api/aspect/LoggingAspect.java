package org.izouir.meetup_api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.izouir.meetup_api.controller.advice.ControllerExceptionHandlingAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlingAdvice.class);

    @Before("execution(* org.izouir.meetup_api.controller.advice.*.*(..))")
    public void beforeAllExceptionHandlingAdvices(final JoinPoint joinPoint) {
        final var e = (RuntimeException) joinPoint.getArgs()[0];
        logger.error(e.getMessage());
    }
}
