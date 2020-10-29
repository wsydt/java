package com.example.wsy.redis.custom.aop;

import com.example.wsy.redis.custom.annotations.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class CacheAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.example.wsy.redis.custom.annotations.Cache)")
    public void cachePointcut(){

    }

    @Around("cachePointcut()")
    public Object doCache(ProceedingJoinPoint joinPoint) {
        System.out.println("first get value in redis : !!!");
        Object value = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
            Cache cache = method.getAnnotation(Cache.class);
            String keyEl = cache.key();
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(keyEl);
            EvaluationContext evaluationContext = new StandardEvaluationContext();

            Object[] args = joinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                evaluationContext.setVariable(parameterNames[i], args[i].toString());
            }
            String key = expression.getValue(evaluationContext).toString();
            value = redisTemplate.opsForValue().get(key);
            if (value != null) {
                System.out.println("get value from cache : " + value);
                return value;
            }

            value = joinPoint.proceed();

            redisTemplate.opsForValue().set(key, value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }

}
