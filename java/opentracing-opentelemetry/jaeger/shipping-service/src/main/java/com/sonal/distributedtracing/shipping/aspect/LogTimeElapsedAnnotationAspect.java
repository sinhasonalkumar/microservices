package com.sonal.distributedtracing.shipping.aspect;

import static net.logstash.logback.argument.StructuredArguments.kv;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogTimeElapsedAnnotationAspect {

	@Around("@annotation(com.sonal.distributedtracing.shipping.annotation.LogTimeElapsed)")
	public Object handleException(ProceedingJoinPoint pjp) throws Throwable {
		
		Instant start = Instant.now();

		String signature = pjp.getSignature().toString();
		
		String methodName = pjp.getSignature().toShortString();

		log.info("ENTRY " + signature);

		Object output;
		try {
			output = pjp.proceed();
		} catch (Throwable t) {

			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();

			log.info("EXIT EXCEPTIONALLY " + signature + " - " +  methodName + "timeElapsed=" + timeElapsed,kv("methodName",methodName), kv("timeElapsed", timeElapsed));

			throw t;
		}

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();

		log.info("EXIT " + signature + " - " +  methodName + "timeElapsed=" + timeElapsed,kv("methodName",methodName), kv("timeElapsed", timeElapsed));

		return output;
	}
}
