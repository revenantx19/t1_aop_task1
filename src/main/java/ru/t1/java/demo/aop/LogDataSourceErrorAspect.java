package ru.t1.java.demo.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.DataSourceErrorLog;
import ru.t1.java.demo.repository.DataSourceErrorLogRepository;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogDataSourceErrorAspect {

    private final DataSourceErrorLogRepository errorLogRepository;

    private static final int maxLength = 255;

    @Around("execution(* ru.t1.java.demo.controller.*.*(..))")
    public Object logError(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("Run LogDataSourceErrorAspect");
            return joinPoint.proceed();
        } catch (Exception e) {
            logErrorToDatabase(joinPoint, e);
            throw e;
        }
    }

    private void logErrorToDatabase(ProceedingJoinPoint joinPoint, Exception e) {
        DataSourceErrorLog errorLog = DataSourceErrorLog.builder()
                .methodSignature(cutStringToMaxLength(joinPoint.getSignature().toShortString()))
                .message(cutStringToMaxLength(e.getMessage()))
                .stackTrace(cutStringToMaxLength(getStackTraceString(e)))
                .build();
        errorLogRepository.save(errorLog);
    }

    private String getStackTraceString(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }

    private String cutStringToMaxLength(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, Math.min(str.length(), maxLength));
    }
}
