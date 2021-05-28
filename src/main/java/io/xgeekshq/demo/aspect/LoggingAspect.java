package io.xgeekshq.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private ObjectMapper jsonMapper;

    @Around("within(io.xgeekshq.demo.controller.*)")
    public Object logAroundController(ProceedingJoinPoint jp) throws Throwable {
        LOGGER.info("Enter {} = {}", jp.getSignature(), toJson(jp.getArgs()));
        Object result = jp.proceed();
        LOGGER.info("Exit {} = {}", jp.getSignature(), toJson(result));
        return result;
    }

    private String toJson(Object object) {
        try {
            return this.jsonMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Error serializing object to json", e);
            return "";
        }
    }

}