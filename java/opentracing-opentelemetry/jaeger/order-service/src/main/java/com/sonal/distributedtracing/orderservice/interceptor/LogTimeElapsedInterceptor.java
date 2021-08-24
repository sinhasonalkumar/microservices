package com.sonal.distributedtracing.orderservice.interceptor;

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
public class LogTimeElapsedInterceptor {

	@Around(value = "execution(* com.sonal.distributedtracing.orderservice..*(..))")
	public Object logTimeElapsed(ProceedingJoinPoint pjp) throws Throwable {
		
		Instant start = Instant.now();
		
		String signature = pjp.getSignature().toString();
		
		log.info("ENTRY " + signature);
		
		Object output;
		try {
			output = pjp.proceed();
		} catch (Throwable t) {
			
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();
			
			log.info("EXIT EXCEPTIONALLY " + signature + "timeElapsed=" + timeElapsed, kv("timeElapsed", timeElapsed));
			
			throw t;
		}
		
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		
		log.info("EXIT " + signature + " timeElapsed=" + timeElapsed, kv("timeElapsed ", timeElapsed));
		
		return output;
	}
}
